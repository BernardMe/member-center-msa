package com.cheshun.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wangzhuo
 * Created on 20210806
 */
@Data
@ToString
@ApiModel("创建补货订单信息")
public class AddRepleshOrderCommand {
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
    /**
     * 申请补货的卡数量
     */
    @ApiModelProperty("申请补货的卡数量")
    private String applyCardQuantity;
    /**
     * 申请补货的OBU数量
     */
    @ApiModelProperty("申请补货的OBU数量")
    private String applyDeviceQuantity;

}
