package com.yyl.store.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//扫描路径，获取controller层的接口
                .apis(RequestHandlerSelectors.basePackage("com.yyl.store.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //标题
                .title("拼京宝")
                //简介
                .description("拼京宝接口文档")
                //作者、网址http:localhost:8088/doc.html(这里注意端口号要与项目一致，如果你的端口号后面还加了前缀，就需要把前缀加上)、邮箱
                .contact(new Contact("yyl","http:localhost:8088/doc.html","651993201@qq.com"))
                //版本
                .version("1.0")
                .build();
    }
}

