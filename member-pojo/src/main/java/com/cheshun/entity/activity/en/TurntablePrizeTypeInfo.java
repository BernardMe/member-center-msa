package com.cheshun.entity.activity.en;

public enum TurntablePrizeTypeInfo {
    COUPON("COUPON", "优惠券"),
    PHYSICAL("PHYSICAL", "实物"),
    NONE("NONE", "谢谢惠顾");

    private final String code;
    private final String name;

    TurntablePrizeTypeInfo(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
