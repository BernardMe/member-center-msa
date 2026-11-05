package com.cheshun.price.consts;

import java.util.Arrays;
import java.util.Optional;

/***
 * 通用状态定义枚举类
 * 1. 状态码定义规则为1位数字
 * 错误码列表：
 *  1: 启用
 *  2: 未启用
 *  ...
 * @author wangzhuo
 */
public enum CommonStatusEnum {

    /**
     * 1启用
     */
    ENABLE((byte)1, "启用"),
    /**
     * 2未启用
     */
    DISABLED((byte) 2, "未启用");

    private Byte code;
    private String name;
    private CommonStatusEnum(String name) {
        this.name = name;
    }
    private CommonStatusEnum(Byte code, String name) {
        this.code = code;
        this.name = name;
    }
    public Byte getCode() {
        return code;
    }
    public String getName() {
        return name;
    }

    // 通过枚举name查询当前枚举，可用for循环替换
    public static CommonStatusEnum getEnumByName(String name) {
        Optional<CommonStatusEnum> statusEnum = Arrays.stream(CommonStatusEnum.values()).filter(x -> x.getName().equals(name)).findFirst();
        return statusEnum.get();
    }

    // 通过枚举code查询当前枚举，可用for循环替换
    public static CommonStatusEnum getEnumByCode(Byte code) {
        Optional<CommonStatusEnum> statusEnum = Arrays.stream(CommonStatusEnum.values()).filter(x -> x.getCode().equals(code)).findFirst();
        return statusEnum.get();
    }
}
