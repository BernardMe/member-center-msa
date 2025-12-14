package com.cheshun.entity.mall;

import java.io.Serializable;

public class GoodsInfosWithBLOBs extends GoodsInfos implements Serializable {
    private String images;

    private String context;

    private String drugCarousel;

    private static final long serialVersionUID = 1L;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public String getDrugCarousel() {
        return drugCarousel;
    }

    public void setDrugCarousel(String drugCarousel) {
        this.drugCarousel = drugCarousel == null ? null : drugCarousel.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", images=").append(images);
        sb.append(", context=").append(context);
        sb.append(", drugCarousel=").append(drugCarousel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}