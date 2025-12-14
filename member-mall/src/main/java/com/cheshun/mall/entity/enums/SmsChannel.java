package com.cheshun.mall.entity.enums;


import com.cheshun.mall.entity.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 短信渠道
 *
 * @author wangzhuo
 * Created on 20210721
 */
public enum SmsChannel implements IEnum<Integer> {
    /**
     * 诚立业
     */
    CHENG_LI_YE(1, "诚立业"),
    /**
     * 梦网
     */
    MENG_WANG(2, "梦网"),
    /**
     * 聚梦
     */
    JU_MENG(3, "聚梦");

    @EnumValue
    @JsonValue
    private final Integer value;
    private final String describe;

    SmsChannel(Integer value, String describe) {
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
    public static SmsChannel valueByCode(Integer code) {
        for (SmsChannel item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }

    public static boolean inRange(Integer code) {
        for (SmsChannel item : values()) {
            if (item.value.equals(code)) {
                return true;
            }
        }
        return false;
    }
}
