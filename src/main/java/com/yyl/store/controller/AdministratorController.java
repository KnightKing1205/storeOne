package com.yyl.store.controller;

import com.yyl.store.dao.AdministratorDao;
import com.yyl.store.entity.req.adjustDiscountReq;
import com.yyl.store.entity.req.adjustPriceReq;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.req.goodsNameReq;
import com.yyl.store.entity.req.warehousingReq;
import com.yyl.store.service.AdministratorService;
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

/**
 * @author 65199
 * @ClassName AdministratorController
 * @description: TODO
 * @date 2024年04月05日
 * @version: 1.0
 */
@Slf4j
@RestController
@Api(tags = "管理员")
@RequestMapping("/administrator")
public class AdministratorController {
    @Autowired
    private RegularService regularService;
    @Autowired
    AdministratorService administratorService;

    @Autowired
    private AdministratorDao administratorDao;
    @ApiOperation(value = "查询余额")
    @GetMapping("/selectBalance")
    public BigDecimal selectBalance(){
        return administratorDao.selectBalance();
    }

    @ApiOperation(value = "商户注册")
    @PostMapping("/enrollAdministrator")
    public String enrollAdministrator(@RequestBody enrollReq req){
        regularService.enrollRegular(req,2);
        return "success";
    }
    @ApiOperation(value = "商品入库")
    @PostMapping("/warehousing")
    public String warehousing(@RequestBody warehousingReq req){
        administratorService.warehousing(req);
        return "success";
    }


    @ApiOperation(value = "商品下架")
    @PostMapping("/delisting")
    public String delisting(@RequestBody goodsNameReq req){
        administratorService.delisting(req);
        return "success";
    }

    @ApiOperation(value = "商品上架")
    @PostMapping("/listing")
    public String listing(@RequestBody goodsNameReq req){
        administratorService.listing(req);
        return "success";
    }

    @ApiOperation(value = "商品价格调整")
    @PostMapping("/adjustPrice")
    public String adjustPrice(@RequestBody adjustPriceReq req){
        BigDecimal price = administratorService.adjustPrice(req);
        return "当前价格为："+price+"￥";
    }

    @ApiOperation(value = "商品折扣调整")
    @PostMapping("/adjustDiscount")
    public String adjustDiscount(@RequestBody adjustDiscountReq req){
        BigDecimal discount = administratorService.adjustDiscount(req);
        BigDecimal bigDecimal = new BigDecimal(100);
        return "当前折扣为："+discount.multiply(bigDecimal)+"%";
    }
}
