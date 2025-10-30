package com.zzjdyf.market.vo.dto;

import com.zzjdyf.market.domain.entity.enums.AgentRole;
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
public class OrderGoodsAdminDto extends BaseDto {
    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private Long orderId;
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
     * 上级内部员工id
     */
    @ApiModelProperty("上级内部员工id")
    private Long staffId;
    /**
     * 上级代理(一级)id
     */
    @ApiModelProperty("上级代理(一级)id")
    private Long agent1Id;
    /**
     * 上级代理(二级)id
     */
    @ApiModelProperty("上级代理(二级)id")
    private Long agent2Id;
    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private Long goodsId;
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
     * 设备状态(待激活|已激活｜已损坏｜已损坏并退还)
     */
    @ApiModelProperty("设备状态")
    private ProductStatus deviceStatus;
}
