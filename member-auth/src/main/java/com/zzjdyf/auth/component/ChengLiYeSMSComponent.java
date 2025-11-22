package com.zzjdyf.auth.component;

import com.zzjdyf.auth.feign.sms.chengliye.ChengLiYeBroker;
import com.zzjdyf.auth.feign.sms.chengliye.request.ChengLiYeGetRequest;
import com.zzjdyf.auth.feign.sms.chengliye.response.ChengLiYeGetResponse;
import com.cheshun.common.tools.utils.Md5Util;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xueqing wang on 2021/4/20 18:04
 */
@Slf4j
@Configuration
@Data
@RequiredArgsConstructor
public class ChengLiYeSMSComponent {
    @Value("${zzjdyf.sms.chengliye.url}")
    private String url;
    /**
     * 用户名
     */
    @Value("${zzjdyf.sms.chengliye.userid}")
    private String userid;
    /**
     * 密码
     */
    @Value("${zzjdyf.sms.chengliye.pwd}")
    private String pwd;

    private final ChengLiYeBroker chengLiYeBroker;

    /**
     * 功能描述: 发送单条短信信息
     *
     * @param mobile  请求信息
     * @param content 请求信息
     * @return UserInfoGetResponse
     * @author xueqing wang
     * @date 2020/12/16 10:41
     */
    public ChengLiYeGetResponse sendSingleMessage(String mobile, String content) {
        ChengLiYeGetRequest build = ChengLiYeGetRequest.builder()
                .content(content)
                .mobile(mobile)
                .userName(userid)
                .sign(Md5Util.getMD5Format(String.format("%s%s%s%s", userid, pwd, mobile, content)))
                .build();
        ChengLiYeGetResponse chengLiYeGetResponse = null;
        try {
            chengLiYeGetResponse = chengLiYeBroker.sendSingleMessage(build);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return chengLiYeGetResponse;
    }


    /**
     * 功能描述: 发送批量短信信息
     *
     * @param mobiles  请求信息
     * @param contents 请求信息
     * @return SmsGetResponse
     * @author xueqing wang
     * @date 2020/12/16 10:41
     */
    public ChengLiYeGetResponse sendBatchMessage(String[] mobiles, String[] contents) {
        ChengLiYeGetResponse chengLiYeGetResponse = null;
        for (int i = 0; i < mobiles.length; i++) {
            chengLiYeGetResponse =    sendSingleMessage(mobiles[i], contents[i]);

        }
        return chengLiYeGetResponse;
    }

}
