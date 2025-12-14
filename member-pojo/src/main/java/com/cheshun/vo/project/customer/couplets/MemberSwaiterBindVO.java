package com.cheshun.vo.project.customer.couplets;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MemberSwaiterBindVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private Integer activityId;

    private String memberCardNo;
    /**
     * 待绑定服务门店编码
     */
    private String serviceStoreCode;
    /**
     * 待绑定服务专员编码
     */
    private String serviceWaiterCode;

}
