package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wangzhuo
 * Created on 20210727
 */
@Data
@ToString
@ApiModel("创建订货订单信息")
public class AddOrderCommand {
    /**
     * 商品id
     */
    @NotNull(message = "请选择商品")
    @ApiModelProperty("商品id")
    private Long goodsId;
    /**
     * 商品规格id
     */
    @NotNull(message = "请选择商品规格")
    @ApiModelProperty("商品规格id")
    private Long goodsSkuId;
    /**
     * 收货人
     */
    @NotNull(message = "请填写收货人")
    @Size(min = 1, max = 30, message = "收货人必填")
    @ApiModelProperty("收货人")
    private String consignee;
    /**
     * 收货人电话
     */
    @NotNull(message = "请填写收货人电话")
    @Size(min = 11, max = 11, message = "收货人电话必填")
    @ApiModelProperty("收货人电话")
    private String consigneePhone;
    /**
     * 地址
     */
    @NotNull(message = "请填写地址")
    @Size(min = 1, max = 100, message = "地址必填")
    @ApiModelProperty("地址")
    private String consigneeAddress;
}
