package com.cheshun.entity.coupon.en;

public enum CouponUseLimitType {
    MONEY("MONEY","金额")
    ,NUM("NUM","数量");

    private final String code;
    private final String name;

    CouponUseLimitType(String code, String name){
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
