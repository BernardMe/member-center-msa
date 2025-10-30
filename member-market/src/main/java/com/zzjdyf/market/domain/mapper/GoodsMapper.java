package com.zzjdyf.market.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzjdyf.market.domain.entity.ClsMarketEtcGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author wangzhuo
 * Created on 20210719
 */
@Mapper
@Repository
public interface GoodsMapper extends BaseMapper<ClsMarketEtcGoods> {
    /**
     * 增加累计销量并扣减库存
     *
     * @param id           id
     * @param cardQuantity 卡数量增量
     * @return 更新数据条数
     */
    @Update("update cls_market_etc_goods set " +
            "sales = sales + #{cardQuantity}, " +
            "stock = stock - #{cardQuantity}, " +
            "update_time = NOW() " +
            "where id = #{id}")
    int increaseSalesAndDecreaseStock(Long id, Integer cardQuantity);

    /**
     * 更新库存
     *
     * @param id    id
     * @param stock 库存增量
     * @return 更新数据条数
     */
    @Update("update cls_market_etc_goods set " +
            "stock = stock + #{stock}, " +
            "update_time = NOW() " +
            "where id = #{id} " +
            "and stock >= -#{stock}")
    int updateStock(Long id, Integer stock);

    /**
     * 扣减累计销量并增加库存
     *
     * @param id           id
     * @param cardQuantity 卡数量
     * @param obuQuantity  OBU数量
     * @return 更新数据条数
     */
    @Update("<script> update cls_market_etc_goods set  " +
            "<if test='cardQuantity != null'> " +
            "sales = sales - #{cardQuantity}, stock = stock + #{cardQuantity},  " +
            "</if> " +
            "<if test='obuQuantity != null'> " +
            "obu_sales = obu_sales - #{obuQuantity}, obu_stock = obu_stock + #{obuQuantity}, " +
            "</if> " +
            "update_time = NOW() " +
            "where id = #{id}  " +
            "</script>")
    int decreaseSalesAndIncreaseStock(@Param("id") Long id, @Param("cardQuantity") Integer cardQuantity,
                                      @Param("obuQuantity") Integer obuQuantity);
}