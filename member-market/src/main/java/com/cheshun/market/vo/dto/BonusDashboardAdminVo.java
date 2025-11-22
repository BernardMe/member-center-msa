package com.cheshun.market.vo.dto;

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
@ApiModel("分润概况统计")
public class BonusDashboardAdminVo {
    /**
     * 平台累计分润
     */
    @ApiModelProperty("平台累计分润")
    private BigDecimal totalBonus = BigDecimal.ZERO;
    /**
     * 平台累计已提现分润
     */
    @ApiModelProperty("平台累计已提现分润")
    private BigDecimal totalWithdrawAmount = BigDecimal.ZERO;
    /**
     * 平台累计分润余额
     */
    @ApiModelProperty("平台累计分润余额")
    private BigDecimal totalBalance = BigDecimal.ZERO;
}
