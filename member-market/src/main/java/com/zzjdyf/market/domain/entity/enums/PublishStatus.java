package com.zzjdyf.market.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 上架状态
 *
 * @author wangzhuo
 * Created on 20210721
 */
public enum PublishStatus implements IEnum<Integer> {
    /**
     * 未上架
     */
    REMOVED(1, "未上架"),
    /**
     * 已上架
     */
    ADDED(2, "已上架");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    PublishStatus(Integer value, String describe) {
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
    public static PublishStatus valueByCode(Integer code) {
        for (PublishStatus item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
