package com.cheshun.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * C端用户登录
 */
@Data
@Api(tags = "微信code登录参数")
public class WeChatLoginParam implements Serializable {

    @ApiModelProperty(value = "微信code")
    private String code;

}
