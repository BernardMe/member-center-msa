package com.zzjdyf.mall.vo.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(tags = "首页多维搜索参数")
public class FacetedSearchVO implements Serializable {

    @ApiModelProperty(value = "关键字")
    private String keyWords;

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "页大小")
    private Integer pageSize;

    @ApiModelProperty(value = "会员卡号")
    private String memberCardNo;

    @ApiModelProperty(value = "价格排序类型(0:默认 2:价格降序 3:价格升序)")
    private Byte sortType;

    @ApiModelProperty(value = "筛选条件参数")
    private FilterKeyVO filterKeyVO;

    @ApiModelProperty(value = "reqId(初始化固定传null，后续查询从响应数据中拿取)")
    private String reqId;
}
