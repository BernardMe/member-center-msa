package com.zzjdyf.auth.service.member.core.base;


import com.zzjdyf.auth.vo.dto.MemberLoginInfo;
import com.zzjdyf.common.api.web.http.common.EntityResult;

/**
 * 用户基础信息服务
 * @author Administrator
 *
 */
//@Public
public interface MemberBaseInfoService {
	/**
	 * 根据用户TOKEN获取用户基础信息。
	 * <br>当没有用户信息时，返回未登录。
	 * @return
	 */
	EntityResult<MemberLoginInfo> getMemberBaseInfoByToken(String userToken);
//	/**
//	 * 修改用户基础信息。
//	 * <br>同时获取当前请求结果，添加需要更新用户基础信息的请求头。
//	 * <br>当userToken不存在时不更新。
//	 * @return
//	 */
//	Result saveUserBaseInfo(MemberLoginInfo base);
//	/**
//	 * 仅当用户信息存在时保存用户基础信息。
//	 * @return
//	 */
//	Result onlySaveUserBaseInfo(MemberLoginInfo base);
//	/**
//	 * 仅当用户信息不存在时保存用户基础信息。
//	 * @param base
//	 * @return
//	 */
//	Result storeUserBaseInfo(MemberLoginInfo base);
//	/**
//	 * 根据用户TOKEN删除用户信息。
//	 * @param userToken
//	 * @return
//	 */
//	Result deleteUserInfoByToken(String userToken);
}
