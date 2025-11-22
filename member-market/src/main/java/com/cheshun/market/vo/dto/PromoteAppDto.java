package com.cheshun.market.vo.dto;

import com.cheshun.market.domain.entity.enums.ActiveMethod;
import com.cheshun.market.domain.entity.enums.AgentRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("推广明细信息")
public class PromoteAppDto extends BaseDto {
    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String agentRealName;
    /**
     * 角色
     */
    @ApiModelProperty("角色")
    private AgentRole agentRole;
    /**
     * 角色名称
     */
    @ApiModelProperty("角色名称")
    private String agentRoleName;
    /**
     * 激活方式
     */
    @ApiModelProperty("激活方式")
    private ActiveMethod method;
    /**
     * 激活方式名称
     */
    @ApiModelProperty("激活方式名称")
    private String methodName;
    /**
     * 激活的车牌号
     */
    @ApiModelProperty("激活的车牌号")
    private String carNo;
    /**
     * 车牌号颜色
     */
    @ApiModelProperty("车牌号颜色")
    private String carColor;
    /**
     * 激活的ETC卡号
     */
    @ApiModelProperty("激活的ETC卡号")
    private String cardSn;
    /**
     * 激活的OBU标签号
     */
    @ApiModelProperty("激活的OBU标签号")
    private String deviceSn;
    /**
     * 激活奖励金额(元)
     */
    @ApiModelProperty("激活奖励金额(元)")
    private BigDecimal activeBonus;
    /**
     * 首次通行金额(元)
     */
    @ApiModelProperty("首次通行金额(元)")
    private BigDecimal firstConsumeBonus;
    /**
     * 首次消费时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("首次消费时间")
    private Date firstConsumeTime;
}
