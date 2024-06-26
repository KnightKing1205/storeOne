package com.yyl.store.configuration;

import com.yyl.store.utils.UserHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 65199
 * @ClassName MyInterceptor
 * @description: TODO
 * @date 2024年04月23日
 * @version: 1.0
 */
public class MyInterceptor implements HandlerInterceptor {
    /**
     * preHandle方法：此方法会在进入controller之前执行，返回Boolean值决定是否执行后续操作
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println(UserHolder.getUser().toString());
        if (UserHolder.getUser() == null){
            response.setStatus(401);
            return false;
        }
        System.out.println("jinru");
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
