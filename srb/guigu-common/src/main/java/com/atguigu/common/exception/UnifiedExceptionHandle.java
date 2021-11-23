package com.atguigu.common.exception;

import com.atguigu.common.result.Result;
import com.atguigu.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;


// 统一异常处理 捕获
// 所有发生的异常都会进入到这里面 这里面所有处理异常的方法其返回结果都以json返回
// 如果使用ControllerAdvice注解的话 需要在所有处理异常的方法上加上 @ResponseBody 注解来返回json
@RestControllerAdvice
@Slf4j
public class UnifiedExceptionHandle {

    @ExceptionHandler(value = Exception.class) // 正是因为这个注解所以所有异常才会走这里 value 代表要处理的异常
    public Result handleException(Exception e){
      log.error(e.getMessage(), e);
      return Result.error();
    }

    // 捕获并处理系统异常
    @ExceptionHandler(value = BadSqlGrammarException.class) // 处理别的异常 value 代表要处理的异常
    public Result handleException(BadSqlGrammarException e){
        log.error(e.getMessage());
        return Result.setResult(ResultEnum.BAD_SQL_GRAMMAR_ERROR);
    }

    // 捕获并处理自定义异常 (业务异常 即 由于接口传参不对啥的 我们自己抛出的异常)
    @ExceptionHandler(value = BusinessException.class)
    public Result handleException(BusinessException e){
        log.error(e.getMessage());
        return Result.error().code(e.getCode()).message(e.getMessage());
    }

    /**
     * Controller上一层相关异常  在进入controller之前报的异常
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class, // 接口写的是post 但是你用get调用 就会报这个异常
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class, // 前端传一个参数但是 参数不合法  比方说接口需要一个json但是调用接口时传的不是json 就会报这个异常
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public Result handleServletException(Exception e) {
        log.error(e.getMessage(), e);
        //SERVLET_ERROR(-102, "servlet请求异常"),
        return Result.error().message(ResultEnum.SERVLET_ERROR.getMessage()).code(ResultEnum.SERVLET_ERROR.getCode());
    }

}
