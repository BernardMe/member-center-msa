package com.cheshun.entity.store;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "社群信息")
@Data
public class CommInfo implements Serializable {


    private String fqrurl;

    private String fgroupName;

    private String fstoreCode;

    private String fstoreName;

    private String fcommMemberNum;


}
