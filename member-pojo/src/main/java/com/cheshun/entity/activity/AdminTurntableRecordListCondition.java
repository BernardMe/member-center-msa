package com.cheshun.entity.activity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminTurntableRecordListCondition extends AdminTurntableRecordDownloadCondition{
    private Integer pageSize;
    private Integer pageNum;
}
