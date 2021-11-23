package com.atguigu.core.controller.api;


import com.atguigu.base.utils.JwtUtils;
import com.atguigu.common.exception.Assert;
import com.atguigu.common.result.Result;
import com.atguigu.common.result.ResultEnum;
import com.atguigu.common.utils.RegexValidateUtils;
import com.atguigu.core.pojo.vo.LoginVO;
import com.atguigu.core.pojo.vo.RegisterVO;
import com.atguigu.core.pojo.vo.UserInfoVO;
import com.atguigu.core.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author liwenyang
 * @since 2021-11-02
 */
@Api(tags = "会员接口")
//@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/api/core/userInfo")
public class UserInfoController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserInfoService service;

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVO registerVO) {

        String mobile = registerVO.getMobile();
        String code = registerVO.getCode();
        String password = registerVO.getPassword();

        // 校验
        Assert.notEmpty(mobile, ResultEnum.MOBILE_NULL_ERROR);
        Assert.notEmpty(code, ResultEnum.CODE_NULL_ERROR);
        Assert.notEmpty(password, ResultEnum.PASSWORD_NULL_ERROR);
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResultEnum.MOBILE_ERROR);

        // 校验验证码是否正确
        String getCodeInRedis = (String) redisTemplate.opsForValue().get("srb:core:sms:code" + mobile);
        log.info("======>>>>>>" + getCodeInRedis);

        Assert.equals(code, getCodeInRedis, ResultEnum.CODE_ERROR);

        // 注册
        service.register(registerVO);
        return Result.success().message("注册成功");

    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVO loginVO, HttpServletRequest httpServletRequest) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();

        Assert.notEmpty(mobile, ResultEnum.MOBILE_NULL_ERROR);
        Assert.notEmpty(password, ResultEnum.PASSWORD_NULL_ERROR);

        // 获取客户端的ip 每次登录都记录一下用户的ip
        String ip = httpServletRequest.getRemoteAddr();
        UserInfoVO userInfoVO = service.login(loginVO, ip);
        return Result.success().data("userInfo", userInfoVO);
    }

    @ApiOperation("校验token")
    @GetMapping("/checkToken")
    public Result checkToken(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("token");
        boolean result = JwtUtils.checkToken(token);

        if (result) {
            return Result.success();
        } else {
            return Result.setResult(ResultEnum.LOGIN_AUTH_ERROR);
        }

    }

    // 校验手机号是否已经被注册 给sms微服务使用
    @ApiOperation("校验手机号是否注册")
    @GetMapping("/checkMobile/{mobile}")
    public boolean checkMobile(@PathVariable String mobile){
       return service.checkMobile(mobile);
    }

}

