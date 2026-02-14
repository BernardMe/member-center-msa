package com.cheshun.price.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cheshun.price.domain.entity.HardwarePriceExample;
import com.cheshun.price.domain.entity.HardwarePrice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商品报价 Mapper
 */
@Mapper
public interface HardwarePriceMapper {

    /**
     * 查询最低价格的商品
     */
    @Select("SELECT * FROM t_hardware_price " +
            "WHERE product_model = #{productModel} " +
            "ORDER BY price ASC LIMIT 1")
    HardwarePrice findLowestPrice(String productModel);

    /**
     * 按平台统计平均价格
     */
    @Select("SELECT platform, AVG(price) as avg_price " +
            "FROM t_hardware_price " +
            "WHERE product_model = #{productModel} " +
            "GROUP BY platform")
    List<HardwarePrice> getAvgPriceByPlatform(String productModel);

    long countByExample(HardwarePriceExample example);

    int deleteByExample(HardwarePriceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HardwarePrice record);

    int insertSelective(HardwarePrice record);

    List<HardwarePrice> selectByExampleWithBLOBs(HardwarePriceExample example);

    List<HardwarePrice> selectByExample(HardwarePriceExample example);

    HardwarePrice selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HardwarePrice record, @Param("example") HardwarePriceExample example);

    int updateByExampleWithBLOBs(@Param("record") HardwarePrice record, @Param("example") HardwarePriceExample example);

    int updateByExample(@Param("record") HardwarePrice record, @Param("example") HardwarePriceExample example);

    int updateByPrimaryKeySelective(HardwarePrice record);

    int updateByPrimaryKeyWithBLOBs(HardwarePrice record);

    int updateByPrimaryKey(HardwarePrice record);
}
