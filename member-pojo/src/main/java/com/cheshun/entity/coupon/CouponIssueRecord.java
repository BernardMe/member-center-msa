package com.cheshun.entity.coupon;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CouponIssueRecord implements Serializable {
    private Integer id;

    private Integer couponId;

    private String couponName;

    private String vipCardCode;

    private LocalDateTime issueTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public String getVipCardCode() {
        return vipCardCode;
    }

    public void setVipCardCode(String vipCardCode) {
        this.vipCardCode = vipCardCode == null ? null : vipCardCode.trim();
    }

    public LocalDateTime getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(LocalDateTime issueTime) {
        this.issueTime = issueTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", couponId=").append(couponId);
        sb.append(", couponName=").append(couponName);
        sb.append(", vipCardCode=").append(vipCardCode);
        sb.append(", issueTime=").append(issueTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}