package com.cheshun.vo.activity.turntable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewAndUploadVO {


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
     * 是否限制门店
     */
    @ApiModelProperty(value = "是否限制门店")
    private String limStore;
    /**
     * 周几
     */
    @ApiModelProperty(value = "周几")
    private List<Integer> weekDaysList;
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

    @ApiModelProperty(value = "奖品列表")
    private List<PVO> prizeList;

    @ApiModelProperty(value = "限制时间类型")
    private String timeType;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PVO {
        /**
         * 名称
         */
        private String name;
        /**
         * 奖品类型（优惠券 虚拟商品）
         */
        private String type;
        /**
         * 奖品编号（虚拟商品无编号，优惠券为优惠券编码，便于后续发放）
         */
        private String code;
        /**
         * 中奖概率
         */
        private Integer probability;
        /**
         * 奖品图片
         */
        private String pictureUrl;
        /**
         * 库存
         */
        private Integer stock;
        /**
         * 优先级
         */
        private Integer sort;
        /**
         * 跳转链接
         */
        private String jumpUrl;
    }
}
