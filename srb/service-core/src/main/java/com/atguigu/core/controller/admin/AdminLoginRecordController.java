package com.atguigu.core.controller.admin;


import com.atguigu.common.result.Result;
import com.atguigu.core.pojo.entity.UserLoginRecord;
import com.atguigu.core.service.UserLoginRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "用户登录日志接口")
//@CrossOrigin
@Slf4j
@RequestMapping("/admin/core/userLoginRecord")
@RestController
public class AdminLoginRecordController {

    @Resource
    private UserLoginRecordService loginService;


    @ApiOperation("用户登录日志列表")
    @GetMapping("/listTop50/{userId}")
    public Result listTop50(
            @ApiParam(value = "用户id", required = true)
            @PathVariable Long userId){

        List<UserLoginRecord> userLoginRecords = loginService.getListTop50(userId);

        return Result.success().data("list", userLoginRecords);

    }


}
