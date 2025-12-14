package com.cheshun.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("退款订单查询参数")
@Data
public class MallOrderRefundQueryDTO {
    @ApiModelProperty("退款单号")
    private String refundNo;
    @ApiModelProperty("原订单编号")
    private String orderNumber;
    @ApiModelProperty("会员卡号")
    private String memberCardNo;
    @ApiModelProperty("退款状态:0-待处理,1-审核通过,2-审核拒绝,3-退款中,4-退款成功,5-退款失败,6-已取消")
    private String refundStatus;
    @ApiModelProperty("退货物流单号")
    private String returnLogisticsNo;
    @ApiModelProperty("门店编码")
    private String storeCode;
    @ApiModelProperty("退款申请开始时间")
    private String startApplyTime;
    @ApiModelProperty("退款申请结束时间")
    private String endApplyTime;
    @ApiModelProperty("页码")
    private Integer pageNum;
    @ApiModelProperty("每页条数")
    private Integer pageSize;
}
