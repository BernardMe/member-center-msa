package com.cheshun.market.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 代理商级别
 *
 * @author wangzhuo
 * Created on 20210721
 */
public enum AgentRole implements IEnum<Integer> {
    /**
     * 员工
     */
    STAFF(1, "员工"),
    /**
     * 一级代理
     */
    AGENT_LEVEL_1(11, "一级代理"),
    /**
     * 二级代理
     */
    AGENT_LEVEL_2(12, "二级代理"),
    /**
     * 三级代理
     */
    AGENT_LEVEL_3(13, "三级代理");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    AgentRole(Integer value, String describe) {
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
    public static AgentRole valueByCode(Integer code) {
        for (AgentRole item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
