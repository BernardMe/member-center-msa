package com.cheshun.mall.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountStatus implements IEnum<Integer> {
    Enable(0,"启用"),

    Deactivate(1,"停用");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    AccountStatus(Integer value, String describe) {
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
    public static AccountStatus valueByCode(Integer code) {
        for (AccountStatus item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
