package com.cheshun.vo.mall;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * C端用户登录
 */
@Data
@Api(tags = "首页获取热搜词参数")
public class HotwordListParam implements Serializable {

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

}
