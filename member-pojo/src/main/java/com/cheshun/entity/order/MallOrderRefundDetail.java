package com.cheshun.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.math.BigDecimal;

/**
 * 退款明细实体类
 * 对应表: mall_order_refund_detail
 */
@Data
@TableName(value = "mall_order_refund_detail")
public class MallOrderRefundDetail {
    
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 退款单ID
     */
    private Long refundId;
    
    /**
     * 退款单号
     */
    private String refundNo;
    
    /**
     * 订单ID
     */
    private Long orderId;
    
    /**
     * 订单明细ID
     */
    private Long orderItemId;
    
    /**
     * 商品货号
     */
    private String goodsCode;
    
    /**
     * 商品名称
     */
    private String goodsName;
    
    /**
     * 商品图片
     */
    private String goodsImage;
    
    /**
     * 退款数量
     */
    private Integer refundQuantity;
    
    /**
     * 商品单价
     */
    private Long unitPrice;
    
    /**
     * 实际退款单价
     */
    private Long refundPrice;
    
    /**
     * 退款金额
     */
    private Long refundAmount;
    
    /**
     * 优惠分摊金额
     */
    private Long discountShare;
    
    /**
     * 优惠券分摊金额
     */
    private Long couponShare;
    
    /**
     * 积分分摊
     */
    private Integer pointsShare;
    
    /**
     * 商品退款原因
     */
    private String refundReason;
    
    /**
     * 商品退款凭证
     */
    private String refundEvidence;
    
    /**
     * 创建时间
     */
    private String createTime;
    
    /**
     * 更新时间
     */
    private String updateTime;
}