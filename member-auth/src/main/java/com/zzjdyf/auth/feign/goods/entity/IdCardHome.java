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
public class IdCardHome extends IdCard {
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 民族
     */
    private String nationality;

    /**
     * 身份证号
     */
    private String idno;

    /**
     * 住址
     */
    private String address;

    /**
     * 出生日期
     */
    private String birthdate;
}
