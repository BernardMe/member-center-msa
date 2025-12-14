package com.cheshun.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VipCouponDetailInfoVO {
    //优惠券信息主键
    private String couponGuid;
    //优惠券方案号
    private String couponNo;
    //优惠券方案名称
    private String couponName;
    //优惠券类型
    private String couponType;
    //会员卡号
    private String vipCardNo;
    //会员名称
    private String vipName;
    //会员手机号
    private String vipPhone;
    //密码
    private String password;
    //发放时间
    private LocalDateTime receiveDateTime;
    //有效起始日期
    private LocalDateTime beginDateTime;
    //有效结束日期
    private LocalDateTime endDateTime;
    //优惠券金额
    private String couponAmount;
    //使用金额限制
    private String usedLimitAmount;
    //使用状态
    private String usedStatus;
    //备注
    private String note;
    //描述
    private String couponDesc;
    //优惠券状态
    private String couponStatus;

    /**
     * 可核销渠道
     */
    private String channelCodeName;
    /**
     * 优惠券使用限制
     */
    private String couponLimitDesc;
    /**
     * 有效期
     */
    private String validityDate;
    /**
     * 核销码
     */
    private String writeOffCode;
}
