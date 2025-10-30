package com.zzjdyf.mall.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 交易类型
 *
 * @author wangzhuo
 * Created on 20210721
 */
public enum TradeType implements IEnum<Integer> {
    /**
     * 订货
     */
    ORDER(1, "订货"),
    /**
     * 补货
     */
    REPLENISH(2, "补货"),
    /**
     * 提现
     */
    WITHDRAW(3, "提现"),
    /**
     * 提现
     */
    REPLENISH_UP(4, "提标补货"),
    /**
     * 退货
     */
    RETURN(5, "退货");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    TradeType(Integer value, String describe) {
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
    public static TradeType valueByCode(Integer code) {
        for (TradeType item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
