package com.cheshun.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author 阿隆
 * Created on 2021/7/20 9:51 下午.
 */
@Data
@ToString
@ApiModel("代理商解约")
public class TerminatedCommand {
    /**
     * 代理商id
     */
    @NotNull(message = "请选择代理商id")
    @ApiModelProperty("代理商id")
    private Long id;
}
