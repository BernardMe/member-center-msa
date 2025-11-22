package com.cheshun.market.vo.query;

import com.cheshun.market.domain.entity.enums.AgentRole;
import com.cheshun.market.domain.entity.enums.TradeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * @author 阿隆
 * Created on 2021/7/20 9:20 下午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("分页查询代理订单参数")
public class AgentTradePageQuery extends PageQuery {
    /**
     * 代理商姓名
     */
    @ApiModelProperty("代理商姓名")
    private String realName;
    /**
     * 代理商手机号
     */
    @ApiModelProperty("代理商手机号")
    private String phone;
    /**
     * 代理商角色
     */
    @ApiModelProperty("代理商角色")
    private AgentRole role;
    /**
     * 订单类型
     */
    @ApiModelProperty("订单类型")
    private TradeType type;
    /**
     * 下单时间-开始
     */
    @ApiModelProperty("下单时间-开始")
    private Date beginDate;
    /**
     * 下单时间-结束
     */
    @ApiModelProperty("下单时间-结束")
    private Date endDate;
}
