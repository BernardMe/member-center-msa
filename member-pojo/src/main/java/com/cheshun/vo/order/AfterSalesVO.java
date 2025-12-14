package com.cheshun.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@ApiModel("售后数据列表")
@Data
public class AfterSalesVO {

    @ApiModelProperty("售后商品详情")
    private List<AfterSalesGoodsVO> afterSalesGoodsVOList;

    @ApiModelProperty("退款单号")
    private String refundNumber;

    @ApiModelProperty("退款总金额")
    private String refundTotalAmount;

    @ApiModelProperty("退款状态:0-待处理,1-审核通过,2-审核拒绝,3-退款中,4-退款成功,5-退款失败,6-已取消")
    private Integer refundStatus;
}
