package com.cheshun.mall.service.impl;

import com.cheshun.mall.feign.verify.shujubao.ShuJuBaoBroker;
import com.cheshun.mall.service.AuthVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 数据包认证
 * Created by xueqing wang on 2021/4/22 11:38
 */
@Service
@RequiredArgsConstructor
public class ShuJuBaoVerifyServiceImpl implements AuthVerifyService {


    private final ShuJuBaoBroker shuJuBaoBroker;

    @Override
    public String getType() {
        return "SHU_JU_BAO";
    }


    @Override
    public Map namePhone2Verify(Map map) {
        return shuJuBaoBroker.namePhone2Verify(map);
    }

    @Override
    public Map namePhoneId3Verify(Map map) {
        return shuJuBaoBroker.namePhoneId3Verify(map);
    }
}
