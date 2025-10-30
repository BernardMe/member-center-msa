package com.zzjdyf.market.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * 营销日统计信息表
 *
 * @author 阿隆
 * Created on 2021/9/29 2:44 下午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcDayStats extends BaseEntity {
    /**
     * 新增代理人数
     */
    private Integer increaseAgentCount = 0;
    /**
     * 累计1级代理人数
     */
    private Integer totalAgent1Count = 0;
    /**
     * 累计2级代理人数
     */
    private Integer totalAgent2Count = 0;
    /**
     * 累计3级代理人数
     */
    private Integer totalAgent3Count = 0;
    /**
     * 累计代理人数
     */
    private Integer totalAgentCount = 0;
    /**
     * 新增发卡量
     */
    private Integer increaseCardCount = 0;
    /**
     * 累计发卡量
     */
    private Integer totalCardCount = 0;
    /**
     * 统计日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date statsDate;
}
