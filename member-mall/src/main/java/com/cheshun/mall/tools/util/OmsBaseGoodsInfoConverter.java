package com.cheshun.mall.tools.util;


import com.cheshun.common.tools.utils.api.ObjectConverter;
import com.cheshun.constant.enumeration.DrugType;
import com.cheshun.po.admin.brochures.BaseGoodsInfoPO;
import com.cheshun.po.admin.brochures.OmsGoodsInfoVO;

import org.springframework.stereotype.Component;

/**
 * oms有效字段处理填充构建GoodsBaseInfoPO
 *
 * @author wangzhuo
 */
@Component
public class OmsBaseGoodsInfoConverter implements ObjectConverter<OmsGoodsInfoVO, BaseGoodsInfoPO> {

    @Override
    public BaseGoodsInfoPO convert(OmsGoodsInfoVO omsGoods) {
        if (omsGoods == null) {
            return null;
        }
        BaseGoodsInfoPO infoPO = new BaseGoodsInfoPO();
        //去除字符串中的指定特殊符号
        String regEx = "[Y∑○N▲F×ηβ]";
        String trimGoodsName = omsGoods.getGoodsName().replaceAll(regEx, "");
        //商品名称
        infoPO.setGoodsName(trimGoodsName);
        infoPO.setDrugTypeName(omsGoods.getCategory());
        //药品类别
        infoPO.setDrugTypeCode(DrugType.getEnumByName(omsGoods.getCategory().trim()).getCode());
        //批准文号
        infoPO.setApprovalNo(omsGoods.getApprovalNo());
        //单位
        infoPO.setGoodsUnit(omsGoods.getUnit());
        //生产公司
        infoPO.setProductionCompany(omsGoods.getProductionCompany());
        //产地
        infoPO.setProductionPlace(omsGoods.getProductionPlace());
        //条码
        infoPO.setBarCode(omsGoods.getBarCode());
        //规格
        infoPO.setSpecifications(omsGoods.getSpecifications());
        //金额转换
        infoPO.setSuggestedRetailPrice((long) (omsGoods.getRetailPrice() * 1000));
        //货号
        infoPO.setResourceGoodsCode(omsGoods.getGoodsCode());
        //填充图片
        infoPO.setGoodsMainImage(omsGoods.getGoodsImage());
        infoPO.setGoodsBannerImages(omsGoods.getGoodsBannerImages());
        infoPO.setGoodsDetailImages(omsGoods.getGoodsDetailImages());
        return infoPO;
    }

}
