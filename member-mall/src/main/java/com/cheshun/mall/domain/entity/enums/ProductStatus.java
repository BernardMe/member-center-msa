package com.cheshun.mall.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 产品状态
 *
 * @author wangzhuo
 * Created on 20211125
 */
public enum ProductStatus implements IEnum<Integer> {
    /**
     * 未激活
     */
    NO_ACTIVE(1, "未激活"),
    /**
     * 已激活
     */
    ACTIVATED(2, "已激活"),
    /**
     * 已损坏未退回
     */
    BROKEN(3, "已损坏未退回"),
    /**
     * 已损坏已退回
     */
    BROKEN_RETURNED(4, "已损坏已退回"),
    /**
     * 未激活退回
     */
    NO_ACTIVE_RETURNED(5, "未激活退回");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    ProductStatus(Integer value, String describe) {
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
    public static ProductStatus valueByCode(Integer code) {
        for (ProductStatus item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
