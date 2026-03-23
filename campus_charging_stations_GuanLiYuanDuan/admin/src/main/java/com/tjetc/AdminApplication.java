package com.tjetc;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




//核心启动类
@SpringBootApplication
//告诉spring去哪里找数据访问层（也就是Mapper接口）
@MapperScan("com.tjetc.dao")
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        System.out.println("===== 校园充电桩后台管理系统启动成功 =====");
    }
}
