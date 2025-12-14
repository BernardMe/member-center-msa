package com.cheshun.constant.enumeration.teacher;

/**
 * @Description 类教师活动登记类型举类
 */
public enum TeacherRecordType {
	//0教师节
	TEACHER_DAY((byte)0, "教师节")
	//1国庆
	, NATIONAL_DAY((byte)1, "国庆")
	//2铁路医保
	, CR_MEDICAL_INSURANCE((byte)2, "铁路医保")
	;
	private Byte code;
	private String name;
	private TeacherRecordType(Byte code, String name) {
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
		for (TeacherRecordType value : TeacherRecordType.values()) {
			if (value.getCode().equals(code)){
				return value.getName();
			}
		}
		return null;
	}
}
