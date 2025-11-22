package com.cheshun.market.vo.query;

import com.cheshun.market.domain.entity.enums.GoodsType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author wangzhuo
 * Created on 20211112
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("分页查询代理商品管理")
public class OrderGoodsMngAppPageQuery extends PageQuery {
    /**
     * 传入的代理商id
     */
    @ApiModelProperty("传入的代理商id")
    private Long agentId;
    /**
     * 类型(1:卡 2:设备)
     */
    @NotNull(message = "请选择卡或设备")
    @ApiModelProperty("类型(1:卡 2:设备)")
    private GoodsType type;
}
