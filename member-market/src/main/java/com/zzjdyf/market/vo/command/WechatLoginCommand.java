package com.zzjdyf.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author
 * Created on 2021/6/12 10:38 上午.
 */
@Data
@ToString
@ApiModel("encryptedData、iv、code登陆")
public class WechatLoginCommand {

    @NotNull(message = "缺少参数encryptedData")
    @ApiModelProperty("手机号")
    private String encryptedData;

    @NotNull(message = "缺少参数iv")
    @ApiModelProperty("iv")
    private String iv;

    @NotNull(message = "缺少参数code")
    @ApiModelProperty("code")
    private String code;
}
