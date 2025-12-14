package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminNewTurntableRequestVO {
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 类型(默认、积分、消费记录)
     */
    @ApiModelProperty(value = "类型(默认、积分、消费记录)")
    private String type;
    /**
     * 海报图片
     */
    @ApiModelProperty(value = "海报图片")
    private String pictureUrl;
    /**
     * 奖品数量
     */
    @ApiModelProperty(value = "奖品数量")
    private Integer prizeNum;
    /**
     * 次数上限
     */
    @ApiModelProperty(value = "次数上限")
    private Integer maxNum;
    /**
     * 限制值
     */
    @ApiModelProperty(value = "限制值")
    private Integer limitValue;
    /**
     * x天抽y次 x
     */
    @ApiModelProperty(value = "x天抽y次 x")
    private Integer limitDays;
    /**
     * x天抽y次 y
     */
    @ApiModelProperty(value = "x天抽y次 y")
    private Integer limitNum;
    /**
     * 周几
     */
    @ApiModelProperty(value = "周几")
    private List<Integer> weekDaysList;
    /**
     * 是否限制门店
     */
    @ApiModelProperty(value = "是否限制门店")
    private String limStore;
    /**
     * 消费开始时间
     */
    @ApiModelProperty(value = "消费开始时间")
    private String costSta;
    /**
     * 消费结束时间
     */
    @ApiModelProperty(value = "消费结束时间")
    private String costEnd;
    /**
     * 活动开始时间
     */
    @ApiModelProperty(value = "活动开始时间")
    private String actStaTime;
    /**
     * 活动结束时间
     */
    @ApiModelProperty(value = "活动结束时间")
    private String actEndTime;

    @ApiModelProperty(value = "限制时间类型")
    private String timeType;
}
