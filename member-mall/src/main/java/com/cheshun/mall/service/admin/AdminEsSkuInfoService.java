package com.cheshun.mall.service.admin;

import com.cheshun.common.component.page.PageCondition;
import com.cheshun.common.result.Result;
import com.cheshun.mall.vo.dto.EsSkuInfoVO;

import java.util.List;

/**
 * 后管-算热搜词及热度Service
 * @author wangzhuo
 * @date 20251025
 */
public interface AdminEsSkuInfoService {

    /**
     * 后管-刷新导入商品sku到ES接口
     * @param
     * @return
     */
    void importData();

    /**
     * 后管-添加单个商品sku
     * @param esSkuInfoVO
     * @return
     */
    Result add(EsSkuInfoVO esSkuInfoVO);

    /**
     * 管理端：热搜词批量添加
     * @param esSkuInfoVOList
     * @return
     */
    Result batchAdd(List<EsSkuInfoVO> esSkuInfoVOList);

    /**
     * 后管：按条件查询（分页）
     * @param pageCondition
     * @param minSort
     * @param maxSort
     * @param goodsName
     * @param symptoms
     * @return
     */
    Result adminQueryByCondition(PageCondition pageCondition,
                                 Integer minSort,    // 最小优先级（可选）
                                 Integer maxSort,        // 最大优先级（可选）
                                 String goodsName,       // 商品名（模糊匹配，可选）
                                 String symptoms);  // 适用症状（精确匹配，可选）

    /**
     * 后管-查看单个商品sku详情
     * @param skuId
     * @return
     */
    Result adminQueryDetail(String skuId);

    /**
     * 后管-修改单个商品sku
     * @param esSkuInfoVO
     * @return
     */
    Result update(EsSkuInfoVO esSkuInfoVO);

    /**
     * 后管-删除单个商品sku
     * @param esSkuInfoVO
     */
    Result delete(EsSkuInfoVO esSkuInfoVO);

    /**
     * 管理端：商品批量删除
     * @param esSkuInfoVOList
     */
    void batchDelete(List<EsSkuInfoVO> esSkuInfoVOList);
}
