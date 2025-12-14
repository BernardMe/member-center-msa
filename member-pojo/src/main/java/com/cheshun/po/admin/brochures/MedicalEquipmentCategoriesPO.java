package com.cheshun.po.admin.brochures;

import com.cheshun.dto.brochures.MedicalEquipmentCategoriesDTO;

import java.util.List;

public class MedicalEquipmentCategoriesPO {

    private Integer id;

    private String categoryCode;

    private String categoryName;

    private String parentCode;

    private Integer frequency;

    private Byte status;

    private List<MedicalEquipmentCategoriesDTO> subList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public List<MedicalEquipmentCategoriesDTO> getSubList() {
        return subList;
    }

    public void setSubList(List<MedicalEquipmentCategoriesDTO> subList) {
        this.subList = subList;
    }

    @Override
    public String toString() {
        return "MedicalEquipmentCategoriesPO{" +
                "id=" + id +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", parentCode='" + parentCode + '\'' +
                ", frequency=" + frequency +
                ", status=" + status +
                ", subList=" + subList +
                '}';
    }
}
