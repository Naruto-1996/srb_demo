package com.atguigu.core.controller;


import com.atguigu.core.pojo.entity.IntegralGrade;
import com.atguigu.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author liwenyang
 * @since 2021-11-02
 */
@Api(tags = "web网站积分管理")
@CrossOrigin
@RestController
@RequestMapping("/api/integralGrade")
public class IntegralGradeController {

    @Resource
    private IntegralGradeService service;

    @GetMapping("/listAll")
    @ApiOperation("获取积分列表")
    public List<IntegralGrade> getIntegralGradeAll(){
        return service.list();
    }
}

