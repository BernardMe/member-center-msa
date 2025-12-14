package com.cheshun.constant.enumeration;

import java.util.Arrays;
import java.util.Optional;

/**
 * 并非是吃的药品的类型，而是在医药行业中的类型。
 * @author Administrator
 *
 */
public enum DrugType {
	NONE("NONE","非药品")
	,SIMPLE("SIMPLE","普通药品")
	,CHINESE_DRUG("CHINESE_DRUG","中药")
	,PRESCRIPTION("PRESCRIPTION","处方药")
	,OTC_RED("OTC_RED", "甲OTC")
	,OTC_GREEN("OTC_GREEN", "乙OTC")
	,CHI_DRUG_PIECE("CHI_DRUG_PIECE","中药饮片")
	,DISPENSING_KL("DISPENSING_KL","配方颗粒")
	,MEDICAL_DEVICE("MEDICAL_DEVICE","医疗器械")
	,HEALTH_FOOD("HEALTH_FOOD","保健食品")
	,FOOD("FOOD","食品")
	,DAILY_NECESSITY("DAILY_NECESSITY","日用品")
	,OTHERS("OTHERS","其他")
	,MEMBER_GIFT("MEMBER_GIFT","会员礼品")
	,FREE_GIFT("FREE_GIFT","赠品")
	,CHINESE_MEDICINE("CHINESE_MEDICINE","中医")
	,DOUBLE_SPAN("DOUBLE_SPAN ","双跨品种")
	;
	private String code;
	private String name;
	private DrugType(String name) {
		this.code = name();
		this.name = name;
	}
	private DrugType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}

	// 通过枚举name查询当前枚举，可用for循环替换
	public static DrugType getEnumByName(String name) {
		Optional<DrugType> drugType = Arrays.stream(DrugType.values()).filter(x -> x.getName().equals(name)).findFirst();
		return drugType.get();
	}
}
