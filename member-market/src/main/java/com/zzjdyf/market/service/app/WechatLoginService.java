package com.zzjdyf.market.service.app;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zzjdyf.common.exception.RRException;
import com.zzjdyf.market.config.ProxyConfig;
import com.zzjdyf.market.domain.dao.AgentDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcAgent;
import com.zzjdyf.market.vo.dto.AgentAppDto;
import com.zzjdyf.market.vo.dto.LittleProgramAuthDto;
import com.zzjdyf.market.vo.dto.WechatLoginDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zzjdyf.market.domain.dao.interfaces.IJson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class WechatLoginService {

    @Bean(name="remoteRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private AgentDao agentDao;

    @Autowired
    private LoginAppService loginAppService;

    private final ProxyConfig proxyConfig;


    /**
     * 根据openid查询用户信息
     */
    public WechatLoginDto getUserInfo(String openId){
        WechatLoginDto wechatLoginDto = new WechatLoginDto();
        ClsMarketEtcAgent clsMarketEtcAgent= agentDao.findOneByOpenId(openId);
        if(clsMarketEtcAgent !=null&&!("".equals(clsMarketEtcAgent.getPhone())||clsMarketEtcAgent.getPhone()==null)){
            AgentAppDto agentAppDto= loginAppService.loginByphone(clsMarketEtcAgent.getPhone());
            wechatLoginDto.setExist(true);
            wechatLoginDto.setAgentAppDto(agentAppDto);
        }else{
            wechatLoginDto.setExist(false);
        }
        return wechatLoginDto;
    }

    /**
     * 小程序应用通过code获取openId
     * @param appId
     * @param secret
     * @param code
     * @return
     */
    public LittleProgramAuthDto getLittleProgramAccessToken(String appId, String secret, String code) throws Exception {

        /*String result = rest.getForObject("https://api.weixin.qq" +
                        ".com/sns/jscode2session?appid="+appId+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code",
                String.class);*/
        CloseableHttpClient httpClient = createHttpClient(proxyConfig);
        String url="https://api.weixin.qq" +
                ".com/sns/jscode2session?appid="+appId+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code";
        HttpPost httppost = new HttpPost(url);
        String result = null;
        CloseableHttpResponse httpResponse = httpClient.execute(httppost);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.OK.value()) {
            throw new Exception("小程序登录异常");
        } else{
            org.apache.http.HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            }
            EntityUtils.consume(entity);
        }

        if (result == null) {
            throw new Exception("小程序登录异常");
        }
        LittleProgramAuthDto response = IJson.OBJECT_MAPPER.readValue(result, new TypeReference<LittleProgramAuthDto>() {
        });
        if (response == null) {
            throw new Exception("小程序登录异常");
        }

       /* LittleProgramAuthDto resp = JSONObject.parseObject(result, LittleProgramAuthDto.class);
        if (resp.getErrcode() != null) {
            log.error("小程序通过code授权获取openId异常，返回信息：{}", result);
            throw new RRException("小程序通过code授权获取openId异常");
        }*/


        return response;
    }

    /**
     * 登录并更新openId
     */
    public AgentAppDto login(String openId,String phone){
        AgentAppDto agentAppDto= Optional.ofNullable(loginAppService.loginByphone(phone))
                .orElseThrow(()-> new RRException("登录失败"));
        ClsMarketEtcAgent agent = Optional.ofNullable(agentDao.findOneByPhone(phone))
                .orElseThrow(() -> new RRException("代理商不存在"));
        agent.setOpenId(openId);
        agentDao.save(agent);
        return agentAppDto;
    }


    /**
     * 解密获取手机号
     * @param encryptedData
     * @param iv
     * @param sessionKey
     * @return
     */
    public String decodeInfo(String encryptedData, String iv,String sessionKey){
        JSONObject userInfo = getWxUserInfo(encryptedData, sessionKey, iv);
        log.info("根据解密算法获取的userInfo=" + userInfo);
        if (null == userInfo || StringUtils.isBlank(userInfo.getString("phoneNumber"))) {
            throw new RRException("获取手机号失败");
        }
        return userInfo.getString("phoneNumber");
    }


    /**
     * 解密
     */
    private static JSONObject getWxUserInfo(String encryptedData, String sessionKey, String iv) {
        Base64.Decoder decoder = Base64.getDecoder();
        // 被加密的数据
        byte[] dataByte = decoder.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = decoder.decode(sessionKey);
        // 偏移量
        byte[] ivByte = decoder.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    private static CloseableHttpClient createHttpClient(ProxyConfig proxyConfig) throws Exception {
        RequestConfig.Builder configBuilder = RequestConfig.custom()
                .setConnectTimeout(30000)
                .setSocketTimeout(30000);
        if (proxyConfig.getEnabled()) {
            HttpHost proxy = new HttpHost(proxyConfig.getHost(), proxyConfig.getPort());
            configBuilder.setProxy(proxy);
        }
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(),
                NoopHostnameVerifier.INSTANCE
        );
        return HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory).setDefaultRequestConfig(configBuilder.build()).build();
    }



}
