package com.zzjdyf.mall.feign.goods.fallback;

import com.zzjdyf.mall.feign.goods.OCRFeignClient;
import com.zzjdyf.mall.feign.goods.request.OCRGetRequest;
import com.zzjdyf.mall.feign.goods.response.OCRGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * 功能描述 熔断降级操作feignClient
 *
 * @author xueqing wang
 * @date 2020/12/17 18:59
 */
@Component
@Slf4j
public class OCRFeignClientFallback implements OCRFeignClient {


    @Override
    public OCRGetResponse ocrIdCard(URI uri, OCRGetRequest ocrGetRequest) {
        return null;
    }

    @Override
    public OCRGetResponse ocrVehicleLicense(URI uri, OCRGetRequest ocrGetRequest) {
        return null;
    }

    @Override
    public OCRGetResponse ocrBankCard(URI uri, OCRGetRequest ocrGetRequest) {
        return null;
    }
}
