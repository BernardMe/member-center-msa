package com.zzjdyf.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface PageInfoParam {
	/**
	 * 表示是否必须分页.
	 * @return
	 */
	boolean limitPage() default true;
}
