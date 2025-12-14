package com.cheshun.entity.coupon.en;

public enum CouponUseTimeType {
    NONE("NONE","固定日期")
    ,GET("GET","领取后");

    private final String code;
    private final String name;

    CouponUseTimeType(String code,String name){
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
