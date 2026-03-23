package com.tjetc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {
    "com.tjetc",
    "com.tjetc.service",
    "com.tjetc.config",
    "com.tjetc.interceptor"
},
excludeFilters = {
    @ComponentScan.Filter(type = org.springframework.context.annotation.FilterType.REGEX,
                         pattern = "com\\.tjetc\\.(admin|Admin).*")
})
@MapperScan(basePackages = {"com.tjetc.dao"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}