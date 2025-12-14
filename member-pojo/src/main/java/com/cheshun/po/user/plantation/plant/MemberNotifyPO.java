package com.cheshun.po.user.plantation.plant;

import lombok.Data;

/**
 * 会员端公告信息PO
 * @author wangzhuo
 * @date 20240802
 */
@Data
public class MemberNotifyPO<T> {

    //会员卡号
    private String vipCardNo;

    //判断是否弹窗
    private Byte isPopup;


    private T notification;



}
