package com.cheshun.entity.mall.shopcard;

import lombok.Data;

/**
 * 商城购物车信息
 * @TableName mall_shop_cart
 */
@Data
public class MallShopCart {
    /**
     * 
     */
    private Long id;

    /**
     * 会员卡号
     */
    private String memberCardNo;

    /**
     * 门店编号
     */
    private String storeCode;

    /**
     * 门店名称
     */
    private String storeName;

    /**
     * 商品编号/货号
     */
    private String goodsCode;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品数量
     */
    private Integer goodsNumber;

    /**
     * 配送方式 0：自提 1：闪送 2：邮寄发货
     */
    private Integer deliveryMethod;

    /**
     * 配送方式
     */
    private String shopMethod;

    /**
     * 创建时间
     */
    private String createtime;

    /**
     * 更新时间
     */
    private String modifytime;

}