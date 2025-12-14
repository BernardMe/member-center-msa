package com.cheshun.vo.activity.turntable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDeletePrizeRequestVO {
    /**
     * 奖品ID
     */
    private List<Integer> prizeIdList;
}
