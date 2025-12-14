package com.cheshun.auth.vo;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class SmsSingleVo implements Serializable {

    /**
     * 发送手机号'
     */
    @NotNull(message = "发送手机号不能为空")
    private String mobiles;
    /**
     * 短信内容
     */
    @NotNull(message = "短信内容不能为空")
    private String contents;
    /**
     * 用户自定义扩展（选填）
     */
    private String exts;
    /**
     * 请求订单号（选填）
     */
    private String seqIds;
    /**
     * 定时发送，yyyy-MM-dd HH:mm:sss（选填）
     */
    private String dsTimes;
    /**
     * 是否是国际短信 1-是(港澳台国际短信) 0-否(不包含港澳台的国内短信) 默认不填则为否（选填）
     */
    private Integer ismses;

}
