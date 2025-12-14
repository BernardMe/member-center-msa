package com.cheshun.po.user.plantation.sign;


import com.cheshun.entity.plantation.PlantationSignInDTO;

import java.io.Serializable;

/**
 * 签到接口返回VPO
 * @author wangzhuo
 * @date 20240728
 */
public class PlantationUserSignInVPO extends PlantationSignInDTO implements Serializable {

	/**
	 * 1表示完成, 0表示未完成
	 */
	private Boolean achieved;

	public Boolean getAchieved() {
		return achieved;
	}

	public void setAchieved(Boolean achieved) {
		this.achieved = achieved;
	}

	/**
	 * 刷新7日签到完成状态
	 * <br>
	 * @param continueDays
	 * @param daysDiff
	 * @return
	 */
	public void refreshAchieved(Integer continueDays, long daysDiff) {
		if (continueDays < 7) {
			setAchieved(Boolean.FALSE);
		} else if (continueDays == 7 ) {
			setAchieved(Boolean.TRUE);
		}
	}
}
