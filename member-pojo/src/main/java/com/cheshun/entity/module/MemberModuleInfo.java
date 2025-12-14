package com.cheshun.entity.module;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemberModuleInfo implements Serializable {

    private String moduleId;

    private String moduleName;

    private String parentId;

    private String moduleSort;

    private String pictureUrl;

    private Integer delStatus;

    private String enable;

    private String jumpPath;

    private String pageType;

    private String createtime;

    private String modifytime;

    private String createUser;

    private String modifyUser;

}
