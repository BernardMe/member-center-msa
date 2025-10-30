package com.zzjdyf.customer.vo.mapper;

import com.zzjdyf.customer.vo.CustomerBean;
import lombok.Data;

/**
 * @AUTHOR: YYJ
 * @DATE : 2021/4/19 15:40
 * @Version 1.0
 */
@Data
public class CustomerMapperBean extends CustomerBean {

    /**
     * 客户id
     */
    private Long vehicleId;


    /**
     * 产品id
     */
    private Long productId;


    /**
     * 客户状态
     */
    private String orderStatus;
}
