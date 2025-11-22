package com.zzjdyf.customer.controller;

import com.cheshun.common.exception.BizCodeEnum;
import com.cheshun.common.tools.utils.R;
import com.zzjdyf.customer.service.CustomerService;
import com.zzjdyf.customer.service.CustomerStrategy;
import com.zzjdyf.customer.vo.CustomerBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 客户创建业务
 *
 * @AUTHOR: YYJ
 * @DATE : 2021/4/16 11:31
 * @Version 1.0
 */
@Slf4j
@Controller("/customer")
public class CustomerController {

    @Resource
    private CustomerStrategy customerStrategy;

    @RequestMapping("/create")
    public R create(CustomerBean customerBean, RedirectAttributes attributes, HttpSession session) {
        CustomerService carService = customerStrategy.getCustomerService(customerBean.getCarOriginType());
        if (carService == null) {
            log.error("未获取到客户修改实现类");
            return R.error(BizCodeEnum.ORDER_TYPE_ERROR.getCode(), BizCodeEnum.ORDER_TYPE_ERROR.getMsg());
        }
        return carService.customerCreate(customerBean);
    }

    @RequestMapping("/update")
    public R update(CustomerBean customerBean, CustomerBean conditionBean, RedirectAttributes attributes, HttpSession session) {
        CustomerService carService = customerStrategy.getCustomerService(customerBean.getCarOriginType());
        if (carService == null) {
            log.error("未获取到客户修改实现类");
            return R.error(BizCodeEnum.ORDER_TYPE_ERROR.getCode(), BizCodeEnum.ORDER_TYPE_ERROR.getMsg());
        }
        return carService.customerUpdate(customerBean, conditionBean);
    }


    @RequestMapping("/query")
    public R query(CustomerBean customerBean, RedirectAttributes attributes, HttpSession session) {
        CustomerService carService = customerStrategy.getCustomerService(customerBean.getCarOriginType());
        if (carService == null) {
            log.error("未获取到客户修改实现类");
            return R.error(BizCodeEnum.ORDER_TYPE_ERROR.getCode(), BizCodeEnum.ORDER_TYPE_ERROR.getMsg());
        }
        return carService.customerQuery(customerBean);
    }
}
