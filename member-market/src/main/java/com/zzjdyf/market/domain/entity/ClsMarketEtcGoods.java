package com.zzjdyf.market.domain.entity;

import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zzjdyf.market.domain.entity.enums.PublishStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * ETC商品信息
 *
 * @author wangzhuo
 * Created on 20210721
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ClsMarketEtcGoods extends BaseEntity {
    /**
     * 品牌
     */
    private String brand;
    /**
     * 型号
     */
    private String model;
    /**
     * 图片
     */
    private String images;
    /**
     * 详情
     */
    private String details;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 销量
     */
    private Long sales;
    /**
     * OBU库存
     */
    private Integer obuStock;
    /**
     * OBU销量
     */
    private Long obuSales;
    /**
     * 状态(未上架|已上架)
     */
    private PublishStatus status;

    @TableField(exist = false)
    private List<String> imageList;

    public List<String> getImageList() {
        if (StringUtils.hasText(images)) {
            return JSONUtil.toBean(images, new TypeReference<List<String>>() {
            }, true);
        }
        return Collections.emptyList();
    }
}