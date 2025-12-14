package com.cheshun.po.user.plantation.plant;

import java.util.List;

/**
 * 用户浇水完成信息PO
 * @author wangzhuo
 * @date 20240728
 */
public class WaterStageInfoPO {

    private Integer plantId;

    private String herbName;

    private Boolean upgrade;

    private Boolean upGift;

    private String giftDetail;

    private Boolean wateringTerminated;

    private List<HerbalStagePO> herbalStages;

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public String getHerbName() {
        return herbName;
    }

    public void setHerbName(String herbName) {
        this.herbName = herbName;
    }

    public Boolean getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Boolean upgrade) {
        this.upgrade = upgrade;
    }

    public Boolean getUpGift() {
        return upGift;
    }

    public void setUpGift(Boolean upGift) {
        this.upGift = upGift;
    }

    public String getGiftDetail() {
        return giftDetail;
    }

    public void setGiftDetail(String giftDetail) {
        this.giftDetail = giftDetail;
    }

    public Boolean getWateringTerminated() {
        return wateringTerminated;
    }

    public void setWateringTerminated(Boolean wateringTerminated) {
        this.wateringTerminated = wateringTerminated;
    }

    public List<HerbalStagePO> getHerbalStages() {
        return herbalStages;
    }

    public void setHerbalStages(List<HerbalStagePO> herbalStages) {
        this.herbalStages = herbalStages;
    }
}
