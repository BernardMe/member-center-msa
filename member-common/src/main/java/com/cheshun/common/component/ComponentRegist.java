package com.cheshun.common.component;

@FunctionalInterface
public interface ComponentRegist<T> {
	void regist(T component);
}
