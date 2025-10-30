package com.zzjdyf.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 返回模拟数据
 *
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MockData {
    /**
     * 可用该注解的profile
     *
     * @return profiles
     */
    String[] profiles() default {};

    /**
     * 返回的模拟数据的类型
     *
     * @return 返回的模拟数据的类型
     */
    Class<?> returnType();

    /**
     * 返回的模拟数据
     *
     * @return 返回的模拟数据
     */
    String returnStringValue() default "";

    /**
     * 返回的模拟数据
     *
     * @return 返回的模拟数据
     */
    boolean returnBooleanValue() default false;

    /**
     * 返回的模拟数据
     *
     * @return 返回的模拟数据
     */
    char returnCharValue() default Character.MIN_VALUE;

    /**
     * 返回的模拟数据
     *
     * @return 返回的模拟数据
     */
    byte returnByteValue() default 0;

    /**
     * 返回的模拟数据
     *
     * @return 返回的模拟数据
     */
    short returnShortValue() default 0;

    /**
     * 返回的模拟数据
     *
     * @return 返回的模拟数据
     */
    int returnIntValue() default 0;

    /**
     * 返回的模拟数据
     *
     * @return 返回的模拟数据
     */
    long returnLongValue() default 0L;

    /**
     * 返回的模拟数据
     *
     * @return 返回的模拟数据
     */
    float returnFloatValue() default 0.0f;

    /**
     * 返回的模拟数据
     *
     * @return 返回的模拟数据
     */
    double returnDoubleValue() default 0.00;
}
