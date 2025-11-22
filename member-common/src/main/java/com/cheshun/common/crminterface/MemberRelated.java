package com.cheshun.common.crminterface;

/**
 * 会员相关
 */
public class MemberRelated {

    // 小程序办卡接口   post
    public final static String CREATE_MEMBER_CARD = "/app/rm_vip/createVipForApplet";

    // 修改会员信息
    public static final String EDIT_MEMBER_CARD = "/app/rm_vip/updateMemberInfo";

    // 查询会员详情
    public static final String QUERY_MEMBER_CARD = "/app/rm_vip/getMemberByCardNoOrPhone";

    //查询会员剩余积分
    public static final String QUERY_MEMBER_POINTS = "/v2/rm/rm_vip/getMemberCardPoint";

    //修改会员信息
    public static final String EDIT_MEMBER_CARD_INFO = "/app/rm_vip/updateMemberInfo";

    //根据会员卡号查询电子钱包信息
    public static final String QUERY_MEMBER_CARD_PURSE_INFO = "/v2/rm/rm_vip/electronicaAccountsGet";

    //重置密码
    public static final String RESET_PURSE_PASSWORD = "/v2/rm/rm_vip/cardPassword";

    // 修改电子钱包密码
    public static final String EDIT_PURSE_PASSWORD = "/app/rm_vip/cardPasswordUpdate";

    // 储值卡查询
    public static final String QUERY_AMOUNT_COUNT = "/v2/rm/rm_vip/getAmountCount";
}
