package com.cheshun.mall.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcMeun extends BaseEntity{


    @ApiModelProperty("菜单名称")
    private String name;

    @ApiModelProperty("父节点id")
    private Long parentId;


    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("url链接")
    private String url;

    @ApiModelProperty("级别")
    private Integer level;


    @ApiModelProperty("节点")
    private Integer leaf;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("菜单列表")
    private List<ClsMarketEtcMeun> meunList;
}
