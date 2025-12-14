package com.cheshun.auth.vo;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SmsRecordVo {

    /**
     * 发送手机号'
     */
    private String mobile;
    /**
     * 短信内容
     */
    private String content;
    /**
     * 0：通讯成功;1:发送成功；2：发送失败；3.通讯失败
     */
    private Integer state;
    /**
     * 失败描述
     */
    private String resultDesc;
    /**
     * 请求订单号
     */
    private String seqId;
    /**
     * 定时发送，yyyy-MM-dd HH:mm:ss
     */
    private String dsTime;
    /**
     * 用户自定义扩展（选填）。106码号后面扩展的部分
     */
    private String ext;
    /**
     * 系统生成的消息编号
     */
    private String msgId;
    /**
     * 通知结果
     */
    private String resultStatus;
    /**
     * 状态描述
     */
    private String statusDes;
    /**
     * (预留)业务来源 1:cls-admin
     */
    private String businessSrc;
    /**
     * 短信渠道1:诚立业 2:梦网 3:聚梦
     */
    private Integer smsChannel;
    /**
     * create_time
     */
    private LocalDateTime createTime;
    /**
     * update_time
     */
    private LocalDateTime updateTime;
}
