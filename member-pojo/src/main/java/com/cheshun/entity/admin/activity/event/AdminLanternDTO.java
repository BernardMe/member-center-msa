package com.cheshun.entity.admin.activity.event;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@ApiModel(description = "管理员分页查看会员花灯分享信息参数")
public class AdminLanternDTO implements Serializable {


    @ApiModelProperty(value = "页码")
    private Integer page;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;

    @ApiModelProperty(value = "分享会员")
    private String shareMember;

    @ApiModelProperty(value = "被分享会员")
    private String receiveMember;

    @ApiModelProperty(value = "分享时间")
    private String createtime;

}
