package com.cheshun.entity.check;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CheckCustomer implements Serializable {
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

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

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
        this.activityName = activityName == null ? null : activityName.trim();
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
        this.choiceCouponNo = choiceCouponNo == null ? null : choiceCouponNo.trim();
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", checkId=").append(checkId);
        sb.append(", activityId=").append(activityId);
        sb.append(", activityName=").append(activityName);
        sb.append(", customerName=").append(customerName);
        sb.append(", customerPhone=").append(customerPhone);
        sb.append(", memberCardNo=").append(memberCardNo);
        sb.append(", customerAddress=").append(customerAddress);
        sb.append(", choiceCouponNo=").append(choiceCouponNo);
        sb.append(", screenshotUrl=").append(screenshotUrl);
        sb.append(", submitCount=").append(submitCount);
        sb.append(", auditStatus=").append(auditStatus);
        sb.append(", received=").append(received);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}