package com.cheshun.auth.service.impl;

import com.cheshun.auth.dao.SmsUnionLogMapper;
import com.cheshun.auth.service.SmsUnionLogService;
import com.cheshun.auth.vo.SmsRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 联合短信记录业务层实现类
 *
 * @author wangzhuo
 * @date 20210905
 */
@Slf4j
@Service
public class SmsUnionLogServiceImpl implements SmsUnionLogService {
    @Autowired
    private SmsUnionLogMapper smsUnionLogMapper;

    @Override
    public void saveSmsUnionLog(List<SmsRecordVo> list) {
        //SmsUnion存储短信记录
        log.debug("联合短信记录插入start");
        try {
           smsUnionLogMapper.insertBatch(list);
        } catch (Exception e1) {
            log.error("短信记录写入数据库异常", e1);
        }
    }
}
