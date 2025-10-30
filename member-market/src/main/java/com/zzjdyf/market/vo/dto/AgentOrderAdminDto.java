package com.zzjdyf.market.vo.dto;

import com.zzjdyf.market.domain.entity.enums.AgentRole;
import com.zzjdyf.market.domain.entity.enums.DepositStatus;
import com.zzjdyf.market.domain.entity.enums.OrderStatus;
import com.zzjdyf.market.domain.entity.enums.TradeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 阿隆
 * Created on 2021/7/29 11:07 下午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("代理商订单信息")
public class AgentOrderAdminDto extends BaseDto {
    /**
     * 类型
     */
    @ApiModelProperty("类型")
    private TradeType type;
    /**
     * 代理商id
     */
    @ApiModelProperty("代理商id")
    private Long agentId;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String agentPhone;
    /**
     * 真实姓名
     */
    @ApiModelProperty("真实姓名")
    private String agentRealName;
    /**
     * 角色
     */
    @ApiModelProperty("角色")
    private AgentRole agentRole;
    /**
     * 审核人
     */
    @ApiModelProperty("审核人")
    private String operator;
    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long goodsId;
    /**
     * 商品规格id
     */
    @ApiModelProperty("商品规格id")
    private Long goodsSkuId;
    /**
     * 待审核商品规格id
     */
    @ApiModelProperty("待审核商品规格id")
    private Long auditGoodsSkuId;
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
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer quantity;
    /**
     * 需缴纳押金(元)
     */
    @ApiModelProperty("需缴纳押金(元)")
    private BigDecimal deposit;
    /**
     * 待审核须缴纳押金(元)
     */
    private BigDecimal auditDeposit;
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
     * 实缴纳押金(元)
     */
    @ApiModelProperty("实缴纳押金(元)")
    private BigDecimal paidDeposit;
    /**
     * 押金状态
     */
    @ApiModelProperty("押金状态")
    private DepositStatus depositStatus;
    /**
     * 申请卡数量
     */
    private Integer cardQuantity;
    /**
     * 申请设备数量
     */
    private Integer deviceQuantity;
    /**
     * 收货人姓名
     */
    @ApiModelProperty("收货人姓名")
    private String consignee;
    /**
     * 收货人电话
     */
    @ApiModelProperty("收货人电话")
    private String consigneePhone;
    /**
     * 收货地址
     */
    @ApiModelProperty("收货地址")
    private String consigneeAddress;
    /**
     * 订单状态
     */
    @ApiModelProperty("订单状态")
    private OrderStatus orderStatus;
    /**
     * 是否降低规格
     */
    private Integer isReduceQuantity;
    /**
     * 是否可补货
     */
    @ApiModelProperty("是否可补货")
    private boolean canReplenish;
    /**
     * 订单商品详情列表
     */
    @ApiModelProperty("订单商品详情列表")
    private List<OrderDetailGoodsAdminDto> orderDetailGoodsList;

    @ApiModelProperty("可降低的规格列表")
    private List<Long> goodsSkuIds;
}
