package com.cheshun.po.order;

import com.cheshun.dto.mall.admin.MallOrderDetailDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("用户订单详情表")
public class MallOrdersPO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "订单号")
    private String number;

    @ApiModelProperty(value = "是否存在处方")
    private Integer isHasPrescription;

    @ApiModelProperty(value = "处方单号")
    private String prescriptionNumber;

    @ApiModelProperty(value = "配送单号")
    private String courierNumber;

    @ApiModelProperty(value = "订单状态 1待付款 2待接单 3已接单 4已完成 5已取消 6已退款 7售后中 8配送中")
    private Integer status;

    @ApiModelProperty(value = "下单用户")
    private String memberCardNo;

    @ApiModelProperty(value = "地址id")
    private Long memberAddressId;

    @ApiModelProperty(value = "下单时间")
    private String orderTime;

    @ApiModelProperty(value = "结账时间")
    private String checkoutTime;

    @ApiModelProperty(value = "接单时间 yyyy-MM-dd hh24:mi:ss")
    private String takeOrderTime;

    @ApiModelProperty(value = "支付方式 1微信")
    private Integer payMethod;

    @ApiModelProperty(value = "支付状态 0未支付 1已支付 2退款")
    private Integer payStatus;

    @ApiModelProperty(value = "实收金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "卖家备注")
    private String remark;

    @ApiModelProperty(value = "手机号 冗余字段")
    private String phone;

    @ApiModelProperty(value = "地址 冗余字段")
    private String address;

    @ApiModelProperty(value = "用户名称")
    private String memberName;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "订单取消原因")
    private String cancelReason;

    @ApiModelProperty(value = "订单拒绝原因 商家拒单原因")
    private String rejectionReason;

    @ApiModelProperty(value = "订单取消时间")
    private String cancelTime;

    @ApiModelProperty(value = "预计送达时间 前端传/后端计算+1小时")
    private String estimatedDeliveryTime;

    @ApiModelProperty(value = "自提时间")
    private String pickTimeSlot;

    @ApiModelProperty(value = "送达时间")
    private String deliveryTime;

    @ApiModelProperty(value = "门店编码")
    private String storeCode;

    @ApiModelProperty(value = "门店名称")
    private String storeName;

    @ApiModelProperty(value = "0:自提 1：闪送 2:邮寄发货")
    private Integer deliveryType;

    @ApiModelProperty(value = "第三方配送类型")
    private String tranType;

    @ApiModelProperty(value = "订单来源编码")
    private String orderFrom;

    @ApiModelProperty(value = "订单来源名称")
    private String orderFromName;

    @ApiModelProperty(value = "1：O2O 2：B2C 3：服务类型 4：其他 5：售卖机")
    private String orderType;

    @ApiModelProperty(value = "自提手机号")
    private String pickupPhone;

    @ApiModelProperty(value = "分销id")
    private String distributorId;

    @ApiModelProperty(value = "收货人地址")
    private String receiveAddress;

    @ApiModelProperty(value = "省")
    private String receiveProvince;

    @ApiModelProperty(value = "市")
    private String receiveCity;

    @ApiModelProperty(value = "区")
    private String receiveArea;

    @ApiModelProperty(value = "支付时间 yyyy-MM-dd hh24:mi:ss")
    private String payTime;

    @ApiModelProperty(value = "是否OTC药品 0：否 1：是")
    private Integer isOtc;

    @ApiModelProperty(value = "原配送费")
    private BigDecimal postFee;

    @ApiModelProperty(value = "商家承担运费优惠")
    private BigDecimal poiTransCoupon;

    @ApiModelProperty(value = "平台承担运费优惠")
    private BigDecimal platTransCoupon;

    @ApiModelProperty(value = "超距离配送费")
    private BigDecimal distancePostFee;

    @ApiModelProperty(value = "顾客实际支付配送费")
    private BigDecimal realPostFee;

    @ApiModelProperty(value = "商品原总额")
    private BigDecimal productsAmount;

    @ApiModelProperty(value = "优惠券优惠总金额")
    private BigDecimal couponsDiscountAmount;

    @ApiModelProperty(value = "邮寄优惠总金额")
    private BigDecimal mailDiscountAmount;

    @ApiModelProperty(value = "账户优惠总金额")
    private BigDecimal activityDiscountAmount;

    @ApiModelProperty(value = "OMS同步状态 0未同步 1同步成功 2同步失败")
    private Integer omsSyncStatus;

    @ApiModelProperty(value = "下账金额")
    private BigDecimal accountAmount;

    @ApiModelProperty(value = "用户线上付款金额")
    private BigDecimal userPayAmount;

    @ApiModelProperty(value = "总付款金额")
    private BigDecimal realPayAmount;

    @ApiModelProperty(value = "顾客支付中对应商品部分金额")
    private BigDecimal userGoodsAmount;

    @ApiModelProperty(value = "创建时间")
    private String createTimeOms;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "客户端是否展示0 1 （用户删除订单时使用）")
    private Integer isDisplay;

    @ApiModelProperty(value = "微信支付交易号")
    private String transactionId;

    @ApiModelProperty(value = "微信退款号")
    private String refundId;

    @ApiModelProperty(value = "确认收货时间 （此刻订单状态为已完成）")
    private String confirmOrderTime;

    @ApiModelProperty(value = "订单商品详情")
    private List<MallOrderDetailDTO> orderDetails;
}