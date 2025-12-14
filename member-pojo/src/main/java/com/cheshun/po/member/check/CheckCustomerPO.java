package com.cheshun.po.member.check;

import java.io.Serializable;


public class CheckCustomerPO implements Serializable {
    private Integer checkId;

    private Integer activityId;

    private String activityName;

    private String customerName;

    private String customerPhone;

    private String memberCardNo;

    private String customerAddress;

    private String choiceCouponNo;

    private String screenshotUrl;

    private Byte submitCount;

    private Byte auditStatus;

    private Byte received;

    private String createTimeStr;

    private String updateTimeStr;

    private static final long serialVersionUID = 1L;

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo == null ? null : memberCardNo.trim();
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress == null ? null : customerAddress.trim();
    }

    public String getChoiceCouponNo() {
        return choiceCouponNo;
    }

    public void setChoiceCouponNo(String choiceCouponNo) {
        this.choiceCouponNo = choiceCouponNo;
    }

    public String getScreenshotUrl() {
        return screenshotUrl;
    }

    public void setScreenshotUrl(String screenshotUrl) {
        this.screenshotUrl = screenshotUrl == null ? null : screenshotUrl.trim();
    }

    public Byte getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Byte submitCount) {
        this.submitCount = submitCount;
    }

    public Byte getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Byte auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Byte getReceived() {
        return received;
    }

    public void setReceived(Byte received) {
        this.received = received;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    @Override
    public String toString() {
        return "CheckCustomerPO{" +
                "checkId=" + checkId +
                ", activityId=" + activityId +
                ", activityName=" + activityName +
                ", customerName='" + customerName + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", memberCardNo='" + memberCardNo + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", choiceCouponNo='" + choiceCouponNo + '\'' +
                ", screenshotUrl='" + screenshotUrl + '\'' +
                ", submitCount='" + submitCount + '\'' +
                ", auditStatus='" + auditStatus + '\'' +
                ", received=" + received +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                '}';
    }

}

