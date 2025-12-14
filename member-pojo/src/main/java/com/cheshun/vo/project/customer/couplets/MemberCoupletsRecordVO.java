package com.cheshun.vo.project.customer.couplets;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(description = "会员提交春联征集参数")
public class MemberCoupletsRecordVO implements Serializable {

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
     * 会员name
     */
    private String memberName;
    /**
     * 已选择的优惠券方案编码
     */
    private String memberPhone;
    /**
     * address
     */
    private String address;
    /**
     * firstLine
     */
    private String firstLine;
    /**
     * secondLine
     */
    private String secondLine;
    /**
     * horizontalScroll
     */
    private String horizontalScroll;
    /**
     * 专员Code
     */
    private String serviceWaiterCode;
    /**
     * 专员Name
     */
    private String serviceWaiterName;
    /**
     * 服务门店Code
     */
    private String serviceStoreCode;
    /**
     * 服务门店Name
     */
    private String serviceStoreName;
    /**
     * 是否加企微(1:是, 0否)
     */
    private Byte isQvxAdd;

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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public void setFirstLine(String firstLine) {
        this.firstLine = firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public void setSecondLine(String secondLine) {
        this.secondLine = secondLine;
    }

    public String getHorizontalScroll() {
        return horizontalScroll;
    }

    public void setHorizontalScroll(String horizontalScroll) {
        this.horizontalScroll = horizontalScroll;
    }

    public String getServiceWaiterCode() {
        return serviceWaiterCode;
    }

    public void setServiceWaiterCode(String serviceWaiterCode) {
        this.serviceWaiterCode = serviceWaiterCode;
    }

    public String getServiceWaiterName() {
        return serviceWaiterName;
    }

    public void setServiceWaiterName(String serviceWaiterName) {
        this.serviceWaiterName = serviceWaiterName;
    }

    public String getServiceStoreCode() {
        return serviceStoreCode;
    }

    public void setServiceStoreCode(String serviceStoreCode) {
        this.serviceStoreCode = serviceStoreCode;
    }

    public String getServiceStoreName() {
        return serviceStoreName;
    }

    public void setServiceStoreName(String serviceStoreName) {
        this.serviceStoreName = serviceStoreName;
    }

    public Byte getIsQvxAdd() {
        return isQvxAdd;
    }

    public void setIsQvxAdd(Byte isQvxAdd) {
        this.isQvxAdd = isQvxAdd;
    }

    @Override
    public String toString() {
        return "MemberCoupletsRecordVO{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", memberCardNo='" + memberCardNo + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberPhone='" + memberPhone + '\'' +
                ", address='" + address + '\'' +
                ", firstLine='" + firstLine + '\'' +
                ", secondLine='" + secondLine + '\'' +
                ", horizontalScroll='" + horizontalScroll + '\'' +
                ", serviceWaiterCode='" + serviceWaiterCode + '\'' +
                ", serviceWaiterName='" + serviceWaiterName + '\'' +
                ", serviceStoreCode='" + serviceStoreCode + '\'' +
                ", serviceStoreName='" + serviceStoreName + '\'' +
                ", isQvxAdd=" + isQvxAdd +
                '}';
    }
}
