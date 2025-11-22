package com.cheshun.market.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 通用状态
 *
 * @author wangzhuo
 * Created on 20210721
 */
public enum GeneralStatus implements IEnum<Integer> {
    /**
     * 禁用
     */
    DISABLED(1, "禁用"),
    /**
     * 正常
     */
    ENABLED(2, "启用");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    GeneralStatus(Integer value, String describe) {
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
    public static GeneralStatus valueByCode(Integer code) {
        for (GeneralStatus item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
