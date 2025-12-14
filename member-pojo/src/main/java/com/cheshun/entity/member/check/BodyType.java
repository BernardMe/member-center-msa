package com.cheshun.entity.member.check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author huowen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
public class BodyType implements Comparable<BodyType>{
    private String type;
    private Integer score;

    @Override
    public int compareTo(BodyType bodyType) {//重写Comparable接口的compareTo方法，
        return bodyType.getScore() - this.score;// 根据score降序排列，升序修改相减顺序即可
    }

}
