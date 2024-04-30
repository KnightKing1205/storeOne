package com.yyl.store.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yyl.store.dao.RegularDao;
import com.yyl.store.entity.req.Token;
import com.yyl.store.entity.req.accountReq;
import com.yyl.store.entity.req.buyReq;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.req.rechargeReq;
import com.yyl.store.entity.ret.Result;
import com.yyl.store.entity.statement;
import com.yyl.store.service.RegularService;
import com.yyl.store.service.StatementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author 65199
 * @ClassName RegularController
 * @description: TODO
 * @date 2024年04月05日
 * @version: 1.0
 */
@Slf4j
@RestController
@Api(tags = "普通用户")
@RequestMapping("/regular")
public class RegularController {
    @Autowired
    private RegularService regularService;
    @Autowired
    private StatementService statementService;

    @Autowired
    private RegularDao regularDao;
    @ApiOperation(value = "普通用户注册")
    @PostMapping("/enrollRegular")
    public String enrollRegular(@RequestBody enrollReq req){
        regularService.enrollRegular(req,1);
        return "success";
    }

    /**
     * 登录功能
     * @param req  包括用户账号和密码
     * @return
     * @throws JsonProcessingException
     */
    @ApiOperation(value = "登录")
    @PostMapping("/loginRegular")
    public Result loginRegular(@RequestBody accountReq req) throws JsonProcessingException {
        return regularService.loginRegular(req);
    }

    @ApiOperation(value = "查询余额")
    @PostMapping("/selectBalance")
    public Result selectBalance() throws JsonProcessingException {
        return regularService.selectBalance();
    }
    @ApiOperation(value = "充值")
    @PostMapping("/recharge")
    public Result recharge (@RequestBody rechargeReq req) throws JsonProcessingException {
        return Result.ok("已成功充值"+req.getMoney()+",您当前余额为："+regularService.recharge(req).getData());
    }
    @ApiOperation(value = "查询商品")
    @PostMapping("/selectGoods")
    public statement selectGoods(@RequestBody accountReq req){
        return statementService.getById(1);
//        return regularService.selectGoods(req);
    }

    @ApiOperation(value = "购买")
    @PostMapping("/buy")
    public statement buy (@RequestBody buyReq req){
        return regularService.buy(req);
    }

}
