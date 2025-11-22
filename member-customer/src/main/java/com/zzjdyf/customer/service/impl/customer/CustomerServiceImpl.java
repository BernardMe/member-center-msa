package com.zzjdyf.customer.service.impl.customer;

import com.cheshun.common.tools.utils.R;
import com.zzjdyf.customer.service.CustomerService;
import com.zzjdyf.customer.vo.CustomerBean;

/**
 * @AUTHOR: YYJ
 * @DATE : 2021/4/25 14:21
 * @Version 1.0
 */
public class CustomerServiceImpl implements CustomerService {

    @Override
    public R customerCreate(CustomerBean bean) {
        return null;
    }

    @Override
    public R customerUpdate(CustomerBean targetBean, CustomerBean conditionBean) {
        return null;
    }

    @Override
    public R customerQuery(CustomerBean bean) {
        return null;
    }

    @Override
    public String getOriginType() {
        return "CUSTOMER";
    }
}
