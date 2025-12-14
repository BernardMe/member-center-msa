package com.cheshun.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 退款单实体类
 * 对应表: mall_order_refund
 */
@Data
@TableName(value = "mall_order_refund")
public class MallOrderRefund {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 退款单号(唯一)
     */
    private String refundNo;
    
    /**
     * 原订单编号
     */
    private String orderNumber;
    
    /**
     * 原订单ID
     */
    private Long orderId;

    /**
     * 售后前原订单状态
     */
    private Integer originalOrderStatus;
    
    /**
     * 用户ID/会员卡号
     */
    private String memberCardNo;
    
    /**
     * 退款总金额
     */
    private Long refundTotalAmount;
    
    /**
     * 退运费金额
     */
    private Long refundFreight;
    
    /**
     * 退回积分
     */
    private Integer refundPoints;
    
    /**
     * 退回优惠券金额
     */
    private BigDecimal couponRefundAmount;
    
    /**
     * 支付方式:1-微信,2-支付宝,3-余额
     */
    private Integer paymentType;
    
    /**
     * 支付平台交易号
     */
    private String transactionId;
    
    /**
     * 支付平台退款单号
     */
    private String refundTransactionId;
    
    /**
     * 退款状态:0-待处理,1-审核通过,2-审核拒绝,3-退款中,4-退款成功,5-退款失败,6-已取消
     */
    private Integer refundStatus;
    
    /**
     * 退款类型:1-仅退款,2-退货退款
     */
    private Integer refundType;
    
    /**
     * 退款原因 必录
     */
    private String refundReason;

    /**
     * 退款描述
     */
    private String refundDesc;
    
    /**
     * 退款凭证(图片等)
     */
    private String refundEvidence;
    
    /**
     * 退货物流单号
     */
    private String returnLogisticsNo;
    
    /**
     * 退货物流公司
     */
    private String returnLogisticsCompany;
    
    /**
     * 退货发货时间
     */
    private String returnLogisticsTime;
    
    /**
     * 审核备注
     */
    private String auditRemark;
    
    /**
     * 审核人ID
     */
    private String auditorId;
    
    /**
     * 审核时间
     */
    private String auditTime;
    
    /**
     * 申请时间
     */
    private String applyTime;
    
    /**
     * 退款时间
     */
    private LocalDateTime refundTime;
    
    /**
     * 完成时间
     */
    private String completeTime;
    
    /**
     * 退款入账账户
     */
    private String refundAccount;
    
    /**
     * 退款账户类型:1-原路退回,2-退到余额,3-其他
     */
    private Integer refundAccountType;
    
    /**
     * 创建时间
     */
    private String createTime;
    
    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * OMS同步状态：0-未同步，1-已同步
     */
    private Integer omsSyncStatus;

    /**
     * 是否退运费 0:否 1:是
     */
    private Integer isRefundDelivery;
    

    
    // 枚举类定义（可选）
    public enum PaymentType {
        WECHAT(1, "微信"),
        ALIPAY(2, "支付宝"),
        BALANCE(3, "余额");
        
        private final Integer code;
        private final String desc;
        
        PaymentType(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        
        public Integer getCode() {
            return code;
        }
        
        public String getDesc() {
            return desc;
        }
    }
    
    public enum RefundStatus {
        PENDING(0, "待处理"),
        APPROVED(1, "审核通过"),
        REJECTED(2, "审核拒绝"),
        REFUNDING(3, "退款中"),
        SUCCESS(4, "退款成功"),
        FAILED(5, "退款失败"),
        CANCELLED(6, "已取消");
        
        private final Integer code;
        private final String desc;
        
        RefundStatus(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        
        public Integer getCode() {
            return code;
        }
        
        public String getDesc() {
            return desc;
        }
    }
    
    public enum RefundType {
        REFUND_ONLY(1, "仅退款"),
        RETURN_REFUND(2, "退货退款");
        
        private final Integer code;
        private final String desc;
        
        RefundType(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }
        
        public Integer getCode() {
            return code;
        }
        
        public String getDesc() {
            return desc;
        }
    }
}