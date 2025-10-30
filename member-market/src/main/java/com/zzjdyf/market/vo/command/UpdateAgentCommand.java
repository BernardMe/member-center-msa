package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 阿隆
 * Created on 2021/7/20 3:31 下午.
 */
@Data
@ToString
@ApiModel("更新代理商")
public class UpdateAgentCommand {
    /**
     * 代理商id
     */
    @NotNull(message = "请选择代理商id")
    @ApiModelProperty("代理商id")
    private Long id;
    /**
     * 姓名
     */
    @Size(min = 2, max = 20, message = "姓名长度为2-20位")
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 手机号
     */
    @Size(min = 11, max = 11, message = "手机号长度为11位")
    @ApiModelProperty("手机号")
    private String phone;
    /**
     * 是否可提现
     */
    @ApiModelProperty("是否可提现")
    private Boolean enableWithdraw;
}
