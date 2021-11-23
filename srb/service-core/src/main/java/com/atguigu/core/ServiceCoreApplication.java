package com.atguigu.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.atguigu") // 告诉Spring 哪个packages下的,用注解标识的类 会被spring自动扫描并且装入bean容器
public class ServiceCoreApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceCoreApplication.class, args);

    }
}
