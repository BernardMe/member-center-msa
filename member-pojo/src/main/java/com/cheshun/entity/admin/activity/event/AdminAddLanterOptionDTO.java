package com.cheshun.entity.admin.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "管理员添加花灯配置信息参数")
public class AdminAddLanterOptionDTO {

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "主题")
    private String topic;

    @ApiModelProperty(value = "一次游戏获取最大积分数量")
    private Integer maxPoint;

    @ApiModelProperty(value = "游戏时间")
    private Integer maxTime;

    @ApiModelProperty(value = "是否启用")
    private String isEnable;

    @ApiModelProperty(value = "图片链接")
    private String imagesUrl;

    @ApiModelProperty(value = "方案编号")
    private String fnumber;

}
