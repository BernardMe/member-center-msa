package com.zzjdyf.market.vo.dto;

import com.zzjdyf.market.domain.entity.enums.ProductStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("商品信息")
public class OrderGoodsAppDto extends BaseDto {
    /**
     * ETC卡号
     */
    @ApiModelProperty("ETC卡号")
    private String cardSn;
    /**
     * OBU标签号
     */
    @ApiModelProperty("OBU标签号")
    private String deviceSn;
    /**
     * 卡状态(待激活|已激活｜已损坏｜已损坏并退还)
     */
    @ApiModelProperty("卡状态")
    private ProductStatus cardStatus;
    /**
     * 卡状态名称
     */
    @ApiModelProperty("卡状态名称")
    private String cardStatusName;
    /**
     * 设备状态(待激活|已激活｜已损坏｜已损坏并退还)
     */
    @ApiModelProperty("设备状态")
    private ProductStatus deviceStatus;
    /**
     * 设备状态名称
     */
    @ApiModelProperty("设备状态名称")
    private String deviceStatusName;
}
