package com.cheshun.constant.enumeration.check;

/**
 * @Description 审核状态枚举类
 */
public enum CheckCustomerAuditStatus {
	//
	NO_NEED((byte)0, "无需审核")
	//
	, AUDIT_SUCCESS((byte)1, "审核成功")
	//
	, AUDIT_FAIL((byte)2, "审核失败")
	//
	, OCR_PROCESSING((byte)3, "OCR识别中")
	//
	, OCR_SUCCESS((byte)4, "OCR识别成功")
	//
	, OCR_FAIL((byte)5, "OCR识别失败")
	;
	private Byte code;
	private String name;
	private CheckCustomerAuditStatus(Byte code, String name) {
		this.code = code;
		this.name = name;
	}
	public Byte getCode() {
		return code;
	}
	public String getName() {
		return name;
	}

}
