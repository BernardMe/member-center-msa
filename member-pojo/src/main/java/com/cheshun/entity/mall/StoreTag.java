package com.cheshun.entity.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 门店标签表
 *
 * @TableName store_tag
 */
@TableName(value = "store_tag")
@Data
public class StoreTag {
    /**
     *
     */
    @TableId(value = "tag_id", type = IdType.AUTO)
    private Integer tagId;

    /**
     * 标签名称
     */
    @TableField(value = "tag_name")
    private String tagName;

    /**
     * 父级标签id
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 标签排序
     */
    @TableField(value = "tag_order")
    private Integer tagOrder;

    /**
     * 状态（0不显示，1显示）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_at")
    private LocalDateTime createAt;
}