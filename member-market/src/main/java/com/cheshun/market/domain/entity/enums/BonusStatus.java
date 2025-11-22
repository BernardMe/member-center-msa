package com.cheshun.market.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 推广奖励状态
 *
 * @author 阿隆
 * Created on 2021/8/2 2:44 下午.
 */
public enum BonusStatus implements IEnum<Integer> {
    /**
     * 待结算
     */
    WAIT(1, "待结算"),
    /**
     * 已结算
     */
    SETTLED(2, "已结算"),
    /**
     * 结算失败
     */
    FAILED(3, "结算失败"),
    ;

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    BonusStatus(Integer value, String describe) {
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
    public static BonusStatus valueByCode(Integer code) {
        for (BonusStatus item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}