package com.cheshun.entity.coupon;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CouponBaseInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CouponBaseInfoExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCouponCodeIsNull() {
            addCriterion("coupon_code is null");
            return (Criteria) this;
        }

        public Criteria andCouponCodeIsNotNull() {
            addCriterion("coupon_code is not null");
            return (Criteria) this;
        }

        public Criteria andCouponCodeEqualTo(String value) {
            addCriterion("coupon_code =", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeNotEqualTo(String value) {
            addCriterion("coupon_code <>", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeGreaterThan(String value) {
            addCriterion("coupon_code >", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_code >=", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeLessThan(String value) {
            addCriterion("coupon_code <", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeLessThanOrEqualTo(String value) {
            addCriterion("coupon_code <=", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeLike(String value) {
            addCriterion("coupon_code like", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeNotLike(String value) {
            addCriterion("coupon_code not like", value, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeIn(List<String> values) {
            addCriterion("coupon_code in", values, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeNotIn(List<String> values) {
            addCriterion("coupon_code not in", values, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeBetween(String value1, String value2) {
            addCriterion("coupon_code between", value1, value2, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponCodeNotBetween(String value1, String value2) {
            addCriterion("coupon_code not between", value1, value2, "couponCode");
            return (Criteria) this;
        }

        public Criteria andCouponNameIsNull() {
            addCriterion("coupon_name is null");
            return (Criteria) this;
        }

        public Criteria andCouponNameIsNotNull() {
            addCriterion("coupon_name is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNameEqualTo(String value) {
            addCriterion("coupon_name =", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotEqualTo(String value) {
            addCriterion("coupon_name <>", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameGreaterThan(String value) {
            addCriterion("coupon_name >", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_name >=", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLessThan(String value) {
            addCriterion("coupon_name <", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLessThanOrEqualTo(String value) {
            addCriterion("coupon_name <=", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameLike(String value) {
            addCriterion("coupon_name like", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotLike(String value) {
            addCriterion("coupon_name not like", value, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameIn(List<String> values) {
            addCriterion("coupon_name in", values, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotIn(List<String> values) {
            addCriterion("coupon_name not in", values, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameBetween(String value1, String value2) {
            addCriterion("coupon_name between", value1, value2, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponNameNotBetween(String value1, String value2) {
            addCriterion("coupon_name not between", value1, value2, "couponName");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionIsNull() {
            addCriterion("coupon_description is null");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionIsNotNull() {
            addCriterion("coupon_description is not null");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionEqualTo(String value) {
            addCriterion("coupon_description =", value, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionNotEqualTo(String value) {
            addCriterion("coupon_description <>", value, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionGreaterThan(String value) {
            addCriterion("coupon_description >", value, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_description >=", value, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionLessThan(String value) {
            addCriterion("coupon_description <", value, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionLessThanOrEqualTo(String value) {
            addCriterion("coupon_description <=", value, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionLike(String value) {
            addCriterion("coupon_description like", value, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionNotLike(String value) {
            addCriterion("coupon_description not like", value, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionIn(List<String> values) {
            addCriterion("coupon_description in", values, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionNotIn(List<String> values) {
            addCriterion("coupon_description not in", values, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionBetween(String value1, String value2) {
            addCriterion("coupon_description between", value1, value2, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponDescriptionNotBetween(String value1, String value2) {
            addCriterion("coupon_description not between", value1, value2, "couponDescription");
            return (Criteria) this;
        }

        public Criteria andCouponRulesIsNull() {
            addCriterion("coupon_rules is null");
            return (Criteria) this;
        }

        public Criteria andCouponRulesIsNotNull() {
            addCriterion("coupon_rules is not null");
            return (Criteria) this;
        }

        public Criteria andCouponRulesEqualTo(String value) {
            addCriterion("coupon_rules =", value, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCouponRulesNotEqualTo(String value) {
            addCriterion("coupon_rules <>", value, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCouponRulesGreaterThan(String value) {
            addCriterion("coupon_rules >", value, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCouponRulesGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_rules >=", value, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCouponRulesLessThan(String value) {
            addCriterion("coupon_rules <", value, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCouponRulesLessThanOrEqualTo(String value) {
            addCriterion("coupon_rules <=", value, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCouponRulesLike(String value) {
            addCriterion("coupon_rules like", value, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCouponRulesNotLike(String value) {
            addCriterion("coupon_rules not like", value, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCouponRulesIn(List<String> values) {
            addCriterion("coupon_rules in", values, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCouponRulesNotIn(List<String> values) {
            addCriterion("coupon_rules not in", values, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCouponRulesBetween(String value1, String value2) {
            addCriterion("coupon_rules between", value1, value2, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCouponRulesNotBetween(String value1, String value2) {
            addCriterion("coupon_rules not between", value1, value2, "couponRules");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeIsNull() {
            addCriterion("creater_code is null");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeIsNotNull() {
            addCriterion("creater_code is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeEqualTo(String value) {
            addCriterion("creater_code =", value, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeNotEqualTo(String value) {
            addCriterion("creater_code <>", value, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeGreaterThan(String value) {
            addCriterion("creater_code >", value, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeGreaterThanOrEqualTo(String value) {
            addCriterion("creater_code >=", value, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeLessThan(String value) {
            addCriterion("creater_code <", value, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeLessThanOrEqualTo(String value) {
            addCriterion("creater_code <=", value, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeLike(String value) {
            addCriterion("creater_code like", value, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeNotLike(String value) {
            addCriterion("creater_code not like", value, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeIn(List<String> values) {
            addCriterion("creater_code in", values, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeNotIn(List<String> values) {
            addCriterion("creater_code not in", values, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeBetween(String value1, String value2) {
            addCriterion("creater_code between", value1, value2, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterCodeNotBetween(String value1, String value2) {
            addCriterion("creater_code not between", value1, value2, "createrCode");
            return (Criteria) this;
        }

        public Criteria andCreaterNameIsNull() {
            addCriterion("creater_name is null");
            return (Criteria) this;
        }

        public Criteria andCreaterNameIsNotNull() {
            addCriterion("creater_name is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterNameEqualTo(String value) {
            addCriterion("creater_name =", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameNotEqualTo(String value) {
            addCriterion("creater_name <>", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameGreaterThan(String value) {
            addCriterion("creater_name >", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameGreaterThanOrEqualTo(String value) {
            addCriterion("creater_name >=", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameLessThan(String value) {
            addCriterion("creater_name <", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameLessThanOrEqualTo(String value) {
            addCriterion("creater_name <=", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameLike(String value) {
            addCriterion("creater_name like", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameNotLike(String value) {
            addCriterion("creater_name not like", value, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameIn(List<String> values) {
            addCriterion("creater_name in", values, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameNotIn(List<String> values) {
            addCriterion("creater_name not in", values, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameBetween(String value1, String value2) {
            addCriterion("creater_name between", value1, value2, "createrName");
            return (Criteria) this;
        }

        public Criteria andCreaterNameNotBetween(String value1, String value2) {
            addCriterion("creater_name not between", value1, value2, "createrName");
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

        public Criteria andCreateTimeEqualTo(LocalDateTime value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(LocalDateTime value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(LocalDateTime value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<LocalDateTime> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeIsNull() {
            addCriterion("coupon_type_code is null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeIsNotNull() {
            addCriterion("coupon_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeEqualTo(String value) {
            addCriterion("coupon_type_code =", value, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeNotEqualTo(String value) {
            addCriterion("coupon_type_code <>", value, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeGreaterThan(String value) {
            addCriterion("coupon_type_code >", value, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_type_code >=", value, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeLessThan(String value) {
            addCriterion("coupon_type_code <", value, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("coupon_type_code <=", value, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeLike(String value) {
            addCriterion("coupon_type_code like", value, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeNotLike(String value) {
            addCriterion("coupon_type_code not like", value, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeIn(List<String> values) {
            addCriterion("coupon_type_code in", values, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeNotIn(List<String> values) {
            addCriterion("coupon_type_code not in", values, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeBetween(String value1, String value2) {
            addCriterion("coupon_type_code between", value1, value2, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeCodeNotBetween(String value1, String value2) {
            addCriterion("coupon_type_code not between", value1, value2, "couponTypeCode");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameIsNull() {
            addCriterion("coupon_type_name is null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameIsNotNull() {
            addCriterion("coupon_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameEqualTo(String value) {
            addCriterion("coupon_type_name =", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameNotEqualTo(String value) {
            addCriterion("coupon_type_name <>", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameGreaterThan(String value) {
            addCriterion("coupon_type_name >", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_type_name >=", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameLessThan(String value) {
            addCriterion("coupon_type_name <", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameLessThanOrEqualTo(String value) {
            addCriterion("coupon_type_name <=", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameLike(String value) {
            addCriterion("coupon_type_name like", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameNotLike(String value) {
            addCriterion("coupon_type_name not like", value, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameIn(List<String> values) {
            addCriterion("coupon_type_name in", values, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameNotIn(List<String> values) {
            addCriterion("coupon_type_name not in", values, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameBetween(String value1, String value2) {
            addCriterion("coupon_type_name between", value1, value2, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andCouponTypeNameNotBetween(String value1, String value2) {
            addCriterion("coupon_type_name not between", value1, value2, "couponTypeName");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeIsNull() {
            addCriterion("issue_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeIsNotNull() {
            addCriterion("issue_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeEqualTo(LocalDateTime value) {
            addCriterion("issue_begin_time =", value, "issueBeginTime");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeNotEqualTo(LocalDateTime value) {
            addCriterion("issue_begin_time <>", value, "issueBeginTime");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeGreaterThan(LocalDateTime value) {
            addCriterion("issue_begin_time >", value, "issueBeginTime");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("issue_begin_time >=", value, "issueBeginTime");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeLessThan(LocalDateTime value) {
            addCriterion("issue_begin_time <", value, "issueBeginTime");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("issue_begin_time <=", value, "issueBeginTime");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeIn(List<LocalDateTime> values) {
            addCriterion("issue_begin_time in", values, "issueBeginTime");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeNotIn(List<LocalDateTime> values) {
            addCriterion("issue_begin_time not in", values, "issueBeginTime");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("issue_begin_time between", value1, value2, "issueBeginTime");
            return (Criteria) this;
        }

        public Criteria andIssueBeginTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("issue_begin_time not between", value1, value2, "issueBeginTime");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeIsNull() {
            addCriterion("issue_end_time is null");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeIsNotNull() {
            addCriterion("issue_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeEqualTo(LocalDateTime value) {
            addCriterion("issue_end_time =", value, "issueEndTime");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeNotEqualTo(LocalDateTime value) {
            addCriterion("issue_end_time <>", value, "issueEndTime");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeGreaterThan(LocalDateTime value) {
            addCriterion("issue_end_time >", value, "issueEndTime");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("issue_end_time >=", value, "issueEndTime");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeLessThan(LocalDateTime value) {
            addCriterion("issue_end_time <", value, "issueEndTime");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("issue_end_time <=", value, "issueEndTime");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeIn(List<LocalDateTime> values) {
            addCriterion("issue_end_time in", values, "issueEndTime");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeNotIn(List<LocalDateTime> values) {
            addCriterion("issue_end_time not in", values, "issueEndTime");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("issue_end_time between", value1, value2, "issueEndTime");
            return (Criteria) this;
        }

        public Criteria andIssueEndTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("issue_end_time not between", value1, value2, "issueEndTime");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeIsNull() {
            addCriterion("use_begin_time is null");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeIsNotNull() {
            addCriterion("use_begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeEqualTo(LocalDateTime value) {
            addCriterion("use_begin_time =", value, "useBeginTime");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeNotEqualTo(LocalDateTime value) {
            addCriterion("use_begin_time <>", value, "useBeginTime");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeGreaterThan(LocalDateTime value) {
            addCriterion("use_begin_time >", value, "useBeginTime");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("use_begin_time >=", value, "useBeginTime");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeLessThan(LocalDateTime value) {
            addCriterion("use_begin_time <", value, "useBeginTime");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("use_begin_time <=", value, "useBeginTime");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeIn(List<LocalDateTime> values) {
            addCriterion("use_begin_time in", values, "useBeginTime");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeNotIn(List<LocalDateTime> values) {
            addCriterion("use_begin_time not in", values, "useBeginTime");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("use_begin_time between", value1, value2, "useBeginTime");
            return (Criteria) this;
        }

        public Criteria andUseBeginTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("use_begin_time not between", value1, value2, "useBeginTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeIsNull() {
            addCriterion("use_end_time is null");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeIsNotNull() {
            addCriterion("use_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeEqualTo(LocalDateTime value) {
            addCriterion("use_end_time =", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeNotEqualTo(LocalDateTime value) {
            addCriterion("use_end_time <>", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeGreaterThan(LocalDateTime value) {
            addCriterion("use_end_time >", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("use_end_time >=", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeLessThan(LocalDateTime value) {
            addCriterion("use_end_time <", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("use_end_time <=", value, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeIn(List<LocalDateTime> values) {
            addCriterion("use_end_time in", values, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeNotIn(List<LocalDateTime> values) {
            addCriterion("use_end_time not in", values, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("use_end_time between", value1, value2, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseEndTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("use_end_time not between", value1, value2, "useEndTime");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeIsNull() {
            addCriterion("use_time_type_code is null");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeIsNotNull() {
            addCriterion("use_time_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeEqualTo(String value) {
            addCriterion("use_time_type_code =", value, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeNotEqualTo(String value) {
            addCriterion("use_time_type_code <>", value, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeGreaterThan(String value) {
            addCriterion("use_time_type_code >", value, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("use_time_type_code >=", value, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeLessThan(String value) {
            addCriterion("use_time_type_code <", value, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("use_time_type_code <=", value, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeLike(String value) {
            addCriterion("use_time_type_code like", value, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeNotLike(String value) {
            addCriterion("use_time_type_code not like", value, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeIn(List<String> values) {
            addCriterion("use_time_type_code in", values, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeNotIn(List<String> values) {
            addCriterion("use_time_type_code not in", values, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeBetween(String value1, String value2) {
            addCriterion("use_time_type_code between", value1, value2, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeCodeNotBetween(String value1, String value2) {
            addCriterion("use_time_type_code not between", value1, value2, "useTimeTypeCode");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameIsNull() {
            addCriterion("use_time_type_name is null");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameIsNotNull() {
            addCriterion("use_time_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameEqualTo(String value) {
            addCriterion("use_time_type_name =", value, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameNotEqualTo(String value) {
            addCriterion("use_time_type_name <>", value, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameGreaterThan(String value) {
            addCriterion("use_time_type_name >", value, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("use_time_type_name >=", value, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameLessThan(String value) {
            addCriterion("use_time_type_name <", value, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameLessThanOrEqualTo(String value) {
            addCriterion("use_time_type_name <=", value, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameLike(String value) {
            addCriterion("use_time_type_name like", value, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameNotLike(String value) {
            addCriterion("use_time_type_name not like", value, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameIn(List<String> values) {
            addCriterion("use_time_type_name in", values, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameNotIn(List<String> values) {
            addCriterion("use_time_type_name not in", values, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameBetween(String value1, String value2) {
            addCriterion("use_time_type_name between", value1, value2, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andUseTimeTypeNameNotBetween(String value1, String value2) {
            addCriterion("use_time_type_name not between", value1, value2, "useTimeTypeName");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitIsNull() {
            addCriterion("time_period_unit is null");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitIsNotNull() {
            addCriterion("time_period_unit is not null");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitEqualTo(String value) {
            addCriterion("time_period_unit =", value, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitNotEqualTo(String value) {
            addCriterion("time_period_unit <>", value, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitGreaterThan(String value) {
            addCriterion("time_period_unit >", value, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitGreaterThanOrEqualTo(String value) {
            addCriterion("time_period_unit >=", value, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitLessThan(String value) {
            addCriterion("time_period_unit <", value, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitLessThanOrEqualTo(String value) {
            addCriterion("time_period_unit <=", value, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitLike(String value) {
            addCriterion("time_period_unit like", value, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitNotLike(String value) {
            addCriterion("time_period_unit not like", value, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitIn(List<String> values) {
            addCriterion("time_period_unit in", values, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitNotIn(List<String> values) {
            addCriterion("time_period_unit not in", values, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitBetween(String value1, String value2) {
            addCriterion("time_period_unit between", value1, value2, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodUnitNotBetween(String value1, String value2) {
            addCriterion("time_period_unit not between", value1, value2, "timePeriodUnit");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthIsNull() {
            addCriterion("time_period_length is null");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthIsNotNull() {
            addCriterion("time_period_length is not null");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthEqualTo(Integer value) {
            addCriterion("time_period_length =", value, "timePeriodLength");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthNotEqualTo(Integer value) {
            addCriterion("time_period_length <>", value, "timePeriodLength");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthGreaterThan(Integer value) {
            addCriterion("time_period_length >", value, "timePeriodLength");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("time_period_length >=", value, "timePeriodLength");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthLessThan(Integer value) {
            addCriterion("time_period_length <", value, "timePeriodLength");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthLessThanOrEqualTo(Integer value) {
            addCriterion("time_period_length <=", value, "timePeriodLength");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthIn(List<Integer> values) {
            addCriterion("time_period_length in", values, "timePeriodLength");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthNotIn(List<Integer> values) {
            addCriterion("time_period_length not in", values, "timePeriodLength");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthBetween(Integer value1, Integer value2) {
            addCriterion("time_period_length between", value1, value2, "timePeriodLength");
            return (Criteria) this;
        }

        public Criteria andTimePeriodLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("time_period_length not between", value1, value2, "timePeriodLength");
            return (Criteria) this;
        }

        public Criteria andConditionNumIsNull() {
            addCriterion("condition_num is null");
            return (Criteria) this;
        }

        public Criteria andConditionNumIsNotNull() {
            addCriterion("condition_num is not null");
            return (Criteria) this;
        }

        public Criteria andConditionNumEqualTo(Double value) {
            addCriterion("condition_num =", value, "conditionNum");
            return (Criteria) this;
        }

        public Criteria andConditionNumNotEqualTo(Double value) {
            addCriterion("condition_num <>", value, "conditionNum");
            return (Criteria) this;
        }

        public Criteria andConditionNumGreaterThan(Double value) {
            addCriterion("condition_num >", value, "conditionNum");
            return (Criteria) this;
        }

        public Criteria andConditionNumGreaterThanOrEqualTo(Double value) {
            addCriterion("condition_num >=", value, "conditionNum");
            return (Criteria) this;
        }

        public Criteria andConditionNumLessThan(Double value) {
            addCriterion("condition_num <", value, "conditionNum");
            return (Criteria) this;
        }

        public Criteria andConditionNumLessThanOrEqualTo(Double value) {
            addCriterion("condition_num <=", value, "conditionNum");
            return (Criteria) this;
        }

        public Criteria andConditionNumIn(List<Double> values) {
            addCriterion("condition_num in", values, "conditionNum");
            return (Criteria) this;
        }

        public Criteria andConditionNumNotIn(List<Double> values) {
            addCriterion("condition_num not in", values, "conditionNum");
            return (Criteria) this;
        }

        public Criteria andConditionNumBetween(Double value1, Double value2) {
            addCriterion("condition_num between", value1, value2, "conditionNum");
            return (Criteria) this;
        }

        public Criteria andConditionNumNotBetween(Double value1, Double value2) {
            addCriterion("condition_num not between", value1, value2, "conditionNum");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeIsNull() {
            addCriterion("condition_type_code is null");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeIsNotNull() {
            addCriterion("condition_type_code is not null");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeEqualTo(String value) {
            addCriterion("condition_type_code =", value, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeNotEqualTo(String value) {
            addCriterion("condition_type_code <>", value, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeGreaterThan(String value) {
            addCriterion("condition_type_code >", value, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("condition_type_code >=", value, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeLessThan(String value) {
            addCriterion("condition_type_code <", value, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeLessThanOrEqualTo(String value) {
            addCriterion("condition_type_code <=", value, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeLike(String value) {
            addCriterion("condition_type_code like", value, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeNotLike(String value) {
            addCriterion("condition_type_code not like", value, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeIn(List<String> values) {
            addCriterion("condition_type_code in", values, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeNotIn(List<String> values) {
            addCriterion("condition_type_code not in", values, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeBetween(String value1, String value2) {
            addCriterion("condition_type_code between", value1, value2, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeCodeNotBetween(String value1, String value2) {
            addCriterion("condition_type_code not between", value1, value2, "conditionTypeCode");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameIsNull() {
            addCriterion("condition_type_name is null");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameIsNotNull() {
            addCriterion("condition_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameEqualTo(String value) {
            addCriterion("condition_type_name =", value, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameNotEqualTo(String value) {
            addCriterion("condition_type_name <>", value, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameGreaterThan(String value) {
            addCriterion("condition_type_name >", value, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("condition_type_name >=", value, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameLessThan(String value) {
            addCriterion("condition_type_name <", value, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameLessThanOrEqualTo(String value) {
            addCriterion("condition_type_name <=", value, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameLike(String value) {
            addCriterion("condition_type_name like", value, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameNotLike(String value) {
            addCriterion("condition_type_name not like", value, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameIn(List<String> values) {
            addCriterion("condition_type_name in", values, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameNotIn(List<String> values) {
            addCriterion("condition_type_name not in", values, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameBetween(String value1, String value2) {
            addCriterion("condition_type_name between", value1, value2, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andConditionTypeNameNotBetween(String value1, String value2) {
            addCriterion("condition_type_name not between", value1, value2, "conditionTypeName");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountIsNull() {
            addCriterion("discounts_amount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountIsNotNull() {
            addCriterion("discounts_amount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountEqualTo(Double value) {
            addCriterion("discounts_amount =", value, "discountsAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountNotEqualTo(Double value) {
            addCriterion("discounts_amount <>", value, "discountsAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountGreaterThan(Double value) {
            addCriterion("discounts_amount >", value, "discountsAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("discounts_amount >=", value, "discountsAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountLessThan(Double value) {
            addCriterion("discounts_amount <", value, "discountsAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountLessThanOrEqualTo(Double value) {
            addCriterion("discounts_amount <=", value, "discountsAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountIn(List<Double> values) {
            addCriterion("discounts_amount in", values, "discountsAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountNotIn(List<Double> values) {
            addCriterion("discounts_amount not in", values, "discountsAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountBetween(Double value1, Double value2) {
            addCriterion("discounts_amount between", value1, value2, "discountsAmount");
            return (Criteria) this;
        }

        public Criteria andDiscountsAmountNotBetween(Double value1, Double value2) {
            addCriterion("discounts_amount not between", value1, value2, "discountsAmount");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagIsNull() {
            addCriterion("all_store_tag is null");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagIsNotNull() {
            addCriterion("all_store_tag is not null");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagEqualTo(String value) {
            addCriterion("all_store_tag =", value, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagNotEqualTo(String value) {
            addCriterion("all_store_tag <>", value, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagGreaterThan(String value) {
            addCriterion("all_store_tag >", value, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagGreaterThanOrEqualTo(String value) {
            addCriterion("all_store_tag >=", value, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagLessThan(String value) {
            addCriterion("all_store_tag <", value, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagLessThanOrEqualTo(String value) {
            addCriterion("all_store_tag <=", value, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagLike(String value) {
            addCriterion("all_store_tag like", value, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagNotLike(String value) {
            addCriterion("all_store_tag not like", value, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagIn(List<String> values) {
            addCriterion("all_store_tag in", values, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagNotIn(List<String> values) {
            addCriterion("all_store_tag not in", values, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagBetween(String value1, String value2) {
            addCriterion("all_store_tag between", value1, value2, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllStoreTagNotBetween(String value1, String value2) {
            addCriterion("all_store_tag not between", value1, value2, "allStoreTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagIsNull() {
            addCriterion("all_goods_tag is null");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagIsNotNull() {
            addCriterion("all_goods_tag is not null");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagEqualTo(String value) {
            addCriterion("all_goods_tag =", value, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagNotEqualTo(String value) {
            addCriterion("all_goods_tag <>", value, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagGreaterThan(String value) {
            addCriterion("all_goods_tag >", value, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagGreaterThanOrEqualTo(String value) {
            addCriterion("all_goods_tag >=", value, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagLessThan(String value) {
            addCriterion("all_goods_tag <", value, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagLessThanOrEqualTo(String value) {
            addCriterion("all_goods_tag <=", value, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagLike(String value) {
            addCriterion("all_goods_tag like", value, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagNotLike(String value) {
            addCriterion("all_goods_tag not like", value, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagIn(List<String> values) {
            addCriterion("all_goods_tag in", values, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagNotIn(List<String> values) {
            addCriterion("all_goods_tag not in", values, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagBetween(String value1, String value2) {
            addCriterion("all_goods_tag between", value1, value2, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andAllGoodsTagNotBetween(String value1, String value2) {
            addCriterion("all_goods_tag not between", value1, value2, "allGoodsTag");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitIsNull() {
            addCriterion("issue_num_limit is null");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitIsNotNull() {
            addCriterion("issue_num_limit is not null");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitEqualTo(Integer value) {
            addCriterion("issue_num_limit =", value, "issueNumLimit");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitNotEqualTo(Integer value) {
            addCriterion("issue_num_limit <>", value, "issueNumLimit");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitGreaterThan(Integer value) {
            addCriterion("issue_num_limit >", value, "issueNumLimit");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("issue_num_limit >=", value, "issueNumLimit");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitLessThan(Integer value) {
            addCriterion("issue_num_limit <", value, "issueNumLimit");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitLessThanOrEqualTo(Integer value) {
            addCriterion("issue_num_limit <=", value, "issueNumLimit");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitIn(List<Integer> values) {
            addCriterion("issue_num_limit in", values, "issueNumLimit");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitNotIn(List<Integer> values) {
            addCriterion("issue_num_limit not in", values, "issueNumLimit");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitBetween(Integer value1, Integer value2) {
            addCriterion("issue_num_limit between", value1, value2, "issueNumLimit");
            return (Criteria) this;
        }

        public Criteria andIssueNumLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("issue_num_limit not between", value1, value2, "issueNumLimit");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumIsNull() {
            addCriterion("max_issue_num is null");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumIsNotNull() {
            addCriterion("max_issue_num is not null");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumEqualTo(Integer value) {
            addCriterion("max_issue_num =", value, "maxIssueNum");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumNotEqualTo(Integer value) {
            addCriterion("max_issue_num <>", value, "maxIssueNum");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumGreaterThan(Integer value) {
            addCriterion("max_issue_num >", value, "maxIssueNum");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_issue_num >=", value, "maxIssueNum");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumLessThan(Integer value) {
            addCriterion("max_issue_num <", value, "maxIssueNum");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumLessThanOrEqualTo(Integer value) {
            addCriterion("max_issue_num <=", value, "maxIssueNum");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumIn(List<Integer> values) {
            addCriterion("max_issue_num in", values, "maxIssueNum");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumNotIn(List<Integer> values) {
            addCriterion("max_issue_num not in", values, "maxIssueNum");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumBetween(Integer value1, Integer value2) {
            addCriterion("max_issue_num between", value1, value2, "maxIssueNum");
            return (Criteria) this;
        }

        public Criteria andMaxIssueNumNotBetween(Integer value1, Integer value2) {
            addCriterion("max_issue_num not between", value1, value2, "maxIssueNum");
            return (Criteria) this;
        }

        public Criteria andShowTagIsNull() {
            addCriterion("show_tag is null");
            return (Criteria) this;
        }

        public Criteria andShowTagIsNotNull() {
            addCriterion("show_tag is not null");
            return (Criteria) this;
        }

        public Criteria andShowTagEqualTo(String value) {
            addCriterion("show_tag =", value, "showTag");
            return (Criteria) this;
        }

        public Criteria andShowTagNotEqualTo(String value) {
            addCriterion("show_tag <>", value, "showTag");
            return (Criteria) this;
        }

        public Criteria andShowTagGreaterThan(String value) {
            addCriterion("show_tag >", value, "showTag");
            return (Criteria) this;
        }

        public Criteria andShowTagGreaterThanOrEqualTo(String value) {
            addCriterion("show_tag >=", value, "showTag");
            return (Criteria) this;
        }

        public Criteria andShowTagLessThan(String value) {
            addCriterion("show_tag <", value, "showTag");
            return (Criteria) this;
        }

        public Criteria andShowTagLessThanOrEqualTo(String value) {
            addCriterion("show_tag <=", value, "showTag");
            return (Criteria) this;
        }

        public Criteria andShowTagLike(String value) {
            addCriterion("show_tag like", value, "showTag");
            return (Criteria) this;
        }

        public Criteria andShowTagNotLike(String value) {
            addCriterion("show_tag not like", value, "showTag");
            return (Criteria) this;
        }

        public Criteria andShowTagIn(List<String> values) {
            addCriterion("show_tag in", values, "showTag");
            return (Criteria) this;
        }

        public Criteria andShowTagNotIn(List<String> values) {
            addCriterion("show_tag not in", values, "showTag");
            return (Criteria) this;
        }

        public Criteria andShowTagBetween(String value1, String value2) {
            addCriterion("show_tag between", value1, value2, "showTag");
            return (Criteria) this;
        }

        public Criteria andShowTagNotBetween(String value1, String value2) {
            addCriterion("show_tag not between", value1, value2, "showTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagIsNull() {
            addCriterion("delete_tag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteTagIsNotNull() {
            addCriterion("delete_tag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteTagEqualTo(String value) {
            addCriterion("delete_tag =", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagNotEqualTo(String value) {
            addCriterion("delete_tag <>", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagGreaterThan(String value) {
            addCriterion("delete_tag >", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagGreaterThanOrEqualTo(String value) {
            addCriterion("delete_tag >=", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagLessThan(String value) {
            addCriterion("delete_tag <", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagLessThanOrEqualTo(String value) {
            addCriterion("delete_tag <=", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagLike(String value) {
            addCriterion("delete_tag like", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagNotLike(String value) {
            addCriterion("delete_tag not like", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagIn(List<String> values) {
            addCriterion("delete_tag in", values, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagNotIn(List<String> values) {
            addCriterion("delete_tag not in", values, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagBetween(String value1, String value2) {
            addCriterion("delete_tag between", value1, value2, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagNotBetween(String value1, String value2) {
            addCriterion("delete_tag not between", value1, value2, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeIsNull() {
            addCriterion("coupon_pool_code is null");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeIsNotNull() {
            addCriterion("coupon_pool_code is not null");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeEqualTo(String value) {
            addCriterion("coupon_pool_code =", value, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeNotEqualTo(String value) {
            addCriterion("coupon_pool_code <>", value, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeGreaterThan(String value) {
            addCriterion("coupon_pool_code >", value, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_pool_code >=", value, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeLessThan(String value) {
            addCriterion("coupon_pool_code <", value, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeLessThanOrEqualTo(String value) {
            addCriterion("coupon_pool_code <=", value, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeLike(String value) {
            addCriterion("coupon_pool_code like", value, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeNotLike(String value) {
            addCriterion("coupon_pool_code not like", value, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeIn(List<String> values) {
            addCriterion("coupon_pool_code in", values, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeNotIn(List<String> values) {
            addCriterion("coupon_pool_code not in", values, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeBetween(String value1, String value2) {
            addCriterion("coupon_pool_code between", value1, value2, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolCodeNotBetween(String value1, String value2) {
            addCriterion("coupon_pool_code not between", value1, value2, "couponPoolCode");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameIsNull() {
            addCriterion("coupon_pool_name is null");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameIsNotNull() {
            addCriterion("coupon_pool_name is not null");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameEqualTo(String value) {
            addCriterion("coupon_pool_name =", value, "couponPoolName");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameNotEqualTo(String value) {
            addCriterion("coupon_pool_name <>", value, "couponPoolName");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameGreaterThan(String value) {
            addCriterion("coupon_pool_name >", value, "couponPoolName");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_pool_name >=", value, "couponPoolName");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameLessThan(String value) {
            addCriterion("coupon_pool_name <", value, "couponPoolName");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameLessThanOrEqualTo(String value) {
            addCriterion("coupon_pool_name <=", value, "couponPoolName");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameLike(String value) {
            addCriterion("coupon_pool_name like", value, "couponPoolName");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameNotLike(String value) {
            addCriterion("coupon_pool_name not like", value, "couponPoolName");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameIn(List<String> values) {
            addCriterion("coupon_pool_name in", values, "couponPoolName");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameNotIn(List<String> values) {
            addCriterion("coupon_pool_name not in", values, "couponPoolName");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameBetween(String value1, String value2) {
            addCriterion("coupon_pool_name between", value1, value2, "couponPoolName");
            return (Criteria) this;
        }

        public Criteria andCouponPoolNameNotBetween(String value1, String value2) {
            addCriterion("coupon_pool_name not between", value1, value2, "couponPoolName");
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