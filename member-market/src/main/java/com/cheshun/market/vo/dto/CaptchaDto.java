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
@ApiModel("图片验证码")
public class CaptchaDto {
    /**
     * 验证码id
     */
    @ApiModelProperty("验证码id")
    private String id;
    /**
     * 验证码Base64图片
     */
    @ApiModelProperty("验证码Base64图片")
    private String image;
}
