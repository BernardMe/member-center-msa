package com.cheshun.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberReceiveCoupon {
    /**
     * 派发渠道,1：新用户赠送，2：首次关注赠送，3：会员资料完善奖励，4：签到奖励，5：券使用赠券，6：拉新奖励，7：特殊日期赠送，8：预流失挽回，9：券过期赠券，10：员工派送，11：批量赠送，12：会员自领，13：H5 赠送,14:抽奖，15:权益礼包,16:消费赠送 ,17: 合约发放 ,19: 促销买赠,20:慢病健康自测 ,21: 积分兑换
     */
    private Integer channelCode;
    /**
     * 派发终端编号
     */
    private final Integer couponDistributeChannelCode=3;
    /**
     * 优惠券方案
     */
    private ArrayList<String> couponNoList;
    /**
     * 会员号
     */
    private String memberNo;
    /**
     * 门店编号
     */
    private String storeCode;
}
