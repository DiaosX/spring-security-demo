package com.mytest.springsecuritydemo3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.mytest.springsecuritydemo3.dao"})
public class SpringSecurityDemo3Application {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemo3Application.class, args);
    }
}
