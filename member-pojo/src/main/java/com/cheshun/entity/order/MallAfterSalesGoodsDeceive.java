package com.cheshun.entity.order;


import lombok.Data;

@Data
public class MallAfterSalesGoodsDeceive {

    private String goodsCode;

    private String goodsName;

    private String goodsImage;

    private Integer refundQuantity;

    private Long refundAmount;
}
