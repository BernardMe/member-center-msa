package com.cheshun.po.user.plantation.assist;


import com.cheshun.entity.plantation.PlantationUserAssist;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * 助力浇水接口返回VPO
 * @author wangzhuo
 * @date 20241230
 */
public class UserAssistInPO extends PlantationUserAssist implements Serializable {

	private String headImg;

	private String nickName;

	/**
	 * 最后浇水日期字符串
	 */
	private String lastAssistDate;

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLastAssistDate() {
		return lastAssistDate;
	}

	public void setLastAssistDate(String lastAssistDate) {
		this.lastAssistDate = lastAssistDate;
	}

	/**
	 * 刷新最后浇水日期字符串
	 * <br>
	 * @return
	 */
	public void refreshLastAssistDate() {
		if (null != this.getUpdateTime()) {
			String lastAssistDate = this.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			setLastAssistDate(String.format("%s浇过", lastAssistDate));
		}
	}
}
