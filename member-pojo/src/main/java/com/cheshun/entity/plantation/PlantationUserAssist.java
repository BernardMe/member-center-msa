package com.cheshun.entity.plantation;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PlantationUserAssist implements Serializable {
    private Integer assistId;

    private String invitationId;

    private String userId;

    private String openId;

    private String userPhone;

    private String inviterUserId;

    private Short assistCount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getAssistId() {
        return assistId;
    }

    public void setAssistId(Integer assistId) {
        this.assistId = assistId;
    }

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId == null ? null : invitationId.trim();
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

    public String getInviterUserId() {
        return inviterUserId;
    }

    public void setInviterUserId(String inviterUserId) {
        this.inviterUserId = inviterUserId == null ? null : inviterUserId.trim();
    }

    public Short getAssistCount() {
        return assistCount;
    }

    public void setAssistCount(Short assistCount) {
        this.assistCount = assistCount;
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
        sb.append(", assistId=").append(assistId);
        sb.append(", invitationId=").append(invitationId);
        sb.append(", userId=").append(userId);
        sb.append(", openId=").append(openId);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", inviterUserId=").append(inviterUserId);
        sb.append(", assistCount=").append(assistCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}