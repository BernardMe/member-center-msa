package com.cheshun.mall.domain.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 提现状态
 * <p>
 * ①各个通道打款，仅需要根据订单状态码即可判断订单是否打款成功：1 成功，2 或 9 失败，15 取消。
 * ②对于状态1（已打款），在无退汇情况下是最终状态（退汇存在于银行卡通道，用户银行卡为 II/III 类银行卡收款超限额，导致打款先成功后退汇，此类情况较少）。
 *
 * @author 阿隆
 * Created on 2021/8/2 2:44 下午.
 */
public enum WithdrawStatus implements IEnum<String> {
    /**
     * 已受理
     * 支付订单接收成功，尚未处理（中间状态，不会回调）
     */
    ACCEPTED("0", "已受理"),
    /**
     * 已打款
     * 订单提交到支付网关成功（中间状态，会回调）
     */
    FINISHED("1", "已打款"),
    /**
     * 打款失败
     * 主要表示订单数据校验不通过（最终状态，会回调）
     */
    FAILED("2", "打款失败"),
    /**
     * 待打款（暂停处理）
     * 暂停处理，满足条件后会继续支付，例如账户余额不足，充值后可以继续打款（中间状态，会回调）
     */
    PENDING_PAUSE("4", "待打款（暂停处理）"),
    /**
     * 打款中（状态未知）
     * 调用支付网关超时等状态异常情况导致，处于等待交易查证的中间状态（中间状态，不会回调）
     */
    PENDING_UNKNOWN("5", "打款中（状态未知）"),
    /**
     * 待打款
     * 订单结算限额检查和风控判断完毕，等待执行打款的状态（中间状态，不会回调）
     */
    PENDING("8", "待打款"),
    /**
     * 打款失败（已退款，退汇或者冲正）
     * 支付被退回（最终状态，会回调）
     */
    REFUND("9", "打款失败(退汇)"),
    /**
     * 取消支付
     * 表示待打款（暂停处理）订单数据被商户主动取消（最终状态，会回调）
     */
    CANCELED("15", "已取消");

    @EnumValue
    @JsonValue
    private final String value;
    private final String describe;

    WithdrawStatus(String value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    @Override
    public String getValue() {
        return value;
    }

    public String getDescribe() {
        return describe;
    }

    @JsonCreator
    public static WithdrawStatus valueByCode(String code) {
        for (WithdrawStatus item : values()) {
            if (item.value.equals(code)) {
                return item;
            }
        }
        return null;
    }
}