package com.cheshun.constant.enumeration.survey;

/**
 * @Description 题目类型枚举类
 */
public enum QuestionType {
	//1-单选题
	SINGLE_CHOICE((byte)1, "单选题")
	//2-多选题
	, MULTIPLE_CHOICE((byte)2, "多选题")
	//3-问答题
	, ESSAY_QUESTION((byte)3, "问答题")
	;
	private Byte code;
	private String name;
	private QuestionType(Byte code, String name) {
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
