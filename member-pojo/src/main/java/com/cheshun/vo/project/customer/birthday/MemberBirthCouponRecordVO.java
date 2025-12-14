package com.cheshun.vo.project.customer.birthday;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "会员提交生日领券参数")
public class MemberBirthCouponRecordVO implements Serializable {

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

    public String getChoiceCouponNo() {
        return choiceCouponNo;
    }

    public void setChoiceCouponNo(String choiceCouponNo) {
        this.choiceCouponNo = choiceCouponNo;
    }

    @Override
    public String toString() {
        return "MemberBirthInfoVO{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", memberCardNo='" + memberCardNo + '\'' +
                ", choiceCouponNo='" + choiceCouponNo + '\'' +
                '}';
    }
}
