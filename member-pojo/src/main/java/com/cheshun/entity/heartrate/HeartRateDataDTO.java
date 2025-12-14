package com.cheshun.entity.heartrate;

import java.io.Serializable;
import java.time.LocalDateTime;

public class HeartRateDataDTO implements Serializable {
    private Integer id;

    private String memberCardNo;

    private String zxdReportId;

    private String oriDataUrl;

    private Byte deviceType;

    private String deviceName;

    private String deviceSn;

    private Byte platform;

    private Byte leadCount;

    private Integer duration;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMemberCardNo() {
        return memberCardNo;
    }

    public void setMemberCardNo(String memberCardNo) {
        this.memberCardNo = memberCardNo == null ? null : memberCardNo.trim();
    }

    public String getZxdReportId() {
        return zxdReportId;
    }

    public void setZxdReportId(String zxdReportId) {
        this.zxdReportId = zxdReportId == null ? null : zxdReportId.trim();
    }

    public String getOriDataUrl() {
        return oriDataUrl;
    }

    public void setOriDataUrl(String oriDataUrl) {
        this.oriDataUrl = oriDataUrl == null ? null : oriDataUrl.trim();
    }

    public Byte getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Byte deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn == null ? null : deviceSn.trim();
    }

    public Byte getPlatform() {
        return platform;
    }

    public void setPlatform(Byte platform) {
        this.platform = platform;
    }

    public Byte getLeadCount() {
        return leadCount;
    }

    public void setLeadCount(Byte leadCount) {
        this.leadCount = leadCount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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
        sb.append(", id=").append(id);
        sb.append(", memberCardNo=").append(memberCardNo);
        sb.append(", zxdReportId=").append(zxdReportId);
        sb.append(", oriDataUrl=").append(oriDataUrl);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", deviceSn=").append(deviceSn);
        sb.append(", platform=").append(platform);
        sb.append(", leadCount=").append(leadCount);
        sb.append(", duration=").append(duration);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}