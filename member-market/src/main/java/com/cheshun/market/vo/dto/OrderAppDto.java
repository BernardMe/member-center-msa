package com.cheshun.market.vo.dto;

import com.cheshun.market.domain.entity.enums.TradeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("个人订单信息")
public class OrderAppDto extends BaseDto {
    /**
     * 代理商id
     */
    @ApiModelProperty("代理商id")
    private Long agentId;
    /**
     * 交易类型
     */
    @ApiModelProperty("交易类型")
    private TradeType type;
    /**
     * 交易类型名称
     */
    @ApiModelProperty("交易类型名称")
    private String typeName;
    /**
     * 状态名称
     */
    @ApiModelProperty("交易类型名称")
    private String statusStr;
    /**
     * 金额(元)
     */
    @ApiModelProperty("金额(元)")
    private BigDecimal amount = BigDecimal.ZERO;
}
