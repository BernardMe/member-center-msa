package com.cheshun.mall.service.impl;

import com.cheshun.mall.service.AuthVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 上上签-认证
 * Created by xueqing wang on 2021/4/22 11:38
 */
@Service
@RequiredArgsConstructor
public class BestSignVerifyServiceImpl implements AuthVerifyService {



    @Override
    public String getType() {
        return "BEST_SIGN";
    }

    @Override
    public Map namePhone2Verify(Map map) {
        return null;
    }

    @Override
    public Map namePhoneId3Verify(Map map) {
        return null;
    }
}
