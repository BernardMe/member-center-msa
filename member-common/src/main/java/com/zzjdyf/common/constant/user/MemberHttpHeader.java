package com.zzjdyf.common.constant.user;
/**
 * 用户端常用HTTP头信息.
 * @author wangzhuo
 *
 */
public enum MemberHttpHeader {
	//设置会话TOKEN
	SET_SESSION_TOKEN("Set-Session-Token")
	//当前会话TOKEN
	,ACCESS_TOKEN("accessToken")
	//设置用户TOKEN
	,SET_USER_TOKEN("Set-User-Token")
	//当前用户TOKEN
	,USER_TOKEN("token")
	//当前用户环境
	,USER_ENVIRONMENT("User-Environment")
	//更新用户信息
	,USER_BASE_INFO_UPDATE("Update-User-Info")
	//门店端操作验证
	,SET_STORE_TOKEN("Store-Token")
	;
	private String Header;
	private MemberHttpHeader(String header) {
		Header = header;
	}
	public String getHeader() {
		return Header;
	}
}
