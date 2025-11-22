package com.cheshun.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author 阿隆
 * Created on 2021/7/29 12:48 下午.
 */
@Data
@ToString
@ApiModel("确认已缴纳金额")
public class ConfirmPaidCommand {
    /**
     * 订单id
     */
    @NotNull(message = "请选择订单")
    @ApiModelProperty("id")
    private Long id;
    /**
     * 实缴纳押金(元)
     */
    @NotNull(message = "请输入实缴纳押金")
    @DecimalMin(value = "0.01", message = "实缴纳押金不能低于0.01元")
    @ApiModelProperty("实缴纳押金(元)")
    private BigDecimal paidDeposit;
}
