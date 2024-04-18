package com.yyl.store.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 65199
 * @ClassName LoginToken
 * @description: TODO
 * @date 2024年04月18日
 * @version: 1.0
 */
public class LoginToken {
    //登录会用到的工具
    public String returnLogin(String password){
        Date now=new Date();
        //HashMap可以存储键值对
        Map<String,Object> claims=new HashMap<>();
        claims.put("password",password);
        //使用jwt生成器生成Token

        String token= Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256,"secret")
                .compact();
        return token;
    }
}
