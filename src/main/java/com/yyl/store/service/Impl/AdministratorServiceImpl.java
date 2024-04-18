package com.yyl.store.service.Impl;

import com.yyl.store.dao.AdministratorDao;
import com.yyl.store.entity.req.adjustDiscountReq;
import com.yyl.store.entity.req.adjustPriceReq;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.req.goodsNameReq;
import com.yyl.store.entity.req.warehousingReq;
import com.yyl.store.entity.users;
import com.yyl.store.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author 65199
 * @ClassName AdministratorServiceImpl
 * @description: TODO
 * @date 2024年04月05日
 * @version: 1.0
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    AdministratorDao administratorDao;
    @Override
    public void warehousing(warehousingReq req) {
        users select = administratorDao.select(req.getUseAccount(), req.getUsePassword());
        if (select.getUseLevel() == 2){
            administratorDao.warehousing(req,select);
        }
    }

    @Override
    public void delisting(goodsNameReq req) {
        users user = administratorDao.select(req.getUseAccount(), req.getUsePassword());
        administratorDao.delisting(req.getGoodsName(),user.getId(),2);
    }

    @Override
    public void listing(goodsNameReq req) {
        users user = administratorDao.select(req.getUseAccount(), req.getUsePassword());
        administratorDao.delisting(req.getGoodsName(),user.getId(),1);
    }

    @Override
    public BigDecimal adjustPrice(adjustPriceReq req) {
        BigDecimal bigDecimal = new BigDecimal(0);
        if (req.getGoodsPrice().compareTo(bigDecimal) < 0){
            return bigDecimal;
        }
        users user = administratorDao.select(req.getUseAccount(), req.getUsePassword());
        administratorDao.adjustPrice(req,user.getId());
        return req.getGoodsPrice();
    }

    @Override
    public BigDecimal adjustDiscount(adjustDiscountReq req) {
        BigDecimal bigDecimal0 = new BigDecimal(0);
        BigDecimal bigDecimal1 = new BigDecimal(1);
        if (req.getGoodsDiscount().compareTo(bigDecimal0) < 0 || req.getGoodsDiscount().compareTo(bigDecimal1) > 0 ){
            return bigDecimal0;
        }
        users user = administratorDao.select(req.getUseAccount(), req.getUsePassword());
        administratorDao.adjustDiscount(req,user.getId());
        return req.getGoodsDiscount();
    }
}
