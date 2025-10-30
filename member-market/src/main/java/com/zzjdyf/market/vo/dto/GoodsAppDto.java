package com.zzjdyf.market.vo.dto;

import com.zzjdyf.market.domain.entity.enums.DepositStatus;
import com.zzjdyf.market.domain.entity.enums.PublishStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("设备商品信息")
public class GoodsAppDto extends BaseDto {
    /**
     * 品牌
     */
    @ApiModelProperty("品牌")
    private String brand;
    /**
     * 型号
     */
    @ApiModelProperty("型号")
    private String model;
    /**
     * 图片
     */
    @ApiModelProperty("图片")
    private List<String> imageList;
    /**
     * 详情
     */
    @ApiModelProperty("详情")
    private String details;
    /**
     * 库存
     */
    @ApiModelProperty("库存")
    private Integer stock;
    /**
     * 销量
     */
    @ApiModelProperty("销量")
    private Long sales;
    /**
     * 状态(未上架|已上架)
     */
    @ApiModelProperty("状态")
    private PublishStatus status;
    /**
     * 押金状态
     */
    @ApiModelProperty("押金状态")
    private DepositStatus depositStatus;
    /**
     * 规格列表
     */
    @ApiModelProperty("规格列表")
    private List<GoodsSkuAdminDto> skuList;
}
