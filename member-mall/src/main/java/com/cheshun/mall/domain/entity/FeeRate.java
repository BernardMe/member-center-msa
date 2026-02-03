package com.cheshun.mall.domain.entity;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FeeRate {

    private final BigDecimal rate; // 例如：0.01 表示 1%

    private FeeRate(BigDecimal rate) {
        this.rate = rate;
    }

    public static FeeRate ofPercentage(BigDecimal percentage) {
        Assert.notNull(percentage, "percentage is null");
        Assert.isTrue(percentage.compareTo(BigDecimal.ZERO) >= 0, "费率不能为负数");
        Assert.isTrue(percentage.compareTo(BigDecimal.valueOf(100)) <= 0, "费率不能超过100%");
        return new FeeRate(percentage.movePointLeft(2)); // 转换为小数
    }

    public static FeeRate ofDecimalRate(BigDecimal rate) {
        Assert.notNull(rate, "rate is null");
        Assert.isTrue(rate.compareTo(BigDecimal.ZERO) >= 0, "费率不能为负数");
        Assert.isTrue(rate.compareTo(BigDecimal.ONE) <= 0, "费率不能超过1");
        return new FeeRate(rate);
    }

    public Money calculateFee(Money money, RoundingMode roundingMode) {
        Assert.notNull(money, "money is null");
        BigDecimal feeInYuan = money.getYuan()
                .multiply(rate)
                .setScale(2, roundingMode);
        return Money.ofYuan(feeInYuan);
    }

    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getPercentage() {
        return rate.movePointRight(2);
    }

    @Override
    public String toString() {
        return String.format("%s%%", getPercentage());
    }
}
