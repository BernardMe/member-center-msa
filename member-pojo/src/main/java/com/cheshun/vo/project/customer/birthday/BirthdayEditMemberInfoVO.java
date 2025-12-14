package com.cheshun.vo.project.customer.birthday;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BirthdayEditMemberInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    private Integer activityId;

    private String memberCardNo;

    private String memberName;

    private String memberPhone;

    private String gender;

    private String birthday;

}
