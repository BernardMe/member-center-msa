package com.cheshun.market.service.admin;

import com.cheshun.market.domain.dao.GlobalSettingDao;
import com.cheshun.market.domain.entity.ClsMarketEtcGlobalSetting;
import com.cheshun.market.domain.entity.enums.GlobalSettingKey;
import com.cheshun.common.exception.RRException;
import com.cheshun.market.vo.command.UpdateGlobalSettingCommand;
import com.cheshun.market.vo.dto.GlobalSettingDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author 阿隆
 * Created on 2021/8/12 5:54 下午.
 */
@Slf4j
@Service
@AllArgsConstructor
public class GlobalSettingService {
    private final GlobalSettingDao globalSettingDao;

    /**
     * 获取全局配置信息
     *
     * @return 全局配置 {@link Map}
     */
    public GlobalSettingDto query() {
        Map<GlobalSettingKey, ClsMarketEtcGlobalSetting> map = globalSettingDao.findAll();
        if (map.size() == 0) {
            throw new RRException("配置信息不存在");
        }
        GlobalSettingDto dto = new GlobalSettingDto();
        dto.setRuleList(new ArrayList<>());
        for (Map.Entry<GlobalSettingKey, ClsMarketEtcGlobalSetting> entry : map.entrySet()) {
            GlobalSettingDto.PromoteRule rule = new GlobalSettingDto.PromoteRule();
            rule.setName(entry.getKey());
            rule.setData(Integer.parseInt(entry.getValue().getData()));
            rule.setDescribe(entry.getValue().getDescribe());
            dto.getRuleList().add(rule);
        }
        return dto;
    }

    /**
     * 更新配置信息
     *
     * @param command 命令 {@link UpdateGlobalSettingCommand}
     */
    public void update(UpdateGlobalSettingCommand command) {
        if (command.getRuleList() == null || command.getRuleList().size() == 0) {
            throw new RRException("配置信息不存在");
        }
        Map<GlobalSettingKey, ClsMarketEtcGlobalSetting> map = globalSettingDao.findAll();
        if (map.size() == 0) {
            throw new RRException("配置信息不存在");
        }
        for (UpdateGlobalSettingCommand.PromoteRule rule : command.getRuleList()) {
            if (!map.containsKey(rule.getName())) {
                continue;
            }
            ClsMarketEtcGlobalSetting entity = map.get(rule.getName());
            entity.setData(String.valueOf(rule.getData()));
            entity.setDescribe(rule.getDescribe());
            globalSettingDao.save(entity);
        }
    }
}
