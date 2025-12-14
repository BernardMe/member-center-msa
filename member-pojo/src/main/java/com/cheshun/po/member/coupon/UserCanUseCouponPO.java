package com.cheshun.po.member.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCanUseCouponPO {
    //优惠券编号
    private String couponNo;
    //优惠券类型名称
    private String couponTypeName;
    //优惠券名称
    private String couponName;
    //价值
    private Double couponAmount;
    //有效时间段
    private String validTime;
    //使用说明
    private String couponDescription;
    //规则说明
    private String couponRules;
}
