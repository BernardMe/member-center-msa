package com.cheshun.entity.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 门店标签关联表
 * @TableName store_join_tag
 */
@TableName(value ="store_join_tag")
@Data
public class StoreJoinTag {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签id
     */
    @TableField(value = "tag_id")
    private Integer tagId;

    /**
     * 门店id
     */
    @TableField(value = "store_id")
    private String storeId;

    /**
     * 状态
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_at")
    private LocalDateTime createAt;

}