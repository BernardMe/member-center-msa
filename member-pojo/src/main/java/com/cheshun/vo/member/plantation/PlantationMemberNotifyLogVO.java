package com.cheshun.vo.member.plantation;

import lombok.Data;

import java.io.Serializable;

/**
 * 会员点击已读种植公告参数VO
 * @Author wangzhuo
 * @Date 20250805
 **/
@Data
public class PlantationMemberNotifyLogVO implements Serializable {

    /**
     * 公告id
     */
    private Integer notificationId;
    /**
     * 会员卡号
     */
    private String vipCardNo;

}

