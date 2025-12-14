package com.cheshun.auth.feign.goods.response;

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
public class OCRGetResponse {
    private Map<String,Map<String,Object>> result;
}
