package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
@ApiModel("批量更新代理商")
public class UpdateBatchAgentCommand {
    /**
     * 是否可提现
     */
    @NotNull(message = "状态不能为空")
    @ApiModelProperty("是否可提现")
    private Integer enableWithdraw;

    @NotNull(message = "请输入备注")
    @ApiModelProperty("备注")
    private String remarks;

    @NotNull
    @ApiModelProperty("供应商id")
    private List<Long> agentIds;
}
