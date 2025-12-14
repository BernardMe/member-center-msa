package com.cheshun.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.mall.domain.entity.ClsMarketEtcMeunMo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MeunMoMapper extends BaseMapper<ClsMarketEtcMeunMo> {

    @Select("select t.meun_id meunId,t.id moId,m.name meunName, t.mo_value moValue,t.mo_name moName " +
            "from cls_market_etc_meun m , cls_market_etc_meun_mo t " +
            "where t.meun_id = m.id and t.meun_id = #{meunId}")
    List<ClsMarketEtcMeunMo> findList(@Param("meunId") Long meunId);
}
