package com.atguigu.core.controller.admin;

import com.atguigu.common.exception.Assert;
import com.atguigu.common.result.Result;
import com.atguigu.common.result.ResultEnum;
import com.atguigu.core.pojo.entity.IntegralGrade;
import com.atguigu.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(tags = "积分等级管理") // controller 层面swagger文档
@RestController
//@CrossOrigin  // 跨域处理 允许跨域
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("获取积分列表")
    @GetMapping("/list")
    public Result listAll() {
        List<IntegralGrade> integralGrades = integralGradeService.list();
        return Result.success().data("list", integralGrades).message("获取列表成功");
    }

    @ApiOperation(value = "逻辑删除数据", notes = "根据id逻辑删除数据") // 方法级层面 swagger文档
    @DeleteMapping("removeById/{id}")
    public Result deleteById(
            @ApiParam(value = "数据Id", example = "1", required = true) // 参数层面 swagger文档
            @PathVariable Long id) {
        boolean result = integralGradeService.removeById(id);
        if (result) {
            return Result.success().message("删除成功");
        } else {
            return Result.success().message("删除失败");
        }

    }

    @PostMapping("/add")
    @ApiOperation("增加一个积分")
    // @RequestBody 希望是json数据类型的传输
    public Result add(@RequestBody IntegralGrade integralGrade) {

        // 这种方式也可以抛出异常但是不够好
//        if (integralGrade.getBorrowAmount() == null){
//            throw new BusinessException(ResultEnum.BORROW_AMOUNT_NULL_ERROR);
//        }
        // 使用自定义断言来处理异常
        Assert.notNull(integralGrade.getBorrowAmount(), ResultEnum.BORROW_AMOUNT_NULL_ERROR);
        boolean save = integralGradeService.save(integralGrade);
        if (save) {
            return Result.success().message("保存成功");
        } else {
            return Result.error().message("保存失败");
        }

    }

    @GetMapping("/getById/{id}")
    @ApiOperation("根据id查找某一个积分")
    public Result getById(@PathVariable Long id) {
        IntegralGrade byId = integralGradeService.getById(id);
        if (byId != null) {
            return Result.success().data("integral", byId);
        } else {
            return Result.error().message("查询失败");
        }
    }

    @PutMapping("/update")
    @ApiOperation("更新一条数据")
    public Result update(@RequestBody IntegralGrade integralGrade) {
        boolean result = integralGradeService.updateById(integralGrade);
        if (result) {
            return Result.success().message("更新成功");
        } else {
            return Result.success().message("更新失败");
        }
    }


}
