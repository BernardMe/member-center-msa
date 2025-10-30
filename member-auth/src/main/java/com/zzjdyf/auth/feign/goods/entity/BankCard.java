package com.zzjdyf.auth.feign.goods.entity;

import lombok.*;

/**
 * Created by xueqing wang on 2021/4/21 17:33
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankCard extends OCR{
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 卡名称
     */
    private String cardName;
    /**
     * 卡类型card_type
     */
    private String cardType;
    /**
     * 有效期限valid_start
     */
    private String validStart;
    /**
     * 有效期限valid_thru
     */
    private String validThru;
    /**
     * 银行卡号
     */
    private String cardNo;
}
