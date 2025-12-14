package com.cheshun.entity.heartrate;

import java.io.Serializable;
import java.time.LocalDateTime;

public class HeartRateReportDTO implements Serializable {
    private Integer id;

    private String memberCardNo;

    private String zxdReportId;

    private String pdfUrl;

    private LocalDateTime startTime;

    private Integer duration;

    private Integer type;

    private LocalDateTime expiresTime;

    private Byte isMine;

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

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl == null ? null : pdfUrl.trim();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDateTime getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(LocalDateTime expiresTime) {
        this.expiresTime = expiresTime;
    }

    public Byte getIsMine() {
        return isMine;
    }

    public void setIsMine(Byte isMine) {
        this.isMine = isMine;
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
        sb.append(", pdfUrl=").append(pdfUrl);
        sb.append(", startTime=").append(startTime);
        sb.append(", duration=").append(duration);
        sb.append(", type=").append(type);
        sb.append(", expiresTime=").append(expiresTime);
        sb.append(", isMine=").append(isMine);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}