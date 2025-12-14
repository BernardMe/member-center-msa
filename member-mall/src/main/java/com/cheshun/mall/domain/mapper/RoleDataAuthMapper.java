package com.cheshun.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.mall.domain.entity.ClsMarketEtcRoleDataAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface RoleDataAuthMapper extends BaseMapper<ClsMarketEtcRoleDataAuth> {

    @Select("update cls_market_etc_role_data_auth set data_auth =#{dataAuth} where role_id =#{roleId} ")
    void updateByRoleId(ClsMarketEtcRoleDataAuth clsMarketEtcRoleDataAuth);


    @Select("select * from  cls_market_etc_role_data_auth  where role_id =#{roleId} ")
    ClsMarketEtcRoleDataAuth queryByRoleId(@Param("roleId") Long roleId);

}
