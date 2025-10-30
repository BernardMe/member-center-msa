package com.zzjdyf.mall.service.member.core.oms.api;

public interface OmsUserInfoApi {
	
	String API_HEAD = "/limit/user";
	
	/**
	 * 根据条件查询用户信息
	 */
	OmsUrl QUERY_USER_INFOS_BY_CONDITION = new OmsUrl("GET", API_HEAD + "/infos");
	/**
	 * 根据手机号查询用户信息
	 */
	OmsUrl QUERY_USER_INFO_BY_PHONE = new OmsUrl("GET", API_HEAD + "/info/phone");
	/**
	 * 微信小程序--根据手机号查询用户信息
	 */
	OmsUrl QUERY_USER_INFO_BY_PHONE_WX = new OmsUrl("GET", API_HEAD + "/info/phone/wx");
	/**
	 * 微信小程序--微信进行门店云绑卡
	 */
	OmsUrl BIND_USER_INFO_WX = new OmsUrl("GET", API_HEAD + "/info/bind/wx");
	/**
	 * 支付宝小程序--根据手机号查询用户信息
	 */
	OmsUrl QUERY_USER_INFO_BY_PHONE_ALIPAY = new OmsUrl("GET", API_HEAD + "/info/phone/alipay");
	/**
	 * 根据用户主键查询用户信息
	 */
	OmsUrl QUERY_USER_INFO_BY_GUID = new OmsUrl("GET", API_HEAD + "/info/guid");
	/**
	 * 根据手机号查询用户详情
	 */
	OmsUrl QUERY_USER_DETAIL_BY_PHONE = new OmsUrl("GET", API_HEAD + "/detail/phone");
	/**
	 * 根据用户主键查询用户详情
	 */
	OmsUrl QUERY_USER_DETAIL_BY_GUID = new OmsUrl("GET", API_HEAD + "/detail/guid");
	/**
	 * 根据用户主键修改用户信息，仅支持修改昵称、头像、出生年月、性别和手机号。
	 */
	OmsUrl MODIFY_USER_INFO_BY_GUID = new OmsUrl("PUT", API_HEAD + "/info");
	/**
	 * 注册OMS会员
	 */
	OmsUrl REGIST_OMS_VIP = new OmsUrl("POST", API_HEAD + "/vip/regist");
	/**
	 * 修改OMS会员
	 */
	OmsUrl UPDATE_OMS_VIP = new OmsUrl("PUT", API_HEAD + "/basicInfo");

	/**
	 * 通过手机号查看用户会员信息
	 */
	OmsUrl QUERY_USER_VIP_INFO = new OmsUrl("GET",API_HEAD+"/vip/phone/info");

	/**
	 * 通过卡号查用户会员信息
	 */
	OmsUrl QUERY_USER_INFO_BY_CARDNO=new OmsUrl("GET",API_HEAD+"/vip/cardNo/info");
}
