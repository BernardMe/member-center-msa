package com.zzjdyf.customer.service;

import com.zzjdyf.common.tools.utils.R;
import com.zzjdyf.customer.vo.CustomerBean;

/**
 * @AUTHOR: YYJ
 * @DATE : 2021/4/25 14:15
 * @Version 1.0
 */
public interface CustomerService {

    /**
     * 客户创建
     *
     * @param bean 客户信息
     * @return 结果
     */
    R customerCreate(CustomerBean bean);

    /**
     * 客户信息修改
     *
     * @param targetBean    需要修改的参数
     * @param conditionBean 修改的条件bean
     * @return 结果
     */
    R customerUpdate(CustomerBean targetBean, CustomerBean conditionBean);

    /**
     * 客户创建
     *
     * @param bean 客户信息
     * @return 结果
     */
    R customerQuery(CustomerBean bean);

    /**
     * @return 客户来源
     */
    String getOriginType();
}
