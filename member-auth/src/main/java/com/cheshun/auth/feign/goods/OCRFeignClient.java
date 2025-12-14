package com.cheshun.auth.feign.goods;

import com.cheshun.auth.feign.goods.fallback.OCRFeignClientFallback;
import com.cheshun.auth.feign.goods.request.OCRGetRequest;
import com.cheshun.auth.feign.goods.response.OCRGetResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

/**
 * 功能描述 用户服务feignClient
 *
 * @author xueqing wang
 * @date 2020/12/17 18:58
 */
@FeignClient(name="ocr-server", url="url-placeholder", fallback = OCRFeignClientFallback.class)
public interface OCRFeignClient {

	/**
	 * 功能描述: 易道OCR身份证识别开始
	 *
	 * @param ocrGetRequest 请求信息
	 * @return OCRGetResponse
	 * @author xueqing wang
	 * @date 2020/12/16 10:41
	 */
	@PostMapping(value="/ocr/v1/id_card")
	public OCRGetResponse ocrIdCard(URI uri, @RequestBody OCRGetRequest ocrGetRequest);


	/**
	 * 功能描述: 易道OCR行驶证识别
	 *
	 * @param ocrGetRequest 请求信息
	 * @return OCRGetResponse
	 * @author xueqing wang
	 * @date 2020/12/16 10:41
	 */
	@PostMapping(value="/ocr/v1/vehicle_license")
	public OCRGetResponse ocrVehicleLicense(URI uri, @RequestBody OCRGetRequest ocrGetRequest);

	/**
	 * 功能描述: 易道OCR银行卡识别
	 *
	 * @param ocrGetRequest 请求信息
	 * @return OCRGetResponse
	 * @author xueqing wang
	 * @date 2020/12/16 10:41
	 */
	@PostMapping(value="/ocr/v1/bank_card")
	public OCRGetResponse ocrBankCard(URI uri, @RequestBody OCRGetRequest ocrGetRequest);


}
