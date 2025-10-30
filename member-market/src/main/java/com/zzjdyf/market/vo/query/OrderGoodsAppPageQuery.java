package com.zzjdyf.market.vo.query;

import com.zzjdyf.market.domain.entity.enums.GoodsType;
import com.zzjdyf.market.domain.entity.enums.ProductStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author wangzhuo
 * Created on 20210725
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("分页查询代理产品明细")
public class OrderGoodsAppPageQuery extends PageQuery {
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
    /**
     * 查询类型(1:未激活 2:已激活 3:已损坏 4:损坏已退还)
     */
    @ApiModelProperty("查询类型(1:未激活 2:已激活 3:已损坏 4:损坏已退还)")
    private ProductStatus status;
}
