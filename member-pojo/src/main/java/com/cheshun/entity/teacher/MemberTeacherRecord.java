package com.cheshun.entity.teacher;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MemberTeacherRecord implements Serializable {
    private Integer recordId;

    private Integer activityId;

    private Byte type;

    private String teacherName;

    private String memberCardNo;

    private String certPicUrl;

    private String certificateNum;

    private String choiceCouponNo;

    private Byte received;

    private LocalDateTime receiveDate;

    private String storeCode;

    private String storeName;

    private String teacherPhone;

    private String homeAddress;

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

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo == null ? null : memberCardNo.trim();
    }

    public String getCertPicUrl() {
        return certPicUrl;
    }

    public void setCertPicUrl(String certPicUrl) {
        this.certPicUrl = certPicUrl == null ? null : certPicUrl.trim();
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum == null ? null : certificateNum.trim();
    }

    public String getChoiceCouponNo() {
        return choiceCouponNo;
    }

    public void setChoiceCouponNo(String choiceCouponNo) {
        this.choiceCouponNo = choiceCouponNo == null ? null : choiceCouponNo.trim();
    }

    public Byte getReceived() {
        return received;
    }

    public void setReceived(Byte received) {
        this.received = received;
    }

    public LocalDateTime getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(LocalDateTime receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode == null ? null : storeCode.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone == null ? null : teacherPhone.trim();
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress == null ? null : homeAddress.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", recordId=").append(recordId);
        sb.append(", activityId=").append(activityId);
        sb.append(", type=").append(type);
        sb.append(", teacherName=").append(teacherName);
        sb.append(", memberCardNo=").append(memberCardNo);
        sb.append(", certPicUrl=").append(certPicUrl);
        sb.append(", certificateNum=").append(certificateNum);
        sb.append(", choiceCouponNo=").append(choiceCouponNo);
        sb.append(", received=").append(received);
        sb.append(", receiveDate=").append(receiveDate);
        sb.append(", storeCode=").append(storeCode);
        sb.append(", storeName=").append(storeName);
        sb.append(", teacherPhone=").append(teacherPhone);
        sb.append(", homeAddress=").append(homeAddress);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}