package com.cheshun.vo.mall.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("商城首页分类返回")
public class MallHomePageVO {

    @ApiModelProperty("分类编号")
    private Long id;

    @ApiModelProperty("父级分类编号")
    private Long parentId;

    @ApiModelProperty("分类名称")
    private String moduleName;

    @ApiModelProperty("是否启用")
    private String enable;

    @ApiModelProperty("创建时间")
    private String createtime;

    @ApiModelProperty("修改时间")
    private String modifytime;

    @ApiModelProperty("排序字段")
    private Integer moduleSort;

    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("子级分类")
    private List<MallHomePageVO> mallHomePageVOList;
}
