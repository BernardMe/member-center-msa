package com.zzjdyf.market.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.market.domain.entity.ClsMarketEtcGlobalSetting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 阿隆
 * Created on 2021/8/12 3:03 下午.
 */
@Mapper
@Repository
public interface GlobalSettingMapper extends BaseMapper<ClsMarketEtcGlobalSetting> {
}
