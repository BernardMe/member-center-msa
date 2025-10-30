package com.zzjdyf.mall.service.member.core.oms.entity;

import java.time.LocalDate;

public class OmsUserInfoVO {
	private String userInfoGuid;
	private String nickName;
	private String headImgUrl;
	private LocalDate birthday;
	private String sex;
	private String phone;
	private String vipTag;
	private String vipCardNo;
	public String getUserInfoGuid() {
		return userInfoGuid;
	}
	public void setUserInfoGuid(String userInfoGuid) {
		this.userInfoGuid = userInfoGuid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getVipTag() {
		return vipTag;
	}
	public void setVipTag(String vipTag) {
		this.vipTag = vipTag;
	}
	public String getVipCardNo() {
		return vipCardNo;
	}
	public void setVipCardNo(String vipCardNo) {
		this.vipCardNo = vipCardNo;
	}
}
