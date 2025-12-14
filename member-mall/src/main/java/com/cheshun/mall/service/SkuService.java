package com.cheshun.mall.service;

import com.cheshun.common.result.Result;

import java.util.Map;

/**
 * SKU服务
 * Created by xueqing wang on 2021/4/22 11:09
 */
public interface SkuService {


    /**
     * 根据id查询的SKU
     * @return
     */
    public  String getType();



    /**
     * 返回响应
     *
     * @param map 参数 {@link Map}
     * @return Result 响应 {@link Result}
     * @author wangzhuo
     */
    public Result buildResult(Map map);


}
