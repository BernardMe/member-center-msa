package com.zzjdyf.mall.feign.verify.bestsign;

import com.zzjdyf.mall.feign.verify.bestsign.fallback.BestSignFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 功能描述 上上签feignClient
 *
 * @author xueqing wang
 * @date 2020/12/17 18:58
 */
@FeignClient(name="bestsign-server", url="url-placeholder", fallback = BestSignFeignClientFallback.class)
public interface BestSignFeignClient {

}
