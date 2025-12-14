package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "查看抽奖记录-返回信息")
public class VipTurnTableRecordResponseVO {
    @ApiModelProperty(value = "记录ID")
    private Integer recordId;

    @ApiModelProperty(value = "奖品ID")
    private Integer prizeId;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "奖品类型")
    private String prizeType;

    @ApiModelProperty(value = "奖品图片")
    private String prizeImgUrl;

    @ApiModelProperty(value = "完结标记")
    private String finishTag;

    @ApiModelProperty(value = "中奖时间")
    private LocalDateTime createTime;
}
