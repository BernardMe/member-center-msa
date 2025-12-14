package com.cheshun.mall.domain.mapper.project.customer.brochures;

import com.cheshun.dto.brochures.MedicalEquipmentCategoriesDTO;
import com.cheshun.dto.brochures.MedicalEquipmentDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MedicalEquipmentDAO {


    /**
     * 批量更新商品信息部分字段(商品名称/价格)
     *
     * @param
     * @return
     */
    int updateBatchByFields(@Param("medicalEquipmentInfoList") List<MedicalEquipmentDTO> medicalEquipmentInfoList);

    /**
     * 根据目录名称获取医疗器械目录分类DTO
     *
     * @param
     * @return
     */
    List<MedicalEquipmentCategoriesDTO> getEquipmentCategoriesByCategoriesName(@Param("categoriesName") String categoriesName);

    /**
     * 根据父级目录名称获取子级医疗器械目录分类
     *
     * @param
     * @return
     */
    List<MedicalEquipmentCategoriesDTO> getEquipmentCategoriesByParent(@Param("parentName") String parentName);
}
