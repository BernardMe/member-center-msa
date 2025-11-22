package com.cheshun.market.vo.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
//import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author 阿隆
 * Created on 2021/7/20 3:31 下午.
 */
@Data
@ToString
@ApiModel("系统管理员登陆")
public class LoginCommand {
    /**
     * 登陆用户名
     */
    @NotNull(message = "请输入登陆用户名")
    //@Length(min = 4, max = 18, message = "登陆用户名长度为4-18位")
    @ApiModelProperty("登陆用户名")
    private String username;
    /**
     * 登陆密码
     */
    @NotNull(message = "请输入登陆密码")
    //@Length(min = 4, max = 18, message = "登陆密码长度为4-18位")
    @ApiModelProperty("登陆密码")
    private String password;
    /**
     * 图片验证码id
     */
    @NotNull(message = "请获取验证码")
    @ApiModelProperty("图片验证码id")
    private String captchaId;
    /**
     * 图片验证码
     */
    @NotNull(message = "请输入验证码")
    @ApiModelProperty("验证码")
    private String captcha;
}
