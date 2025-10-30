package com.zzjdyf.customer.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * ETC 客户类
 *
 * @AUTHOR: YYJ
 * @DATE : 2021/4/19 14:09
 * @Version 1.0
 */
@Data
public class CustomerEntity {

    /**
     * id
     */
    private Long id;
    /**
     * 客户号
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Timestamp created;
    /**
     * 状态 默认1
     */
    private String status;
    /**
     * 客户号
     */
    private Long accountId;
    /**
     * 当前状态
     */
    private Long currentStatus;
    /**
     * ETC卡类型
     */
    private String etcCardType;

    /**
     * 快递公司
     */
    private String expressCompany;
    /**
     * 快递单号
     */
    private String expressNumber;
    /**
     * 服务费
     */
    private String serviceRate;
    /**
     * 服务费描述
     */
    private String serviceRateDetail;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 客户状态
     */
    private String orderStatus;
    /**
     * 客户来源
     */
    private String sourceType;
    /**
     * 客户id
     */
    private Long carIds;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 修改时间
     */
    private Timestamp updateTime;
    /**
     * 收货地址
     */
    private String area;
    /**
     * 建行信用分
     */
    private Long ccbcreditScore;
    /**
     * etc信用分
     */
    private Long etccreditScore;
    /**
     * 视博金控信用分
     */
    private Long sbCreditScore;
    /**
     * 标普信用分
     */
    private Long bpCreditScore;
    /**
     * 信用分1
     */
    private Long creditScore1;
    /**
     * 信用分2
     */
    private Long creditScore2;
    /**
     * 信用分3
     */
    private Long creditScore3;
    /**
     * 信用分4
     */
    private Long creditScore4;
    /**
     * 客户id
     */
    private String orderId;
    /**
     *
     */
    private String dotNumber;
    /**
     *
     */
    private String dotName;
    /**
     *
     */
    private String transferType;
    /**
     * id
     */
    private String orderNo;
    /**
     * 合同签署状态,placeholder:合同签署状态:,title:合同签署状态
     */
    private String contractStatus;
    /**
     * 父客户编号
     */
    private String orderFatNo;
    /**
     * 网点地址
     */
    private String dotAddress;
    /**
     * 收件人电话
     */
    private String sendPhone;
    /**
     * 当前客户ETC卡额度
     */
    private Long quota;
    /**
     * 收货人姓名
     */
    private String receiveName;
    /**
     * 1 默认待办，2 已办
     */
    private Long quotaStatus;
    /**
     * 经办人ID
     */
    private String operId;
    /**
     * 经办时间
     */
    private java.sql.Timestamp operTime;
    /**
     * 复核人ID
     */
    private String checkId;
    /**
     * 复核人时间
     */
    private java.sql.Timestamp checkTime;
    /**
     * 经办人姓名
     */
    private String operName;
    /**
     * 复核人姓名
     */
    private String checkName;
    /**
     * 同盾分
     */
    private Long tongdunfen;
    /**
     * E-邮寄，S-自提
     */
    private String recvtype;
    /**
     * 资金方 040-中原银行，004-中国银行，003-农业银行
     */
    private String channelname;
    /**
     * 农行网点编号（推荐人工号）
     */
    private String channelDotNumber;
    /**
     * 是否发送视博下单，0：45转周未下单，1：45转周已下单，2：日周产品已下单,3第三方下单信息未校验不报送视博，4第三方下单信息已校验开始走风控不报送视博
     */
    private String isSendShiBo;
    /**
     * 排序字段
     */
    private String orderBy;
    /**
     * 需补交的保证金金额
     */
    private String cashAmt;
    /**
     * 下单失败时记录客户已绑定的卡号
     */
    private String etcCard;
    /**
     * 发行方
     */
    private String issuer;
    /**
     * 发行方编码
     */
    private String issuerCode;
    /**
     * 风险等级  0：一般风险；1：高风险  默认0
     */
    private Long riskGrade;
    /**
     * 审核类型  ：0:正常审核 ；1：免审 默认0
     */
    private Long auditType;
    /**
     * 免审风控标记状态，1已复核待处理，2已复核，空值为待复核，（审核类型为免审时该字段有效）
     */
    private Long auditStatus;
    /**
     * 二要素认证结果：0-不通过，1-通过
     */
    private String twoElementsVerify;

}
