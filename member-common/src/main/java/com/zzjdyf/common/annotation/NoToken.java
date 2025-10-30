package com.zzjdyf.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 无需登陆即可访问的注解
 *
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoToken {
}
