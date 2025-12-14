package com.cheshun.mall.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 代理商代理商品押金状态
 *
 * @author wangzhuo
 * Created on 20210721
 */
public enum DepositStatus implements IEnum<Integer> {
    /**
     * 待缴纳押金
     */
    WAIT_PAYMENT(1, "待缴纳押金"),
    /**
     * 已缴纳押金
     */
    PAID(2, "已缴纳押金"),
    /**
     * 已退还押金
     */
    RETURN(3, "已退还押金"),
    /**
     * 待补足押金
     */
    WAIT_FILLED(4, "待补足押金");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    DepositStatus(Integer value, String describe) {
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
    public static DepositStatus valueByCode(Integer code) {
        for (DepositStatus item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
