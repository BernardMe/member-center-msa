package com.zzjdyf.customer.feign.fallback;

import com.zzjdyf.common.exception.BizCodeEnum;
import com.zzjdyf.common.tools.utils.R;
import com.zzjdyf.customer.feign.CustomerFeignService;
import com.zzjdyf.customer.vo.CustomerBean;

/**
 * @AUTHOR: YYJ
 * @DATE : 2021/4/16 15:09
 * @Version 1.0
 */
public class CustomerFallbackService implements CustomerFeignService {

    @Override
    public R create(CustomerBean orderBean) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }

}
