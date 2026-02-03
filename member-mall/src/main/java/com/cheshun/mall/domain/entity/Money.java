package com.cheshun.mall.domain.entity;

import org.springframework.util.Assert;

import java.beans.Transient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 资金类交易业务(如电商交易、支付)中，经常提到的Money类。
 * <p>该类是不可变的（immutable）（见{@link #add}，会返回新的Money），并且实现了Comparable和Serializable接口。
 * <p><a href="https://mp.weixin.qq.com/s/34JEFRrV_b6J_8Z_O3MbLg">原始文章</a>
 *
 * @author zhangguozhan
 * @date 2025-01-07 14:40
 * @see FeeRate
 */
public class Money implements Serializable, Comparable<Money> {
    private static final long serialVersionUID = 1L;

    //    private BigDecimal yuan;
    private long fen;


    /**
     * 私有构造函数，确保通过工厂方法{@link #ofYuan,#ofFen}来创建实例。
     *
     * @param fen
     */
    public Money(long fen) {
        this.fen = fen;
    }

    public static Money ofYuan(BigDecimal yuan) {
        Assert.notNull(yuan, "yuan is null");
        if (yuan.scale() > 2) {
            throw new IllegalArgumentException("元 目前只支持两位小数位数");
        }
        long fen = yuan.movePointRight(2).longValue();
        Assert.isTrue(fen >= 0, "元必须大于等于0");
        return new Money(fen);
    }

    public static Money ofFen(Long fen) {
        Assert.notNull(fen, "fen is null");
        Assert.isTrue(fen >= 0, "分必须大于等于0");
        return new Money(fen);
    }

    public long getFen() {
        return fen;
    }

    public BigDecimal getYuan() {
        return convertFen2Yuan();
    }

    @Transient
    public String getYuanString() {
        return String.valueOf(convertFen2Yuan());
    }

    /**
     * 加法操作，返回新的Money实例。（注意Money类是不可变类，此操作不会修改当前Money对象的属性值）
     *
     * @param other
     * @return
     */
    public Money add(Money other) {
        Assert.notNull(other, "other is null");
        Assert.notNull(other.fen, "other.yuan is null");
        long sum = this.fen + other.fen;
        return ofFen(sum);
    }


    /**
     * 计算手续费，保留2位小数（四舍五入）
     *
     * @param feeRate
     * @return
     */
    public Money calculateFee(FeeRate feeRate) {
        return calculateFee(feeRate, RoundingMode.HALF_UP);//HALF_UP 表示 四舍五入
    }

    /**
     * 计算手续费，保留2位小数（全舍）
     *
     * @param feeRate
     * @return
     */
    public Money calculateFeeByTruncation(FeeRate feeRate) {
        return calculateFee(feeRate, RoundingMode.DOWN);//DOWN 表示 直接舍去
    }

    /**
     * 计算手续费，保留2位小数-protected（按指定的RoundingMode来计算）
     * （本方法仅在本包内测试使用，对外暴露最好包装一下roundingMode，从而规避因个别开发者对RoundingMode了解不足带来的资金bug）
     *
     * @param feeRate
     * @param roundingMode
     * @return
     */
    protected Money calculateFee(FeeRate feeRate, RoundingMode roundingMode) {
        Assert.notNull(feeRate, "feeRatePercentage is null");
        return feeRate.calculateFee(this, roundingMode);
    }

    @Override
    public int compareTo(Money obj) {
        return Long.compare(fen, obj.fen);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof Money) return compareTo((Money) obj) == 0;
        return false;
    }

    @Override
    public String toString() {
        return String.format("%s元(%s分)", convertFen2Yuan(), fen);
    }


    private BigDecimal convertFen2Yuan() {
        return BigDecimal.valueOf(fen).movePointLeft(2);
    }
}
