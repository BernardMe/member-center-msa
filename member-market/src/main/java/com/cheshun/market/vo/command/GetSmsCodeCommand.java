package com.cheshun.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 阿隆
 * Created on 2021/7/8 20:07.
 */
@Data
@ToString
@ApiModel("获取短信验证码")
public class GetSmsCodeCommand {
    /**
     * 手机号
     */
    @NotNull(message = "请输入手机号")
    @Size(min = 11, max = 11, message = "手机号长度为11位")
    @ApiModelProperty("手机号")
    private String phone;
}
