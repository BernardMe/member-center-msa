package com.cheshun.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MemberInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String memberCardNo;

    private String memberName;

    private String memberPhone;

    private String gender;

    private String birthday;

}
