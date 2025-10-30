package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author wangzhuo
 * Created on 2021/7/15 08:53.
 */
@Data
@ToString
@ApiModel("审核代理商")
public class AuditAgentCommand {
    /**
     * 代理商id
     */
    @NotNull(message = "请选择代理商id")
    @ApiModelProperty("代理商id")
    private Long id;
    /**
     * 是否审核通过
     */
    @NotNull(message = "请选择是否审核通过")
    @ApiModelProperty("是否审核通过")
    private Boolean pass;
    /**
     * 审核拒绝原因
     */
    @ApiModelProperty("审核拒绝原因")
    private String refuseReason;
}
