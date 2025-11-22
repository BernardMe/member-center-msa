package com.cheshun.common.constant;

public interface RedisConstant {
	/**
	 * 少数据量无事务Redis
	 */
	String REDIS_NAME_SINGLE = "singleRedis";
	/**
	 * 少数据量有事务Redis
	 */
	String REDIS_NAME_SINGLE_TX = "txSingleRedis";
	/**
	 * 大数据量无事务Redis
	 */
	String REDIS_NAME_MULTIPLE = "multipleRedis";
	/**
	 * 大数据量有事务Redis
	 */
	String REDIS_NAME_MULTIPLE_TX = "txMultipleRedis";
	/**
	 * 少数据量无事务字符串Redis
	 */
	String REDIS_NAME_STRING = "stringRedis";
	/**
	 * 大数据量无事务字符串Redis
	 */
	String REDIS_NAME_MULTIPLE_STRING = "multipleStringRedis";
	/**
	 * 基础信息库Redis--使用无事务字符串Redis
	 */
	String REDIS_NAME_BASE_LIBRARY = "baseLibraryRedis";
	/**
	 * 无事务监听Redis
	 */
	String REDIS_NAME_LISTENER = "listenerLibraryRedis";
	/**
	 * 无事务监听StringRedis
	 */
	String REDIS_NAME_LISTENER_STRING = "listenerLibraryStringRedis";
	/**
	 * 无事务监听StringRedis
	 */
	String REDIS_NAME_STRING_LISTENER = "stringListenerLibraryRedis";
	/**
	 * 默认Redis内key的头信息.
	 */
	String KEY_HEAD = "MEMBERCENTER.";
}
