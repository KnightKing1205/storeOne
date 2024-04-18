package com.yyl.store.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyl.store.dao.RegularDao;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.users;
import com.yyl.store.dao.userDao;
import com.yyl.store.service.ComFoundException;
import com.yyl.store.service.LoginToken;
import com.yyl.store.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

/**
 * @author YangYuanLei
 * @ClassName userServiceImpl
 * @description: TODO
 * @date 2024年04月02日
 * @version: 1.0
 */
@Service
public class userServiceImpl extends ServiceImpl<userDao,users> implements userService {
    @Autowired
    private userDao userDao;
    @Autowired
    RegularDao regularDao;
//    @Override
//    public users select() {
//        return userDao.select();
//    }

    //写sql语句的类
    //注册的类，注解可以校验是不是父接口的子类
    @Override
    public String enroll(enrollReq user) {
        String username = user.getUseName();
        String password = user.getUsePassword();
        String account = user.getUseAccount();
        String address = user.getUseAddress();
        try {
            if (username == null || password == null) {
                return "账号密码中不能为空";
            }
            QueryWrapper<users> qw = new QueryWrapper<>();
            qw.eq("userName", username);
//            users userEnrollEnroll1 = userDao.selectOne(qw);
//            if (userEnrollEnroll1 != null) {
//                return "已有账号";
//            }
        } catch (NullPointerException e) {
            throw new ComFoundException();
        }
        //使用jwt加密用户名来生成随机token
        LoginToken loginToken = new LoginToken();
        String userUserNameJwt = loginToken.returnLogin(username);
        //使用BCrypt对密码进行加密
        String BCryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        //开始存储数据
        users userEnrollEnrollSave = new users();
        userEnrollEnrollSave.setUseName(username);
        userEnrollEnrollSave.setUseAccount(account);
        userEnrollEnrollSave.setUseLevel(1);
        userEnrollEnrollSave.setUseAddress(address);
        userEnrollEnrollSave.setUsePassword(BCryptPassword);
        userEnrollEnrollSave.setToken(userUserNameJwt);
        int insert = userDao.inserts(userEnrollEnrollSave);
        if (insert != 1) {
            return "插入失败";
        }
        return userUserNameJwt;
    }

    @Override
    public String login(users user) {
        //获取前端的数据
        String username = user.getUseName();
        String password = user.getUsePassword();
        String token = user.getToken();
        if (username == null || password == null) {
            return "账号不为空";
        }
        //判断数据是否无误
        LambdaQueryWrapper<users> lqw = new LambdaQueryWrapper<>();
        lqw.eq(users::getUseName,username);
//        lqw.select(users::getId, users::getUsePassword,users::getToken);
//        List<users> userList = userDao.select(lqw);
        users userList = userDao.select(username);
//        if (userList == null || userList.size() == 0) {
//            return "用户名或密码错误";
//        }
        if (userList == null) {
            return "用户名或密码错误";
        }
        //用户跨游览器导致token缺失解决
        if (token == null) {
            String tokenOne=null;
//            for (users userToken:userList){
//                tokenOne= userToken.getToken();
//            }

                tokenOne= userList.getToken();

            if (tokenOne==null){
                //如果数据库中查不到token则证明这个用户要么是黑户，要么是工作人员的失误
                return "token为空,登录失败！";
            }else {
                //前端拿到token在进行登录，则能登录成功
                return tokenOne;
            }
        }
        //对密码进行解密
        String passwordBCryptCheckpw = null;

//        for (users userPassword : userList) {
//            passwordBCryptCheckpw = userPassword.getUsePassword();
//        }
            passwordBCryptCheckpw = userList.getUsePassword();

        //使用BCrypt.checkpw(password,passwordBCryptCheckpw)来确认数据是否正确
        Boolean flag = BCrypt.checkpw(password, passwordBCryptCheckpw);
        if (flag != true) {
            return "用户名或密码错误";
        }

        //使用jwt加密用户名来生成随机token
        LoginToken loginToken = new LoginToken();
        String ReturnUserNameToken = loginToken.returnLogin(password);
//        //查询登录的是哪一个人的id;
//        String idStr = null;
////        for (users userId : userList) {
////            idStr = String.valueOf(userId.getId()) + "L";
////        }
//
//        idStr = String.valueOf(userList.getId()) + "L";
//
//        long id = Long.parseLong(idStr.replace("L", ""));
//
//        //更新token
//        users userPutToken = userDao.selectById(userList.getId());
//
//        userPutToken.setToken(ReturnUserNameToken);
//        int flags = userDao.updateById(userPutToken);
//        if (flags != 1) {
//            return "添加失败";
//        }
        return ReturnUserNameToken;
    }
}
