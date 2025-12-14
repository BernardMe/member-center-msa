package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户查看转盘-奖品列表-返回信息")
public class VipTurnTablePrizeInfoResponseVO {
    @ApiModelProperty(value = "奖品ID")
    private Integer prizeId;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "奖品图片")
    private String prizeImgUrl;

    @ApiModelProperty(value = "奖品类型")
    private String prizeType;
}
