package com.atguigu.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    // 配置分类接口文档
    @Bean
    public Docket AdminApiConfig(){
      return   new Docket(DocumentationType.SWAGGER_2)
              .groupName("AdminApi")
              .apiInfo(AdminApiInfo())
              .select() // 创建一个过滤器选择器对象
              .paths(Predicates.and(PathSelectors.regex("/admin/.*"))) // Predicates.and表示要满足这个条件 PathSelectors.regex表示路径选择器通过正则匹配
              .build();
    }
    // 文档信息
    private ApiInfo AdminApiInfo(){
        return new ApiInfoBuilder()
                .title("后台管理API文档")
                .description("描述了接口的调用方式")
                .version("1.0.0")
                .contact(new Contact("李文洋","https://blog.wenyang.life","12345678@qq.com"))
                .build();
    }



    // 配置分类接口文档
    @Bean
    public Docket WebApiConfig(){
        return   new Docket(DocumentationType.SWAGGER_2)
                .groupName("WebApi")
                .apiInfo(WebApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }
    private ApiInfo WebApiInfo(){
        return new ApiInfoBuilder()
                .title("WEB API文档")
                .description("描述了接口的调用方式")
                .version("1.0.0")
                .contact(new Contact("李文洋","https://blog.wenyang.life","12345678@qq.com"))
                .build();
    }

}
