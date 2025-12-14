package com.cheshun.entity.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 门店商城上下架表
 * @TableName store_mall_status
 */
@TableName(value ="store_mall_status")
@Data
@Builder
public class StoreMallStatus {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 门店编码
     */
    @TableField(value = "store_code")
    private String storeCode;

    /**
     * 更新时间
     */
    @TableField(value = "update_at")
    private LocalDateTime updateAt;
}