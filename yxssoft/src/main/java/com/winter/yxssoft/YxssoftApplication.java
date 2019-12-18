package com.winter.yxssoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.winter.yxssoft.dao")
public class YxssoftApplication {

    public static void main(String[] args) {
        System.out.println("启动 spring boot……");
        SpringApplication.run(YxssoftApplication.class, args);
    }

}
