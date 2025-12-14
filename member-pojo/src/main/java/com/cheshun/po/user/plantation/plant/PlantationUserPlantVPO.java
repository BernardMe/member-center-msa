package com.cheshun.po.user.plantation.plant;

import com.cheshun.entity.plantation.PlantationUserPlantDTO;
import java.io.Serializable;

/**
 * 用户植株信息VPO
 * @author wangzhuo
 * @date 20240728
 */
public class PlantationUserPlantVPO extends PlantationUserPlantDTO implements Serializable {
    private Boolean achieved;
    /**
     * 阶段名称
     */
    private String stageName;

    public PlantationUserPlantVPO() {
    }

    public Boolean getAchieved() {
        return this.achieved;
    }

    public void setAchieved(Boolean achieved) {
        this.achieved = achieved;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public void refreshAchieved(Integer continueDays, long daysDiff) {
        if (continueDays < 7) {
            this.setAchieved(Boolean.FALSE);
        } else if (continueDays == 7) {
            this.setAchieved(Boolean.TRUE);
        }

    }
}
