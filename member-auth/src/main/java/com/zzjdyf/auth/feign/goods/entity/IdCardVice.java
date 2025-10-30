package com.zzjdyf.auth.feign.goods.entity;

import lombok.*;

/**
 * Created by xueqing wang on 2021/4/21 17:33
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdCardVice extends IdCard {

    /**
     * 有效期限valid
     */
    private String valid;
    /**
     * 签发机关issued
     */
    private String issued;
}
