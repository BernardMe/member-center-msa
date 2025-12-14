package com.cheshun.vo.activity.diabetes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiabetesShareUpdateVO {
    private int id;
    private String name;
    private String pictureUrl;
    private String rulesUrl;
    private String manualSwitch;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;
    private String updateUser;
}
