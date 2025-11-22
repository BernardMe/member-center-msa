package com.cheshun.market.vo.command;

import com.cheshun.market.domain.entity.enums.PublishStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author wangzhuo
 * Created on 20210721
 */
@Data
@ToString
@ApiModel("新增商品")
public class AddGoodsCommand {
    /**
     * 品牌
     */
    @NotNull(message = "请选择品牌")
    @ApiModelProperty("品牌")
    private String brand;
    /**
     * 型号
     */
    @NotNull(message = "请选择型号")
    @ApiModelProperty("型号")
    private String model;
    /**
     * 图片
     */
    @NotNull(message = "请上传图片")
    @Size(min = 1, max = 5, message = "图片只能上传1-5张")
    @ApiModelProperty("图片")
    private List<String> imageList;
    /**
     * 详情
     */
    @NotNull(message = "请输入详情")
    @Size(min = 2, max = 500, message = "详情长度为2-500位")
    @ApiModelProperty("详情")
    private String details;
    /**
     * 库存
     */
    @NotNull(message = "请输入库存")
    @Min(value = 1, message = "库存不能低于1件")
    @ApiModelProperty("库存")
    private Integer stock;
    /**
     * 状态(未上架|已上架)
     */
    @NotNull(message = "请选择状态")
    @ApiModelProperty("状态")
    private PublishStatus status;
    /**
     * 规格
     */
    @NotNull(message = "请添加规格")
    @Size(min = 1, max = 5, message = "只能添加1-5个规格")
    @ApiModelProperty("规格")
    private List<Sku> skuList;

    @Data
    public static class Sku {
        /**
         * 数量
         */
        @NotNull(message = "请输入数量")
        @Min(value = 1, message = "数量不能低于1件")
        @ApiModelProperty("数量")
        private Integer quantity;
        /**
         * 押金(元)
         */
        @NotNull(message = "请输入押金")
        @DecimalMin(value = "0.01", message = "押金不能低于0.01元")
        @ApiModelProperty("押金(元)")
        private BigDecimal deposit;
        /**
         * 补货比例
         */
        @NotNull(message = "请输入补货比例")
        @DecimalMin(value = "0.01", message = "补货比例不能低于1%")
        @ApiModelProperty("补货比例")
        private BigDecimal replRatio;
        /**
         * 补货时是否全补
         */
        @NotNull(message = "请选择补货时是否全补")
        @ApiModelProperty("补货时是否全补")
        private Boolean fullRepl;
        /**
         * 状态(未上架|已上架)
         */
        @NotNull(message = "请选择状态")
        @ApiModelProperty("状态")
        private PublishStatus status;
    }
}
