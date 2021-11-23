package com.atguigu.sms.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "aliyun.sms")
@Component
@Data
public class SmsProperties implements InitializingBean {

    //    region-id: cn-hangzhou
    //    key-id: LTAI4G5Svnb2TWBMuKnNT6jY
    //    key-secret: N7v6R4V3EJ1SGDZlsqtqo8QyVVMmtQ
    //    template-code: SMS_96695065
    //    sign-name: 谷粒

    private String regionId;
    private String keyId;
    private String keySecret;
    private String templateCode;
    private String signName;

    public static String REGION_Id;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String TEMPLATE_CODE;
    public static String SIGN_NAME;

    // 我们实现InitializingBean的方法 当properties被赋值之后 我们这里再次给静态的常量赋值
    // 这样就可以使用 SmsProperties.xxxxx 使用常量属性了

    @Override
    public void afterPropertiesSet() throws Exception {
        REGION_Id = regionId;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        TEMPLATE_CODE = templateCode;
        SIGN_NAME = signName;
    }
}
