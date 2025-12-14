package com.cheshun.vo.member.coupon;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "优惠券派发参数")
public class SendByCouponNoVO implements Serializable {

    /**
     * 发券来源模块编码
     */
    private String couponOriginModelCode;

    /**
     * 活动名称
     */
    private Integer activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 会员卡号
     */
    private String memberCardNo;

    /**
     * 会员手机号
     */
    private String phone;

    /**
     * 券编号拼接字符串
     */
    private String couponCodeStr;

    public String getCouponOriginModelCode() {
        return couponOriginModelCode;
    }

    public void setCouponOriginModelCode(String couponOriginModelCode) {
        this.couponOriginModelCode = couponOriginModelCode;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCouponCodeStr() {
        return couponCodeStr;
    }

    public void setCouponCodeStr(String couponCodeStr) {
        this.couponCodeStr = couponCodeStr;
    }
}
