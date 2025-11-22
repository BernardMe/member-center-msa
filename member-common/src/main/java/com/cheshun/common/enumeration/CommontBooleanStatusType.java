package com.cheshun.common.enumeration;

/**
 * 通用是否状态枚举类类型
 */
public enum CommontBooleanStatusType {

    //0:否
    NO((byte)0, "否")
    //1:是
    , YES((byte)1, "是")
    ;
    private Byte code;
    private String name;
    private CommontBooleanStatusType(Byte code, String name) {
        this.code = code;
        this.name = name;
    }
    public Byte getCode() {
        return code;
    }
    public String getName() {
        return name;
    }

}
