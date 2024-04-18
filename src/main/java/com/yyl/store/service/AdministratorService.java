package com.yyl.store.service;

import com.yyl.store.entity.req.adjustDiscountReq;
import com.yyl.store.entity.req.adjustPriceReq;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.req.goodsNameReq;
import com.yyl.store.entity.req.warehousingReq;

import java.math.BigDecimal;

/**
 * @author 65199
 */
public interface AdministratorService {

    void warehousing(warehousingReq req);

    void delisting(goodsNameReq req);

    void listing(goodsNameReq req);

    BigDecimal adjustPrice(adjustPriceReq req);

    BigDecimal adjustDiscount(adjustDiscountReq req);
}
