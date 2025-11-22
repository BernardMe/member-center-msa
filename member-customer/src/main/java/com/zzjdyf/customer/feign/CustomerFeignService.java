package com.zzjdyf.customer.feign;

import com.cheshun.common.tools.utils.R;
import com.zzjdyf.customer.feign.fallback.CustomerFallbackService;
import com.zzjdyf.customer.vo.CustomerBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "cheshun-order", fallback = CustomerFallbackService.class)
public interface CustomerFeignService {

    @RequestMapping("/order/create")
    R create(@RequestBody CustomerBean orderBean);

}
