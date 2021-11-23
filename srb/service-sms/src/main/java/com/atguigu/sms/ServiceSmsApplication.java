package com.atguigu.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// 告诉Spring 哪个packages下的,用注解标识的类 会被spring自动扫描并且装入bean容器
@ComponentScan("com.atguigu")
// 关于校验手机号 core是服务的提供者 sms是服务的消费者  @EnableFeignClients  需要在服务的消费者启动类上添加
@EnableFeignClients
public class ServiceSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class, args);
    }
}
