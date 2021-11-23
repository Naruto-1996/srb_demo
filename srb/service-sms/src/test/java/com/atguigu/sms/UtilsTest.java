package com.atguigu.sms;

import com.atguigu.sms.utils.SmsProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class UtilsTest {

    @Test
    public void test(){
        System.out.println(SmsProperties.KEY_ID);
    }
}
