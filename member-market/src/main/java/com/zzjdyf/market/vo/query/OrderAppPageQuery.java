package com.zzjdyf.market.vo.query;

import com.zzjdyf.market.domain.entity.enums.AgentRole;
import com.zzjdyf.market.domain.entity.enums.TradeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author wangzhuo
 * Created on 20210725
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("移动端-分页查询订单参数")
public class OrderAppPageQuery extends PageQuery {

    /**
     * 起时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("起时间")
    private Date startTime;
    /**
     * 止时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("止时间")
    private Date endTime;
    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private Long id;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @Size(min = 11, max = 11, message = "手机号长度为11位")
    private String phone;
    /**
     * 级别
     */
    @ApiModelProperty("级别")
    private AgentRole agentRole;
    /**
     * 订单类型
     */
    @ApiModelProperty("订单类型")
    private TradeType type;
}
