package com.zzjdyf.auth.vo.dto;

import lombok.Data;

@Data
public class WechatMinappPhoneVPO {
	private String encryptedData;
	private String iv;
}
