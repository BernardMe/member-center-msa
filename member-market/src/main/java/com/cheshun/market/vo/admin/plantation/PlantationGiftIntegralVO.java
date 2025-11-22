package com.cheshun.market.vo.admin.plantation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: new_shop
 * @description: 百草园礼品积分实体类
 * @author: clf
 * @create: 2024-07-22 17:12
 **/

@Data
@ApiModel(description = "礼品积分信息")
public class PlantationGiftIntegralVO {

    @ApiModelProperty("礼品积分主键")
    private Long integralId;

    @ApiModelProperty("礼品积分值")
    private Double integralValue;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标记（0 正常 1已删除）")
    private Byte deleteFlag;

    @ApiModelProperty("删除时间")
    private LocalDateTime deleteTime;

    @ApiModelProperty("创建人")
    private Integer createBy;

    @ApiModelProperty("更新人")
    private Integer updateBy;
}

