package com.yyl.store.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yyl.store.entity.req.Token;
import com.yyl.store.entity.req.accountReq;
import com.yyl.store.entity.req.buyReq;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.req.rechargeReq;
import com.yyl.store.entity.ret.Result;
import com.yyl.store.entity.statement;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 65199
 */
public interface RegularService {
    void enrollRegular(enrollReq req,int x);

    Result recharge(rechargeReq req) throws JsonProcessingException;

    List<statement> selectGoods(accountReq req);

    statement buy(buyReq req);

    Result loginRegular(accountReq req) throws JsonProcessingException;

    Result selectBalance() throws JsonProcessingException;
}
