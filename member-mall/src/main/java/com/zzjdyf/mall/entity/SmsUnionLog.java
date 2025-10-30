package com.zzjdyf.mall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zzjdyf.mall.vo.SmsRecordVo;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 联合短信记录
 */
@Data
@TableName(value = "t_sms_union_log")
public class SmsUnionLog {

    private Long id;
    private String mobile;//发送手机号
    private String content;//客户号短信内容
    private Integer state;//0：通讯成功;1:发送成功；2：发送失败；3.通讯失败
    private String result_desc;//失败描述
    private String seq_id;//请求订单号
    private String ds_time;//定时发送，yyyy-MM-dd HH:mm:sss
    private String ext;//用户自定义扩展（选填）。106码号后面扩展的部分。需要和后台人员确认权限
    private String msg_id;//系统生成的消息编号
    private String result_status;//通知结果
    private String status_des;//状态描述
    private String business_src;//(预留)业务来源 1:cls-admin
    private Integer sms_channel;//短信渠道1:诚立业 2:梦网 3:聚梦
    private LocalDateTime create_time;//
    private LocalDateTime update_time;//

    public SmsUnionLog(SmsRecordVo smsRecordVo) {
        this.mobile = smsRecordVo.getMobile();
        this.content = smsRecordVo.getContent();
        this.state = smsRecordVo.getState();
        this.result_desc = smsRecordVo.getResultDesc();
        this.seq_id = smsRecordVo.getSeqId();
        this.ds_time = smsRecordVo.getDsTime();
        this.ext = smsRecordVo.getExt();
        this.msg_id = smsRecordVo.getMsgId();
        this.result_status = smsRecordVo.getResultStatus();
        this.status_des = smsRecordVo.getStatusDes();
        this.business_src = smsRecordVo.getBusinessSrc();
        this.sms_channel = smsRecordVo.getSmsChannel();
        this.create_time = smsRecordVo.getCreateTime();
        this.update_time = smsRecordVo.getUpdateTime();
    }

    public SmsUnionLog() {

    }
}
