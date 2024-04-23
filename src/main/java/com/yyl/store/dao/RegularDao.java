package com.yyl.store.dao;

import com.yyl.store.entity.goods;
import com.yyl.store.entity.req.accountReq;
import com.yyl.store.entity.req.buyReq;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.req.rechargeReq;
import com.yyl.store.entity.statement;
import com.yyl.store.entity.users;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 65199
 */
public interface RegularDao {
    void enrollRegular(@Param("req") enrollReq req,@Param("x") int x);

    BigDecimal selectBalance (@Param("req") accountReq req);

    void recharge(@Param("req") rechargeReq req,@Param("user") accountReq user);

    List<goods> selectGoods(accountReq req);

    void goodsNumReduce(@Param("req") buyReq req);

    void createStatement(@Param("req") buyReq req,
                         @Param("user") users user,
                         @Param("goods") goods commodity,
                         @Param("provide") users provide,
                         @Param("money") BigDecimal money,
                         @Param("date") Date date);

    void balanceReduce(@Param("req") buyReq req,
                       @Param("money") BigDecimal money);

    statement selectStatement(@Param("req") buyReq req,
                              @Param("provide") users provide,
                              @Param("money") BigDecimal money);

    users selectIs(@Param("req") accountReq req);
}
