package com.cheshun.entity.plantation;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PlantationStageWordsDTO implements Serializable {
    /**
     * 话语编号
     */
    private Integer wordsId;
    /**
     * 阶段编号
     */
    private Integer stageId;
    /**
     * 展示话语所需当前阶段已浇水克数
     */
    private Short usedDripNum;
    /**
     * 启用状态(1:已启用, 0:未启用)
     */
    private Byte status;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 话语文本
     */
    private String wordsText;
    /**
     * 话语序号
     */
    private Integer wordsSort;

    private static final long serialVersionUID = 1L;

    public Integer getWordsId() {
        return wordsId;
    }

    public void setWordsId(Integer wordsId) {
        this.wordsId = wordsId;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Short getUsedDripNum() {
        return usedDripNum;
    }

    public void setUsedDripNum(Short usedDripNum) {
        this.usedDripNum = usedDripNum;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public String getWordsText() {
        return wordsText;
    }

    public void setWordsText(String wordsText) {
        this.wordsText = wordsText == null ? null : wordsText.trim();
    }

    public Integer getWordsSort() {
        return wordsSort;
    }

    public void setWordsSort(Integer wordsSort) {
        this.wordsSort = wordsSort;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wordsId=").append(wordsId);
        sb.append(", stageId=").append(stageId);
        sb.append(", usedDripNum=").append(usedDripNum);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", wordsText=").append(wordsText);
        sb.append(", wordsSort=").append(wordsSort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}