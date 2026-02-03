package com.cheshun.mall.domain.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    // 商品ID
    private Integer goodsId;
    // 商品名称
    private String productName;
    // 商品价格
    private Money price;
    // 购买数量
    private Integer quantity;
    // 商品图片
    private String image;
    // 是否选中
    private Boolean selected = true;
    // 商品总价
    public Long getTotalPrice() {
        return price.getFen() * quantity;
    }
}
