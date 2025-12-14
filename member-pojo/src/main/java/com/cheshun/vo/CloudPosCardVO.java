package com.cheshun.vo;

import lombok.Data;

/**
 * 根据手机号/卡号查询云pod会员信息
 * @author huowen
 */
@Data
public class CloudPosCardVO {
    //企业号
    private String entId;
    //手机号
    private String memberPhone;
    //姓名
    private String memberName;
    //可用状态
    private String enable;
    //邮箱
    private String email;
    //性别，1男，2女 0未知
    private String sex;
    //生日
    private String brithday;
    //详细地址
    private String detailedAddress;
    //省份
    private String province;
    //城市
    private String city;
    //区域
    private String region;
    //卡级别编码
    private String cardLevelNo;
    //卡级别名称
    private String cardLevelName;
    //会员卡号
    private String memberCardNo;
    //办卡渠道，1erp待客办卡，2企微代客办卡，3公众号自主，4医馆，5会员小程序，6商城小程序，7界面新增，8界面导入，9慢病
    private String channel;
    //门店编号
    private String storeCode;
    //办卡店员号
    private String clerkCode;
    //密码
    private String password;
    //头像
    private String headPort;
    //积分余额
    private Double pointsValue;
    //服务店员编码
    private String serviceWaiterCode;
    //服务门店编码
    private String serviceStoreCode;
    //服务专员名称
    private String serviceWaiterName;
    //办卡店员名称
    private String clerkName;
    //办卡门店名称
    private String storeName;
    //服务门店名称
    private String serviceStoreName;

}
