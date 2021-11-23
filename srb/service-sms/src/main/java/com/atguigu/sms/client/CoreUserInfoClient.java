package com.atguigu.sms.client;


import com.atguigu.sms.client.fallback.CoreUserInfoClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// service-core 是 service-core微服务里application.yml 中起的名字
// @FeignClient代表你要远程调用哪个微服务
// 远程调用可能出现失败的情况，加入熔断处理 fallback
@FeignClient(value = "service-core", fallback = CoreUserInfoClientFallback.class)
public interface CoreUserInfoClient {

    // 把core中的接口 复制到这里 我们在代码调用这个方法 就相当于调用了core中的接口   这里的路径应该是全部路径
    @GetMapping("/api/core/userInfo/checkMobile/{mobile}")
    boolean checkMobile(@PathVariable String mobile);
}
