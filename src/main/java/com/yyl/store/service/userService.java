package com.yyl.store.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.users;

/**
 * @author YangYuanLei
 * @ClassName userService
 * @description: TODO
 * @date 2024年04月02日
 * @version: 1.0
 */
public interface userService extends IService<users> {
//    users select();
    /**
     * 用户注册
     * enroll
     * @return
     */
    String enroll(enrollReq user);



    /**
     * 用户登录
     * @param
     *  user
     * @return
     */
//    String login(String userName, String password,String token);
    String login(users user);
}
