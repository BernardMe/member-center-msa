package com.cheshun.dto.order;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrdersExcelDTO {
    @ExcelProperty("门店编码")
    private String storeCode;
    @ExcelProperty("门店名称")
    private String storeName;
    @ExcelProperty("订单状态 1待付款 2待接单 3已接单 4已完成 5已取消 6已退款 7售后中 8配送中")
    private Integer status;
    @ExcelProperty("订单号")
    private String number;
    @ExcelProperty("下单时间")
    private String orderTime;
    @ExcelProperty("实收金额")
    private BigDecimal amount;
    @ExcelProperty("0:自提 1：闪送 2:邮寄发货")
    private Integer deliveryType;
    @ExcelProperty("省")
    private String receiveProvince;
    @ExcelProperty("市")
    private String receiveCity;
    @ExcelProperty("区")
    private String receiveArea;
    @ExcelProperty("处方号")
    private String prescriptionNumber;
    @ExcelProperty("配送类型")
    private String prescriptionType;
    @ExcelProperty("第三方配送类型")
    private String tranType;
    @ExcelProperty("配送单号")
    private String courierNumber;

}
