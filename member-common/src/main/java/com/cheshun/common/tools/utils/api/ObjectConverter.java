package com.cheshun.common.tools.utils.api;

/**
 * 对象转换器
 * @author Administrator
 *
 * @param <T>
 * @param <V>
 */
public interface ObjectConverter<T, V> {
	V convert(T t);
}
