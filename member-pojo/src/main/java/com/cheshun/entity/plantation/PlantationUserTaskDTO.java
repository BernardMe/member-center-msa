package com.cheshun.entity.plantation;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PlantationUserTaskDTO implements Serializable {

    /**
     * 用户任务id
     */
    private Integer userTaskId;
    /**
     * 任务id
     */
    private Integer taskId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 微信openId
     */
    private String openId;
    /**
     * 会员卡号
     */
    private String memberCardNo;
    /**
     * 任务提示
     */
    private String taskTips;
    /**
     * 任务类型 1-一次性任务 2-多次可完成任务
     */
    private Byte taskType;
    /**
     * 任务分组类型 1-下单 2-浏览 3-领取券 4-积分兑换 5-问卷
     */
    private Byte taskClass;
    /**
     * 状态 0=待完成，1=部分完成，2=已完成未领取，3=已完成已领取
     */
    private Byte status;
    /**
     * 发放方式 1=单次发放，2=完成发放
     */
    private Byte issueType;
    /**
     * 完成所需次数
     */
    private Byte timeDripNumber;
    /**
     * 已完成次数
     */
    private Byte completeNum;
    /**
     * 奖励水滴数
     */
    private Byte awardDrip;
    /**
     * 是否已删除(1:是, 0:否)
     */
    private Byte isDelete;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(Integer userTaskId) {
        this.userTaskId = userTaskId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public String getTaskTips() {
        return taskTips;
    }

    public void setTaskTips(String taskTips) {
        this.taskTips = taskTips == null ? null : taskTips.trim();
    }

    public Byte getTaskType() {
        return taskType;
    }

    public void setTaskType(Byte taskType) {
        this.taskType = taskType;
    }

    public Byte getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(Byte taskClass) {
        this.taskClass = taskClass;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIssueType() {
        return issueType;
    }

    public void setIssueType(Byte issueType) {
        this.issueType = issueType;
    }

    public Byte getTimeDripNumber() {
        return timeDripNumber;
    }

    public void setTimeDripNumber(Byte timeDripNumber) {
        this.timeDripNumber = timeDripNumber;
    }

    public Byte getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Byte completeNum) {
        this.completeNum = completeNum;
    }

    public Byte getAwardDrip() {
        return awardDrip;
    }

    public void setAwardDrip(Byte awardDrip) {
        this.awardDrip = awardDrip;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
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
        sb.append(", userTaskId=").append(userTaskId);
        sb.append(", taskId=").append(taskId);
        sb.append(", userId=").append(userId);
        sb.append(", openId=").append(openId);
        sb.append(", taskTips=").append(taskTips);
        sb.append(", taskType=").append(taskType);
        sb.append(", taskClass=").append(taskClass);
        sb.append(", status=").append(status);
        sb.append(", issueType=").append(issueType);
        sb.append(", timeDripNumber=").append(timeDripNumber);
        sb.append(", completeNum=").append(completeNum);
        sb.append(", awardDrip=").append(awardDrip);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}