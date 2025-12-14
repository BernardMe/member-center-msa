package com.cheshun.vo.project.customer.teacher;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "教师登记信息参数")
public class TeacherCertifyInfoVO implements Serializable {

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
    private String teacherName;
    /**
     * 顾客家庭住址
     */
    private String teacherAddress;
    /**
     * 顾客手机号
     */
    private String teacherPhone;
    /**
     * 教资证编号
     */
    private String certificateNum;
    /**
     * 分享截图url
     */
    private String certPicUrl;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum;
    }

    public String getCertPicUrl() {
        return certPicUrl;
    }

    public void setCertPicUrl(String certPicUrl) {
        this.certPicUrl = certPicUrl;
    }

    public String getChoiceCouponNo() {
        return choiceCouponNo;
    }

    public void setChoiceCouponNo(String choiceCouponNo) {
        this.choiceCouponNo = choiceCouponNo;
    }

    @Override
    public String toString() {
        return "TeacherCertifyInfoVO{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", memberCardNo='" + memberCardNo + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherAddress='" + teacherAddress + '\'' +
                ", teacherPhone='" + teacherPhone + '\'' +
                ", certificateNum='" + certificateNum + '\'' +
                ", certPicUrl='" + certPicUrl + '\'' +
                ", choiceCouponNo='" + choiceCouponNo + '\'' +
                '}';
    }
}
