package com.yyl.store.service.Impl;

import com.yyl.store.dao.AdministratorDao;
import com.yyl.store.dao.RegularDao;
import com.yyl.store.entity.goods;
import com.yyl.store.entity.req.accountReq;
import com.yyl.store.entity.req.buyReq;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.req.rechargeReq;
import com.yyl.store.entity.statement;
import com.yyl.store.entity.users;
import com.yyl.store.service.RegularService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    private AdministratorDao administratorDao;
    @Override
    public void enrollRegular(enrollReq req,int x) {
        regularDao.enrollRegular(req,x);
    }

    @Override
    public BigDecimal recharge(rechargeReq req) {
        regularDao.recharge(req);
        return regularDao.selectBalance(req);
    }

    @Override
    public List<goods> selectGoods(accountReq req) {

        return regularDao.selectGoods(req);
    }

    @Override
    public statement buy(buyReq req) {
        users user =administratorDao.select(req.getUseAccount(), req.getUsePassword());
        List<goods> commodities = regularDao.selectGoods(req);
        goods commodity = new goods();
        for (goods x : commodities) {
            if (Objects.equals(x.getGoodsName(), req.getGoodsName())){
                commodity = x;
            }
        }
        log.info("4");
        users provide = administratorDao.selectProvide(commodity.getGoodsProvide());
        BigDecimal num = new BigDecimal(req.getGoodsNum());
        BigDecimal money = commodity.getGoodsPrice().multiply(num.multiply(commodity.getGoodsDiscount()));
        if (user.getUseBalance().compareTo(money) < 0
                || req.getGoodsNum() > commodity.getGoodsNum()){
            log.info("5");
            return null;
        }
        log.info("0");
        //先减少库存
        Date date = new Date();
        regularDao.goodsNumReduce(req);
        log.info("1");
        //生成订单
        regularDao.createStatement(req,user,commodity,provide,money,date);
        log.info("2");
        //扣款
        regularDao.balanceReduce(req,money);
        log.info("3");
        //返回订单
        return regularDao.selectStatement(req,provide,money);
    }
}
