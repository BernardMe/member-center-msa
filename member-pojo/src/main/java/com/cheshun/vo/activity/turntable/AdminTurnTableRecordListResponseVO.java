package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTurnTableRecordListResponseVO {
    @ApiModelProperty(value = "记录id（不显示）")
    private Integer recordId;

    @ApiModelProperty(value = "转盘id（不显示）")
    private Integer turntableId;

    @ApiModelProperty(value = "转盘名称")
    private String turnTableName;

    @ApiModelProperty(value = "用户卡号")
    private String userCardNo;

    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "奖品id（不显示）")
    private Integer prizeId;

    @ApiModelProperty(value = "奖品名称")
    private String prizeName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
