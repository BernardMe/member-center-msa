package com.cheshun.constant.enumeration;

/**
 * @Description 活动状态操作类型枚举类
 */
public enum CommonActivityOperType {
	//
	DISABLED((byte)0, "禁用")
	//
	, ENABLE((byte)1, "启用")
	;
	private Byte code;
	private String name;
	private CommonActivityOperType(Byte code, String name) {
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
