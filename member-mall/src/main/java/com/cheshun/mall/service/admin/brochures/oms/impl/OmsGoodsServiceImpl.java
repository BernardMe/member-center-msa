package com.cheshun.mall.service.admin.brochures.oms.impl;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.cheshun.common.api.web.http.common.EntityResult;
import com.cheshun.common.api.web.http.common.ListResult;
import com.cheshun.common.constant.CommonServiceConstant;
import com.cheshun.common.tools.utils.ResultUtil;
import com.cheshun.mall.tools.util.OmsBaseGoodsInfoConverter;
import com.cheshun.mall.service.admin.brochures.oms.OmsGoodsService;
import com.cheshun.po.admin.brochures.BaseGoodsInfoPO;
import com.cheshun.po.admin.brochures.OmsGoodsInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cheshun.common.constant.CommonServiceConstant.PE;

@Service
public class OmsGoodsServiceImpl implements OmsGoodsService, CommonServiceConstant {
    @Autowired
    private OmsBaseGoodsInfoConverter goodsInfoConverter;

    @Override
    public ListResult<BaseGoodsInfoPO> queryOmsGoodsInfo(Collection<String> goodsCodes) {
        if (goodsCodes == null || goodsCodes.size() == 0) {
            return ResultUtil.listResult(PE);
        }
        //组装URL
        StringBuilder bui = new StringBuilder();
        bui.append("https://oms.zzjdyf.com/noms/v2/limit/base/goods/detail/list");
        bui.append("?goodsCodeList=");
        bui.append(String.join(",", goodsCodes));


        //发送请求
//		HttpResponse execute = HttpRequest.post(CloudPosCouponConstant.CLOUD_POS_BASE_URL + CloudPosCouponConstant.GET_COUPON_SCHEME_INFOS_URL)
//				.header("entId", CloudPosCouponConstant.ENTID)
//				.header("appId", CloudPosCouponConstant.APPID)
//				.header("accessToken", token)
//				.body(requestJSON)
//				.execute();
//		String resp = execute.body();
//		CouponQueryResponse couponQueryResponse = JSON.parseObject(resp, CouponQueryResponse.class);
//		if (!couponQueryResponse.getCode().equals("200")) {
//			log.error("查询优惠券方案列表失败！ code：" + couponQueryResponse.getCode() + " msg:" + couponQueryResponse.getMsg());
//			return new EntityResult<>(new OptResult(Integer.valueOf(couponQueryResponse.getCode()), false, couponQueryResponse.getMsg()));
//		}


        HttpResponse execute = HttpRequest.get(bui.toString())
                .header("appId", "TEST_SYSM")
                .header("sign", "34D740470B3E3FC4ED33CDC6529EB8DC1AFD8F86689BC22822854C94609A2D4D")
                .header("timestamp", "1735794064633")
                .execute();
        String resp = execute.body();
        //解析结果
        ListResult<OmsGoodsInfoVO> goodsResult = ResultUtil.parseListResult(resp, OmsGoodsInfoVO.class);
        if (!goodsResult.getSuccess()) {
            ResultUtil.listResult(goodsResult);
        }

        return ResultUtil.convertListResult(goodsResult, goodsInfoConverter);
    }

    @Override
    public EntityResult<Map<String, BaseGoodsInfoPO>> queryOmsGoodsInfoForMap(Collection<String> goodsCodes) {
        Map<String, BaseGoodsInfoPO> result = new HashMap<String, BaseGoodsInfoPO>();
        //查询OMS商品信息
        List<BaseGoodsInfoPO> goodsInfos = queryOmsGoodsInfo(goodsCodes).getListData();
        if (goodsInfos != null && goodsInfos.size() > 0) {
            for (BaseGoodsInfoPO po : goodsInfos) {
                if (po != null) {
                    result.put(po.getResourceGoodsCode(), po);
                }
            }
        }
        return ResultUtil.entResult(result);
    }

}
