package com.cheshun.price.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品报价历史记录
 * 用于追踪价格变化趋势
 */
@Data
@TableName("t_price_history")
public class PriceHistory {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 关联的商品报价ID
     */
    private Long priceId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 电商平台
     */
    private String platform;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 价格变化类型: UP-上涨, DOWN-下降, STABLE-稳定
     */
    private String priceChangeType;

    /**
     * 价格变化金额
     */
    private BigDecimal priceChange;

    /**
     * 记录时间
     */
    private LocalDateTime recordTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}