package com.cheshun.entity.member.check;

import lombok.Data;

import java.util.List;

/**
 * @author huowen
 */
@Data
public class RatingParam {

    private String vipId;

    private String cardCode;

    private List<Integer> qa;

}
