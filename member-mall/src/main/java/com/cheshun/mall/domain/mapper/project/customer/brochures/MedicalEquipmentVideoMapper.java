package com.cheshun.mall.domain.mapper.project.customer.brochures;

import com.cheshun.dto.brochures.MedicalEquipmentVideo;
import com.cheshun.dto.brochures.MedicalEquipmentVideoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MedicalEquipmentVideoMapper {
    long countByExample(MedicalEquipmentVideoExample example);

    int deleteByExample(MedicalEquipmentVideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MedicalEquipmentVideo record);

    int insertSelective(MedicalEquipmentVideo record);

    List<MedicalEquipmentVideo> selectByExample(MedicalEquipmentVideoExample example);

    MedicalEquipmentVideo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MedicalEquipmentVideo record, @Param("example") MedicalEquipmentVideoExample example);

    int updateByExample(@Param("record") MedicalEquipmentVideo record, @Param("example") MedicalEquipmentVideoExample example);

    int updateByPrimaryKeySelective(MedicalEquipmentVideo record);

    int updateByPrimaryKey(MedicalEquipmentVideo record);
}