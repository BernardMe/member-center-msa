package com.zzjdyf.mall.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 退货订单关联设备状态
 *
 * @author wangzhuo
 * Created on 20211112
 */
public enum ReturnGoodsStatus implements IEnum<Integer> {
    /**
     * 正常
     */
    NORMAL(0, "正常"),
    /**
     * 拒绝
     */
    REJECTED(1, "拒绝");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    ReturnGoodsStatus(Integer value, String describe) {
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
    public static ReturnGoodsStatus valueByCode(Integer code) {
        for (ReturnGoodsStatus item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
