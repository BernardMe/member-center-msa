package com.cheshun.market.vo.admin.plantation;

import com.zzjdyf.po.user.plantation.plant.PlantationStageWordsPO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: 中药材植株阶段话语VO类
 * @author: wangzhuo
 * @create: 20241216
 **/
@ApiModel(description = "种植阶段话语信息")
@Data
public class PlantationStageWordsVO {

    @ApiModelProperty(value = "阶段编号")
    private Integer stageId;

    @ApiModelProperty(value = "话语列表数据")
    private List<PlantationStageWordsPO> wordsList;
}
