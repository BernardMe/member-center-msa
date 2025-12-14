package com.cheshun.entity.order;

import com.cheshun.vo.store.StoreInfoVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 订单表 --查数据库联查用
 */
@Data
public class MallOrderReceiveData implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 订单编号  --数据展示用
     */
    private String orderNumber;

    /**
     * 订单金额 --数据展示用  对应 real_pay_amount
     */
    private Long orderAmount;

    /**
     * 订单状态 --数据展示用
     */
    private Integer orderStatus;

    /**
     * 门店信息 --数据展示用
     */
    private StoreInfoVO storeInfoVO;
    /**
     * 订单编号
     */
    private String number;

    /**
     * 是否存在处方
     */
    private Integer isHasPrescription;

    /**
     * 处方单号
     */
    private String prescriptionNumber;

    /**
     * 配送单号
     */
    private String courierNumber;

    /**
     * 订单状态 1待付款 2待接单 3已接单 4已完成 5已取消 6已退款 7售后中 8配送中
     */
    private Integer status;

    /**
     * 下单用户
     */
    private String memberCardNo;

    /**
     * 地址id
     */
    private Long memberAddressId;

    /**
     * 下单时间
     */
    private String orderTime;

    /**
     * 结账时间
     */
    private String checkoutTime;

    /**
     * 接单时间 yyyy-MM-dd hh24:mi:ss
     */
    private String takeOrderTime;

    /**
     * 支付方式 1微信
     */
    private Integer payMethod;

    /**
     * 支付状态 0未支付 1已支付 2退款
     */
    private Integer payStatus;

    /**
     * 实收金额
     */
    private Long amount;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 手机号 冗余字段
     */
    private String phone;

    /**
     * 地址 冗余字段
     */
    private String address;

    /**
     * 用户名称
     */
    private String memberName;

    /**
     * 收货人
     */
    private String consignee;

    /**
     * 订单取消原因
     */
    private String cancelReason;

    /**
     * 订单拒绝原因 商家拒单原因
     */
    private String rejectionReason;

    /**
     * 订单取消时间
     */
    private String cancelTime;


    /**
     * 预计送达时间 前端传/后端计算+1小时
     */
    private String estimatedDeliveryTime;

    /**
     * 自提时间
     */
    private String pickTimeSlot;

    /**
     * 送达时间
     */
    private String deliveryTime;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 0:自提 1：闪送 2:邮寄发货
     */
    private Integer deliveryType;

    /**
     * 第三方配送类型
     */
    private String tranType;

    /**
     * 订单来源编码
     */
    private String orderFrom;

    /**
     * 订单来源名称
     */
    private String orderFromName;

    /**
     * 1：O2O 2：B2C 3：服务类型 4：其他 5：售卖机
     */
    private String orderType;

    /**
     * 自提手机号
     */
    private String pickupPhone;

    /**
     * 分销id
     */
    private String distributorId;

    /**
     * 收货人地址
     */
    private String receiveAddress;

    /**
     * 省
     */
    private String receiveProvince;

    /**
     * 市
     */
    private String receiveCity;

    /**
     * 区
     */
    private String receiveArea;

    /**
     * 支付时间 yyyy-MM-dd hh24:mi:ss
     */
    private String payTime;

    /**
     * 是否OTC药品 0：否 1：是
     */
    private Integer isOtc;

    /**
     * 原配送费
     */
    private Long postFee;

    /**
     * 商家承担运费优惠
     */
    private Long poiTransCoupon;

    /**
     * 平台承担运费优惠
     */
    private Long platTransCoupon;

    /**
     * 超距离配送费
     */
    private Long distancePostFee;

    /**
     * 顾客实际支付配送费
     */
    private Long realPostFee;

    /**
     * 商品原总额
     */
    private Long productsAmount;

    /**
     * 优惠券优惠总金额
     */
    private Long couponsDiscountAmount;

    /**
     * 邮寄优惠总金额
     */
    private Long mailDiscountAmount;

    /**
     * 账户优惠总金额
     */
    private Long activityDiscountAmount;

    /**
     * OMS同步状态 0未同步 1同步成功 2同步失败
     */
    private Integer omsSyncStatus;

    /**
     * 下账金额
     */
    private Long accountAmount;

    /**
     * 用户线上付款金额
     */
    private Long userPayAmount;

    /**
     * 总付款金额
     */
    private Long realPayAmount;

    /**
     * 顾客支付中对应商品部分金额
     */
    private Long userGoodsAmount;

    /**
     * 优惠总金额
     */
    private Long totalDiscountAmount;

    /**
     * 创建时间
     */
    private String createTimeOms;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 客户端是否展示0 1 （用户删除订单时使用）
     */
    private Integer isDisplay;

    /**
     * 微信支付交易号
     */
    private String transactionId;

    /**
     * 微信退款号
     */
    private String refundId;

    /**
     * 确认收货时间 （此刻订单状态为已完成）
     */
    private String confirmOrderTime;

    /**
     * 累计退款金额
     */
    private Long refundAmount;

    /**
     * 累计退款次数
     */
    private Integer refundNum;

    /**
     * 退款状态:0-无退款,1-部分退款,2-全额退款
     */
    private Integer refundStatus;

    /**
     * 订单明细数据 --数据展示用
     */
    private List<MallOrderDetailReceiveData> mallOrderDetailReceiveDataS;

    /**
     * 售后状态 门店名称
     */
    private String storeName;;

    /**
     * 单个订单详情数据 -- 数据展示用
     */
    private List<UserOrderDisPlayDetailItemReceiveData> userOrderDisPlayDetailItemReceiveDataS;
}
