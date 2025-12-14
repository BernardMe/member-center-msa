package com.cheshun.po.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cheshun.entity.order.MallOrderRefundDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel("退款订单详情表")
public class MallOrderRefundPO {
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @ApiModelProperty(value = "退款单号(唯一)")
    private String refundNo;
    @ApiModelProperty(value = "原订单编号")
    private String orderNumber;
    @ApiModelProperty(value = "原订单ID")
    private Long orderId;
    @ApiModelProperty(value = "用户ID/会员卡号")
    private String memberCardNo;
    @ApiModelProperty(value = "退款总金额")
    private BigDecimal refundTotalAmount;
    @ApiModelProperty(value = "退运费金额")
    private BigDecimal refundFreight;
    @ApiModelProperty(value = "退回积分")
    private Integer refundPoints;
    @ApiModelProperty(value = "退回优惠券金额")
    private BigDecimal couponRefundAmount;
    @ApiModelProperty(value = "支付方式:1-微信,2-支付宝,3-余额")
    private Integer paymentType;
    @ApiModelProperty(value = "支付平台交易号")
    private String transactionId;
    @ApiModelProperty(value = "支付平台退款单号")
    private String refundTransactionId;
    @ApiModelProperty(value = "退款状态:0-待处理,1-审核通过,2-审核拒绝,3-退款中,4-退款成功,5-退款失败,6-已取消")
    private Integer refundStatus;
    @ApiModelProperty(value = "退款类型:1-仅退款,2-退货退款")
    private Integer refundType;
    @ApiModelProperty(value = "退款原因 必录")
    private String refundReason;
    @ApiModelProperty(value = "退款描述")
    private String refundDesc;
    @ApiModelProperty(value = "退款凭证(图片等)")
    private String refundEvidence;
    @ApiModelProperty(value = "退货物流单号")
    private String returnLogisticsNo;
    @ApiModelProperty(value = "退货物流公司")
    private String returnLogisticsCompany;
    @ApiModelProperty(value = "退货发货时间")
    private String returnLogisticsTime;
    @ApiModelProperty(value = "审核备注")
    private String auditRemark;
    @ApiModelProperty(value = "审核人ID")
    private String auditorId;
    @ApiModelProperty(value = "审核时间")
    private String auditTime;
    @ApiModelProperty(value = "申请时间")
    private String applyTime;
    @ApiModelProperty(value = "退款时间")
    private LocalDateTime refundTime;
    @ApiModelProperty(value = "完成时间")
    private String completeTime;
    @ApiModelProperty(value = "退款入账账户")
    private String refundAccount;
    @ApiModelProperty(value = "退款账户类型:1-原路退回,2-退到余额,3-其他")
    private Integer refundAccountType;
    @ApiModelProperty(value = "创建时间")
    private String createTime;
    @ApiModelProperty(value = "更新时间")
    private String updateTime;
    @ApiModelProperty(value = "门店编码")
    private String storeCode;
    @ApiModelProperty(value = "退款详情")
    private List<MallOrderRefundDetail> mallOrderRefundDetails;
    @ApiModelProperty(value = "OMS退款状态")
    private Integer omsSyncStatus;
}