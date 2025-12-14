package com.cheshun.mall.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 全局配置信息
 *
 * @author 阿隆
 * Created on 2021/8/12 13:08.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcGlobalSetting extends BaseEntity {
    /**
     * 名称
     */
    private String name;
    /**
     * 值
     */
    private String data;
    /**
     * 描述
     */
    @TableField(value = "`describe`")
    private String describe;
}
