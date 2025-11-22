package com.cheshun.market.vo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author 阿隆
 * Created on 2021/7/29 8:43 上午.
 */
@Data
@ToString
@ApiModel("分润概况统计")
public class AgentDashboardAdminVo {
    /**
     * 一级代理数量
     */
    @ApiModelProperty("一级代理数量")
    private int agent1Count=0;
    /**
     * 二级代理数量
     */
    @ApiModelProperty("二级代理数量")
    private int agent2Count=0;
    /**
     * 三级代理数量
     */
    @ApiModelProperty("三级代理数量")
    private int agent3Count=0;
}
