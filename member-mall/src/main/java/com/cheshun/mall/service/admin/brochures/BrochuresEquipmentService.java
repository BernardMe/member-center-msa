package com.cheshun.mall.service.admin.brochures;

import com.cheshun.common.api.web.http.common.EntityResult;
import com.cheshun.common.api.web.http.common.ListResult;
import com.cheshun.common.component.page.PageCondition;
import com.cheshun.common.result.Result;
import com.cheshun.dto.brochures.MedicalEquipmentDTO;
import com.cheshun.po.admin.brochures.BrochuresEquipmentPO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BrochuresEquipmentService {

    /**
     * 上传保存医疗器械记录
     *
     * @param file
     * @return
     */
    Result uploadBrochuresEquipmentInfo(MultipartFile file);

    /**
     * 批量同步OMS商品信息
     *
     * @param goodsBaseInfoList
     * @return
     */
    Result syncOmsGoodsInfoBatch(List<BrochuresEquipmentPO> goodsBaseInfoList);

    /**
     * 根据货号批量查询医疗器械详情
     * <br>需要调用方判断上下架。
     *
     * @param platGoodsCodes
     * @return
     */
    ListResult<MedicalEquipmentDTO> queryGoodsBaseDetailsByPlatGoodsCode(Collection<String> platGoodsCodes);

    /**
     * 根据货号批量查询医疗器械详情
     * <br>需要调用方判断上下架。
     * <br>返回一个MAP
     *
     * @param platGoodsCodes
     * @return
     */
    EntityResult<Map<String, MedicalEquipmentDTO>> queryGoodsBaseDetailsByPlatGoodsCodeForMap(Collection<String> platGoodsCodes);

    /**
     * 后台管理查询医疗器械分组列表信息
     *
     * @param pageCondition
     * @param categoryName
     * @param equipmentName
     * @param goodsCode
     * @return
     */
    Result adminQueryEquipmentByCategory(PageCondition pageCondition, String categoryName, String equipmentName, String goodsCode);

    /**
     * 查询医疗器械分组列表信息
     *
     * @param pageCondition
     * @param categoryName
     * @param equipmentName
     * @param goodsCode
     * @return
     */
    Result queryEquipmentByCategory(PageCondition pageCondition, String categoryName, String equipmentName, String goodsCode);

    /**
     * 根据货号查询医疗器械详情
     *
     * @param goodsCode
     * @return
     */
    Result queryEquipmentDetail(String goodsCode);

    /**
     * 根据货号更新医疗器械信息
     *
     * @param brochuresEquipmentPO
     * @return
     */
    Result updateEquipmentInfo(BrochuresEquipmentPO brochuresEquipmentPO);

    /**
     * 批量更新医疗器械部分字段(商品名称/价格)
     * <br>
     *
     * @param medicalEquipmentDTOS
     * @return
     */
    Result updateBaseGoodsInfosByFields(List<MedicalEquipmentDTO> medicalEquipmentDTOS);

    /**
     * 根据父节点获取子级医疗器械目录分类
     * <br>
     *
     * @return
     */
    Result getEquipmentCategoriesByParent(String parentName);

    /**
     * 查询器械目录分类访问频次
     * <br>
     *
     * @return
     */
    Result adminQueryEquipmentCategoryList(PageCondition pageCondition, String categoryName, String parentCode);
}
