package com.cheshun.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author wangzhuo
 * Created on 20210725
 */
@Data
@ToString
@ApiModel("新增代理")
public class AddAgentCommand {
    /**
     * 邀请人代理商Id
     */
    @NotNull(message = "请扫描邀请人的推广二维码")
    @ApiModelProperty("邀请人代理商Id")
    private Long inviterId;
    /**
     * 姓名
     */
    @NotNull(message = "请输入姓名")
    @Size(min = 2, max = 20, message = "姓名长度为2-20位")
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 身份证
     */
    @NotNull(message = "请输入身份证")
    @Size(min = 18, max = 18, message = "身份证长度为18位")
    @ApiModelProperty("身份证")
    private String idCard;
    /**
     * 手机号
     */
    @NotNull(message = "请输入手机号")
    @Size(min = 11, max = 11, message = "手机号长度为11位")
    @ApiModelProperty("手机号")
    private String phone;
}
