package com.zzjdyf.mall.feign.goods.request;

import com.alibaba.fastjson.annotation.JSONField;
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
public class OCRGetRequest {

    @JSONField(name = "image_base64")
    private String imageBase64;
}
