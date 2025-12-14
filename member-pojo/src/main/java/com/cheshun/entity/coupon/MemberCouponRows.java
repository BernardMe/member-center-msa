package com.cheshun.entity.coupon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 云POS-查询会员持有优惠券信息返回信息
 * data信息
 * rows信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCouponRows {
    /**
     *所属企业编号
     */
    private String belongEntId;
    /**
     *所属企业名称
     */
    private String belongEntName;
    /**
     *来源单据编号
     */
    private String billNo;
    /**
     *x 日起，y 天有效(Y)
     */
    private Integer canUseDays;
    /**
     *可核销渠道
     */
    private String channelCode;
    /**
     *可核销渠道名称
     */
    private String channelCodeName;
    /**
     *满足条件类型 1：满数量；2：
     * 满金额；
     */
    private Integer conditionTypeCode;
    /**
     *条件值
     */
    private BigDecimal conditionValue;
    /**
     *券说明
     */
    private String couponDescription;
    /**
     *优惠券使用限制(纯数字)
     */
    private BigDecimal couponLimit;
    /**
     *优惠券使用限制
     */
    private String couponLimitDesc;
    /**
     *方案名称
     */
    private String couponName;
    /**
     *方案编号
     */
    private String couponNo;
    /**
     *方案类型，优惠券类型：1：
     * 满减券；2：折扣券；3：代金
     * 券.4：运费券；6：礼品券
     */
    private Integer couponType;
    /**
     *方案类型名称
     */
    private String couponTypeName;
    /**
     *券面额
     */
    private BigDecimal couponValue;
    /**
     *使用渠道编号 1：ERP、2：
     * CRM、3：会员小程序、4：
     * 店员助手、5:微商城 6：医馆、
     * 7：慢病
     */
    private Integer distributeChannel;
    /**
     *结束时间
     */
    private String endTime;
    /**
     *会员券编号
     */
    private String memberCouponNo;
    /**
     *优惠券状态：1:未开始,2：未
     * 核销；3.已过期；4.已核销。
     * 5.作废
     */
    private String memberCouponState;
    /**
     *会员名称
     */
    private String memberName;
    /**
     *会员卡号
     */
    private String memberNo;
    /**
     *会员手机号
     */
    private String memberPhone;
    /**
     *x 日起，y 天有效(X)
     */
    private Integer notUseDays;
    /**
     * 密码
     */
    private String passWord;
    /**
     *零售单金额
     */
    private BigDecimal orderAmount;
    /**
     *派发渠道
     */
    private String receiveChannel;
    /**
     *派发时间
     */
    private String receiveDate;
    /**
     *领取门店编码
     */
    private String receiveStoreCode;
    /**
     *领取门店名称
     */
    private String receiveStoreName;
    /**
     *生效时间
     */
    private String startTime;
    /**
     *优惠券有效期
     */
    private String validityDate;
    /**
     *优惠券有效期时间
     */
    private String validityTime;
    /**
     *减免金额，核销金额
     */
    private BigDecimal writeOffAmount;
    /**
     *核销渠道编码，1：ERP、2：
     * 微商城、3：医馆、4：手动核
     * 销
     */
    private Integer writeOffChannelCode;
    /**
     *核销码
     */
    private String writeOffCode;
    /**
     *核销门店编码
     */
    private String writeOffStoreCode;
    /**
     *核销门店名称
     */
    private String writeOffStoreName;
    /**
     *核销时间
     */
    private String writeOffTime;
}