package com.cheshun.entity.mall;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GoodsInfosExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsInfosExample() {
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

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Integer value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Integer value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Integer value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Integer value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Integer> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Integer> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeIsNull() {
            addCriterion("goods_code is null");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeIsNotNull() {
            addCriterion("goods_code is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeEqualTo(String value) {
            addCriterion("goods_code =", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotEqualTo(String value) {
            addCriterion("goods_code <>", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeGreaterThan(String value) {
            addCriterion("goods_code >", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("goods_code >=", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeLessThan(String value) {
            addCriterion("goods_code <", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeLessThanOrEqualTo(String value) {
            addCriterion("goods_code <=", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeLike(String value) {
            addCriterion("goods_code like", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotLike(String value) {
            addCriterion("goods_code not like", value, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeIn(List<String> values) {
            addCriterion("goods_code in", values, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotIn(List<String> values) {
            addCriterion("goods_code not in", values, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeBetween(String value1, String value2) {
            addCriterion("goods_code between", value1, value2, "goodsCode");
            return (Criteria) this;
        }

        public Criteria andGoodsCodeNotBetween(String value1, String value2) {
            addCriterion("goods_code not between", value1, value2, "goodsCode");
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

        public Criteria andSubTitleIsNull() {
            addCriterion("sub_title is null");
            return (Criteria) this;
        }

        public Criteria andSubTitleIsNotNull() {
            addCriterion("sub_title is not null");
            return (Criteria) this;
        }

        public Criteria andSubTitleEqualTo(String value) {
            addCriterion("sub_title =", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotEqualTo(String value) {
            addCriterion("sub_title <>", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleGreaterThan(String value) {
            addCriterion("sub_title >", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleGreaterThanOrEqualTo(String value) {
            addCriterion("sub_title >=", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLessThan(String value) {
            addCriterion("sub_title <", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLessThanOrEqualTo(String value) {
            addCriterion("sub_title <=", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleLike(String value) {
            addCriterion("sub_title like", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotLike(String value) {
            addCriterion("sub_title not like", value, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleIn(List<String> values) {
            addCriterion("sub_title in", values, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotIn(List<String> values) {
            addCriterion("sub_title not in", values, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleBetween(String value1, String value2) {
            addCriterion("sub_title between", value1, value2, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSubTitleNotBetween(String value1, String value2) {
            addCriterion("sub_title not between", value1, value2, "subTitle");
            return (Criteria) this;
        }

        public Criteria andSpecificationIsNull() {
            addCriterion("specification is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationIsNotNull() {
            addCriterion("specification is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationEqualTo(String value) {
            addCriterion("specification =", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotEqualTo(String value) {
            addCriterion("specification <>", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationGreaterThan(String value) {
            addCriterion("specification >", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationGreaterThanOrEqualTo(String value) {
            addCriterion("specification >=", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationLessThan(String value) {
            addCriterion("specification <", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationLessThanOrEqualTo(String value) {
            addCriterion("specification <=", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationLike(String value) {
            addCriterion("specification like", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotLike(String value) {
            addCriterion("specification not like", value, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationIn(List<String> values) {
            addCriterion("specification in", values, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotIn(List<String> values) {
            addCriterion("specification not in", values, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationBetween(String value1, String value2) {
            addCriterion("specification between", value1, value2, "specification");
            return (Criteria) this;
        }

        public Criteria andSpecificationNotBetween(String value1, String value2) {
            addCriterion("specification not between", value1, value2, "specification");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionIsNull() {
            addCriterion("drug_description is null");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionIsNotNull() {
            addCriterion("drug_description is not null");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionEqualTo(String value) {
            addCriterion("drug_description =", value, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionNotEqualTo(String value) {
            addCriterion("drug_description <>", value, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionGreaterThan(String value) {
            addCriterion("drug_description >", value, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("drug_description >=", value, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionLessThan(String value) {
            addCriterion("drug_description <", value, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionLessThanOrEqualTo(String value) {
            addCriterion("drug_description <=", value, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionLike(String value) {
            addCriterion("drug_description like", value, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionNotLike(String value) {
            addCriterion("drug_description not like", value, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionIn(List<String> values) {
            addCriterion("drug_description in", values, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionNotIn(List<String> values) {
            addCriterion("drug_description not in", values, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionBetween(String value1, String value2) {
            addCriterion("drug_description between", value1, value2, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugDescriptionNotBetween(String value1, String value2) {
            addCriterion("drug_description not between", value1, value2, "drugDescription");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoIsNull() {
            addCriterion("drug_approval_no is null");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoIsNotNull() {
            addCriterion("drug_approval_no is not null");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoEqualTo(String value) {
            addCriterion("drug_approval_no =", value, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoNotEqualTo(String value) {
            addCriterion("drug_approval_no <>", value, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoGreaterThan(String value) {
            addCriterion("drug_approval_no >", value, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoGreaterThanOrEqualTo(String value) {
            addCriterion("drug_approval_no >=", value, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoLessThan(String value) {
            addCriterion("drug_approval_no <", value, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoLessThanOrEqualTo(String value) {
            addCriterion("drug_approval_no <=", value, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoLike(String value) {
            addCriterion("drug_approval_no like", value, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoNotLike(String value) {
            addCriterion("drug_approval_no not like", value, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoIn(List<String> values) {
            addCriterion("drug_approval_no in", values, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoNotIn(List<String> values) {
            addCriterion("drug_approval_no not in", values, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoBetween(String value1, String value2) {
            addCriterion("drug_approval_no between", value1, value2, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDrugApprovalNoNotBetween(String value1, String value2) {
            addCriterion("drug_approval_no not between", value1, value2, "drugApprovalNo");
            return (Criteria) this;
        }

        public Criteria andDosageFormIsNull() {
            addCriterion("dosage_form is null");
            return (Criteria) this;
        }

        public Criteria andDosageFormIsNotNull() {
            addCriterion("dosage_form is not null");
            return (Criteria) this;
        }

        public Criteria andDosageFormEqualTo(String value) {
            addCriterion("dosage_form =", value, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andDosageFormNotEqualTo(String value) {
            addCriterion("dosage_form <>", value, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andDosageFormGreaterThan(String value) {
            addCriterion("dosage_form >", value, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andDosageFormGreaterThanOrEqualTo(String value) {
            addCriterion("dosage_form >=", value, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andDosageFormLessThan(String value) {
            addCriterion("dosage_form <", value, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andDosageFormLessThanOrEqualTo(String value) {
            addCriterion("dosage_form <=", value, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andDosageFormLike(String value) {
            addCriterion("dosage_form like", value, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andDosageFormNotLike(String value) {
            addCriterion("dosage_form not like", value, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andDosageFormIn(List<String> values) {
            addCriterion("dosage_form in", values, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andDosageFormNotIn(List<String> values) {
            addCriterion("dosage_form not in", values, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andDosageFormBetween(String value1, String value2) {
            addCriterion("dosage_form between", value1, value2, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andDosageFormNotBetween(String value1, String value2) {
            addCriterion("dosage_form not between", value1, value2, "dosageForm");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNull() {
            addCriterion("manufacturer is null");
            return (Criteria) this;
        }

        public Criteria andManufacturerIsNotNull() {
            addCriterion("manufacturer is not null");
            return (Criteria) this;
        }

        public Criteria andManufacturerEqualTo(String value) {
            addCriterion("manufacturer =", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotEqualTo(String value) {
            addCriterion("manufacturer <>", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThan(String value) {
            addCriterion("manufacturer >", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerGreaterThanOrEqualTo(String value) {
            addCriterion("manufacturer >=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThan(String value) {
            addCriterion("manufacturer <", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLessThanOrEqualTo(String value) {
            addCriterion("manufacturer <=", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerLike(String value) {
            addCriterion("manufacturer like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotLike(String value) {
            addCriterion("manufacturer not like", value, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerIn(List<String> values) {
            addCriterion("manufacturer in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotIn(List<String> values) {
            addCriterion("manufacturer not in", values, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerBetween(String value1, String value2) {
            addCriterion("manufacturer between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andManufacturerNotBetween(String value1, String value2) {
            addCriterion("manufacturer not between", value1, value2, "manufacturer");
            return (Criteria) this;
        }

        public Criteria andOriginAddressIsNull() {
            addCriterion("origin_address is null");
            return (Criteria) this;
        }

        public Criteria andOriginAddressIsNotNull() {
            addCriterion("origin_address is not null");
            return (Criteria) this;
        }

        public Criteria andOriginAddressEqualTo(String value) {
            addCriterion("origin_address =", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressNotEqualTo(String value) {
            addCriterion("origin_address <>", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressGreaterThan(String value) {
            addCriterion("origin_address >", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressGreaterThanOrEqualTo(String value) {
            addCriterion("origin_address >=", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressLessThan(String value) {
            addCriterion("origin_address <", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressLessThanOrEqualTo(String value) {
            addCriterion("origin_address <=", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressLike(String value) {
            addCriterion("origin_address like", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressNotLike(String value) {
            addCriterion("origin_address not like", value, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressIn(List<String> values) {
            addCriterion("origin_address in", values, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressNotIn(List<String> values) {
            addCriterion("origin_address not in", values, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressBetween(String value1, String value2) {
            addCriterion("origin_address between", value1, value2, "originAddress");
            return (Criteria) this;
        }

        public Criteria andOriginAddressNotBetween(String value1, String value2) {
            addCriterion("origin_address not between", value1, value2, "originAddress");
            return (Criteria) this;
        }

        public Criteria andEan13IsNull() {
            addCriterion("ean13 is null");
            return (Criteria) this;
        }

        public Criteria andEan13IsNotNull() {
            addCriterion("ean13 is not null");
            return (Criteria) this;
        }

        public Criteria andEan13EqualTo(String value) {
            addCriterion("ean13 =", value, "ean13");
            return (Criteria) this;
        }

        public Criteria andEan13NotEqualTo(String value) {
            addCriterion("ean13 <>", value, "ean13");
            return (Criteria) this;
        }

        public Criteria andEan13GreaterThan(String value) {
            addCriterion("ean13 >", value, "ean13");
            return (Criteria) this;
        }

        public Criteria andEan13GreaterThanOrEqualTo(String value) {
            addCriterion("ean13 >=", value, "ean13");
            return (Criteria) this;
        }

        public Criteria andEan13LessThan(String value) {
            addCriterion("ean13 <", value, "ean13");
            return (Criteria) this;
        }

        public Criteria andEan13LessThanOrEqualTo(String value) {
            addCriterion("ean13 <=", value, "ean13");
            return (Criteria) this;
        }

        public Criteria andEan13Like(String value) {
            addCriterion("ean13 like", value, "ean13");
            return (Criteria) this;
        }

        public Criteria andEan13NotLike(String value) {
            addCriterion("ean13 not like", value, "ean13");
            return (Criteria) this;
        }

        public Criteria andEan13In(List<String> values) {
            addCriterion("ean13 in", values, "ean13");
            return (Criteria) this;
        }

        public Criteria andEan13NotIn(List<String> values) {
            addCriterion("ean13 not in", values, "ean13");
            return (Criteria) this;
        }

        public Criteria andEan13Between(String value1, String value2) {
            addCriterion("ean13 between", value1, value2, "ean13");
            return (Criteria) this;
        }

        public Criteria andEan13NotBetween(String value1, String value2) {
            addCriterion("ean13 not between", value1, value2, "ean13");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryIsNull() {
            addCriterion("drug_category is null");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryIsNotNull() {
            addCriterion("drug_category is not null");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryEqualTo(String value) {
            addCriterion("drug_category =", value, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryNotEqualTo(String value) {
            addCriterion("drug_category <>", value, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryGreaterThan(String value) {
            addCriterion("drug_category >", value, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("drug_category >=", value, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryLessThan(String value) {
            addCriterion("drug_category <", value, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryLessThanOrEqualTo(String value) {
            addCriterion("drug_category <=", value, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryLike(String value) {
            addCriterion("drug_category like", value, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryNotLike(String value) {
            addCriterion("drug_category not like", value, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryIn(List<String> values) {
            addCriterion("drug_category in", values, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryNotIn(List<String> values) {
            addCriterion("drug_category not in", values, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryBetween(String value1, String value2) {
            addCriterion("drug_category between", value1, value2, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andDrugCategoryNotBetween(String value1, String value2) {
            addCriterion("drug_category not between", value1, value2, "drugCategory");
            return (Criteria) this;
        }

        public Criteria andRetailPriceIsNull() {
            addCriterion("retail_price is null");
            return (Criteria) this;
        }

        public Criteria andRetailPriceIsNotNull() {
            addCriterion("retail_price is not null");
            return (Criteria) this;
        }

        public Criteria andRetailPriceEqualTo(Integer value) {
            addCriterion("retail_price =", value, "retailPrice");
            return (Criteria) this;
        }

        public Criteria andRetailPriceNotEqualTo(Integer value) {
            addCriterion("retail_price <>", value, "retailPrice");
            return (Criteria) this;
        }

        public Criteria andRetailPriceGreaterThan(Integer value) {
            addCriterion("retail_price >", value, "retailPrice");
            return (Criteria) this;
        }

        public Criteria andRetailPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("retail_price >=", value, "retailPrice");
            return (Criteria) this;
        }

        public Criteria andRetailPriceLessThan(Integer value) {
            addCriterion("retail_price <", value, "retailPrice");
            return (Criteria) this;
        }

        public Criteria andRetailPriceLessThanOrEqualTo(Integer value) {
            addCriterion("retail_price <=", value, "retailPrice");
            return (Criteria) this;
        }

        public Criteria andRetailPriceIn(List<Integer> values) {
            addCriterion("retail_price in", values, "retailPrice");
            return (Criteria) this;
        }

        public Criteria andRetailPriceNotIn(List<Integer> values) {
            addCriterion("retail_price not in", values, "retailPrice");
            return (Criteria) this;
        }

        public Criteria andRetailPriceBetween(Integer value1, Integer value2) {
            addCriterion("retail_price between", value1, value2, "retailPrice");
            return (Criteria) this;
        }

        public Criteria andRetailPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("retail_price not between", value1, value2, "retailPrice");
            return (Criteria) this;
        }

        public Criteria andIsBondedIsNull() {
            addCriterion("is_bonded is null");
            return (Criteria) this;
        }

        public Criteria andIsBondedIsNotNull() {
            addCriterion("is_bonded is not null");
            return (Criteria) this;
        }

        public Criteria andIsBondedEqualTo(Boolean value) {
            addCriterion("is_bonded =", value, "isBonded");
            return (Criteria) this;
        }

        public Criteria andIsBondedNotEqualTo(Boolean value) {
            addCriterion("is_bonded <>", value, "isBonded");
            return (Criteria) this;
        }

        public Criteria andIsBondedGreaterThan(Boolean value) {
            addCriterion("is_bonded >", value, "isBonded");
            return (Criteria) this;
        }

        public Criteria andIsBondedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_bonded >=", value, "isBonded");
            return (Criteria) this;
        }

        public Criteria andIsBondedLessThan(Boolean value) {
            addCriterion("is_bonded <", value, "isBonded");
            return (Criteria) this;
        }

        public Criteria andIsBondedLessThanOrEqualTo(Boolean value) {
            addCriterion("is_bonded <=", value, "isBonded");
            return (Criteria) this;
        }

        public Criteria andIsBondedIn(List<Boolean> values) {
            addCriterion("is_bonded in", values, "isBonded");
            return (Criteria) this;
        }

        public Criteria andIsBondedNotIn(List<Boolean> values) {
            addCriterion("is_bonded not in", values, "isBonded");
            return (Criteria) this;
        }

        public Criteria andIsBondedBetween(Boolean value1, Boolean value2) {
            addCriterion("is_bonded between", value1, value2, "isBonded");
            return (Criteria) this;
        }

        public Criteria andIsBondedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_bonded not between", value1, value2, "isBonded");
            return (Criteria) this;
        }

        public Criteria andCoverImageIsNull() {
            addCriterion("cover_image is null");
            return (Criteria) this;
        }

        public Criteria andCoverImageIsNotNull() {
            addCriterion("cover_image is not null");
            return (Criteria) this;
        }

        public Criteria andCoverImageEqualTo(String value) {
            addCriterion("cover_image =", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageNotEqualTo(String value) {
            addCriterion("cover_image <>", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageGreaterThan(String value) {
            addCriterion("cover_image >", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageGreaterThanOrEqualTo(String value) {
            addCriterion("cover_image >=", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageLessThan(String value) {
            addCriterion("cover_image <", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageLessThanOrEqualTo(String value) {
            addCriterion("cover_image <=", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageLike(String value) {
            addCriterion("cover_image like", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageNotLike(String value) {
            addCriterion("cover_image not like", value, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageIn(List<String> values) {
            addCriterion("cover_image in", values, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageNotIn(List<String> values) {
            addCriterion("cover_image not in", values, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageBetween(String value1, String value2) {
            addCriterion("cover_image between", value1, value2, "coverImage");
            return (Criteria) this;
        }

        public Criteria andCoverImageNotBetween(String value1, String value2) {
            addCriterion("cover_image not between", value1, value2, "coverImage");
            return (Criteria) this;
        }

        public Criteria andMainImageIsNull() {
            addCriterion("main_image is null");
            return (Criteria) this;
        }

        public Criteria andMainImageIsNotNull() {
            addCriterion("main_image is not null");
            return (Criteria) this;
        }

        public Criteria andMainImageEqualTo(String value) {
            addCriterion("main_image =", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageNotEqualTo(String value) {
            addCriterion("main_image <>", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageGreaterThan(String value) {
            addCriterion("main_image >", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageGreaterThanOrEqualTo(String value) {
            addCriterion("main_image >=", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageLessThan(String value) {
            addCriterion("main_image <", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageLessThanOrEqualTo(String value) {
            addCriterion("main_image <=", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageLike(String value) {
            addCriterion("main_image like", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageNotLike(String value) {
            addCriterion("main_image not like", value, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageIn(List<String> values) {
            addCriterion("main_image in", values, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageNotIn(List<String> values) {
            addCriterion("main_image not in", values, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageBetween(String value1, String value2) {
            addCriterion("main_image between", value1, value2, "mainImage");
            return (Criteria) this;
        }

        public Criteria andMainImageNotBetween(String value1, String value2) {
            addCriterion("main_image not between", value1, value2, "mainImage");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(LocalDateTime value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(LocalDateTime value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(LocalDateTime value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(LocalDateTime value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<LocalDateTime> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<LocalDateTime> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andModifytimeIsNull() {
            addCriterion("modifytime is null");
            return (Criteria) this;
        }

        public Criteria andModifytimeIsNotNull() {
            addCriterion("modifytime is not null");
            return (Criteria) this;
        }

        public Criteria andModifytimeEqualTo(LocalDateTime value) {
            addCriterion("modifytime =", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotEqualTo(LocalDateTime value) {
            addCriterion("modifytime <>", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeGreaterThan(LocalDateTime value) {
            addCriterion("modifytime >", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("modifytime >=", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeLessThan(LocalDateTime value) {
            addCriterion("modifytime <", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("modifytime <=", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeIn(List<LocalDateTime> values) {
            addCriterion("modifytime in", values, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotIn(List<LocalDateTime> values) {
            addCriterion("modifytime not in", values, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("modifytime between", value1, value2, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("modifytime not between", value1, value2, "modifytime");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsIsNull() {
            addCriterion("goods_keywords is null");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsIsNotNull() {
            addCriterion("goods_keywords is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsEqualTo(String value) {
            addCriterion("goods_keywords =", value, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsNotEqualTo(String value) {
            addCriterion("goods_keywords <>", value, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsGreaterThan(String value) {
            addCriterion("goods_keywords >", value, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("goods_keywords >=", value, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsLessThan(String value) {
            addCriterion("goods_keywords <", value, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsLessThanOrEqualTo(String value) {
            addCriterion("goods_keywords <=", value, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsLike(String value) {
            addCriterion("goods_keywords like", value, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsNotLike(String value) {
            addCriterion("goods_keywords not like", value, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsIn(List<String> values) {
            addCriterion("goods_keywords in", values, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsNotIn(List<String> values) {
            addCriterion("goods_keywords not in", values, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsBetween(String value1, String value2) {
            addCriterion("goods_keywords between", value1, value2, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andGoodsKeywordsNotBetween(String value1, String value2) {
            addCriterion("goods_keywords not between", value1, value2, "goodsKeywords");
            return (Criteria) this;
        }

        public Criteria andSpuIsNull() {
            addCriterion("spu is null");
            return (Criteria) this;
        }

        public Criteria andSpuIsNotNull() {
            addCriterion("spu is not null");
            return (Criteria) this;
        }

        public Criteria andSpuEqualTo(String value) {
            addCriterion("spu =", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotEqualTo(String value) {
            addCriterion("spu <>", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuGreaterThan(String value) {
            addCriterion("spu >", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuGreaterThanOrEqualTo(String value) {
            addCriterion("spu >=", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuLessThan(String value) {
            addCriterion("spu <", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuLessThanOrEqualTo(String value) {
            addCriterion("spu <=", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuLike(String value) {
            addCriterion("spu like", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotLike(String value) {
            addCriterion("spu not like", value, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuIn(List<String> values) {
            addCriterion("spu in", values, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotIn(List<String> values) {
            addCriterion("spu not in", values, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuBetween(String value1, String value2) {
            addCriterion("spu between", value1, value2, "spu");
            return (Criteria) this;
        }

        public Criteria andSpuNotBetween(String value1, String value2) {
            addCriterion("spu not between", value1, value2, "spu");
            return (Criteria) this;
        }

        public Criteria andSkuIsNull() {
            addCriterion("sku is null");
            return (Criteria) this;
        }

        public Criteria andSkuIsNotNull() {
            addCriterion("sku is not null");
            return (Criteria) this;
        }

        public Criteria andSkuEqualTo(String value) {
            addCriterion("sku =", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotEqualTo(String value) {
            addCriterion("sku <>", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThan(String value) {
            addCriterion("sku >", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuGreaterThanOrEqualTo(String value) {
            addCriterion("sku >=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThan(String value) {
            addCriterion("sku <", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLessThanOrEqualTo(String value) {
            addCriterion("sku <=", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuLike(String value) {
            addCriterion("sku like", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotLike(String value) {
            addCriterion("sku not like", value, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuIn(List<String> values) {
            addCriterion("sku in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotIn(List<String> values) {
            addCriterion("sku not in", values, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuBetween(String value1, String value2) {
            addCriterion("sku between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andSkuNotBetween(String value1, String value2) {
            addCriterion("sku not between", value1, value2, "sku");
            return (Criteria) this;
        }

        public Criteria andEffectIsNull() {
            addCriterion("effect is null");
            return (Criteria) this;
        }

        public Criteria andEffectIsNotNull() {
            addCriterion("effect is not null");
            return (Criteria) this;
        }

        public Criteria andEffectEqualTo(String value) {
            addCriterion("effect =", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectNotEqualTo(String value) {
            addCriterion("effect <>", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectGreaterThan(String value) {
            addCriterion("effect >", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectGreaterThanOrEqualTo(String value) {
            addCriterion("effect >=", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectLessThan(String value) {
            addCriterion("effect <", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectLessThanOrEqualTo(String value) {
            addCriterion("effect <=", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectLike(String value) {
            addCriterion("effect like", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectNotLike(String value) {
            addCriterion("effect not like", value, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectIn(List<String> values) {
            addCriterion("effect in", values, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectNotIn(List<String> values) {
            addCriterion("effect not in", values, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectBetween(String value1, String value2) {
            addCriterion("effect between", value1, value2, "effect");
            return (Criteria) this;
        }

        public Criteria andEffectNotBetween(String value1, String value2) {
            addCriterion("effect not between", value1, value2, "effect");
            return (Criteria) this;
        }

        public Criteria andSupportO2oIsNull() {
            addCriterion("support_o2o is null");
            return (Criteria) this;
        }

        public Criteria andSupportO2oIsNotNull() {
            addCriterion("support_o2o is not null");
            return (Criteria) this;
        }

        public Criteria andSupportO2oEqualTo(Byte value) {
            addCriterion("support_o2o =", value, "supportO2o");
            return (Criteria) this;
        }

        public Criteria andSupportO2oNotEqualTo(Byte value) {
            addCriterion("support_o2o <>", value, "supportO2o");
            return (Criteria) this;
        }

        public Criteria andSupportO2oGreaterThan(Byte value) {
            addCriterion("support_o2o >", value, "supportO2o");
            return (Criteria) this;
        }

        public Criteria andSupportO2oGreaterThanOrEqualTo(Byte value) {
            addCriterion("support_o2o >=", value, "supportO2o");
            return (Criteria) this;
        }

        public Criteria andSupportO2oLessThan(Byte value) {
            addCriterion("support_o2o <", value, "supportO2o");
            return (Criteria) this;
        }

        public Criteria andSupportO2oLessThanOrEqualTo(Byte value) {
            addCriterion("support_o2o <=", value, "supportO2o");
            return (Criteria) this;
        }

        public Criteria andSupportO2oIn(List<Byte> values) {
            addCriterion("support_o2o in", values, "supportO2o");
            return (Criteria) this;
        }

        public Criteria andSupportO2oNotIn(List<Byte> values) {
            addCriterion("support_o2o not in", values, "supportO2o");
            return (Criteria) this;
        }

        public Criteria andSupportO2oBetween(Byte value1, Byte value2) {
            addCriterion("support_o2o between", value1, value2, "supportO2o");
            return (Criteria) this;
        }

        public Criteria andSupportO2oNotBetween(Byte value1, Byte value2) {
            addCriterion("support_o2o not between", value1, value2, "supportO2o");
            return (Criteria) this;
        }

        public Criteria andSupportB2cIsNull() {
            addCriterion("support_b2c is null");
            return (Criteria) this;
        }

        public Criteria andSupportB2cIsNotNull() {
            addCriterion("support_b2c is not null");
            return (Criteria) this;
        }

        public Criteria andSupportB2cEqualTo(Byte value) {
            addCriterion("support_b2c =", value, "supportB2c");
            return (Criteria) this;
        }

        public Criteria andSupportB2cNotEqualTo(Byte value) {
            addCriterion("support_b2c <>", value, "supportB2c");
            return (Criteria) this;
        }

        public Criteria andSupportB2cGreaterThan(Byte value) {
            addCriterion("support_b2c >", value, "supportB2c");
            return (Criteria) this;
        }

        public Criteria andSupportB2cGreaterThanOrEqualTo(Byte value) {
            addCriterion("support_b2c >=", value, "supportB2c");
            return (Criteria) this;
        }

        public Criteria andSupportB2cLessThan(Byte value) {
            addCriterion("support_b2c <", value, "supportB2c");
            return (Criteria) this;
        }

        public Criteria andSupportB2cLessThanOrEqualTo(Byte value) {
            addCriterion("support_b2c <=", value, "supportB2c");
            return (Criteria) this;
        }

        public Criteria andSupportB2cIn(List<Byte> values) {
            addCriterion("support_b2c in", values, "supportB2c");
            return (Criteria) this;
        }

        public Criteria andSupportB2cNotIn(List<Byte> values) {
            addCriterion("support_b2c not in", values, "supportB2c");
            return (Criteria) this;
        }

        public Criteria andSupportB2cBetween(Byte value1, Byte value2) {
            addCriterion("support_b2c between", value1, value2, "supportB2c");
            return (Criteria) this;
        }

        public Criteria andSupportB2cNotBetween(Byte value1, Byte value2) {
            addCriterion("support_b2c not between", value1, value2, "supportB2c");
            return (Criteria) this;
        }

        public Criteria andSupportUserIsNull() {
            addCriterion("support_user is null");
            return (Criteria) this;
        }

        public Criteria andSupportUserIsNotNull() {
            addCriterion("support_user is not null");
            return (Criteria) this;
        }

        public Criteria andSupportUserEqualTo(Byte value) {
            addCriterion("support_user =", value, "supportUser");
            return (Criteria) this;
        }

        public Criteria andSupportUserNotEqualTo(Byte value) {
            addCriterion("support_user <>", value, "supportUser");
            return (Criteria) this;
        }

        public Criteria andSupportUserGreaterThan(Byte value) {
            addCriterion("support_user >", value, "supportUser");
            return (Criteria) this;
        }

        public Criteria andSupportUserGreaterThanOrEqualTo(Byte value) {
            addCriterion("support_user >=", value, "supportUser");
            return (Criteria) this;
        }

        public Criteria andSupportUserLessThan(Byte value) {
            addCriterion("support_user <", value, "supportUser");
            return (Criteria) this;
        }

        public Criteria andSupportUserLessThanOrEqualTo(Byte value) {
            addCriterion("support_user <=", value, "supportUser");
            return (Criteria) this;
        }

        public Criteria andSupportUserIn(List<Byte> values) {
            addCriterion("support_user in", values, "supportUser");
            return (Criteria) this;
        }

        public Criteria andSupportUserNotIn(List<Byte> values) {
            addCriterion("support_user not in", values, "supportUser");
            return (Criteria) this;
        }

        public Criteria andSupportUserBetween(Byte value1, Byte value2) {
            addCriterion("support_user between", value1, value2, "supportUser");
            return (Criteria) this;
        }

        public Criteria andSupportUserNotBetween(Byte value1, Byte value2) {
            addCriterion("support_user not between", value1, value2, "supportUser");
            return (Criteria) this;
        }

        public Criteria andSearchTagIsNull() {
            addCriterion("search_tag is null");
            return (Criteria) this;
        }

        public Criteria andSearchTagIsNotNull() {
            addCriterion("search_tag is not null");
            return (Criteria) this;
        }

        public Criteria andSearchTagEqualTo(Byte value) {
            addCriterion("search_tag =", value, "searchTag");
            return (Criteria) this;
        }

        public Criteria andSearchTagNotEqualTo(Byte value) {
            addCriterion("search_tag <>", value, "searchTag");
            return (Criteria) this;
        }

        public Criteria andSearchTagGreaterThan(Byte value) {
            addCriterion("search_tag >", value, "searchTag");
            return (Criteria) this;
        }

        public Criteria andSearchTagGreaterThanOrEqualTo(Byte value) {
            addCriterion("search_tag >=", value, "searchTag");
            return (Criteria) this;
        }

        public Criteria andSearchTagLessThan(Byte value) {
            addCriterion("search_tag <", value, "searchTag");
            return (Criteria) this;
        }

        public Criteria andSearchTagLessThanOrEqualTo(Byte value) {
            addCriterion("search_tag <=", value, "searchTag");
            return (Criteria) this;
        }

        public Criteria andSearchTagIn(List<Byte> values) {
            addCriterion("search_tag in", values, "searchTag");
            return (Criteria) this;
        }

        public Criteria andSearchTagNotIn(List<Byte> values) {
            addCriterion("search_tag not in", values, "searchTag");
            return (Criteria) this;
        }

        public Criteria andSearchTagBetween(Byte value1, Byte value2) {
            addCriterion("search_tag between", value1, value2, "searchTag");
            return (Criteria) this;
        }

        public Criteria andSearchTagNotBetween(Byte value1, Byte value2) {
            addCriterion("search_tag not between", value1, value2, "searchTag");
            return (Criteria) this;
        }

        public Criteria andStorageIsNull() {
            addCriterion("storage is null");
            return (Criteria) this;
        }

        public Criteria andStorageIsNotNull() {
            addCriterion("storage is not null");
            return (Criteria) this;
        }

        public Criteria andStorageEqualTo(String value) {
            addCriterion("storage =", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotEqualTo(String value) {
            addCriterion("storage <>", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageGreaterThan(String value) {
            addCriterion("storage >", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageGreaterThanOrEqualTo(String value) {
            addCriterion("storage >=", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageLessThan(String value) {
            addCriterion("storage <", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageLessThanOrEqualTo(String value) {
            addCriterion("storage <=", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageLike(String value) {
            addCriterion("storage like", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotLike(String value) {
            addCriterion("storage not like", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageIn(List<String> values) {
            addCriterion("storage in", values, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotIn(List<String> values) {
            addCriterion("storage not in", values, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageBetween(String value1, String value2) {
            addCriterion("storage between", value1, value2, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotBetween(String value1, String value2) {
            addCriterion("storage not between", value1, value2, "storage");
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