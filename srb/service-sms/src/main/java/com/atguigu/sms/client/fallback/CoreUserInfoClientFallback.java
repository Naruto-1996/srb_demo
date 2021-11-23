package com.atguigu.sms.client.fallback;

import com.atguigu.sms.client.CoreUserInfoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


// 熔断处理
@Service
@Slf4j
public class CoreUserInfoClientFallback implements CoreUserInfoClient {


    @Override
    public boolean checkMobile(String mobile) {
        log.error("远程调用失败，熔断处理-----");
        return false;  // 手机号不重复
    }
}
