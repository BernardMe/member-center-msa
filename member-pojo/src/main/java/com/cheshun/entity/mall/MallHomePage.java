package com.cheshun.entity.mall;

import lombok.Data;

import java.io.Serializable;
@Data
public class MallHomePage implements Serializable {

    private Long id;

    private Long parentId;

    private String moduleName;

    private String enable;

    private String createtime;

    private String modifytime;

    private Integer moduleSort;

    private String image;
}
