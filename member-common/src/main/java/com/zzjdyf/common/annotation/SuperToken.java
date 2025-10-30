package com.zzjdyf.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 超级token注解
 *
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SuperToken {
    String[] token() default {};
}
