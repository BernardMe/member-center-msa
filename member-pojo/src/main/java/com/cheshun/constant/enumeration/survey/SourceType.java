package com.cheshun.constant.enumeration.survey;

/**
 * @Description 来源类型枚举类
 */
public enum SourceType {
	//1-1V1回访
	REVISIT_1V1((byte)1, "1V1回访"),
	//2-特药组
	SPECIALTY_DRUGS((byte)2, "特药组")
	;
	private Byte code;
	private String name;
	private SourceType(Byte code, String name) {
		this.code = code;
		this.name = name;
	}
	public Byte getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static String getNameByCode(byte code){
		for (SourceType value : SourceType.values()) {
			if (value.getCode().equals(code)){
				return value.getName();
			}
		}
		return null;
	}
}
