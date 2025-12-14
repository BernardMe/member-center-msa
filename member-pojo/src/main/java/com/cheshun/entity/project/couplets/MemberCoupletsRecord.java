package com.cheshun.entity.project.couplets;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MemberCoupletsRecord implements Serializable {
    private Integer recordId;

    private Integer activityId;

    private String memberCardNo;

    private String memberName;

    private String memberPhone;

    private String address;

    private String firstLine;

    private String secondLine;

    private String horizontalScroll;

    private String serviceWaiterCode;

    private String serviceWaiterName;

    private String serviceStoreCode;

    private String serviceStoreName;

    private Byte isQvxAdd;

    private LocalDateTime createTime;

    private static final long serialVersionUID = 1L;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone == null ? null : memberPhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine == null ? null : firstLine.trim();
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine == null ? null : secondLine.trim();
    }

    public String getHorizontalScroll() {
        return horizontalScroll;
    }

    public void setHorizontalScroll(String horizontalScroll) {
        this.horizontalScroll = horizontalScroll == null ? null : horizontalScroll.trim();
    }

    public String getServiceWaiterCode() {
        return serviceWaiterCode;
    }

    public void setServiceWaiterCode(String serviceWaiterCode) {
        this.serviceWaiterCode = serviceWaiterCode == null ? null : serviceWaiterCode.trim();
    }

    public String getServiceWaiterName() {
        return serviceWaiterName;
    }

    public void setServiceWaiterName(String serviceWaiterName) {
        this.serviceWaiterName = serviceWaiterName == null ? null : serviceWaiterName.trim();
    }

    public String getServiceStoreCode() {
        return serviceStoreCode;
    }

    public void setServiceStoreCode(String serviceStoreCode) {
        this.serviceStoreCode = serviceStoreCode == null ? null : serviceStoreCode.trim();
    }

    public String getServiceStoreName() {
        return serviceStoreName;
    }

    public void setServiceStoreName(String serviceStoreName) {
        this.serviceStoreName = serviceStoreName == null ? null : serviceStoreName.trim();
    }

    public Byte getIsQvxAdd() {
        return isQvxAdd;
    }

    public void setIsQvxAdd(Byte isQvxAdd) {
        this.isQvxAdd = isQvxAdd;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordId=").append(recordId);
        sb.append(", activityId=").append(activityId);
        sb.append(", memberCardNo=").append(memberCardNo);
        sb.append(", memberName=").append(memberName);
        sb.append(", memberPhone=").append(memberPhone);
        sb.append(", address=").append(address);
        sb.append(", firstLine=").append(firstLine);
        sb.append(", secondLine=").append(secondLine);
        sb.append(", horizontalScroll=").append(horizontalScroll);
        sb.append(", serviceWaiterCode=").append(serviceWaiterCode);
        sb.append(", serviceWaiterName=").append(serviceWaiterName);
        sb.append(", serviceStoreCode=").append(serviceStoreCode);
        sb.append(", serviceStoreName=").append(serviceStoreName);
        sb.append(", isQvxAdd=").append(isQvxAdd);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

}