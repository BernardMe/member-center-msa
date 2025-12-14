package com.cheshun.entity.plantation;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 水滴记录DTO
 * @author wangzhuo
 * @date 20240826
 */
public class PlantationDripRecordDTO implements Serializable {

    private Integer id;
    /**
     * 任务id
     */
    private Integer taskId;
    /**
     * 水滴克数(含正负)
     */
    private Short dripNum;
    /**
     * 增减标记（1 增加 2减少）
     */
    private Byte dripDirect;
    /**
     * 浇水行为名称
     */
    private String waterName;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 会员openid
     */
    private String openId;
    /**
     * 记录时间
     */
    private LocalDateTime recordDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Short getDripNum() {
        return dripNum;
    }

    public void setDripNum(Short dripNum) {
        this.dripNum = dripNum;
    }

    public Byte getDripDirect() {
        return dripDirect;
    }

    public void setDripDirect(Byte dripDirect) {
        this.dripDirect = dripDirect;
    }

    public String getWaterName() {
        return waterName;
    }

    public void setWaterName(String waterName) {
        this.waterName = waterName == null ? null : waterName.trim();
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

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskId=").append(taskId);
        sb.append(", dripNum=").append(dripNum);
        sb.append(", dripDirect=").append(dripDirect);
        sb.append(", waterName=").append(waterName);
        sb.append(", userId=").append(userId);
        sb.append(", openId=").append(openId);
        sb.append(", recordDate=").append(recordDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}