package com.zzjdyf.mall.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 代理商审核状态
 *
 * @author wangzhuo
 * Created on 20210721
 */
public enum AgentAuditStatus implements IEnum<Integer> {
    /**
     * 待审核
     */
    WAIT_AUDIT(1, "待审核"),
    /**
     * 已拒绝
     */
    DENIED(2, "已拒绝"),
    /**
     * 已通过
     */
    PASSED(3, "已通过"),
    /**
     * 已申请解约
     */
    APPLY_TERMINATE(4, "已申请解约"),
    /**
     * 已解约
     */
    TERMINATED(5, "已解约");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    AgentAuditStatus(Integer value, String describe) {
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
    public static AgentAuditStatus valueByCode(Integer code) {
        for (AgentAuditStatus item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
