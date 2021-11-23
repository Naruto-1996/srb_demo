package com.atguigu.core.controller.admin;


import com.atguigu.base.utils.JwtUtils;
import com.atguigu.common.exception.Assert;
import com.atguigu.common.result.Result;
import com.atguigu.common.result.ResultEnum;
import com.atguigu.common.utils.RegexValidateUtils;
import com.atguigu.core.pojo.entity.UserInfo;
import com.atguigu.core.pojo.query.UserInfoQuery;
import com.atguigu.core.pojo.vo.LoginVO;
import com.atguigu.core.pojo.vo.RegisterVO;
import com.atguigu.core.pojo.vo.UserInfoVO;
import com.atguigu.core.service.UserInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
@Api(tags = "会员管理接口")
//@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/admin/core/userInfo")
public class AdminUserInfoController {

    @Resource
    private UserInfoService service;

    @ApiOperation("获取会员分页列表")
    @GetMapping("/list/{page}/{limit}")
    public Result listPage(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(value = "查询对象", required = false)
                    UserInfoQuery userInfoQuery
    ) {

        Page<UserInfo> pageParam = new Page<>(page, limit);
        IPage<UserInfo> pageModel = service.listPage(pageParam, userInfoQuery);

        return Result.success().data("pageModel", pageModel);
    }


    @ApiOperation("用户锁定或解锁")
    @PutMapping("/lock/{id}/{status}")
    public Result lock(
            @ApiParam(value = "用户id", required = true)
            @PathVariable Long id,
            @ApiParam(value = "锁定状态 （0 锁定 1 正常）", required = true)
            @PathVariable Integer status) {

        service.lock(id, status);
        return Result.success().message(status == 1 ? "解锁成功" : "锁定成功");
    }


}

