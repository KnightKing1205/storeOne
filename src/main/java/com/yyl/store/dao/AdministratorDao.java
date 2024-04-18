package com.yyl.store.dao;

import com.yyl.store.entity.req.adjustDiscountReq;
import com.yyl.store.entity.req.adjustPriceReq;
import com.yyl.store.entity.req.warehousingReq;
import com.yyl.store.entity.users;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author 65199
 */
public interface AdministratorDao {
    BigDecimal selectBalance ();

    users select(@Param("useAccount") String useAccount,@Param("usePassword") String usePassword);

    void warehousing(@Param("req") warehousingReq req,@Param("users") users users);

    void delisting(@Param("goodsName") String goodsName, @Param("id") Integer id,@Param("x")int x);

    void adjustPrice(@Param("req") adjustPriceReq req,@Param("id") Integer id);

    void adjustDiscount(@Param("req") adjustDiscountReq req, @Param("id") Integer id);

    users selectProvide(@Param("id") Integer goodsProvide);
}
