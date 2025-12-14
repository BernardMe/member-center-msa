package com.cheshun.vo.order;

import com.cheshun.vo.store.StoreInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("用户端-单个售后单详情展示")
public class UserRefundDisplayVO {

    @ApiModelProperty("退款原因")
    private String refundReason;

    @ApiModelProperty("退款凭证(图片 -最多三张)")
    private String refundEvidence;

    @ApiModelProperty("退款金额/按照实付金额")
    private String refundTotalAmount;

    @ApiModelProperty("退款描述")
    private String refundDesc;

    @ApiModelProperty("原订单编号")
    private String orderNumber;

    @ApiModelProperty("售后编号")
    private String refundNumber;

    @ApiModelProperty("申请时间")
    private String applyTime;

    @ApiModelProperty("退款状态:0-待处理,1-审核通过,2-审核拒绝,3-退款中,4-退款成功,5-退款失败,6-已取消")
    private Integer refundStatus;

    @ApiModelProperty("门店信息")
    private StoreInfoVO storeInfoVO;

    @ApiModelProperty("退款商品信息")
    private List<AfterSalesGoodsDetailVO> afterSalesGoodsDetailVOs;

    @ApiModelProperty("门店编号")
    private String storeCode;



}
