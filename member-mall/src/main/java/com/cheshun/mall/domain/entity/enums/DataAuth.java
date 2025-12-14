package com.cheshun.mall.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum DataAuth implements IEnum<Integer> {
    Personal(0,"个人"),

    Company(1,"公司");


    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    DataAuth(Integer value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getDescribe() {
        return describe;
    }

    @JsonCreator
    public static DataAuth valueByCode(Integer code) {
        for (DataAuth item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
