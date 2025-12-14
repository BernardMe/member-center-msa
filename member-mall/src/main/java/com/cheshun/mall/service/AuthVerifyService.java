package com.cheshun.mall.service;

import java.util.Map;

/**
 * Created by xueqing wang on 2021/4/22 11:09
 */
public  interface AuthVerifyService {


    /**
     * 每个认证服务的type，用于区分
     * @return
     */
    public  String getType();


    /**
     * 手机号二要素验证
     *
     * @return null
     * @author xueqing wang
     * @date 2021/4/22 11:11
     */
    public  Map namePhone2Verify(Map map);

    /**
     * 手机号三要素验证
     *
     * @return null
     * @author xueqing wang
     * @date 2021/4/22 11:11
     */
    public  Map namePhoneId3Verify(Map map);


}
