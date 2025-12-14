package com.cheshun.mall.domain.mapper.project.customer.brochures;

import com.cheshun.dto.brochures.MedicalEquipmentCategoriesDTO;
import com.cheshun.dto.brochures.MedicalEquipmentCategoriesDTOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MedicalEquipmentCategoriesMapper {
    long countByExample(MedicalEquipmentCategoriesDTOExample example);

    int deleteByExample(MedicalEquipmentCategoriesDTOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MedicalEquipmentCategoriesDTO record);

    int insertSelective(MedicalEquipmentCategoriesDTO record);

    List<MedicalEquipmentCategoriesDTO> selectByExample(MedicalEquipmentCategoriesDTOExample example);

    MedicalEquipmentCategoriesDTO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MedicalEquipmentCategoriesDTO record, @Param("example") MedicalEquipmentCategoriesDTOExample example);

    int updateByExample(@Param("record") MedicalEquipmentCategoriesDTO record, @Param("example") MedicalEquipmentCategoriesDTOExample example);

    int updateByPrimaryKeySelective(MedicalEquipmentCategoriesDTO record);

    int updateByPrimaryKey(MedicalEquipmentCategoriesDTO record);
}
