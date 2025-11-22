package com.cheshun.market.vo.dto;

import com.cheshun.market.domain.entity.enums.PublishStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("商品规格信息")
public class GoodsSkuAdminDto extends BaseDto {
    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long goodsId;
    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer quantity;
    /**
     * 押金(元)
     */
    @ApiModelProperty("押金(元)")
    private BigDecimal deposit;
    /**
     * 补货比例
     */
    @ApiModelProperty("补货比例")
    private BigDecimal replRatio;
    /**
     * 补货时是否全补
     */
    @ApiModelProperty("补货时是否全补")
    private Boolean fullRepl;
    /**
     * 状态(未上架|已上架)
     */
    @ApiModelProperty("状态")
    private PublishStatus status;
}
