package com.cheshun.mall.vo;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ToString
public class SmsBatchVo implements Serializable {

    /**
     * 发送手机号'
     */
    @NotNull(message = "mobiles_array_is_empty")
    private String[] mobiles;
    /**
     * 短信内容
     */
    @NotNull(message = "contents_array_is_empty")
    private String[] contents;
    /**
     * 用户自定义扩展（选填）
     */
    private String[] exts;
    /**
     * 请求订单号（选填）
     */
    private String[] seqIds;
    /**
     * 定时发送，yyyy-MM-dd HH:mm:sss（选填）
     */
    private String[] dsTimes;
    /**
     * 是否是国际短信 1-是(港澳台国际短信) 0-否(不包含港澳台的国内短信) 默认不填则为否（选填）
     */
    private Integer[] ismses;

}
