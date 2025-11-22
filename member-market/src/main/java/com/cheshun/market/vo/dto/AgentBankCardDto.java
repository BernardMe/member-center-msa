package com.cheshun.market.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 阿隆
 * Created on 2021/8/4 1:35 下午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("代理商银行卡信息")
public class AgentBankCardDto extends BaseDto {
    /**
     * 银行名称
     */
    @ApiModelProperty("银行名称")
    private String bankName;
    /**
     * 银行开户卡号
     */
    @ApiModelProperty("银行开户卡号")
    private String cardNo;
}
