package com.cheshun.po.user.plantation.plant;

/**
 * HerbalStage信息PO
 * @author wangzhuo
 * @date 20240728
 */
public class HerbalStagePO {

    private Integer stageUpId;

    private String stageUpName;

    private Short dripCapacity;

    private String stageUpImage;

    private Integer stageSort;

    private Integer stageUpRewardId;


    public Integer getStageUpId() {
        return stageUpId;
    }

    public void setStageUpId(Integer stageUpId) {
        this.stageUpId = stageUpId;
    }

    public String getStageUpName() {
        return stageUpName;
    }

    public void setStageUpName(String stageUpName) {
        this.stageUpName = stageUpName;
    }

    public Short getDripCapacity() {
        return dripCapacity;
    }

    public void setDripCapacity(Short dripCapacity) {
        this.dripCapacity = dripCapacity;
    }

    public String getStageUpImage() {
        return stageUpImage;
    }

    public void setStageUpImage(String stageUpImage) {
        this.stageUpImage = stageUpImage;
    }

    public Integer getStageSort() {
        return stageSort;
    }

    public void setStageSort(Integer stageSort) {
        this.stageSort = stageSort;
    }

    public Integer getStageUpRewardId() {
        return stageUpRewardId;
    }

    public void setStageUpRewardId(Integer stageUpRewardId) {
        this.stageUpRewardId = stageUpRewardId;
    }
}
