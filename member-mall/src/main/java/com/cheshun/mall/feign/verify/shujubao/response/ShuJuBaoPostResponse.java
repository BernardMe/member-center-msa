package com.cheshun.mall.feign.verify.shujubao.response;

import lombok.*;

import java.util.Map;

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
public class ShuJuBaoPostResponse {

    private String code;

    private Boolean success;

    private String message;

    private String seqNo;

    private Map data;
}
