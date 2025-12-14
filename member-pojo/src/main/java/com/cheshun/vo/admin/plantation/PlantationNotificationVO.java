package com.cheshun.vo.admin.plantation;

import lombok.Data;

/**
 * 公告VO类
 * @author wangzhuo
 * @date 20250802
 */
@Data
public class PlantationNotificationVO {

    //主键ID
    private Integer notificationId;

    //创建人主键
    private Integer managerId;

    //是否启用
    private Byte isEnable;

    //通知标题
    private String title;

    //通知内容
    private String content;

    //PICTURE_URL 图片url，多个英文逗号分隔
    private String pictureUrl;

    //消息关联植株id
    private Integer herbId;


}
