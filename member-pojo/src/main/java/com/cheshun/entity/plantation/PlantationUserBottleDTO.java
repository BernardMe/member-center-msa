package com.cheshun.entity.plantation;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PlantationUserBottleDTO implements Serializable {
    private Integer bottleId;

    private String userId;

    private String openId;

    private String userPhone;

    private Short bottleDripTotal;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getBottleId() {
        return bottleId;
    }

    public void setBottleId(Integer bottleId) {
        this.bottleId = bottleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Short getBottleDripTotal() {
        return bottleDripTotal;
    }

    public void setBottleDripTotal(Short bottleDripTotal) {
        this.bottleDripTotal = bottleDripTotal;
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
        sb.append(", bottleId=").append(bottleId);
        sb.append(", userId=").append(userId);
        sb.append(", openId=").append(openId);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", bottleDripTotal=").append(bottleDripTotal);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}