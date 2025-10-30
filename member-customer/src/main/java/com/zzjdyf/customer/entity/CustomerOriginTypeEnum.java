package com.zzjdyf.customer.entity;

/**
 * 客户查询来源，比如来源是视博，货车之家，DSB
 *
 * @AUTHOR: YYJ
 * @DATE : 2021/4/16 15:55
 * @Version 1.0
 */
public enum CustomerOriginTypeEnum {
    CONSUMER("CONSUMER");
    /**
     * 客户请求来源
     */
    private String originType;

    CustomerOriginTypeEnum(String originType) {
        this.originType = originType;
    }

}
