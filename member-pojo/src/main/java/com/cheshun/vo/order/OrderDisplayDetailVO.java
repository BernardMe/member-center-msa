package com.cheshun.vo.order;

import com.cheshun.vo.store.StoreInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("从订单列表查询单个订单详情")
public class OrderDisplayDetailVO {


    @ApiModelProperty(value = "会员卡号", hidden = true)
    private String memberCardNo;

    @ApiModelProperty(value = "订单ID", hidden = true)
    private Long id;

    @ApiModelProperty("订单编号")
    private String orderNumber;

    @ApiModelProperty("订单状态 0: 全部 1：未付款 2：待发货 3:待收货 4:已完成 5已取消 6已退款 7.售后中")
    private Integer orderStatus;

    @ApiModelProperty("订单备注")
    private String remark;

    @ApiModelProperty("门店信息")
    private StoreInfoVO storeInfoVO;

    @ApiModelProperty("门店编号")
    private String storeCode;

    @ApiModelProperty("下单时间")
    private String orderTime;

    /**
     * 订单金额 对应 real_pay_amount
     */
    @ApiModelProperty("订单金额")
    private String orderAmount;

    @ApiModelProperty(value = "配送方式 0：自提 1：闪送 2：邮寄发货")
    private Integer deliveryType;


    @ApiModelProperty("商品详情")
    private List<UserOrderDisPlayDetailItemVO> userOrderDisPlayDetailItemVO;






}
