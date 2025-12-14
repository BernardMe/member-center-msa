package com.cheshun.vo.order;

import com.cheshun.entity.mall.GoodsTag;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("用户端-订单展示接口(商品详情)")
public class UserOrderDetailItemVO {

    @ApiModelProperty(value = "货号")
    private String goodsCode;
    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品规格")
    private String specification;

    @ApiModelProperty(value = "主图")
    private String mainImage;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsNumber;

    @ApiModelProperty(value = "标签")
    private List<GoodsTag> goodsTag;

}
