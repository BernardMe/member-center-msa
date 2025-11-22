package com.cheshun.common.crminterface;

/**
 * 积分相关
 */
public class IntegralRelated {

    // 通用添积分   POST
    public static final String ADD_INTEGRAL = "/v2/rm/rm_vip/addPointForGeneral";

    // 通用减积分   POST
    public static final String DEL_INTEGRAL = "/v2/rm/rm_vip/reducePointForGeneral";

    // 积分兑换优惠券   POST
    public static final String EXCHANGE_TO_COUPON = "app/rm_vip/couponExchange";

    // 查询会员剩余积分   POST
    public static final String QUERY_INTEGRAL = "/v2/rm/rm_vip/getMemberCardPoint";
}
