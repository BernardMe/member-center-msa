package com.zzjdyf.market.vo.dto;

import com.zzjdyf.market.domain.entity.enums.GoodsType;
import com.zzjdyf.market.domain.entity.enums.ProductStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 * @author wangzhuo
 * Created on 20211108
 */
@Data
@ToString
@ApiModel("订单商品详情列表信息")
public class OrderDetailGoodsAdminDto {
    /**
     * 商品类型(卡|设备)
     */
    @ApiModelProperty("商品类型")
    private GoodsType goodsType;
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
    @ApiModelProperty("卡状态描述")
    private String cardStatusStr;
    /**
     * 设备状态(待激活|已激活｜已损坏｜已损坏并退还)
     */
    @ApiModelProperty("设备状态")
    private ProductStatus deviceStatus;
    /**
     * 设备状态名称
     */
    @ApiModelProperty("设备状态描述")
    private String deviceStatusStr;

}
