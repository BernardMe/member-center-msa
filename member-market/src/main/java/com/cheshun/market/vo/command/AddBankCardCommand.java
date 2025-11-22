package com.cheshun.market.vo.command;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 阿隆
 * Created on 2021/8/4 1:28 下午.
 */
@Data
@ToString
@ApiModel("添加银行卡")
public class AddBankCardCommand {
//    /**
//     * 姓名
//     */
//    @NotNull(message = "请输入姓名")
//    @Size(min = 2, max = 20, message = "姓名长度为2-20位")
//    @ApiModelProperty("姓名")
//    private String name;
//    /**
//     * 身份证
//     */
//    @NotNull(message = "请输入身份证")
//    @Size(min = 18, max = 18, message = "身份证长度为18位")
//    @ApiModelProperty("身份证")
//    private String idCard;
    /**
     * 银行开户卡号
     */
    @NotNull(message = "请输入银行卡卡号")
    @Size(min = 5, max = 32, message = "银行卡号长度为5～32位")
    private String cardNo;
}
