package com.yyl.store.utils;

import com.yyl.store.entity.req.accountReq;

/**
 * @author 65199
 * @ClassName UserHolder
 * @description: TODO
 * @date 2024年04月25日
 * @version: 1.0
 */
public class UserHolder {
    private static final ThreadLocal<accountReq> tl = new ThreadLocal<>();

    public static void saveUser(accountReq user){
        tl.set(user);
    }

    public static accountReq getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
