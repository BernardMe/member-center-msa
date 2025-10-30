package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 阿隆
 * Created on 2021/6/12 10:38 上午.
 */
@Data
@ToString
@ApiModel("手机号+短信验证码登陆")
public class SmsLoginCommand {
    /**
     * 手机号
     */
    @NotNull(message = "请输入手机号")
    @Size(min = 11, max = 11, message = "手机号长度为11位")
    @ApiModelProperty("手机号")
    private String phone;
    /**
     * 短信验证码
     */
    @NotNull(message = "请输入短信验证码")
    @Size(min = 6, max = 6, message = "短信验证码长度为6位")
    @ApiModelProperty("短信验证码")
    private String smsCode;
}
