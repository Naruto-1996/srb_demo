package com.atguigu.common.exception;

import com.atguigu.common.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

// 自定义断言
@Slf4j
public class Assert {

    /**
     * 断言obj不为空， 如果为空就抛出异常
     * @param obj
     * @param resultEnum
     */
    public static void notNull(Object obj, ResultEnum resultEnum){
        if (obj == null){
            log.info("obj is null........");
            throw new BusinessException(resultEnum);
        }
    }

    /**
     * 断言对象为空
     * 如果对象obj不为空，则抛出异常
     * @param object
     * @param resultEnum
     */
    public static void isNull(Object object, ResultEnum resultEnum) {
        if (object != null) {
            log.info("obj is not null......");
            throw new BusinessException(resultEnum);
        }
    }

    /**
     * 断言表达式为真
     * 如果不为真，则抛出异常
     *
     * @param expression 是否成功
     */
    public static void isTrue(boolean expression, ResultEnum resultEnum) {
        if (!expression) {
            log.info("fail...............");
            throw new BusinessException(resultEnum);
        }
    }

    /**
     * 断言两个对象不相等
     * 如果相等，则抛出异常
     * @param m1
     * @param m2
     * @param resultEnum
     */
    public static void notEquals(Object m1, Object m2,  ResultEnum resultEnum) {
        if (m1.equals(m2)) {
            log.info("equals...............");
            throw new BusinessException(resultEnum);
        }
    }

    /**
     * 断言两个对象相等
     * 如果不相等，则抛出异常
     * @param m1
     * @param m2
     * @param resultEnum
     */
    public static void equals(Object m1, Object m2,  ResultEnum resultEnum) {
        if (!m1.equals(m2)) {
            log.info("not equals...............");
            throw new BusinessException(resultEnum);
        }
    }

    /**
     * 断言参数不为空
     * 如果为空，则抛出异常
     * @param s
     * @param resultEnum
     */
    public static void notEmpty(String s, ResultEnum resultEnum) {
        if (!StringUtils.hasLength(s)) {
            log.info("is empty...............");
            throw new BusinessException(resultEnum);
        }
    }
}
