package com.yyl.store.service;

import com.yyl.store.entity.goods;
import com.yyl.store.entity.req.accountReq;
import com.yyl.store.entity.req.buyReq;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.req.rechargeReq;
import com.yyl.store.entity.statement;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 65199
 */
public interface RegularService {
    void enrollRegular(enrollReq req,int x);

    BigDecimal recharge(rechargeReq req);

    List<goods> selectGoods(accountReq req);

    statement buy(buyReq req);
}
