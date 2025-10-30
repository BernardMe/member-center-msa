package com.zzjdyf.market.vo.command;

import com.zzjdyf.market.domain.entity.enums.GoodsType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author wangzhuo
 * Created on 20211112
 */
@Data
@ToString
@ApiModel("处理退货订单")
public class DealReturnCommand {
    /**
     * 订单id
     */
    @NotNull(message = "请选择订单")
    @ApiModelProperty("id")
    private Long id;
    /**
     * 商品类型(卡|设备)
     */
    private GoodsType goodsType;
    /**
     * 是否降低规格
     */
    private Integer isReduceQuantity;
    /**
     * 降低后规格
     */
    private Long newSkuId;
}
