package com.cheshun.po.member.check;

import java.io.Serializable;


public class CheckActivityPO implements Serializable {


    private Integer activityId;

    private String activityName;

    private String couponNoStr;

    private String bgImgUrl;

    private String startTimeStr;

    private String endTimeStr;

    private Byte isOcr;

    private String ocrKeyword;

    private Byte status;

    private String createTimeStr;

    private String updateTimeStr;

    private static final long serialVersionUID = 1L;

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

    public String getCouponNoStr() {
        return couponNoStr;
    }

    public void setCouponNoStr(String couponNoStr) {
        this.couponNoStr = couponNoStr;
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

    public Byte getIsOcr() {
        return isOcr;
    }

    public void setIsOcr(Byte isOcr) {
        this.isOcr = isOcr;
    }

    public String getOcrKeyword() {
        return ocrKeyword;
    }

    public void setOcrKeyword(String ocrKeyword) {
        this.ocrKeyword = ocrKeyword;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        return "CheckActivityPO{" +
                "activityId=" + activityId +
                ", activityName='" + activityName + '\'' +
                ", couponNoStr='" + couponNoStr + '\'' +
                ", startTimeStr='" + startTimeStr + '\'' +
                ", endTimeStr='" + endTimeStr + '\'' +
                ", isOcr=" + isOcr +
                ", ocrKeyword='" + ocrKeyword + '\'' +
                ", status=" + status +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                '}';
    }
}

