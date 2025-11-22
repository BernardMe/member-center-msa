package com.cheshun.market.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cheshun.market.domain.entity.ClsMarketEtcAccount;
import com.cheshun.market.vo.dto.AccountAdminDto;
import com.cheshun.market.vo.query.AccountPageQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface AccountMapper extends BaseMapper<ClsMarketEtcAccount> {

    @Select("select * from cls_market_etc_account where phone = #{phone} and status =#{status}")
    ClsMarketEtcAccount findOneByPhone(@Param("phone") String phone, @Param("status") Integer status);

    @Select("<script>select * from cls_market_etc_account a left join  cls_market_etc_role r on a.role_id = r.id where 1 =1 " +
            "<when test=\"accountName !=null and accountName !='' \"> and (a.account_name  like CONCAT('%',#{accountName},'%')  or a.create_name like CONCAT('%',#{accountName},'%'))" +
            "</when><when test=\" status !=null and status !='' \"> and a.status = #{status}</when> order by a.create_time desc</script>")
    IPage<AccountAdminDto> findListByNameOrPhone(IPage<AccountPageQuery> page, @Param("accountName") String accountName, @Param("status") String status);
}
