package com.cheshun.mall.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cheshun.mall.domain.entity.ClsMarketEtcRole;
import com.cheshun.mall.vo.query.RolePageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface RoleMapper  extends BaseMapper<ClsMarketEtcRole> {


    /**
     * 查询角色列表
     */
    @Select("<script>select * from cls_market_etc_role where 1=1" +
            "<when test='roleName !=null'> and (role_name like CONCAT('%',#{roleName},'%')  or create_name = CONCAT('%',#{roleName},'%'))" +
            "</when><when test='status !=null'> and status = #{status}</when> order by create_time desc</script>")
    IPage<ClsMarketEtcRole> findPageList(IPage<RolePageQuery> page, @Param("roleName") String roleName, @Param("status") Integer status);
}
