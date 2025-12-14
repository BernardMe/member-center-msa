package com.cheshun.mall.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 订单状态
 *
 * @author wangzhuo
 * Created on 20210721
 */
public enum OrderStatus implements IEnum<Integer> {
    /**
     * 待发货
     */
    WAIT_DELIVER(1, "待发货"),
    /**
     * 已发货
     */
    SHIPPED(2, "已发货"),
    /**
     * 申请补货
     */
    APPLY_REPLENISH(3, "申请补货"),
    /**
     * 已拒绝
     */
    DENIED(4, "已拒绝"),
    /**
     * 已取消
     */
    CANCELLED(5, "已取消"),
    /**
     * 申请退货
     */
    APPLY_RETURN(6, "申请退货"),
    /**
     * 已退货
     */
    RETURNED(7, "已退货");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    OrderStatus(Integer value, String describe) {
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
    public static OrderStatus valueByCode(Integer code) {
        for (OrderStatus item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
