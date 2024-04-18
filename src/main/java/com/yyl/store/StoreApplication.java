package com.yyl.store;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author YangYuanLei
 */
@Slf4j
@Configuration
@SpringBootApplication
@MapperScan("com.yyl.store.dao")
public class StoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
        log.info("http://localhost:8080/doc.html#/");
        log.info("项目启动成功·····");
    }

}
