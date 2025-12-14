package com.cheshun.vo.activity.turntable;

import com.cheshun.entity.activity.TurntableStoreList;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminTurnTableDetailResponseVO {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Integer id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 类型(默认、积分、消费记录、最近消费)
     */
    @ApiModelProperty(value = "类型(默认、积分、消费记录、最近消费)")
    private String type;
    /**
     * 海报图片
     */
    @ApiModelProperty(value = "海报图片")
    private String pictureUrl;
    /**
     * 是否限制门店
     */
    @ApiModelProperty(value = "是否限制门店")
    private String limStore;
    /**
     * 满足值
     */
    @ApiModelProperty(value = "满足值")
    private Integer limitValue;
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
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;
    /**
     * 自动开关时间
     */
    @ApiModelProperty(value = "自动开关时间")
    private List<Integer> weekDaysList;
    /**
     * 奖品列表
     */
    @ApiModelProperty(value = "奖品列表")
    private List<TurnTablePrizeResponseVO> prizeList;
    /**
     * 指定门店编号列表
     */
    @ApiModelProperty(value = "指定门店编号列表")
    private List<TurntableStoreList> limitStoreList;

    /**
     * 消费开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "消费开始时间")
    private LocalDateTime costSta;
    /**
     * 消费结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "消费结束时间")
    private LocalDateTime costEnd;
    /**
     * 活动开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "活动开始时间")
    private LocalDateTime actStaTime;
    /**
     * 活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "活动结束时间")
    private LocalDateTime actEndTime;

    @ApiModelProperty(value = "限制时间类型")
    private String timeType;
}
