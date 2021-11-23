package com.atguigu.common.exception;

import com.atguigu.common.result.ResultEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

// 自定义异常 必须是一个运行时异常
@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException{

    // 错误码
    private Integer code;
    // 错误消息
    private String message;

    // 下面是提供了很多中抛出异常的方法

    /**
     * 错误消息
     * @param message
     */
    public BusinessException(String message){
        this.message = message;
    }

    /**
     * 错误码和错误消息
     * @param code
     * @param message
     */
    public BusinessException(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    /**
     *
     * @param code
     * @param message
     * @param throwable 原始异常对象 原始捕获的异常信息
     */
    public BusinessException(Integer code, String message, Throwable throwable){
        super(throwable);
        this.code = code;
        this.message = message;
    }

    /**
     *
     * @param resultEnum 接收枚举类型
     */
    public BusinessException(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }


    /**
     * 枚举加原始异常对象
     * @param resultEnum
     * @param throwable
     */
    public BusinessException(ResultEnum resultEnum, Throwable throwable){
        super(throwable);
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }




}
