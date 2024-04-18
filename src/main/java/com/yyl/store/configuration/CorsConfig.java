package com.yyl.store.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 65199
 * @ClassName CorsConfig
 * @description: TODO
 * @date 2024年04月18日
 * @version: 1.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/shop/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders("Content-Type")
                .allowCredentials(true).maxAge(3600);
    }
}
