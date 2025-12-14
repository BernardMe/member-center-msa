package com.cheshun.entity.plantation;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 任务明细DTO
 * @author wangzhuo
 * @date 20240826
 */
public class PlantationTaskLogDTO implements Serializable {
    /**
     * 任务明细id
     */
    private Integer taskLogId;
    /**
     * 任务id
     */
    private Integer taskId;
    /**
     * 用户任务id
     */
    private Integer userTaskId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 会员微信openId
     */
    private String openId;
    /**
     * 奖励水滴数
     */
    private Byte awardDrip;
    /**
     * 订单编号
     */
    private String billCode;
    /**
     * 任务链接id
     */
    private Integer linkId;
    /**
     * 券主键
     */
    private Integer ticketId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getTaskLogId() {
        return taskLogId;
    }

    public void setTaskLogId(Integer taskLogId) {
        this.taskLogId = taskLogId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(Integer userTaskId) {
        this.userTaskId = userTaskId;
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

    public Byte getAwardDrip() {
        return awardDrip;
    }

    public void setAwardDrip(Byte awardDrip) {
        this.awardDrip = awardDrip;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode == null ? null : billCode.trim();
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
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
        sb.append(", taskLogId=").append(taskLogId);
        sb.append(", taskId=").append(taskId);
        sb.append(", userTaskId=").append(userTaskId);
        sb.append(", userId=").append(userId);
        sb.append(", openId=").append(openId);
        sb.append(", awardDrip=").append(awardDrip);
        sb.append(", billCode=").append(billCode);
        sb.append(", linkId=").append(linkId);
        sb.append(", ticketId=").append(ticketId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}