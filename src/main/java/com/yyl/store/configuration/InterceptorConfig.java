package com.yyl.store.configuration;

import org.springframework.context.annotation.Configuration;
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
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")//拦截所有的路径
                .excludePathPatterns("/regular/loginRegular");
    }
}
