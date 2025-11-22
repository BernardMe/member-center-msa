package com.cheshun.market.vo.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author 阿隆
 * Created on 2021/8/2 6:40 下午.
 */
@Data
public class YunZhangHuResultDto<T> {
    /**
     * 响应码 String 0000：成功，其他详见附录 6.3 响应码列表
     */
    private String code;
    /**
     * 响应码描述 String 详见附录 6.3 响应码列表
     */
    private String message;
    /**
     * 请求 ID String 请求 ID，原值返回
     */
    @JsonAlias({"request_id", "requestID"})
    private String requestId;
    /**
     * 返回数据
     */
    private T data;
}
