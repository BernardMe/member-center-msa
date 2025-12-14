package com.cheshun.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户订单表（OMS对接）
 *
 * @TableName mall_orders
 */
@TableName(value = "mall_orders")
@Data
public class MallOrders implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单号
     */
    @TableField(value = "number")
    private String number;

    /**
     * 是否存在处方
     */
    @TableField(value = "is_has_prescription")
    private Integer isHasPrescription;

    /**
     * 处方单号
     */
    @TableField(value = "prescription_number")
    private String prescriptionNumber;

    /**
     * 配送单号
     */
    @TableField(value = "courier_number")
    private String courierNumber;

    /**
     * 订单状态 1待付款 2待接单 3已接单 4已完成 5已取消 6已退款 7售后中 8配送中
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 下单用户
     */
    @TableField(value = "member_card_no")
    private String memberCardNo;

    /**
     * 地址id
     */
    @TableField(value = "member_address_id")
    private Long memberAddressId;

    /**
     * 下单时间
     */
    @TableField(value = "order_time")
    private String orderTime;

    /**
     * 结账时间
     */
    @TableField(value = "checkout_time")
    private String checkoutTime;

    /**
     * 接单时间 yyyy-MM-dd hh24:mi:ss
     */
    @TableField(value = "take_order_time")
    private String takeOrderTime;

    /**
     * 支付方式 1微信
     */
    @TableField(value = "pay_method")
    private Integer payMethod;

    /**
     * 支付状态 0未支付 1已支付 2退款
     */
    @TableField(value = "pay_status")
    private Integer payStatus;

    /**
     * 实收金额
     */
    @TableField(value = "amount")
    private Long amount;

    /**
     * 卖家备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 手机号 冗余字段
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 地址 冗余字段
     */
    @TableField(value = "address")
    private String address;

    /**
     * 用户名称
     */
    @TableField(value = "member_name")
    private String memberName;

    /**
     * 收货人
     */
    @TableField(value = "consignee")
    private String consignee;

    /**
     * 订单取消原因
     */
    @TableField(value = "cancel_reason")
    private String cancelReason;

    /**
     * 订单拒绝原因 商家拒单原因
     */
    @TableField(value = "rejection_reason")
    private String rejectionReason;

    /**
     * 订单取消时间
     */
    @TableField(value = "cancel_time")
    private String cancelTime;


    /**
     * 预计送达时间 前端传/后端计算+1小时
     */
    @TableField(value = "estimated_delivery_time")
    private String estimatedDeliveryTime;

    /**
     * 自提时间
     */
    @TableField(value = "pickup_time_slot")
    private String pickTimeSlot;

    /**
     * 送达时间
     */
    @TableField(value = "delivery_time")
    private String deliveryTime;

    /**
     * 门店编码
     */
    @TableField(value = "store_code")
    private String storeCode;

    /**
     * 0:自提 1：闪送 2:邮寄发货
     */
    @TableField(value = "delivery_type")
    private Integer deliveryType;

    /**
     * 第三方配送类型
     */
    @TableField(value = "tran_type")
    private String tranType;

    /**
     * 订单来源编码
     */
    @TableField(value = "order_from")
    private String orderFrom;

    /**
     * 订单来源名称
     */
    @TableField(value = "order_from_name")
    private String orderFromName;

    /**
     * 1：O2O 2：B2C 3：服务类型 4：其他 5：售卖机
     */
    @TableField(value = "order_type")
    private String orderType;

    /**
     * 自提手机号
     */
    @TableField(value = "pickup_phone")
    private String pickupPhone;

    /**
     * 分销id
     */
    @TableField(value = "distributor_id")
    private String distributorId;

    /**
     * 收货人地址
     */
    @TableField(value = "receive_address")
    private String receiveAddress;

    /**
     * 省
     */
    @TableField(value = "receive_province")
    private String receiveProvince;

    /**
     * 市
     */
    @TableField(value = "receive_city")
    private String receiveCity;

    /**
     * 区
     */
    @TableField(value = "receive_area")
    private String receiveArea;

    /**
     * 支付时间 yyyy-MM-dd hh24:mi:ss
     */
    @TableField(value = "pay_time")
    private String payTime;

    /**
     * 是否OTC药品 0：否 1：是
     */
    @TableField(value = "is_otc")
    private Integer isOtc;

    /**
     * 原配送费
     */
    @TableField(value = "post_fee")
    private Long postFee;

    /**
     * 商家承担运费优惠
     */
    @TableField(value = "poi_trans_coupon")
    private Long poiTransCoupon;

    /**
     * 平台承担运费优惠
     */
    @TableField(value = "plat_trans_coupon")
    private Long platTransCoupon;

    /**
     * 超距离配送费
     */
    @TableField(value = "distance_post_fee")
    private Long distancePostFee;

    /**
     * 顾客实际支付配送费
     */
    @TableField(value = "real_post_fee")
    private Long realPostFee;

    /**
     * 商品原总额
     */
    @TableField(value = "products_amount")
    private Long productsAmount;

    /**
     * 优惠券优惠总金额
     */
    @TableField(value = "coupons_discount_amount")
    private Long couponsDiscountAmount;

    /**
     * 邮寄优惠总金额
     */
    @TableField(value = "mail_discount_amount")
    private Long mailDiscountAmount;

    /**
     * 账户优惠总金额
     */
    @TableField(value = "activity_discount_amount")
    private Long activityDiscountAmount;

    /**
     * OMS同步状态 0未同步 1同步成功 2同步失败
     */
    @TableField(value = "oms_sync_status")
    private Integer omsSyncStatus;

    /**
     * 下账金额
     */
    @TableField(value = "account_amount")
    private Long accountAmount;

    /**
     * 用户线上付款金额
     */
    @TableField(value = "user_pay_amount")
    private Long userPayAmount;

    /**
     * 总付款金额
     */
    @TableField(value = "real_pay_amount")
    private Long realPayAmount;

    /**
     * 顾客支付中对应商品部分金额
     */
    @TableField(value = "user_goods_amount")
    private Long userGoodsAmount;

    /**
     * 优惠总金额
     */
    @TableField(value = "total_discount_amount")
    private Long totalDiscountAmount;

    /**
     * 创建时间
     */
    @TableField(value = "create_time_oms")
    private String createTimeOms;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private String updateTime;

    /**
     * 客户端是否展示0 1 （用户删除订单时使用）
     */
    @TableField(value = "is_display")
    private Integer isDisplay;

    /**
     * 微信支付交易号
     */
    @TableField(value = "transaction_id")
    private String transactionId;

    /**
     * 微信退款号
     */
    @TableField(value = "refund_id")
    private String refundId;

    /**
     * 确认收货时间 （此刻订单状态为已完成）
     */
    @TableField(value = "confirm_order_time")
    private String confirmOrderTime;

    /**
     * 累计退款金额
     */
    @TableField(value = "refund_amount")
    private Long refundAmount;

    /**
     * 累计退款次数
     */
    @TableField(value = "refund_num")
    private Integer refundNum;

    /**
     * 退款状态:0-无退款,1-部分退款,2-全额退款
     */
    @TableField(value = "refund_status")
    private Integer refundStatus;

    @TableField(value = "refund_number")
    private String refundNumber;
}