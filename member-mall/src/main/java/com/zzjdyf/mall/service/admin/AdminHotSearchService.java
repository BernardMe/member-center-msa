package com.zzjdyf.mall.service.admin;

import com.zzjdyf.common.component.page.PageCondition;
import com.zzjdyf.common.result.Result;
import com.zzjdyf.mall.domain.entity.EsHotSearch;
import com.zzjdyf.mall.vo.dto.ESHotSearchVO;

import java.util.List;

/**
 * 后管-算热搜词及热度Service
 * @author wangzhuo
 * @date 20251025
 */
public interface AdminHotSearchService {

    /**
     * 后管-热搜词添加
     * @param esHotSearchVO
     * @return
     */
    Result add(ESHotSearchVO esHotSearchVO);

    /**
     * 管理端：热搜词批量添加
     * @param hotSearchVOList
     * @return
     */
    Result batchAdd(List<ESHotSearchVO> hotSearchVOList);

    /**
     * 后管-修改单个热搜词
     * @param esHotSearchVO
     * @return
     */
    Result update(ESHotSearchVO esHotSearchVO);

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
                                 List<String> symptoms);  // 适用症状（精确匹配，可选）

    /**
     * 后管-删除单个热搜词
     * @param esHotSearchVO
     */
    Result delete(ESHotSearchVO esHotSearchVO);

    /**
     * 管理端：商品批量删除
     * @param ESGoodsSearchDTOs
     */
    void batchDelete(List<EsHotSearch> ESGoodsSearchDTOs);
}
