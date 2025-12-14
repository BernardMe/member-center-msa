package com.cheshun.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.mall.domain.entity.ClsMarketEtcRoleMeun;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface RoleMeunMapper extends BaseMapper<ClsMarketEtcRoleMeun> {


    @Select("delete from cls_market_etc_role_meun where role_id =#{roleId} and meun_id=#{meunId}")
    void deleteByRoleMeunId(@Param("roleId") Long roleId, @Param("meunId") Long meunId);

    @Select("select * from cls_market_etc_role_meun where role_id =#{roleId} and meun_id=#{meunId} and status ='0'")
    ClsMarketEtcRoleMeun findOneByRoleMeunId(ClsMarketEtcRoleMeun clsMarketEtcRoleMeun);

    @Select("select t.* from cls_market_etc_role_meun t ,cls_market_etc_meun m where t.meun_id = m.id and " +
            "m.leaf =#{leaf} and t.role_id = #{roleId} and t.status ='0' and m.status ='0' ")
    List<ClsMarketEtcRoleMeun> findListByRoleId(@Param("roleId") Long roleId, @Param("leaf") Integer leaf);


}
