package com.cheshun.entity.project.birthday;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MemberBirthdayRecord implements Serializable {
    private Integer recordId;

    private Integer activityId;

    private String memberCardNo;

    private String memberName;

    private String choiceCouponNo;

    private String memberPhone;

    private LocalDateTime birthdayUpTime;

    private String inputBirthday;

    private Byte received;

    private LocalDateTime receiveDate;

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

    public String getChoiceCouponNo() {
        return choiceCouponNo;
    }

    public void setChoiceCouponNo(String choiceCouponNo) {
        this.choiceCouponNo = choiceCouponNo == null ? null : choiceCouponNo.trim();
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone == null ? null : memberPhone.trim();
    }

    public LocalDateTime getBirthdayUpTime() {
        return birthdayUpTime;
    }

    public void setBirthdayUpTime(LocalDateTime birthdayUpTime) {
        this.birthdayUpTime = birthdayUpTime;
    }

    public String getInputBirthday() {
        return inputBirthday;
    }

    public void setInputBirthday(String inputBirthday) {
        this.inputBirthday = inputBirthday == null ? null : inputBirthday.trim();
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
        sb.append(", choiceCouponNo=").append(choiceCouponNo);
        sb.append(", memberPhone=").append(memberPhone);
        sb.append(", birthdayUpTime=").append(birthdayUpTime);
        sb.append(", inputBirthday=").append(inputBirthday);
        sb.append(", received=").append(received);
        sb.append(", receiveDate=").append(receiveDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}