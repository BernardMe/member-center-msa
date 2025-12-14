package com.cheshun.entity.coupon;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CouponBaseInfo implements Serializable {
    private Integer id;

    private String couponCode;

    private String couponName;

    private String couponDescription;

    private String couponRules;

    private String createrCode;

    private String createrName;

    private LocalDateTime createTime;

    private String couponTypeCode;

    private String couponTypeName;

    private LocalDateTime issueBeginTime;

    private LocalDateTime issueEndTime;

    private LocalDateTime useBeginTime;

    private LocalDateTime useEndTime;

    private String useTimeTypeCode;

    private String useTimeTypeName;

    private String timePeriodUnit;

    private Integer timePeriodLength;

    private Double conditionNum;

    private String conditionTypeCode;

    private String conditionTypeName;

    private Double discountsAmount;

    private String allStoreTag;

    private String allGoodsTag;

    private Integer issueNumLimit;

    private Integer maxIssueNum;

    private String showTag;

    private String deleteTag;

    private String couponPoolCode;

    private String couponPoolName;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode == null ? null : couponCode.trim();
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public String getCouponDescription() {
        return couponDescription;
    }

    public void setCouponDescription(String couponDescription) {
        this.couponDescription = couponDescription == null ? null : couponDescription.trim();
    }

    public String getCouponRules() {
        return couponRules;
    }

    public void setCouponRules(String couponRules) {
        this.couponRules = couponRules == null ? null : couponRules.trim();
    }

    public String getCreaterCode() {
        return createrCode;
    }

    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCouponTypeCode() {
        return couponTypeCode;
    }

    public void setCouponTypeCode(String couponTypeCode) {
        this.couponTypeCode = couponTypeCode == null ? null : couponTypeCode.trim();
    }

    public String getCouponTypeName() {
        return couponTypeName;
    }

    public void setCouponTypeName(String couponTypeName) {
        this.couponTypeName = couponTypeName == null ? null : couponTypeName.trim();
    }

    public LocalDateTime getIssueBeginTime() {
        return issueBeginTime;
    }

    public void setIssueBeginTime(LocalDateTime issueBeginTime) {
        this.issueBeginTime = issueBeginTime;
    }

    public LocalDateTime getIssueEndTime() {
        return issueEndTime;
    }

    public void setIssueEndTime(LocalDateTime issueEndTime) {
        this.issueEndTime = issueEndTime;
    }

    public LocalDateTime getUseBeginTime() {
        return useBeginTime;
    }

    public void setUseBeginTime(LocalDateTime useBeginTime) {
        this.useBeginTime = useBeginTime;
    }

    public LocalDateTime getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(LocalDateTime useEndTime) {
        this.useEndTime = useEndTime;
    }

    public String getUseTimeTypeCode() {
        return useTimeTypeCode;
    }

    public void setUseTimeTypeCode(String useTimeTypeCode) {
        this.useTimeTypeCode = useTimeTypeCode == null ? null : useTimeTypeCode.trim();
    }

    public String getUseTimeTypeName() {
        return useTimeTypeName;
    }

    public void setUseTimeTypeName(String useTimeTypeName) {
        this.useTimeTypeName = useTimeTypeName == null ? null : useTimeTypeName.trim();
    }

    public String getTimePeriodUnit() {
        return timePeriodUnit;
    }

    public void setTimePeriodUnit(String timePeriodUnit) {
        this.timePeriodUnit = timePeriodUnit == null ? null : timePeriodUnit.trim();
    }

    public Integer getTimePeriodLength() {
        return timePeriodLength;
    }

    public void setTimePeriodLength(Integer timePeriodLength) {
        this.timePeriodLength = timePeriodLength;
    }

    public Double getConditionNum() {
        return conditionNum;
    }

    public void setConditionNum(Double conditionNum) {
        this.conditionNum = conditionNum;
    }

    public String getConditionTypeCode() {
        return conditionTypeCode;
    }

    public void setConditionTypeCode(String conditionTypeCode) {
        this.conditionTypeCode = conditionTypeCode == null ? null : conditionTypeCode.trim();
    }

    public String getConditionTypeName() {
        return conditionTypeName;
    }

    public void setConditionTypeName(String conditionTypeName) {
        this.conditionTypeName = conditionTypeName == null ? null : conditionTypeName.trim();
    }

    public Double getDiscountsAmount() {
        return discountsAmount;
    }

    public void setDiscountsAmount(Double discountsAmount) {
        this.discountsAmount = discountsAmount;
    }

    public String getAllStoreTag() {
        return allStoreTag;
    }

    public void setAllStoreTag(String allStoreTag) {
        this.allStoreTag = allStoreTag == null ? null : allStoreTag.trim();
    }

    public String getAllGoodsTag() {
        return allGoodsTag;
    }

    public void setAllGoodsTag(String allGoodsTag) {
        this.allGoodsTag = allGoodsTag == null ? null : allGoodsTag.trim();
    }

    public Integer getIssueNumLimit() {
        return issueNumLimit;
    }

    public void setIssueNumLimit(Integer issueNumLimit) {
        this.issueNumLimit = issueNumLimit;
    }

    public Integer getMaxIssueNum() {
        return maxIssueNum;
    }

    public void setMaxIssueNum(Integer maxIssueNum) {
        this.maxIssueNum = maxIssueNum;
    }

    public String getShowTag() {
        return showTag;
    }

    public void setShowTag(String showTag) {
        this.showTag = showTag == null ? null : showTag.trim();
    }

    public String getDeleteTag() {
        return deleteTag;
    }

    public void setDeleteTag(String deleteTag) {
        this.deleteTag = deleteTag == null ? null : deleteTag.trim();
    }

    public String getCouponPoolCode() {
        return couponPoolCode;
    }

    public void setCouponPoolCode(String couponPoolCode) {
        this.couponPoolCode = couponPoolCode == null ? null : couponPoolCode.trim();
    }

    public String getCouponPoolName() {
        return couponPoolName;
    }

    public void setCouponPoolName(String couponPoolName) {
        this.couponPoolName = couponPoolName == null ? null : couponPoolName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", couponCode=").append(couponCode);
        sb.append(", couponName=").append(couponName);
        sb.append(", couponDescription=").append(couponDescription);
        sb.append(", couponRules=").append(couponRules);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", couponTypeCode=").append(couponTypeCode);
        sb.append(", couponTypeName=").append(couponTypeName);
        sb.append(", issueBeginTime=").append(issueBeginTime);
        sb.append(", issueEndTime=").append(issueEndTime);
        sb.append(", useBeginTime=").append(useBeginTime);
        sb.append(", useEndTime=").append(useEndTime);
        sb.append(", useTimeTypeCode=").append(useTimeTypeCode);
        sb.append(", useTimeTypeName=").append(useTimeTypeName);
        sb.append(", timePeriodUnit=").append(timePeriodUnit);
        sb.append(", timePeriodLength=").append(timePeriodLength);
        sb.append(", conditionNum=").append(conditionNum);
        sb.append(", conditionTypeCode=").append(conditionTypeCode);
        sb.append(", conditionTypeName=").append(conditionTypeName);
        sb.append(", discountsAmount=").append(discountsAmount);
        sb.append(", allStoreTag=").append(allStoreTag);
        sb.append(", allGoodsTag=").append(allGoodsTag);
        sb.append(", issueNumLimit=").append(issueNumLimit);
        sb.append(", maxIssueNum=").append(maxIssueNum);
        sb.append(", showTag=").append(showTag);
        sb.append(", deleteTag=").append(deleteTag);
        sb.append(", couponPoolCode=").append(couponPoolCode);
        sb.append(", couponPoolName=").append(couponPoolName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}