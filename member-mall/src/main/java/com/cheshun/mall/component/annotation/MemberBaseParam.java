package com.cheshun.mall.component.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface MemberBaseParam {
	/**
	 * 默认必须有用户信息
	 * @return
	 */
	boolean required() default true;
}
