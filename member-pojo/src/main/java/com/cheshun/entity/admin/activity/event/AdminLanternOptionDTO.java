package com.cheshun.entity.admin.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(description = "管理员查看花灯配置信息参数")
public class AdminLanternOptionDTO {

//    @ApiModelProperty(value = "花灯配置id")
//    private Integer id;
//
    @ApiModelProperty(value = "主题")
    private String topic;

//    @ApiModelProperty(value = "一次游戏获取最大积分数量")
//    private Integer maxPoint;

//    @ApiModelProperty(value = "游戏时间")
//    private Integer max_time;

    @ApiModelProperty(value = "创建时间")
    private String createtime;

    @ApiModelProperty(value = "修改时间")
    private String modifytime;

    @ApiModelProperty(value = "是否启用")
    private Integer isEnable;

    @ApiModelProperty(value = "页码")
    private Integer page;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;
}
