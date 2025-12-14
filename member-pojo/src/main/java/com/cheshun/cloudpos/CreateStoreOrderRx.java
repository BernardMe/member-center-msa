package com.cheshun.cloudpos;

import lombok.*;


/**
 * 云pos-订单下账-请求参数(处方)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateStoreOrderRx {
    /**
     * 处方单号
     */
    private String rxNo;

    /**
     * 处方单中处方药种类数
     */
    private String skuCount;

    /**
     * 用药人姓名
     */
    private String patientName;

    /**
     * 性别
     * 0：男
     * 1：女
     */
    private String sex;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 医院
     */
    private String hospital;

    /**
     * 科室
     */
    private String section;

    /**
     * 开方医生
     */
    private String doctor;

    /**
     * 审方药师
     */
    private String apothecary;

    /**
     * 开方时间
     */
    private String prescribeTime;

    /**
     * 审方时间
     */
    private String checkingTime;

    /**
     * 处方图片
     */
    private String rxImgUrl;

    /**
     * 处方类型
     */
    private String rxType;
}
