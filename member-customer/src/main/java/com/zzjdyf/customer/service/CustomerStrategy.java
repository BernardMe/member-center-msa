package com.zzjdyf.customer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 获取创建客户的实现类
 *
 * @AUTHOR: YYJ
 * @DATE : 2021/4/16 15:49
 * @Version 1.0
 */
@Service
public class CustomerStrategy {
    private static Logger logger = LoggerFactory.getLogger(CustomerStrategy.class);
    @Resource
    private List<CustomerService> customerServiceList;

    /**
     * 获取创建客户的实现类
     *
     * @param customerOriginType 客户请求来源
     * @return 实现类
     */
    public CustomerService getCustomerService(String customerOriginType) {
        if (StringUtils.isEmpty(customerOriginType)) {
            logger.error("未获取到客户请求来源");
            return null;
        }
        for (CustomerService customerService : customerServiceList) {
            if (customerOriginType.equals(customerService.getOriginType())) {
                return customerService;
            }
        }
        logger.error("此客户请求来源未有实现类");
        return null;
    }
}
