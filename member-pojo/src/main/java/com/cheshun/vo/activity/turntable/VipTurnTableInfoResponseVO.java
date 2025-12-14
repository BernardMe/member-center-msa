package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "用户查看转盘-返回信息")
public class VipTurnTableInfoResponseVO {
    @ApiModelProperty(value = "转盘ID")
    private Integer turntableId;

    @ApiModelProperty(value = "海报图片")
    private String imgUrl;

    @ApiModelProperty(value = "含有次数")
    private Integer hasTimes;

    @ApiModelProperty(value = "最大次数")
    private Integer maxTimes;

    @ApiModelProperty(value = "奖品数量")
    private Integer prizeNum;

    @ApiModelProperty(value = "使用备用次数标记")
    private String isTimesTag;

    @ApiModelProperty(value = "奖品信息")
    private List<VipTurnTablePrizeInfoResponseVO> prizeInfoList;
}
