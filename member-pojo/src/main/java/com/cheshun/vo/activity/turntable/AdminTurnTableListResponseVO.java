package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTurnTableListResponseVO {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;
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
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 开启状态
     */
    @ApiModelProperty(value = "当前状态（总状态不可改）")
    private String nowStatus;
    /**
     * 手动开关
     */
    @ApiModelProperty(value = "手动开关（子状态可修改）")
    private String manualSwitch;
    /**
     * 是否限制门店
     */
    @ApiModelProperty(value = "是否限制门店")
    private String limStore;
    /**
     * 奖品数量
     */
    @ApiModelProperty(value = "当前奖品数量")
    private Integer nowPrizeNum;
}
