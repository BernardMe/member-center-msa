package com.cheshun.po.project.customer.teacher;

import java.io.Serializable;

/**
 * 会员参与教资证登记活动状态PO
 * @author wangzhuo
 */
public class TeacherMemberRecordPO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private Integer activityId;
    /**
     * 教师姓名
     */
    private String teacherName;
    /**
     * 会员卡号
     */
    private String memberCardNo;
    /**
     * 教资证编码
     */
    private String certificateNum;
    /**
     * 教资证图片url
     */
    private String certPicUrl;
    /**
     * 已选择的优惠券方案编码
     */
    private String choiceCouponNo;
    /**
     * 领取时间Str
     */
    private String receiveDateStr;
    /**
     * 教师手机号
     */
    private String teacherPhone;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }



    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo == null ? null : memberCardNo.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public String getReceiveDateStr() {
        return receiveDateStr;
    }

    public void setReceiveDateStr(String receiveDateStr) {
        this.receiveDateStr = receiveDateStr;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    @Override
    public String toString() {
        return "TeacherMemberRecordPO{" +
                "activityId=" + activityId +
                ", teacherName='" + teacherName + '\'' +
                ", memberCardNo='" + memberCardNo + '\'' +
                ", certificateNum='" + certificateNum + '\'' +
                ", certPicUrl='" + certPicUrl + '\'' +
                ", choiceCouponNo='" + choiceCouponNo + '\'' +
                ", receiveDateStr='" + receiveDateStr + '\'' +
                ", teacherPhone='" + teacherPhone + '\'' +
                '}';
    }
}

