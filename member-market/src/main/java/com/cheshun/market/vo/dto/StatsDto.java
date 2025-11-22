package com.cheshun.market.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 阿隆
 * Created on 2021/9/29 3:34 下午.
 */
@Data
@ToString
@ApiModel("统计信息")
public class StatsDto {
    /**
     * 统计概况
     */
    @ApiModelProperty("统计概况")
    private Stats general;
    /**
     * 代理商排行统计
     */
    @ApiModelProperty("代理商排行统计")
    private List<AgentStats> agentRank;

    @Data
    @ToString
    @ApiModel("统计概况")
    public static class Stats {
        /**
         * 新增代理人数
         */
        @ApiModelProperty("新增代理人数")
        private Integer increaseAgentCount = 0;
        /**
         * 新增代理人数同比增长
         */
        @ApiModelProperty("新增代理人数同比增长")
        private BigDecimal increaseAgentCountRate = BigDecimal.ZERO;
        /**
         * 累计代理人数
         */
        @ApiModelProperty("累计代理人数")
        private Integer totalAgentCount = 0;
        /**
         * 累计代理人数同比增长
         */
        @ApiModelProperty("累计代理人数同比增长")
        private BigDecimal totalAgentCountRate = BigDecimal.ZERO;
        /**
         * 累计1级代理人数
         */
        @ApiModelProperty("累计1级代理人数")
        private Integer totalAgent1Count = 0;
        /**
         * 累计2级代理人数
         */
        @ApiModelProperty("累计2级代理人数")
        private Integer totalAgent2Count = 0;
        /**
         * 累计3级代理人数
         */
        @ApiModelProperty("累计3级代理人数")
        private Integer totalAgent3Count = 0;
        /**
         * 新增发卡量
         */
        @ApiModelProperty("新增发卡量")
        private Integer increaseCardCount = 0;
        /**
         * 新增发卡量同比增长
         */
        @ApiModelProperty("新增发卡量同比增长")
        private BigDecimal increaseCardCountRate = BigDecimal.ZERO;
        /**
         * 累计发卡量
         */
        @ApiModelProperty("累计发卡量")
        private Integer totalCardCount = 0;
        /**
         * 累计发卡量同比增长
         */
        @ApiModelProperty("累计发卡量同比增长")
        private BigDecimal totalCardCountRate = BigDecimal.ZERO;
    }

    @Data
    @ToString
    @ApiModel("代理商统计")
    public static class AgentStats {
        /**
         * 代理商名称
         */
        @ApiModelProperty("代理商名称")
        private String agentName;
        /**
         * 发卡量
         */
        @ApiModelProperty("发卡量")
        private Integer cardCount = 0;
        /**
         * 通行笔数
         */
        @ApiModelProperty("通行笔数")
        private Integer passageTimes = 0;
        /**
         * 交易金额
         */
        @ApiModelProperty("交易金额")
        private String passageAmount = "";
    }
}
