package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author 阿隆
 * Created on 2021/8/4 1:28 下午.
 */
@Data
@ToString
@ApiModel("删除银行卡")
public class DeleteBankCardCommand {
    /**
     * id
     */
    @NotNull(message = "请选择银行卡")
    @ApiModelProperty("id")
    private Long id;
}
