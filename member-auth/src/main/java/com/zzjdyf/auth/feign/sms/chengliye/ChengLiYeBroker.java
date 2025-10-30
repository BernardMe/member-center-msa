package com.zzjdyf.auth.feign.sms.chengliye;

import cn.hutool.json.JSONUtil;
import com.zzjdyf.auth.feign.sms.chengliye.request.ChengLiYeGetRequest;
import com.zzjdyf.auth.feign.sms.chengliye.response.ChengLiYeGetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 功能描述 短信代理类
 *
 * @author xueqing wang
 * @date 2020/12/16 12:14
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ChengLiYeBroker {
    @Value("${zzjdyf.sms.chengliye.url}")
    private String url;

    @Qualifier("restProxyTemplate")
    private final RestTemplate restProxyTemplate;


    /**
     * 功能描述: 发送单条短信
     *
     * @param chengLiYeGetRequest
     * @return SmsGetResponse
     * @author xueqing wang
     * @date 2020/12/16 12:14
     */
    public ChengLiYeGetResponse sendSingleMessage(ChengLiYeGetRequest chengLiYeGetRequest) throws Exception {
        log.info("请求地址：{}",url);
        log.info("请求参数：{}",chengLiYeGetRequest);
        String string = restProxyTemplate.postForObject(url, chengLiYeGetRequest, String.class);
        log.info("响应参数：{}",string);

        ChengLiYeGetResponse chengLiYeGetResponse = JSONUtil.toBean(string, ChengLiYeGetResponse.class);
        return chengLiYeGetResponse;
    }

}
