package com.cheshun.vo.member.check;

import com.cheshun.entity.member.check.BodyType;
import com.cheshun.entity.member.check.VipCheckCopywriting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author huowen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResultVO {

    private String main;

    private List<String> secondary;

    private List<BodyType> socres;

    private List<VipCheckCopywriting> copywritings;

    private String name;

    private String img;

}
