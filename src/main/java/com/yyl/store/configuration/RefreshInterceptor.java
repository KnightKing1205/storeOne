package com.yyl.store.configuration;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yyl.store.entity.req.accountReq;
import com.yyl.store.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author 65199
 * @ClassName MyInterceptor
 * @description: TODO
 * @date 2024年04月23日
 * @version: 1.0
 */
public class RefreshInterceptor implements HandlerInterceptor {
    private StringRedisTemplate stringRedisTemplate;

    //json转化工具
    private static final ObjectMapper mapper = new ObjectMapper();
    public RefreshInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * preHandle方法：此方法会在进入controller之前执行，返回Boolean值决定是否执行后续操作
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        StrUtil.isBlank(token);
        String json = stringRedisTemplate.opsForValue().get(token);
        //反序列化
        accountReq user;
        try {
            user = mapper.readValue(json, accountReq.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if (user == null) {
                return true;
        }
        UserHolder.saveUser(user);
        stringRedisTemplate.expire(token,30, TimeUnit.MINUTES);
        //中间写逻辑代码，比如判断是否登录成功，失败则返回false
        return true;
    }

    /**
     * postHandle方法：此方法将在controller执行之后执行，但是视图还没有解析，可向ModelAndView中添加数据(前后端不分离的)。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //
        System.out.println("controller 执行完了");
    }

    /**
     * afterCompletion方法：该方法会在整个请求结束（请求结束，但是并未返回结果给客户端）之后执行， 可获取响应数据及异常信息。
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        System.out.println("我获取到了一个返回的结果："+response);
        System.out.println("请求结束了");
    }
}
