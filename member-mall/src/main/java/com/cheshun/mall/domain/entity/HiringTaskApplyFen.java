package com.cheshun.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 2.3 持久层使用Money与数据库交互
 * 这里，我们以 MybatisPlus 为例。
 * 通过扩展 mybatis 的 TypeHandler 来实现。
 *
 * 2.3.2 如果数据库里的金额字段是以“分”为单位的整形数据。那么，我们使用 MoneyFenTypeHandler
 */

/**
 * 按下面这样通过@TableField指定 typeHandler 来使用。
 */
@Data
@TableName(value = "hiring_task_apply_fen", autoResultMap = true)
public class HiringTaskApplyFen {

    @TableField(typeHandler = MoneyFenTypeHandler.class)
    private Money settlementAmount;

    // other fields
}