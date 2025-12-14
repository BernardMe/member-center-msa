package com.cheshun.po.user.plantation.plant;

import com.cheshun.entity.plantation.PlantationStageWordsDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户任务参数VO
 * @Author wangzhuo
 * @Date 20241216
 **/
@Data
public class PlantationStageWordsPO extends PlantationStageWordsDTO implements Serializable {

    /**
     * 展示话语所需当前阶段已浇水次数
     */
    private Integer waterTimes;

}
