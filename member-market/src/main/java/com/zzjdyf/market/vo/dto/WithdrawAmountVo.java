package com.zzjdyf.market.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author 阿隆
 * Created on 2021/7/29 8:43 上午.
 */
@Data
@ToString
@ApiModel("提现金额")
public class WithdrawAmountVo {
    /**
     * 累计已提现金额
     */
    @ApiModelProperty("累计已提现金额")
    private BigDecimal totalAmount = BigDecimal.ZERO;
}
