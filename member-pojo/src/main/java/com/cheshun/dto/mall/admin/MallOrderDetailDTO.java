package com.cheshun.dto.mall.admin;

import com.cheshun.cloudpos.MallOrderOmsResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MallOrderDetailDTO implements Serializable {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品图片")
    private String goodsImage;

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单编号")
    private String orderNumber;

    @ApiModelProperty(value = "货号")
    private String goodsCode;

    @ApiModelProperty(value = "是否处方药 0:否 1:是")
    private Integer isPrescription;

    @ApiModelProperty(value = "数量")
    private Integer goodsNumber;

    @ApiModelProperty(value = "门店编码")
    private String storeCode;

    @ApiModelProperty(value = "商品原价，4位小数")
    private Long retailPrice;

    @ApiModelProperty(value = "商品总金额，2位小数；原价*数量")
    private Long retailAmount;

    @ApiModelProperty(value = "折扣，默认0，2位小数")
    private Long discount;

    @ApiModelProperty(value = "折扣价，4位小数")
    private Long discountPrice;

    @ApiModelProperty(value = "折扣金额，2位小数")
    private Long discountAmount;

    @ApiModelProperty(value = "分摊单价，实际售价(分摊单价即这个商品实际最终售价)，4位小数")
    private Long sharePrice;

    @ApiModelProperty(value = "分摊金额(分摊金额指的是该商品的实际销售的总额)，2位小数")
    private Long shareAmount;

    @ApiModelProperty(value = "分摊折扣(与折扣价相同)，默认0，2位小数")
    private Long shareDiscount;

    @ApiModelProperty(value = "0：非赠品 1：赠品")
    private Integer isGift;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "更新时间")
    private String updateTime;

    @ApiModelProperty(value = "已退款数量")
    private Integer refundedQuantity;

    @ApiModelProperty(value = "已退款金额")
    private Long refundedAmount;

    @ApiModelProperty(value = "排序")
    private Integer goodsSort;

    @ApiModelProperty(value = "OMS批号列表")
    private List<MallOrderOmsResult> omsResultList;
}