package com.cheshun.cloudpos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 云pos-订单下账-请求参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateStoreOrder {
    /**
     * 订单编号
     */
    private String billNo;

    /**
     * 出库单：LSCK
     * 退款单：LSTH
     */
    private String billType;

    /**
     * 原单号（订单编号）
     */
    private String srcBillNo;

    /**
     * 门店编码
     */
    private String storeNo;

    /**
     * 1：O2O
     * 2：B2C
     * 3：服务类型
     * 4：其他
     */
    private String orderType;

    /**
     * O2O
     * B2C
     * 服务类型
     * 其他
     */
    private String orderTypeName;

    /**
     * 来源类型
     * OMS：OMS
     * B2C：B2C
     * O2O：O2O
     */
    private String caller;

    /**
     * 新版本若传此字段，根据此字段控制下账方式，若不传此字段，根据“是否按批号下账”字段控制下账方式；
     * 业务处理方式：
     * 0：按商品下账
     * 1：按批号下账
     * 2：按批次下账
     */
    private String bizType;

    /**
     * 是否按批号下账（旧版本使用此字段）
     * 0：按商品下账
     * 1：按批号下账
     */
    private Integer isLot;

    /**
     * 收货人手机号
     */
    private String receivePhone;

    /**
     * 下单时间
     * yyyy-MM-dd hh24:mi:ss
     */
    private String createTime;

    /**
     * 接单时间
     * yyyy-MM-dd hh24:mi:ss
     */
    private String takeOrderTime;

    /**
     * 支付时间
     * yyyy-MM-dd hh24:mi:ss
     */
    private String payTime;

    /**
     * 是否OTC药品
     *
     * 只要存在一个品种为OTC药品就需要传是，没有OTC品种就传否，此字段用来记录这个订单是否含有OTC药品）
     * 0代表否，1代表是
     */
    private Integer isOTC;

    /**
     *
     */
    private BigDecimal postFee;

    /**
     * 顾客实际支付配送费
     * 2位小数
     */
    private BigDecimal realPostFee;

    /**
     * 订单总额（订单总金额，商品 + 运费 + 包装费）
     */
    private BigDecimal amount;

    /**
     * 下账金额
     * 用户实付的金额
     */
    private BigDecimal accountAmount;

    /**
     * 用户线上付款金额（用户线上支付，送货单使用）
     */
    private BigDecimal userPayAmount;

    /**
     * 总付款金额（用户支付 + 平台承担优惠）
     */
    private BigDecimal realPayAmount;

    /**
     * 指顾客对商品的实付金额
     */
    private BigDecimal userGoodsAmount;

    /**
     * 是否开发票，1开，0不开。
     */
    private Integer isNeedInvoice;

    /**
     * 支付状态（已支付、货到付款）
     */
    private String payStatus;

    /**
     * 当日订单流水号（订单小号）
     */
    private String orderIndex;

    /**
     * 拣货操作员信息
     */
    private String pickerNo;

    /**
     * 是否专送（0：是；1：否）
     */
    private String isSelfDeliver;

    /**
     * 是否转自送（0：否；1：是）
     */
    private String isChangeSelfDeliver;

    /**
     * 0:未签收;1:已签收
     */
    private Integer isSign;

    /**
     * 商家承担运费优惠的商品总额
     *
     * 比如我们商城这边一个单子5元的运费，但是满减把运费免了，那传5即可
     */
    private BigDecimal apportionedAmount;

    /**
     *  会员编号
     */
    private String vipCardNo;
}
