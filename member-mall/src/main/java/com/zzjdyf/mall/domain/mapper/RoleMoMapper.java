package com.zzjdyf.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.mall.domain.entity.ClsMarketEtcRoleMo;
import com.zzjdyf.mall.vo.dto.RoleMoAdminDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface RoleMoMapper extends BaseMapper<ClsMarketEtcRoleMo> {

    @Select("delete from cls_market_etc_role_mo where meun_id =#{meunId} and role_id =#{roleId}")
    void deleteByMeunId(ClsMarketEtcRoleMo clsMarketEtcRoleMo);


    @Select(" select r.id,r.role_id,r.meun_id,r.mo_value,mo.mo_name,m.name  meun_name ,mo.mo_type from cls_market_etc_role_mo r LEFT JOIN " +
            " cls_market_etc_meun_mo mo on r.mo_value = mo.mo_value LEFT JOIN  cls_market_etc_meun m on r.meun_id =m.id " +
            " where r.role_id =#{roleId} and r.meun_id =#{meunId}")
    List<RoleMoAdminDto> findListByRoleAndMenu(@Param("meunId") Long meunId, @Param("roleId") Long roleId);
}
