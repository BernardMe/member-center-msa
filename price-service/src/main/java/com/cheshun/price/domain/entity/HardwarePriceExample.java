package com.cheshun.price.domain.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HardwarePriceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HardwarePriceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductModelIsNull() {
            addCriterion("product_model is null");
            return (Criteria) this;
        }

        public Criteria andProductModelIsNotNull() {
            addCriterion("product_model is not null");
            return (Criteria) this;
        }

        public Criteria andProductModelEqualTo(String value) {
            addCriterion("product_model =", value, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductModelNotEqualTo(String value) {
            addCriterion("product_model <>", value, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductModelGreaterThan(String value) {
            addCriterion("product_model >", value, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductModelGreaterThanOrEqualTo(String value) {
            addCriterion("product_model >=", value, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductModelLessThan(String value) {
            addCriterion("product_model <", value, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductModelLessThanOrEqualTo(String value) {
            addCriterion("product_model <=", value, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductModelLike(String value) {
            addCriterion("product_model like", value, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductModelNotLike(String value) {
            addCriterion("product_model not like", value, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductModelIn(List<String> values) {
            addCriterion("product_model in", values, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductModelNotIn(List<String> values) {
            addCriterion("product_model not in", values, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductModelBetween(String value1, String value2) {
            addCriterion("product_model between", value1, value2, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductModelNotBetween(String value1, String value2) {
            addCriterion("product_model not between", value1, value2, "productModel");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("product_type is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("product_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(String value) {
            addCriterion("product_type =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(String value) {
            addCriterion("product_type <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(String value) {
            addCriterion("product_type >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(String value) {
            addCriterion("product_type >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(String value) {
            addCriterion("product_type <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(String value) {
            addCriterion("product_type <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLike(String value) {
            addCriterion("product_type like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotLike(String value) {
            addCriterion("product_type not like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<String> values) {
            addCriterion("product_type in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<String> values) {
            addCriterion("product_type not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(String value1, String value2) {
            addCriterion("product_type between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(String value1, String value2) {
            addCriterion("product_type not between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andBrandIsNull() {
            addCriterion("brand is null");
            return (Criteria) this;
        }

        public Criteria andBrandIsNotNull() {
            addCriterion("brand is not null");
            return (Criteria) this;
        }

        public Criteria andBrandEqualTo(String value) {
            addCriterion("brand =", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotEqualTo(String value) {
            addCriterion("brand <>", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThan(String value) {
            addCriterion("brand >", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandGreaterThanOrEqualTo(String value) {
            addCriterion("brand >=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThan(String value) {
            addCriterion("brand <", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLessThanOrEqualTo(String value) {
            addCriterion("brand <=", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandLike(String value) {
            addCriterion("brand like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotLike(String value) {
            addCriterion("brand not like", value, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandIn(List<String> values) {
            addCriterion("brand in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotIn(List<String> values) {
            addCriterion("brand not in", values, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandBetween(String value1, String value2) {
            addCriterion("brand between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andBrandNotBetween(String value1, String value2) {
            addCriterion("brand not between", value1, value2, "brand");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNull() {
            addCriterion("platform is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNotNull() {
            addCriterion("platform is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformEqualTo(String value) {
            addCriterion("platform =", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotEqualTo(String value) {
            addCriterion("platform <>", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThan(String value) {
            addCriterion("platform >", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThanOrEqualTo(String value) {
            addCriterion("platform >=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThan(String value) {
            addCriterion("platform <", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThanOrEqualTo(String value) {
            addCriterion("platform <=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLike(String value) {
            addCriterion("platform like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotLike(String value) {
            addCriterion("platform not like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformIn(List<String> values) {
            addCriterion("platform in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotIn(List<String> values) {
            addCriterion("platform not in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformBetween(String value1, String value2) {
            addCriterion("platform between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotBetween(String value1, String value2) {
            addCriterion("platform not between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andProductUrlIsNull() {
            addCriterion("product_url is null");
            return (Criteria) this;
        }

        public Criteria andProductUrlIsNotNull() {
            addCriterion("product_url is not null");
            return (Criteria) this;
        }

        public Criteria andProductUrlEqualTo(String value) {
            addCriterion("product_url =", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotEqualTo(String value) {
            addCriterion("product_url <>", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlGreaterThan(String value) {
            addCriterion("product_url >", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlGreaterThanOrEqualTo(String value) {
            addCriterion("product_url >=", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlLessThan(String value) {
            addCriterion("product_url <", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlLessThanOrEqualTo(String value) {
            addCriterion("product_url <=", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlLike(String value) {
            addCriterion("product_url like", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotLike(String value) {
            addCriterion("product_url not like", value, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlIn(List<String> values) {
            addCriterion("product_url in", values, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotIn(List<String> values) {
            addCriterion("product_url not in", values, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlBetween(String value1, String value2) {
            addCriterion("product_url between", value1, value2, "productUrl");
            return (Criteria) this;
        }

        public Criteria andProductUrlNotBetween(String value1, String value2) {
            addCriterion("product_url not between", value1, value2, "productUrl");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNull() {
            addCriterion("original_price is null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNotNull() {
            addCriterion("original_price is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceEqualTo(BigDecimal value) {
            addCriterion("original_price =", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotEqualTo(BigDecimal value) {
            addCriterion("original_price <>", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThan(BigDecimal value) {
            addCriterion("original_price >", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("original_price >=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThan(BigDecimal value) {
            addCriterion("original_price <", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("original_price <=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIn(List<BigDecimal> values) {
            addCriterion("original_price in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotIn(List<BigDecimal> values) {
            addCriterion("original_price not in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_price between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_price not between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeIsNull() {
            addCriterion("sales_volume is null");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeIsNotNull() {
            addCriterion("sales_volume is not null");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeEqualTo(Integer value) {
            addCriterion("sales_volume =", value, "salesVolume");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeNotEqualTo(Integer value) {
            addCriterion("sales_volume <>", value, "salesVolume");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeGreaterThan(Integer value) {
            addCriterion("sales_volume >", value, "salesVolume");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales_volume >=", value, "salesVolume");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeLessThan(Integer value) {
            addCriterion("sales_volume <", value, "salesVolume");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeLessThanOrEqualTo(Integer value) {
            addCriterion("sales_volume <=", value, "salesVolume");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeIn(List<Integer> values) {
            addCriterion("sales_volume in", values, "salesVolume");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeNotIn(List<Integer> values) {
            addCriterion("sales_volume not in", values, "salesVolume");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeBetween(Integer value1, Integer value2) {
            addCriterion("sales_volume between", value1, value2, "salesVolume");
            return (Criteria) this;
        }

        public Criteria andSalesVolumeNotBetween(Integer value1, Integer value2) {
            addCriterion("sales_volume not between", value1, value2, "salesVolume");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("shop_name is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("shop_name is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("shop_name =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("shop_name <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("shop_name >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("shop_name >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("shop_name <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("shop_name <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("shop_name like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("shop_name not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("shop_name in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("shop_name not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("shop_name between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("shop_name not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNull() {
            addCriterion("image_url is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("image_url is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("image_url =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("image_url <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("image_url >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_url >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("image_url <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("image_url <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("image_url like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("image_url not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("image_url in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("image_url not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("image_url between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("image_url not between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeIsNull() {
            addCriterion("crawl_time is null");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeIsNotNull() {
            addCriterion("crawl_time is not null");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeEqualTo(Date value) {
            addCriterion("crawl_time =", value, "crawlTime");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeNotEqualTo(Date value) {
            addCriterion("crawl_time <>", value, "crawlTime");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeGreaterThan(Date value) {
            addCriterion("crawl_time >", value, "crawlTime");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("crawl_time >=", value, "crawlTime");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeLessThan(Date value) {
            addCriterion("crawl_time <", value, "crawlTime");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeLessThanOrEqualTo(Date value) {
            addCriterion("crawl_time <=", value, "crawlTime");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeIn(List<Date> values) {
            addCriterion("crawl_time in", values, "crawlTime");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeNotIn(List<Date> values) {
            addCriterion("crawl_time not in", values, "crawlTime");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeBetween(Date value1, Date value2) {
            addCriterion("crawl_time between", value1, value2, "crawlTime");
            return (Criteria) this;
        }

        public Criteria andCrawlTimeNotBetween(Date value1, Date value2) {
            addCriterion("crawl_time not between", value1, value2, "crawlTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}