package com.atguigu.common.result;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    private int code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    // 构造函数私有化
    private Result() {}

    /**-----使用链式编程，返回类本身-----**/

    // 通用返回成功
    public static Result success(){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        return result;
    }

    // 通用返回失败
    public static Result error(){
        Result result = new Result();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMessage(ResultEnum.ERROR.getMessage());
        return result;
    }

    // 设置结果 形参为枚举
    public static Result setResult(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }


    // 自定义返回数据
    public Result data(Map<String,Object> map) {
        this.setData(map);
        return this;
    }

    // 通用设置data
    public Result data(String key,Object value) {
        this.data.put(key, value);
        return this;
    }

    // 自定义状态信息
    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    // 自定义状态码
    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }


}
