package com.cheshun.vo.mall.admin;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsCategoryCreateVO {
    @ApiModelProperty
    private Integer id;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String name;

    /**
     * 父级分类编号(0表示1级分类)
     */
    @ApiModelProperty(value = "父级分类编号(0表示1级分类)")
    private Integer parentId;

    /**
     * 排序字段，数字越小越靠前
     */
    @ApiModelProperty(value = "排序字段，数字越小越靠前")
    private Integer sortOrder;

    /**
     * 分类图标
     */
    @ApiModelProperty(value = "分类图标")
    private String icon;

    /**
     * 是否显示(1显示，0隐藏)
     */
    @ApiModelProperty(value = "是否显示(1显示，0隐藏)")
    private Integer status;

    /**
     * 是否热门(1是，0否)
     */
    @ApiModelProperty(value = "是否热门(1是，0否)")
    private Integer isHot;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updatedBy;
}
