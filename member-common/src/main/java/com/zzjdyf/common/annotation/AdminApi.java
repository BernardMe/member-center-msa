package com.zzjdyf.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 后管系统API注解
 * <p>
 * 被该注解标记的API仅允许系统管理员调用,不允许非系统管理员用户访问
 *
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AdminApi {
}
