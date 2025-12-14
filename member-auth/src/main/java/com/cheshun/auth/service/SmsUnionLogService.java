package com.cheshun.auth.service;

import com.cheshun.auth.vo.SmsRecordVo;

import java.util.List;

/**
 * 联合短信记录业务层
 *
 * @author wangzhuo
 * @date 20210902
 */
public interface SmsUnionLogService {

     /**
     * 联合短信记录保存多条
     *
     * @param smsRecordVo 记录列表 {@link List< SmsRecordVo >}
     */
    void saveSmsUnionLog(List<SmsRecordVo> smsRecordVo);

}
