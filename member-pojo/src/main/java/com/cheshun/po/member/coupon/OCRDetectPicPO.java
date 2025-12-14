package com.cheshun.po.member.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OCRDetectPicPO {
    //是否包含待验证关键字
    private Byte isContained;
}
