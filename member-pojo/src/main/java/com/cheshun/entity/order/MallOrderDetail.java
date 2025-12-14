package com.cheshun.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单明细表（OMS对接）
 *
 * @TableName mall_order_detail
 */
@TableName(value = "mall_order_detail")
@Data
public class MallOrderDetail implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     * 商品图片
     */
    @TableField(value = "goods_image")
    private String goodsImage;

    /**
     * 订单id
     */
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 订单编号
     */
    @TableField(value = "order_number")
    private String orderNumber;

    /**
     * 货号
     */
    @TableField(value = "goods_code")
    private String goodsCode;


    /**
     * 是否处方药 0:否 1:是
     */
    @TableField(value = "is_prescription")
    private Integer isPrescription;

    /**
     * 数量
     */
    @TableField(value = "goods_number")
    private Integer goodsNumber;


    /**
     * 门店编码
     */
    @TableField(value = "store_code")
    private String storeCode;

    /**
     * 商品原价，4位小数
     */
    @TableField(value = "retail_price")
    private Long retailPrice;

    /**
     * 商品总金额，2位小数；原价*数量
     */
    @TableField(value = "retail_amount")
    private Long retailAmount;

    /**
     * 折扣，默认0，2位小数
     */
    @TableField(value = "discount")
    private Long discount;

    /**
     * 折扣价，4位小数
     */
    @TableField(value = "discount_price")
    private Long discountPrice;

    /**
     * 折扣金额，2位小数
     */
    @TableField(value = "discount_amount")
    private Long discountAmount;

    /**
     * 分摊单价，实际售价(分摊单价即这个商品实际最终售价)，4位小数
     */
    @TableField(value = "share_price")
    private Long sharePrice;

    /**
     * 分摊金额(分摊金额指的是该商品的实际销售的总额)，2位小数
     */
    @TableField(value = "share_amount")
    private Long shareAmount;

    /**
     * 分摊折扣(与折扣价相同)，默认0，2位小数
     */
    @TableField(value = "share_discount")
    private Long shareDiscount;

    /**
     * 0：非赠品 1：赠品
     */
    @TableField(value = "is_gift")
    private Integer isGift;

    /**
     * 创建人
     */
    @TableField(value = "create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private String createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private String updateTime;

    /**
     * 已退款数量
     */
    @TableField(value = "refunded_quantity")
    private Integer refundedQuantity;

    /**
     * 已退款金额
     */
    @TableField(value = "refunded_amount")
    private Long refundedAmount;

}