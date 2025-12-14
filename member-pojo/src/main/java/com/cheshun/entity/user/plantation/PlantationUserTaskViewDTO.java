package com.cheshun.entity.user.plantation;



import com.cheshun.entity.plantation.PlantationLinkDTO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户任务信息ViewDTO
 * @author wangzhuo
 * @date 20240826
 */
public class PlantationUserTaskViewDTO implements Serializable {
    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 任务类型 1-一次性任务 2-多次可完成任务
     */
    private Byte taskType;

    /**
     * 任务分组类型 1-下单 2-浏览 3-领取券 4-积分兑换
     */
    private Byte taskClass;

    /**
     * 任务分组类型名称
     */
    private String taskClassName;

    /**
     * 发放方式 1=单次发放，2=完成发放
     */
    private Byte issueType;

    /**
     * 任务提示
     */
    private String taskTips;

    /**
     * 完成所需次数
     */
    private Byte timeDripNumber;

    /**
     * 奖励水滴数
     */
    private Short awardDrip;

    /**
     * 图标url
     */
    private String iconUrl;

    /**
     * 是否显示图标
     */
    private Byte showIcon;

    /**
     * 任务名称
     */
    private String showTaskName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 启用状态(1:已启用, 0:未启用)
     */
    private Byte status;

    /**
     * 是否已删除(1:是, 0:否)
     */
    private Byte isDelete;

    /**
     * 是否快捷方式(1:是, 0:否)
     */
    private Byte isShortcut;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 完成的次数
     */
    private Byte completeNum;
    /**
     * linkId
     */
    private Integer linkId;
    /**
     * 链接信息
     */
    private PlantationLinkDTO link;
    /**
     * userTaskId
     */
    private Integer userTaskId;
    /**
     * 状态 0=待完成，1=部分完成，2=已完成未领取，3=已完成已领取
     */
    private Byte userTaskStatus;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    public String getTaskClassName() {
        return taskClassName;
    }

    public void setTaskClassName(String taskClassName) {
        this.taskClassName = taskClassName;
    }

    public Byte getIssueType() {
        return issueType;
    }

    public void setIssueType(Byte issueType) {
        this.issueType = issueType;
    }

    public String getTaskTips() {
        return taskTips;
    }

    public void setTaskTips(String taskTips) {
        this.taskTips = taskTips;
    }

    public Byte getTimeDripNumber() {
        return timeDripNumber;
    }

    public void setTimeDripNumber(Byte timeDripNumber) {
        this.timeDripNumber = timeDripNumber;
    }

    public Short getAwardDrip() {
        return awardDrip;
    }

    public void setAwardDrip(Short awardDrip) {
        this.awardDrip = awardDrip;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Byte getShowIcon() {
        return showIcon;
    }

    public void setShowIcon(Byte showIcon) {
        this.showIcon = showIcon;
    }

    public String getShowTaskName() {
        return showTaskName;
    }

    public void setShowTaskName(String showTaskName) {
        this.showTaskName = showTaskName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Byte getIsShortcut() {
        return isShortcut;
    }

    public void setIsShortcut(Byte isShortcut) {
        this.isShortcut = isShortcut;
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

    public Byte getCompleteNum() {
        return completeNum;
    }

    public void setCompleteNum(Byte completeNum) {
        this.completeNum = completeNum;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public PlantationLinkDTO getLink() {
        return link;
    }

    public void setLink(PlantationLinkDTO link) {
        this.link = link;
    }

    public Integer getUserTaskId() {
        return userTaskId;
    }

    public void setUserTaskId(Integer userTaskId) {
        this.userTaskId = userTaskId;
    }

    public Byte getUserTaskStatus() {
        return userTaskStatus;
    }

    public void setUserTaskStatus(Byte userTaskStatus) {
        this.userTaskStatus = userTaskStatus;
    }
}
