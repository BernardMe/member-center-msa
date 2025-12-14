package com.cheshun.mall.domain.mapper.project.customer.brochures;

import com.cheshun.dto.brochures.MedicalEquipmentDTO;
import com.cheshun.dto.brochures.MedicalEquipmentDTOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MedicalEquipmentMapper {
    long countByExample(MedicalEquipmentDTOExample example);

    int deleteByExample(MedicalEquipmentDTOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MedicalEquipmentDTO record);

    int insertSelective(MedicalEquipmentDTO record);

    List<MedicalEquipmentDTO> selectByExample(MedicalEquipmentDTOExample example);

    MedicalEquipmentDTO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MedicalEquipmentDTO record, @Param("example") MedicalEquipmentDTOExample example);

    int updateByExample(@Param("record") MedicalEquipmentDTO record, @Param("example") MedicalEquipmentDTOExample example);

    int updateByPrimaryKeySelective(MedicalEquipmentDTO record);

    int updateByPrimaryKey(MedicalEquipmentDTO record);

    int batchInsert(@Param("list") List<MedicalEquipmentDTO> list);
}
