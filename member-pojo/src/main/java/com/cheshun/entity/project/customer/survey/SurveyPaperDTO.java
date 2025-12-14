package com.cheshun.entity.project.customer.survey;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SurveyPaperDTO implements Serializable {
    private Integer paperId;

    private String paperName;

    private Integer questionNum;

    private Integer recordNum;

    private Byte status;

    private Integer sort;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Byte source;

    private String smallProgramCode;

    private Byte isIssueCoupon;

    private String couponNo;

    private Byte isDirected;

    private Byte isExportDely;

    private String forewordText;

    private String epilogueText;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName == null ? null : paperName.trim();
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public Integer getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(Integer recordNum) {
        this.recordNum = recordNum;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }

    public String getSmallProgramCode() {
        return smallProgramCode;
    }

    public void setSmallProgramCode(String smallProgramCode) {
        this.smallProgramCode = smallProgramCode == null ? null : smallProgramCode.trim();
    }

    public Byte getIsIssueCoupon() {
        return isIssueCoupon;
    }

    public void setIsIssueCoupon(Byte isIssueCoupon) {
        this.isIssueCoupon = isIssueCoupon;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo == null ? null : couponNo.trim();
    }

    public Byte getIsDirected() {
        return isDirected;
    }

    public void setIsDirected(Byte isDirected) {
        this.isDirected = isDirected;
    }

    public Byte getIsExportDely() {
        return isExportDely;
    }

    public void setIsExportDely(Byte isExportDely) {
        this.isExportDely = isExportDely;
    }

    public String getForewordText() {
        return forewordText;
    }

    public void setForewordText(String forewordText) {
        this.forewordText = forewordText == null ? null : forewordText.trim();
    }

    public String getEpilogueText() {
        return epilogueText;
    }

    public void setEpilogueText(String epilogueText) {
        this.epilogueText = epilogueText == null ? null : epilogueText.trim();
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
        sb.append(", paperId=").append(paperId);
        sb.append(", paperName=").append(paperName);
        sb.append(", questionNum=").append(questionNum);
        sb.append(", recordNum=").append(recordNum);
        sb.append(", status=").append(status);
        sb.append(", sort=").append(sort);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", source=").append(source);
        sb.append(", smallProgramCode=").append(smallProgramCode);
        sb.append(", isIssueCoupon=").append(isIssueCoupon);
        sb.append(", couponNo=").append(couponNo);
        sb.append(", isDirected=").append(isDirected);
        sb.append(", isExportDely=").append(isExportDely);
        sb.append(", forewordText=").append(forewordText);
        sb.append(", epilogueText=").append(epilogueText);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}