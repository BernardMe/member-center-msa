package com.cheshun.market.service.app;

import com.cheshun.market.domain.dao.GlobalSettingDao;
import com.cheshun.market.domain.entity.ClsMarketEtcGlobalSetting;
import com.cheshun.market.domain.entity.enums.GlobalSettingKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 阿隆
 * Created on 2021/8/12 5:54 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class PromoteRuleService {
    private final GlobalSettingDao globalSettingDao;

    /**
     * 获取推广规则列表
     *
     * @return 推广规则列表 {@link List}
     */
    public List<String> query() {
        Map<GlobalSettingKey, ClsMarketEtcGlobalSetting> map = globalSettingDao.findAll();
        List<String> ruleList = new ArrayList<>(map.size());
        for (GlobalSettingKey key : GlobalSettingKey.values()) {
            if (key == GlobalSettingKey.STAFF_BONUS) {
                // 跳过员工的规则
                continue;
            }
            if (map.containsKey(key)) {
                ruleList.add(map.get(key).getDescribe());
            }
        }
        return ruleList;
    }
}
