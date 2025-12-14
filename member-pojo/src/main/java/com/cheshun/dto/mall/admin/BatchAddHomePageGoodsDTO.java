package com.cheshun.dto.mall.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("批量添加分类商品信息")
public class BatchAddHomePageGoodsDTO {

    @ApiModelProperty("分类id")
    private Long moduleId;

    @ApiModelProperty("商品数据 “货号 英文,相隔”")
    private String[] goodsData;

}
