package com.cheshun.entity.order;



import lombok.Data;


@Data
public class UserOrderDisPlayDetailItemReceiveData {


    private Long retailPrice;

    private Long retailAmount;

    private Long discountAmount;

    private Long discountPrice;

    private String goodsCode;

    private String productName;

    private String specification;

    private String mainImage;

    private Integer goodsNumber;


}
