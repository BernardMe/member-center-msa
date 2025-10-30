package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author 阿隆
 * Created on 2021/8/4 3:11 下午.
 */
@Data
@ToString
@ApiModel("代理商提现信息")
public class AgentWithdrawCommand {
    /**
     * 银行卡id
     */
    @NotNull(message = "请选择银行卡")
    @ApiModelProperty("银行卡id")
    private Long bankCardId;
    /**
     * 提现金额(元)
     */
    @NotNull(message = "请输入提现金额")
    @DecimalMin(value = "0.01", message = "提现金额不能低于0.01元")
    @ApiModelProperty("提现金额(元)")
    private BigDecimal amount;
}
