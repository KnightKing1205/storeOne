package com.yyl.store.controller;

import com.yyl.store.dao.userDao;
import com.yyl.store.entity.Result;
import com.yyl.store.entity.ResultCode;
import com.yyl.store.entity.req.enrollReq;
import com.yyl.store.entity.users;
import com.yyl.store.service.QueryTokenService;
import com.yyl.store.service.userService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author YangYuanLei
 * @ClassName userController
 * @description: TODO
 * @date 2024年04月02日
 * @version: 1.0
 */
@RestController
@RequestMapping("addresses")
public class userController {
    @Autowired
    private userService userService;
    @Autowired
    private QueryTokenService queryTokenService;
//    @ApiOperation(value = "获取时间", httpMethod = "GET") // 在这里添加注解
//    @GetMapping("select")
//    public users select(){
//        return userService.select();
//    }

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result getPersonInfo(@RequestBody users user){
        String data = userService.login(user);
        Boolean flag= queryTokenService.returnQueryToken(data);
        Integer code= !flag ? ResultCode.POST_ERR:ResultCode.POST_OK;
        String message= !flag ?"登录失败":"登录成功！";
        Date date=new Date();
        Result result = Result.loginBody(code, flag, message,data,date);
        return result;
    }
    //@RequestBody把前端的值转化为json类型

    /**
     * 注册账户
     * @param user
     * @return
     */
    @PostMapping("/enroll")
    public Result postPersonInfo(@RequestBody enrollReq user){
        int code;
        String message=null;
        //返回的是token
        String data = userService.enroll(user);
        code=data!=null? ResultCode.POST_OK:ResultCode.POST_ERR;
        message=code != ResultCode.POST_OK?data=null:"注册成功！";
        if (data=="已有账号"){message="注册失败！";code=ResultCode.POST_ERR;data=null;}
        Boolean success=code!=ResultCode.POST_ERR?true:false;
        Date date=new Date();
        Result result = Result.enroll(code,success,message,data,date);
        return result;
    }

}
