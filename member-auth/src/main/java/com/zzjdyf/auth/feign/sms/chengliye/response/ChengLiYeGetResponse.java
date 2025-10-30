package com.zzjdyf.auth.feign.sms.chengliye.response;

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
public class ChengLiYeGetResponse {
    /**
     * Description:发送短信结果
     */
    private boolean statusCode;

    private String resultCode;

    private String resultMsg;

    private Long msgid;

    private String seqid;

}
