package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户转盘抽奖-返回信息")
public class VipPlayTurnTableInfoResponseVO {
    @ApiModelProperty(value = "中奖记录ID")
    private Integer recordId;

    @ApiModelProperty(value = "奖品ID")
    private Integer prizeId;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "奖品图片")
    private String prizeImgUrl;

    @ApiModelProperty(value = "奖品类型")
    private String prizeType;
}
