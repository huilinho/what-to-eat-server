package com.what.to.eat.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * SpringBoot启动类
 *
 * @author
 */
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
public class WhatToEatApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(WhatToEatApplication.class);
        springApplication.run(args);
    }

}
