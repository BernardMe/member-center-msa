package com.cheshun.po.project.customer.birthday;

import java.io.Serializable;

/**
 * 会员生日信息领券活动状态PO
 * @author wangzhuo
 * @date 20251203
 */
public class MemberBirthdayActivityPO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private Integer activityId;
    /**
     * 会员卡号
     */
    private String activityName;
    /**
     * 会员卡号
     */
    private String memberCardNo;
    /**
     * 活动背景图片url
     */
    private String bgImgUrl;
    /**
     * 开始时间
     */
    private String startTimeStr;
    /**
     * 结束时间
     */
    private String endTimeStr;
    /**
     * 状态(0禁用, 1正常)
     */
    private Byte status;
    /**
     * 是否已删除(0正常, 1已删除)
     */
    private Byte isDelete;
    /**
     * 参与状态(0否 1是)
     */
    private Byte participateStatus;
    /**
     * 参与记录
     */
    private MemberBirthdayRecordPO recordPO;

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

    public String getBgImgUrl() {
        return bgImgUrl;
    }

    public void setBgImgUrl(String bgImgUrl) {
        this.bgImgUrl = bgImgUrl;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo == null ? null : memberCardNo.trim();
    }

    public Byte getParticipateStatus() {
        return participateStatus;
    }

    public void setParticipateStatus(Byte participateStatus) {
        this.participateStatus = participateStatus;
    }

    public MemberBirthdayRecordPO getRecordPO() {
        return recordPO;
    }

    public void setRecordPO(MemberBirthdayRecordPO recordPO) {
        this.recordPO = recordPO;
    }

    @Override
    public String toString() {
        return "MemberTeacherActivityPO{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", memberCardNo='" + memberCardNo + '\'' +
                ", bgImgUrl='" + bgImgUrl + '\'' +
                ", startTimeStr='" + startTimeStr + '\'' +
                ", endTimeStr='" + endTimeStr + '\'' +
                ", status=" + status +
                ", isDelete=" + isDelete +
                ", participateStatus=" + participateStatus +
                ", recordPO=" + recordPO +
                '}';
    }
}

