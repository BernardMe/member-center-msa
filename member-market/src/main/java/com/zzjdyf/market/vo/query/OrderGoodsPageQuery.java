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
 * @author 阿隆
 * Created on 2021/7/20 9:20 下午.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@ApiModel("分页查询订单商品参数")
public class OrderGoodsPageQuery extends PageQuery {
    /**
     * 类型(1:卡 2:设备)
     */
    @NotNull(message = "请选择查询类型")
    @ApiModelProperty("类型(1:卡 2:设备)")
    private GoodsType type;
    /**
     * 代理商姓名
     */
    @ApiModelProperty("代理商姓名")
    private String realName;
    /**
     * 代理商手机号
     */
    @ApiModelProperty("代理商手机号")
    private String phone;
    /**
     * 代理商级别
     */
    @ApiModelProperty("代理商级别")
    private Integer role;
    /**
     * ETC卡号
     */
    @ApiModelProperty("ETC卡号")
    private String cardSn;
    /**
     * 卡状态
     */
    @ApiModelProperty("卡状态")
    private ProductStatus cardStatus;
    /**
     * OBU标签号
     */
    @ApiModelProperty("OBU标签号")
    private String deviceSn;
    /**
     * 设备状态
     */
    @ApiModelProperty("设备状态")
    private ProductStatus deviceStatus;
}
