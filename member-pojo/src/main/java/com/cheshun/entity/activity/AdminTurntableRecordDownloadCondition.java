package com.cheshun.entity.activity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminTurntableRecordDownloadCondition {
    private String turntableName;
    private String cardNo;
    private String phone;
    private String prizeName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
