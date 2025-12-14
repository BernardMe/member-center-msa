package com.cheshun.mall.feign.sms.chengliye.request;

import lombok.*;

/**
 * 功能描述 实际描述替换此处
 *
 * @author xueqing wang
 * @date 2020/12/23 15:03
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChengLiYeGetRequest {


    /**
     * 用户名
     */
    private String userName;


    /**
     * 短信手机号
     */
    private String mobile;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 定时时间，为空时表示立即发送（选填）
     * 格式：yyyy-MM-dd HH:mm:ss
     * 例如：2016-09-10 09:00:00
     */
    private String dsTime;

    /**
     * 用户自定义扩展（选填）。106码号后面扩展的部分。需要和后台人员确认权限
     */
    private String ext;
    /**
     * 客户自定义消息id（选填），如果客户不填则由平台侧生成唯一编号
     * 注：不要带|字符
     */
    private String seqId;

    /**
     * 是否国际短信（选填），不填则默认为0。
     * 0-	国内短信（不包含港澳台）
     * mobile字段不要带86国家区域识别码。
     * 1-	国际短信（包含港澳台）
     * mobile字段带上国家区域代码。如果值是1，不要发送国内号码
     */
    private Integer isms;

    /**
     * 签名字段 ，可为空
     */
    private String sign;
}
