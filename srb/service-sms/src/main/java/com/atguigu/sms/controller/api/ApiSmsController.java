package com.atguigu.sms.controller.api;

import com.atguigu.common.exception.Assert;
import com.atguigu.common.result.Result;
import com.atguigu.common.result.ResultEnum;
import com.atguigu.common.utils.RandomUtils;
import com.atguigu.common.utils.RegexValidateUtils;
import com.atguigu.sms.client.CoreUserInfoClient;
import com.atguigu.sms.service.SmsService;
import com.atguigu.sms.utils.SmsProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
//@CrossOrigin
@Slf4j
@Api(tags = "短信接口管理")
@RequestMapping("/api/sms")
public class ApiSmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    // 将远程调用的微服务的客服端注入进来
    @Resource
    private CoreUserInfoClient coreUserInfoClient;


    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public Result sendSms(
            @ApiParam(value = "手机号", required = true)
            @PathVariable String mobile){

        // 校验手机号码不为空
        Assert.notEmpty(mobile, ResultEnum.MOBILE_NULL_ERROR);
        // 校验手机号码合法性
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResultEnum.MOBILE_ERROR);

        // 判断手机号是否已经注册  远程服务调用 core
        Assert.isTrue(!coreUserInfoClient.checkMobile(mobile), ResultEnum.MOBILE_EXIST_ERROR);

        String verificationCode = RandomUtils.getSixBitRandom();

        HashMap<String,Object> map = new HashMap<>();
        map.put("code", verificationCode);

        //smsService.send(mobile, SmsProperties.TEMPLATE_CODE, map);

        // 将验证码存入redis 5分钟有效
        redisTemplate.opsForValue().set("srb:core:sms:code" + mobile, verificationCode, 5, TimeUnit.MINUTES);

     return Result.success().message("短信发送成功");
    }

}
