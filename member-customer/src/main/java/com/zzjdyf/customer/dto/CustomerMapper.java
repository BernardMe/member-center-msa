package com.zzjdyf.customer.dto;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.customer.entity.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * etc客户
 *
 * @AUTHOR: YYJ
 * @DATE : 2021/4/19 14:00
 * @Version 1.0
 */
@Mapper
@Repository
public interface CustomerMapper extends BaseMapper<CustomerEntity> {
}
