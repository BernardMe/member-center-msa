package com.cheshun.mall.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 激活方式
 *
 * @author 阿隆
 * Created on 2021/8/12 11:56 上午.
 */
public enum ActiveMethod implements IEnum<String> {
    /**
     * E-用户线上申请
     * 客户直接申请，由甲方直接邮寄给客户（代理商无需申请空白ETC套装）
     */
    E("E", "用户线上申请,自己激活"),
    /**
     * S-代理商线下安装激活
     * 代理商交押金申请空白ETC套装，现场给客户安装激活
     */
    S("S", "代理商线下安装激活"),
    ;

    @EnumValue
    @JsonValue
    private final String value;
    private final String describe;

    ActiveMethod(String value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getDescribe() {
        return describe;
    }

    @JsonCreator
    public static ActiveMethod valueByCode(String code) {
        for (ActiveMethod item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
