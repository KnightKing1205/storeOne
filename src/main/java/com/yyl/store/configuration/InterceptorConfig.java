package com.yyl.store.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 65199
 * @ClassName InterceptorConfig
 * @description: TODO
 * @date 2024年04月23日
 * @version: 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RefreshInterceptor( stringRedisTemplate )).order(0)
                .addPathPatterns("/**")//拦截所有的路径
                .excludePathPatterns("/regular/loginRegular");

        registry.addInterceptor(new MyInterceptor()).order(1)
                .addPathPatterns("/**")//拦截所有的路径
                .excludePathPatterns("/regular/loginRegular");
    }
}
