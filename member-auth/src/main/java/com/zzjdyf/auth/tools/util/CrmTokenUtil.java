package com.zzjdyf.auth.tools.util;

import com.alibaba.fastjson.JSONObject;
import com.zzjdyf.common.crminterface.AccessTokenRelated;
import com.zzjdyf.common.properties.CrmProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CrmTokenUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CrmProperties crmProperties;

    /**
     * Post传递方法
     */
    public JSONObject postRequest(String suffixUrl, Object obj) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("appId", "rmcloud");
        httpHeaders.set("accountId", "1723234505499609088");
        httpHeaders.set("entId", "193127");
        httpHeaders.set("entid", "193127");
        httpHeaders.set("access_token", getAccessToken());

        // 将传入的对象转换成 JSON
        HttpEntity<Object> reqHttpEntity = new HttpEntity<>(obj, httpHeaders);

        // 使用 responseType 泛型来指定返回类型
        return restTemplate.postForObject(crmProperties.getBaseurl() + suffixUrl, reqHttpEntity, JSONObject.class);
    }

    /**
     * Get传递方法
     */
    public JSONObject getRequest(String url, Object obj) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("appId", "rmcloud");
        httpHeaders.set("accountId", "1723234505499609088");
        httpHeaders.set("entId", "entId");
        httpHeaders.set("access_token", getAccessToken());
        HttpEntity<Object> reqHttpEntity = new HttpEntity<>(new JSONObject((Map<String, Object>) obj), httpHeaders);
        return restTemplate.postForObject(url, reqHttpEntity, JSONObject.class);
    }

    /**
     * 调取苍穹接口的token
     */
    private String getAccessToken() {
        if (redisTemplate.opsForValue().get("crm_member_token") != null) {
            return redisTemplate.opsForValue().get("crm_member_token").toString();
        }
        Map map = new HashMap<String, String>();
        map.put("appId", "zzjsjzx");
        map.put("appSecuret", "Romens@ZhangZhongJing#2378");
        map.put("username", "xxglb@hnzzj.com");
        map.put("tenantid", "hnzzj");
        map.put("accountId", "1723234505499609088");
        ResponseEntity<String> respForAppToken = restTemplate.postForEntity(AccessTokenRelated.URL_FOR_APP_TOKEN, new HttpEntity<>(map), String.class);
        String appToken = JSONObject.parseObject(respForAppToken.getBody()).getJSONObject("data").getString("app_token");
        map.put("user", "xxglb@hnzzj.com");
        map.put("apptoken", appToken);
        map.put("usertype", "Email");
        ResponseEntity<String> respForAccessToken = restTemplate.postForEntity(AccessTokenRelated.URL_FOR_ACCESS_TOKEN, new HttpEntity<>(map), String.class);
        String accessToken = JSONObject.parseObject(respForAccessToken.getBody()).getJSONObject("data").getString("access_token");

        log.info(String.format("获取苍穹 access_token数据成功！access_token = %s", accessToken));

        redisTemplate.opsForValue().set("crm_member_token", accessToken, 100, TimeUnit.MINUTES);

        return accessToken;
    }


}
