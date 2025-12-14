package com.cheshun.po.user.plantation.sign;

import java.io.Serializable;
import java.util.List;

/**
 * 用户当天签到情况接口返回PO
 * @author wangzhuo
 * @date 20240728
 */
public class PlantationUserSignInPO implements Serializable {

	/**
	 * 签到节点数据
	 */
	private List<SignInPOJO> listData;

	/**
	 * 当前天数
	 */
	private Integer userSignDay;

	public List<SignInPOJO> getListData() {
		return listData;
	}

	public void setListData(List<SignInPOJO> listData) {
		this.listData = listData;
	}

	public Integer getUserSignDay() {
		return userSignDay;
	}

	public void setUserSignDay(Integer userSignDay) {
		this.userSignDay = userSignDay;
	}
}
