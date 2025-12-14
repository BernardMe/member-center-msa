package com.cheshun.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cheshun.auth.component.ChengLiYeSMSComponent;
import com.cheshun.auth.entity.enums.SmsChannel;
import com.cheshun.auth.feign.sms.chengliye.response.ChengLiYeGetResponse;
import com.cheshun.auth.service.AuthSmsService;
import com.cheshun.auth.service.SmsUnionLogService;
import com.cheshun.auth.vo.SmsRecordVo;
import com.cheshun.common.exception.RRException;
import com.cheshun.common.result.Result;
import com.cheshun.common.tools.utils.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.cheshun.common.result.Result.ERR_CODE;

/**
 * 成立业sms服务
 * Created by xueqing wang on 2021/6/16 13:54
 */
@Service
@RequiredArgsConstructor
public class ChengLiYeSmsServiceImpl implements AuthSmsService {

    /**
     * 是否挡板
     */
    @Value("${zzjdyf.sms.apcAuard}")
    private Boolean apcAuard;
    /**
     * 梦网要求发的测试短信内容
     */
    @Value("${zzjdyf.sms.chengliye.testContent}")
    private String testContent;

    @Autowired
    private SmsUnionLogService smsUnionLogService;

    private final ChengLiYeSMSComponent chengLiYeSMSComponent;
    @Override
    public String getType() {
        return "CHENG_LI_YE";
    }

    @Override
    public Map sendMessage(String phone, String content) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(content)) {
            throw new RRException("CHENG_LI_YE_发送短信参数异常");
        }
        content =  apcAuard ? testContent : content;
        ChengLiYeGetResponse response = chengLiYeSMSComponent.sendSingleMessage(phone, content);
        if (null == response) {
            throw new RRException("CHENG_LI_YE_单条短信异常");
        }
        saveRecord(new String[]{phone}, new String[]{content}, response);
        return BeanUtil.beanToMap(response);
    }

    @Override
    public Result buildResult(Map map) {
        if ("1".equals(String.valueOf(map.get("resultCode")))) {
            Map<String, Object> ok = new HashMap<>();
            ok.put("message", (String.valueOf(map.get("resultMsg"))));
            ok.put("data", (String.valueOf(map.get("msgid"))));
            return ResultUtil.result(ok);
        }
        return ResultUtil.result(ERR_CODE, String.valueOf(map.get("resultMsg")));
    }

    private void saveRecord(String[] mobiles, String[] contents, ChengLiYeGetResponse resp) {
        if ("1".equals(resp.getResultCode())) {
            SmsRecordVo[] smsUnionLogsArr = new SmsRecordVo[mobiles.length];
            for (int i = 0; i < mobiles.length; i++) {
                smsUnionLogsArr[i] = SmsRecordVo.builder()
                        .mobile(mobiles[i])
                        .content(contents[i])
                        .state(0)//通讯成功
                        .seqId(String.valueOf(resp.getMsgid()))
                        .statusDes(resp.getResultMsg())
                        .smsChannel(SmsChannel.CHENG_LI_YE.getValue())
                        .createTime(LocalDateTime.now())
                        .build();
            }
            smsUnionLogService.saveSmsUnionLog(Stream.of(smsUnionLogsArr).collect(Collectors.toCollection(ArrayList::new)));
        } else {
            //提交失败记录
            SmsRecordVo[] smsUnionLogsArr = new SmsRecordVo[mobiles.length];
            for (int i = 0; i < mobiles.length; i++) {
                smsUnionLogsArr[i] = SmsRecordVo.builder()
                        .mobile(mobiles[i])
                        .content(contents[i])
                        .state(3)//通讯失败
                        .seqId(String.valueOf(resp.getMsgid()))
                        .statusDes(resp.getResultMsg())
                        .smsChannel(SmsChannel.CHENG_LI_YE.getValue())
                        .createTime(LocalDateTime.now())
                        .build();
            }
            smsUnionLogService.saveSmsUnionLog(Stream.of(smsUnionLogsArr).collect(Collectors.toCollection(ArrayList::new)));
        }
    }


}
