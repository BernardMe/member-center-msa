package com.cheshun.market.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author 阿隆
 * Created on 2021/7/20 3:34 下午.
 */
@Data
@ToString
@Builder
@ApiModel("系统管理员")
public class AdminUserDto {
    /**
     * 登陆手机号
     */
    @ApiModelProperty("登陆手机号")
    private String phone;
    /**
     * 登陆token
     */
    @ApiModelProperty("登陆token")
    private String token;

    @ApiModelProperty("登录id")
    private Long id;
}
