package com.cheshun.market.service.admin.brochures.oms;

import com.cheshun.common.api.web.http.common.EntityResult;
import com.cheshun.common.api.web.http.common.ListResult;
import com.cheshun.market.domain.po.admin.brochures.BaseGoodsInfoPO;

import java.util.Collection;
import java.util.Map;

public interface OmsGoodsService {

    /**
     * 查询OMS商品信息
     *
     * @param goodsCodes
     * @return
     */
    ListResult<BaseGoodsInfoPO> queryOmsGoodsInfo(Collection<String> goodsCodes);

    /**
     * 查询OMS商品信息
     * <br>必然返回MAP。
     * <br>KEY是商品编码
     *
     * @param goodsCodes
     * @return
     */
    EntityResult<Map<String, BaseGoodsInfoPO>> queryOmsGoodsInfoForMap(Collection<String> goodsCodes);
}
