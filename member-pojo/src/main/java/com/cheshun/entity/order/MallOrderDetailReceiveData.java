package com.cheshun.entity.order;


import lombok.Data;

import java.io.Serializable;

@Data
public class MallOrderDetailReceiveData implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    
    private String goodsImage;
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 货号
     */
    private String goodsCode;


    /**
     * 是否处方药 0:否 1:是
     */
    private Integer isPrescription;

    /**
     * 数量
     */
    private Integer goodsNumber;


    /**
     * 门店编码
     */
    private String storeCode;

    /**
     * 商品原价，4位小数
     */
    private Long retailPrice;

    /**
     * 商品总金额，2位小数；原价*数量
     */
    private Long retailAmount;

    /**
     * 折扣，默认0，2位小数
     */
    private Long discount;

    /**
     * 折扣价，4位小数
     */
    private Long discountPrice;

    /**
     * 折扣金额，2位小数
     */
    private Long discountAmount;

    /**
     * 分摊单价，实际售价(分摊单价即这个商品实际最终售价)，4位小数
     */
    private Long sharePrice;

    /**
     * 分摊金额(分摊金额指的是该商品的实际销售的总额)，2位小数
     */
    private Long shareAmount;

    /**
     * 分摊折扣(与折扣价相同)，默认0，2位小数
     */
    private Long shareDiscount;

    /**
     * 0：非赠品 1：赠品
     */
    private Integer isGift;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 已退款数量
     */
    private Integer refundedQuantity;

    /**
     * 已退款金额
     */
    private Long refundedAmount;
}
