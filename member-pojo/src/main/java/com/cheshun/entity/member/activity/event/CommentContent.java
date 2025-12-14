package com.cheshun.entity.member.activity.event;

import lombok.Data;

import java.io.Serializable;
@Data
public class CommentContent implements Serializable {

    private String commentId;

    private String commentCardNo;

    private String commentName;

    private String phone;

    private String followContent;

    private Integer singleId;

}
