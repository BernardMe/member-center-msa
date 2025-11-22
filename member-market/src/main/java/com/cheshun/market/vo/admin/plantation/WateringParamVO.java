package com.cheshun.market.vo.admin.plantation;

/**
 * 用户浇水传参VO
 * @author wangzhuo
 * @date 20240728
 */
public class WateringParamVO {

    private Integer plantId;

    private Integer waterTimes;

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public Integer getWaterTimes() {
        return waterTimes;
    }

    public void setWaterTimes(Integer waterTimes) {
        this.waterTimes = waterTimes;
    }
}
