package com.cheshun.market.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode()
@Data
@ToString(callSuper = true)
@ApiModel("待补货详情信息")
public class CanReplenishAppDto {
    /**
     * 订货申请的规格数量
     */
    @ApiModelProperty("订货申请的规格数量")
    private Integer skuQuantity;
    /**
     * 未激活卡数量
     */
    @ApiModelProperty("未激活卡数量")
    private Integer noActiveQuantity;
    /**
     * 已激活卡数量
     */
    @ApiModelProperty("已激活卡数量")
    private Integer activatedQuantity;
    /**
     * 累计卡数量
     */
    @ApiModelProperty("累计卡数量")
    private Integer totalQuantity;
    /**
     * 提标补货申请的设备规格
     */
    @ApiModelProperty("提标补货申请的设备规格")
    private Integer auditSkuQuantity;
    /**
     * 可补货的卡数量
     */
    @ApiModelProperty("可补货的卡数量")
    private Integer replenishQuantity;
    /**
     * 未激活OBU数量
     */
    @ApiModelProperty("未激活OBU数量")
    private Integer noActiveObuQuantity;
    /**
     * 已激活OBU数量
     */
    @ApiModelProperty("已激活OBU数量")
    private Integer activatedObuQuantity;
    /**
     * 累计OBU数量
     */
    @ApiModelProperty("累计OBU数量")
    private Integer totalObuQuantity;
    /**
     * 可补货的OBU数量
     */
    @ApiModelProperty("可补货的OBU数量")
    private Integer replenishObuQuantity;
    /**
     * 是否可补货
     */
    @ApiModelProperty("是否可补货")
    private Boolean canReplenish;
}
