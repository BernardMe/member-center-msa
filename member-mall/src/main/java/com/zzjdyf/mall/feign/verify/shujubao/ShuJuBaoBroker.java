package com.zzjdyf.mall.feign.verify.shujubao;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.cdp.product.security.encode.CdpEncryptUtil;
import com.cdp.product.security.sign.CdpSignUtil;
import com.zzjdyf.mall.feign.verify.shujubao.request.NamePhone2VerifyPostRequest;
import com.zzjdyf.mall.feign.verify.shujubao.request.NamePhoneID3VerifyPostRequest;
import com.zzjdyf.mall.feign.verify.shujubao.response.ShuJuBaoPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述 数据宝代理类
 *
 * @author xueqing wang
 * @date 2020/12/16 12:14
 */
@Component
@RequiredArgsConstructor
public class ShuJuBaoBroker {
    @Value("${zzjdyf.verify.shujubao.url}")
    String url;

    @Value("${zzjdyf.verify.shujubao.secretKey}")
    String secretKey;


    @Value("${zzjdyf.verify.shujubao.namePhoneKey}")
    String namePhoneKey;


    @Value("${zzjdyf.verify.shujubao.namePhoneIdKey}")
    String namePhoneIdKey;

    /**
     * 数据宝服务feign
     */
    //private final ShuJuBaoFeignClient shuJuBaoFeignClient;



    /**
     * 功能描述: 二要素认证
     *
     * @param map 请求信息
     * @return OCRGetResponse
     * @author xueqing wang
     * @date 2020/12/16 10:41
     */
    public Map namePhone2Verify(Map map) {
        ShuJuBaoPostResponse shuJuBaoPostResponse = null;
        try {


            String name = CdpEncryptUtil.aesEncrypt(map.get("name").toString(), secretKey);
            String mobile = CdpEncryptUtil.aesEncrypt(map.get("mobile").toString(), secretKey);
            String timestamp = CdpEncryptUtil.aesEncrypt( System.currentTimeMillis() +"", secretKey);
            Map<String, String> params = new HashMap<>();
            params = MapUtil.builder(params)
                    .put("name", name)
                    .put("mobile", mobile)
                    .put("timestamp", timestamp)
                    .build();

            NamePhone2VerifyPostRequest namePhone2VerifyPostRequest = NamePhone2VerifyPostRequest.builder()
                    .name(name)
                    .mobile(mobile)
                    .timestamp(timestamp)
                    .key(namePhoneKey)
                    .sign(CdpSignUtil.sign(params))
                    .build();

            //shuJuBaoPostResponse = shuJuBaoFeignClient.namePhone2Verify(new URI(url), namePhone2VerifyPostRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BeanUtil.beanToMap(shuJuBaoPostResponse);
    }


    /**
     * 功能描述: 三要素认证
     *
     * @param map 请求信息
     * @return OCRGetResponse
     * @author xueqing wang
     * @date 2020/12/16 10:41
     */
    public Map namePhoneId3Verify(Map map) {
        ShuJuBaoPostResponse shuJuBaoPostResponse = null;
        try {

            String name = CdpEncryptUtil.aesEncrypt(map.get("name").toString(), secretKey);
            String mobile = CdpEncryptUtil.aesEncrypt(map.get("mobile").toString(), secretKey);
            String idcard = CdpEncryptUtil.aesEncrypt(map.get("idcard").toString(), secretKey);
            String timestamp = CdpEncryptUtil.aesEncrypt(System.currentTimeMillis() +"", secretKey);
            Map<String, String> params = new HashMap<>();
            params = MapUtil.builder(params)
                    .put("name", name)
                    .put("mobile", mobile)
                    .put("idcard", idcard)
                    .put("timestamp", timestamp)
                    .build();

            NamePhoneID3VerifyPostRequest namePhoneID3VerifyPostRequest = NamePhoneID3VerifyPostRequest.builder()
                    .name(name)
                    .mobile(mobile)
                    .idcard(idcard)
                    .timestamp(timestamp)
                    .key(namePhoneIdKey)
                    .sign(CdpSignUtil.sign(params))
                    .build();
            //shuJuBaoPostResponse = shuJuBaoFeignClient.namePhoneId3Verify(new URI(url), namePhoneID3VerifyPostRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BeanUtil.beanToMap(shuJuBaoPostResponse);
    }

}
