package com.zzjdyf.market.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.market.domain.entity.ClsMarketEtcMeun;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MeunMapper extends BaseMapper<ClsMarketEtcMeun> {

    List<ClsMarketEtcMeun> findList(@Param("roleId")Long roleId);

    @Select("select * from cls_market_etc_meun where leaf =#{leaf} and status =0")
    List<ClsMarketEtcMeun> queryListByLeaf(@Param("leaf")Integer leaf);

    @Select("select * from cls_market_etc_meun where id =#{meunId}")
    ClsMarketEtcMeun queryByMeunIdId(@Param("meunId")Long meunId);
}
