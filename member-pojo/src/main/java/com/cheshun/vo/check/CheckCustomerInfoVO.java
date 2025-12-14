package com.cheshun.vo.check;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "登记会员信息参数")
public class CheckCustomerInfoVO implements Serializable {

    /**
     * 活动Id
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
     * 顾客姓名
     */
    private String customerName;

    /**
     * 顾客家庭住址
     */
    private String customerAddress;

    /**
     * 顾客手机号
     */
    private String customerPhone;

    /**
     * 分享截图url
     */
    private String screenshotUrl;

    /**
     * 已选择的优惠券方案编码
     */
    private String choiceCouponNo;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getScreenshotUrl() {
        return screenshotUrl;
    }

    public void setScreenshotUrl(String screenshotUrl) {
        this.screenshotUrl = screenshotUrl;
    }

    public String getChoiceCouponNo() {
        return choiceCouponNo;
    }

    public void setChoiceCouponNo(String choiceCouponNo) {
        this.choiceCouponNo = choiceCouponNo;
    }
}
