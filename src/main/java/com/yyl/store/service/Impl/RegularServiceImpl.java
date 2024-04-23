package com.yyl.store.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyl.store.dao.AdministratorDao;
import com.yyl.store.dao.RegularDao;
import com.yyl.store.entity.goods;
import com.yyl.store.entity.req.Token;
import com.yyl.store.entity.req.accountReq;
import com.yyl.store.entity.req.buyReq;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.req.rechargeReq;
import com.yyl.store.entity.statement;
import com.yyl.store.entity.users;
import com.yyl.store.service.LoginToken;
import com.yyl.store.service.RegularService;
import com.yyl.store.service.StatementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @author 65199
 * @ClassName RegularServiceImpl
 * @description: TODO
 * @date 2024年04月05日
 * @version: 1.0
 */
@Slf4j
@Service
public class RegularServiceImpl implements RegularService {
    @Autowired
    private RegularDao regularDao;
    @Autowired
    private StatementService statementService;
    @Autowired
    private AdministratorDao administratorDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    //json转化工具
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void enrollRegular(enrollReq req, int x) {
        regularDao.enrollRegular(req, x);
    }

    /**
     * 用户充值
     *
     * @param req
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public BigDecimal recharge(rechargeReq req) throws JsonProcessingException {
        //根据token取出用户信息
        String val = stringRedisTemplate.opsForValue().get(req.getToken());
        if (val == null) {
            return null;
        }
        // 反序列化
        accountReq user = mapper.readValue(val, accountReq.class);
        while (true) {
            if (!Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(user.getUseAccount() + "balance", "1"))) {
                break;
            }
        }
        log.info("开始充值");

        //休眠20s
        try {
            Thread.sleep(1000 * 20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //充值
        regularDao.recharge(req, user);
        //开锁
        redisTemplate.delete(user.getUseAccount() + "balance");
        //返回用户余额
        return regularDao.selectBalance(user);
    }

    @Override
    public List<statement> selectGoods(accountReq req) {
        List ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        int id = 1;
        return statementService.getBaseMapper().selectBatchIds(ids);
//        return regularDao.selectGoods(req);
    }

    @Override
    public statement buy(buyReq req) {
        users user = administratorDao.select(req.getUseAccount(), req.getUsePassword());
        List<goods> commodities = regularDao.selectGoods(req);
        goods commodity = new goods();
        for (goods x : commodities) {
            if (Objects.equals(x.getGoodsName(), req.getGoodsName())) {
                commodity = x;
            }
        }
        log.info("4");
        users provide = administratorDao.selectProvide(commodity.getGoodsProvide());
        BigDecimal num = new BigDecimal(req.getGoodsNum());
        BigDecimal money = commodity.getGoodsPrice().multiply(num.multiply(commodity.getGoodsDiscount()));
        if (user.getUseBalance().compareTo(money) < 0
                || req.getGoodsNum() > commodity.getGoodsNum()) {
            log.info("5");
            return null;
        }
        log.info("0");
        //先减少库存
        Date date = new Date();
        regularDao.goodsNumReduce(req);
        log.info("1");
        //生成订单
        regularDao.createStatement(req, user, commodity, provide, money, date);
        log.info("2");
        //扣款
        regularDao.balanceReduce(req, money);
        log.info("3");
        //返回订单
        return regularDao.selectStatement(req, provide, money);
    }

    /**
     * 用户登录
     *
     * @param req
     * @return
     */
    @Override
    public String loginRegular(accountReq req) throws JsonProcessingException {
        //查询数据库该账号是否存在
        users is = regularDao.selectIs(req);
        if (is == null) {
            return "用户密码错误";
        }

        //生成token
        LoginToken loginToken = new LoginToken();
        String token = loginToken.returnLogin(req.getUseAccount());

//            序列化
        String jsonUser = mapper.writeValueAsString(req);

//            存储token与用户的连接信息
        stringRedisTemplate.opsForValue().set(token, jsonUser, 8, TimeUnit.HOURS);
        return token;
    }

    /**
     * @param req
     * @return
     */
    @Override
    public BigDecimal selectBalance(Token req) throws JsonProcessingException {
        String val = stringRedisTemplate.opsForValue().get(req.getToken());
        //反序列化
        accountReq user = mapper.readValue(val, accountReq.class);
        while (true) {
            if (!Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(user.getUseAccount() + "balance", "1"))) {
                break;
            }
        }
        //休眠20s
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        BigDecimal balance = regularDao.selectBalance(user);
        //开锁
        redisTemplate.delete(user.getUseAccount() + "balance");
        return balance;
    }
}
