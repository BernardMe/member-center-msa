package com.cheshun.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.auth.entity.SmsUnionLog;
import com.cheshun.auth.vo.SmsRecordVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SmsUnionLogMapper extends BaseMapper<SmsUnionLog> {

    /**
     * 联合短信保存多条记录
     * @param smsRecordVo
     */
    void insertBatch(List<SmsRecordVo> smsRecordVo);
}
