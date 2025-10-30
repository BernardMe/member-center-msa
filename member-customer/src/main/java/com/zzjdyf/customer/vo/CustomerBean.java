package com.zzjdyf.customer.vo;

import lombok.Data;

/**
 * 客户接口父类bean
 *
 * @AUTHOR: YYJ
 * @DATE : 2021/4/16 11:36
 * @Version 1.0
 */
@Data
public class CustomerBean {
    /**
     * 客户来源
     */
    private String carOriginType;

    /**
     * 客户号
     */
    private String orderNo;
    /**
     * 客户id
     */
    private String orderId;

    /**
     * 客户收货地址
     */
    private String orderAddress;

}
