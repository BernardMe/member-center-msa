package com.zzjdyf.market.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 代理商日统计信息
 *
 * @author 阿隆
 * Created on 2021/9/29 2:44 下午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcAgentDayStats extends BaseEntity {
    /**
     * 代理商id
     */
    private Long agentId;
    /**
     * 发卡量
     */
    private Integer cardCount = 0;
    /**
     * 通行笔数
     */
    private Integer passageTimes = 0;
    /**
     * 交易金额
     */
    private BigDecimal passageAmount = BigDecimal.ZERO;
    /**
     * 统计日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date statsDate;
}
