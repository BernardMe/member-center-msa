package com.cheshun.mall.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 商品类型(卡|设备)
 *
 * @author wangzhuo
 * Created on 20210721
 */
public enum GoodsType implements IEnum<Integer> {
    /**
     * 卡
     */
    CARD(1, "卡"),
    /**
     * 设备
     */
    DEVICE(2, "设备");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    GoodsType(Integer value, String describe) {
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
    public static GoodsType valueByCode(Integer code) {
        for (GoodsType item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
