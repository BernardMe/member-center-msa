package com.cheshun.entity.project.customer.survey;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SurveySuggestionReplyDto {
    private String recordId;
    private String memberCardNo;
    private String paperId;
    private String questionId;
    private String questionType;
    private String answer;
    private String userPhone;
    private String userName;
    private String address;
    private String suggestionId;
    private String replyEmpId;
    private String replyEmpName;
    private String replyContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;
    private String replyStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime replyCreateDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime replyUpdateDate;
}
