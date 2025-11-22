package com.zzjdyf.auth.service;

import com.cheshun.common.result.Result;

import java.util.Map;

/**
 * 短信服务
 * Created by xueqing wang on 2021/4/22 11:09
 */
public interface  AuthSmsService {


    /**
     * 每个短信服务的type，用于区分
     * @return
     */
    public  String getType();


    /**
     * 发送短信
     *
     * @return null
     * @author xueqing wang
     * @date 2021/4/22 11:11
     */
    public Map sendMessage(String phone, String content);

    /**
     * 返回响应
     *
     * @param map 参数 {@link Map}
     * @return Result 响应 {@link Result}
     * @author wangzhuo
     */
    public Result buildResult(Map map);


}
