package com.cheshun.entity.plantation;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PlantationConfigGlobalDTO implements Serializable {
    private Integer id;

    private String configCode;

    private String configName;

    private Byte configType;

    private Byte configDataClass;

    private String configValue;

    private String defaultValue;

    private Byte permissionEnable;

    private Integer permissionRoleId;

    private Byte isDelete;

    private Integer managerId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode == null ? null : configCode.trim();
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public Byte getConfigType() {
        return configType;
    }

    public void setConfigType(Byte configType) {
        this.configType = configType;
    }

    public Byte getConfigDataClass() {
        return configDataClass;
    }

    public void setConfigDataClass(Byte configDataClass) {
        this.configDataClass = configDataClass;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue == null ? null : defaultValue.trim();
    }

    public Byte getPermissionEnable() {
        return permissionEnable;
    }

    public void setPermissionEnable(Byte permissionEnable) {
        this.permissionEnable = permissionEnable;
    }

    public Integer getPermissionRoleId() {
        return permissionRoleId;
    }

    public void setPermissionRoleId(Integer permissionRoleId) {
        this.permissionRoleId = permissionRoleId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", configCode=").append(configCode);
        sb.append(", configName=").append(configName);
        sb.append(", configType=").append(configType);
        sb.append(", configDataClass=").append(configDataClass);
        sb.append(", configValue=").append(configValue);
        sb.append(", defaultValue=").append(defaultValue);
        sb.append(", permissionEnable=").append(permissionEnable);
        sb.append(", permissionRoleId=").append(permissionRoleId);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", managerId=").append(managerId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}