package com.cheshun.market.service;

import cn.hutool.core.map.MapBuilder;
import com.cheshun.market.config.AccountConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 阿隆
 * Created on 2021/9/8 5:21 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class CommonService {
    private final AccountConfig accountConfig;

    public Map<String, String> getAccount() {
        return MapBuilder.<String, String>create()
                .put("name", accountConfig.getName())
                .put("bankName", accountConfig.getBankName())
                .put("accountNumber", accountConfig.getAccountNumber())
                .build();
    }
}
