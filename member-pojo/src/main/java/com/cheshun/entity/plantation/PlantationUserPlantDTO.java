package com.cheshun.entity.plantation;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PlantationUserPlantDTO implements Serializable {
    /**
     * 植株id
     */
    private Integer plantId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 会员openid
     */
    private String openId;
    /**
     * 用户手机号
     */
    private String userPhone;
    /**
     * 中药材类型编号
     */
    private Integer herbId;
    /**
     * 中药材类型名称
     */
    private String herbName;
    /**
     * 当前成长阶段编号
     */
    private Integer currStage;
    /**
     * 该植株总浇水克数
     */
    private Short plantDripNum;
    /**
     * 当前阶段已浇水克数
     */
    private Short usedDripNum;
    /**
     * 植株状态(1:种植中,2:已收获)
     */
    private Short plantState;
    /**
     * 植株创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
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

    public Integer getHerbId() {
        return herbId;
    }

    public void setHerbId(Integer herbId) {
        this.herbId = herbId;
    }

    public String getHerbName() {
        return herbName;
    }

    public void setHerbName(String herbName) {
        this.herbName = herbName == null ? null : herbName.trim();
    }

    public Integer getCurrStage() {
        return currStage;
    }

    public void setCurrStage(Integer currStage) {
        this.currStage = currStage;
    }

    public Short getPlantDripNum() {
        return plantDripNum;
    }

    public void setPlantDripNum(Short plantDripNum) {
        this.plantDripNum = plantDripNum;
    }

    public Short getUsedDripNum() {
        return usedDripNum;
    }

    public void setUsedDripNum(Short usedDripNum) {
        this.usedDripNum = usedDripNum;
    }

    public Short getPlantState() {
        return plantState;
    }

    public void setPlantState(Short plantState) {
        this.plantState = plantState;
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
        sb.append(", plantId=").append(plantId);
        sb.append(", userId=").append(userId);
        sb.append(", openId=").append(openId);
        sb.append(", userPhone=").append(userPhone);
        sb.append(", herbId=").append(herbId);
        sb.append(", herbName=").append(herbName);
        sb.append(", currStage=").append(currStage);
        sb.append(", plantDripNum=").append(plantDripNum);
        sb.append(", usedDripNum=").append(usedDripNum);
        sb.append(", plantState=").append(plantState);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}