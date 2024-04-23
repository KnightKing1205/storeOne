package com.yyl.store.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yyl.store.dao.RegularDao;
import com.yyl.store.entity.goods;
import com.yyl.store.entity.req.Token;
import com.yyl.store.entity.req.accountReq;
import com.yyl.store.entity.req.buyReq;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.req.rechargeReq;
import com.yyl.store.entity.statement;
import com.yyl.store.service.RegularService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

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
    private RegularDao regularDao;
    @ApiOperation(value = "普通用户注册")
    @PostMapping("/enrollRegular")
    public String enrollRegular(@RequestBody enrollReq req){
        regularService.enrollRegular(req,1);
        return "success";
    }
    @ApiOperation(value = "登录")
    @PostMapping("/loginRegular")
    public String loginRegular(@RequestBody accountReq req) throws JsonProcessingException {
        return regularService.loginRegular(req);
    }

    @ApiOperation(value = "查询余额")
    @PostMapping("/selectBalance")
    public BigDecimal selectBalance(@RequestBody Token req) throws JsonProcessingException {
        return regularService.selectBalance(req);
    }
    @ApiOperation(value = "充值")
    @PostMapping("/recharge")
    public String recharge (@RequestBody rechargeReq req){
        return "已成功充值"+req.getMoney()+",您当前余额为："+regularService.recharge(req);
    }
    @ApiOperation(value = "查询商品")
    @PostMapping("/selectGoods")
    public List<goods> selectGoods(@RequestBody accountReq req){
        return regularService.selectGoods(req);
    }

    @ApiOperation(value = "购买")
    @PostMapping("/buy")
    public statement buy (@RequestBody buyReq req){
        return regularService.buy(req);
    }

}
