package com.cheshun.mall.service.member.core.common;


import com.cheshun.common.api.web.http.common.EntityResult;

/**
 * 用户会话存储服务.
 * <br>未进行用户会话与会员会话的绑定.
 * <br>未继承{{CommonSessionConstant}中12小时后过期(int OVER_TIME = 12;)}.
 * @author wangzhuo
 *
 */
public interface CommonAccessService {
	/**
	 * 获取当前会话TOKEN
	 * @return
	 */
	EntityResult<String> getAccessToken();

//	/**
//	 * 创建会话存储,存储时间为12个小时.
//	 * @return 返回会话存储的TOKEN.
//	 */
//	EntityResult<String> createSessionStorage();
//	/**
//	 * 获取会话存储中Key值对应的字符串.
//	 * <br>无法获取超过过期时间的值,同时会删除该key.
//	 * <br>PS:当key在MallUserSessionStorageKey中出现且是不公开的时候无法获取对应的值.
//	 * @param token
//	 * @param key
//	 * @return
//	 */
//	EntityResult<String> getPublicStorage(String token, String key);
//	/**
//	 * 获取会话存储中Key值对应的字符串,不判断key的公开程度,谨慎使用.
//	 * <br>无法获取超过过期时间的值,同时会删除该key.
//	 * @param token
//	 * @param key
//	 * @return
//	 */
//	EntityResult<String> getStorage(String token, String key);

}
