package com.cheshun.mall.feign.verify.shujubao.request;

import lombok.*;

/**
 * Created by xueqing wang on 2021/4/22 18:00
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NamePhone2VerifyPostRequest {

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 姓名
     */
    private String name;

    /**
     * 时间戳
     */
    private String timestamp;


    /**
     * 签名
     */
    private String sign;

    /**
     *数据宝key
     */
    private String key;
}
