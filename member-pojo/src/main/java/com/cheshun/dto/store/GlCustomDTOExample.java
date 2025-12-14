package com.cheshun.dto.store;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GlCustomDTOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GlCustomDTOExample() {
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

        public Criteria andCMdfqIsNull() {
            addCriterion("C_MDFQ is null");
            return (Criteria) this;
        }

        public Criteria andCMdfqIsNotNull() {
            addCriterion("C_MDFQ is not null");
            return (Criteria) this;
        }

        public Criteria andCMdfqEqualTo(String value) {
            addCriterion("C_MDFQ =", value, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCMdfqNotEqualTo(String value) {
            addCriterion("C_MDFQ <>", value, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCMdfqGreaterThan(String value) {
            addCriterion("C_MDFQ >", value, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCMdfqGreaterThanOrEqualTo(String value) {
            addCriterion("C_MDFQ >=", value, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCMdfqLessThan(String value) {
            addCriterion("C_MDFQ <", value, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCMdfqLessThanOrEqualTo(String value) {
            addCriterion("C_MDFQ <=", value, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCMdfqLike(String value) {
            addCriterion("C_MDFQ like", value, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCMdfqNotLike(String value) {
            addCriterion("C_MDFQ not like", value, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCMdfqIn(List<String> values) {
            addCriterion("C_MDFQ in", values, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCMdfqNotIn(List<String> values) {
            addCriterion("C_MDFQ not in", values, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCMdfqBetween(String value1, String value2) {
            addCriterion("C_MDFQ between", value1, value2, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCMdfqNotBetween(String value1, String value2) {
            addCriterion("C_MDFQ not between", value1, value2, "cMdfq");
            return (Criteria) this;
        }

        public Criteria andCDqIsNull() {
            addCriterion("C_DQ is null");
            return (Criteria) this;
        }

        public Criteria andCDqIsNotNull() {
            addCriterion("C_DQ is not null");
            return (Criteria) this;
        }

        public Criteria andCDqEqualTo(String value) {
            addCriterion("C_DQ =", value, "cDq");
            return (Criteria) this;
        }

        public Criteria andCDqNotEqualTo(String value) {
            addCriterion("C_DQ <>", value, "cDq");
            return (Criteria) this;
        }

        public Criteria andCDqGreaterThan(String value) {
            addCriterion("C_DQ >", value, "cDq");
            return (Criteria) this;
        }

        public Criteria andCDqGreaterThanOrEqualTo(String value) {
            addCriterion("C_DQ >=", value, "cDq");
            return (Criteria) this;
        }

        public Criteria andCDqLessThan(String value) {
            addCriterion("C_DQ <", value, "cDq");
            return (Criteria) this;
        }

        public Criteria andCDqLessThanOrEqualTo(String value) {
            addCriterion("C_DQ <=", value, "cDq");
            return (Criteria) this;
        }

        public Criteria andCDqLike(String value) {
            addCriterion("C_DQ like", value, "cDq");
            return (Criteria) this;
        }

        public Criteria andCDqNotLike(String value) {
            addCriterion("C_DQ not like", value, "cDq");
            return (Criteria) this;
        }

        public Criteria andCDqIn(List<String> values) {
            addCriterion("C_DQ in", values, "cDq");
            return (Criteria) this;
        }

        public Criteria andCDqNotIn(List<String> values) {
            addCriterion("C_DQ not in", values, "cDq");
            return (Criteria) this;
        }

        public Criteria andCDqBetween(String value1, String value2) {
            addCriterion("C_DQ between", value1, value2, "cDq");
            return (Criteria) this;
        }

        public Criteria andCDqNotBetween(String value1, String value2) {
            addCriterion("C_DQ not between", value1, value2, "cDq");
            return (Criteria) this;
        }

        public Criteria andDsIsNull() {
            addCriterion("DS is null");
            return (Criteria) this;
        }

        public Criteria andDsIsNotNull() {
            addCriterion("DS is not null");
            return (Criteria) this;
        }

        public Criteria andDsEqualTo(String value) {
            addCriterion("DS =", value, "ds");
            return (Criteria) this;
        }

        public Criteria andDsNotEqualTo(String value) {
            addCriterion("DS <>", value, "ds");
            return (Criteria) this;
        }

        public Criteria andDsGreaterThan(String value) {
            addCriterion("DS >", value, "ds");
            return (Criteria) this;
        }

        public Criteria andDsGreaterThanOrEqualTo(String value) {
            addCriterion("DS >=", value, "ds");
            return (Criteria) this;
        }

        public Criteria andDsLessThan(String value) {
            addCriterion("DS <", value, "ds");
            return (Criteria) this;
        }

        public Criteria andDsLessThanOrEqualTo(String value) {
            addCriterion("DS <=", value, "ds");
            return (Criteria) this;
        }

        public Criteria andDsLike(String value) {
            addCriterion("DS like", value, "ds");
            return (Criteria) this;
        }

        public Criteria andDsNotLike(String value) {
            addCriterion("DS not like", value, "ds");
            return (Criteria) this;
        }

        public Criteria andDsIn(List<String> values) {
            addCriterion("DS in", values, "ds");
            return (Criteria) this;
        }

        public Criteria andDsNotIn(List<String> values) {
            addCriterion("DS not in", values, "ds");
            return (Criteria) this;
        }

        public Criteria andDsBetween(String value1, String value2) {
            addCriterion("DS between", value1, value2, "ds");
            return (Criteria) this;
        }

        public Criteria andDsNotBetween(String value1, String value2) {
            addCriterion("DS not between", value1, value2, "ds");
            return (Criteria) this;
        }

        public Criteria andCMdfq1IsNull() {
            addCriterion("C_MDFQ1 is null");
            return (Criteria) this;
        }

        public Criteria andCMdfq1IsNotNull() {
            addCriterion("C_MDFQ1 is not null");
            return (Criteria) this;
        }

        public Criteria andCMdfq1EqualTo(String value) {
            addCriterion("C_MDFQ1 =", value, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andCMdfq1NotEqualTo(String value) {
            addCriterion("C_MDFQ1 <>", value, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andCMdfq1GreaterThan(String value) {
            addCriterion("C_MDFQ1 >", value, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andCMdfq1GreaterThanOrEqualTo(String value) {
            addCriterion("C_MDFQ1 >=", value, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andCMdfq1LessThan(String value) {
            addCriterion("C_MDFQ1 <", value, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andCMdfq1LessThanOrEqualTo(String value) {
            addCriterion("C_MDFQ1 <=", value, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andCMdfq1Like(String value) {
            addCriterion("C_MDFQ1 like", value, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andCMdfq1NotLike(String value) {
            addCriterion("C_MDFQ1 not like", value, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andCMdfq1In(List<String> values) {
            addCriterion("C_MDFQ1 in", values, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andCMdfq1NotIn(List<String> values) {
            addCriterion("C_MDFQ1 not in", values, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andCMdfq1Between(String value1, String value2) {
            addCriterion("C_MDFQ1 between", value1, value2, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andCMdfq1NotBetween(String value1, String value2) {
            addCriterion("C_MDFQ1 not between", value1, value2, "cMdfq1");
            return (Criteria) this;
        }

        public Criteria andTjbhIsNull() {
            addCriterion("TJBH is null");
            return (Criteria) this;
        }

        public Criteria andTjbhIsNotNull() {
            addCriterion("TJBH is not null");
            return (Criteria) this;
        }

        public Criteria andTjbhEqualTo(String value) {
            addCriterion("TJBH =", value, "tjbh");
            return (Criteria) this;
        }

        public Criteria andTjbhNotEqualTo(String value) {
            addCriterion("TJBH <>", value, "tjbh");
            return (Criteria) this;
        }

        public Criteria andTjbhGreaterThan(String value) {
            addCriterion("TJBH >", value, "tjbh");
            return (Criteria) this;
        }

        public Criteria andTjbhGreaterThanOrEqualTo(String value) {
            addCriterion("TJBH >=", value, "tjbh");
            return (Criteria) this;
        }

        public Criteria andTjbhLessThan(String value) {
            addCriterion("TJBH <", value, "tjbh");
            return (Criteria) this;
        }

        public Criteria andTjbhLessThanOrEqualTo(String value) {
            addCriterion("TJBH <=", value, "tjbh");
            return (Criteria) this;
        }

        public Criteria andTjbhLike(String value) {
            addCriterion("TJBH like", value, "tjbh");
            return (Criteria) this;
        }

        public Criteria andTjbhNotLike(String value) {
            addCriterion("TJBH not like", value, "tjbh");
            return (Criteria) this;
        }

        public Criteria andTjbhIn(List<String> values) {
            addCriterion("TJBH in", values, "tjbh");
            return (Criteria) this;
        }

        public Criteria andTjbhNotIn(List<String> values) {
            addCriterion("TJBH not in", values, "tjbh");
            return (Criteria) this;
        }

        public Criteria andTjbhBetween(String value1, String value2) {
            addCriterion("TJBH between", value1, value2, "tjbh");
            return (Criteria) this;
        }

        public Criteria andTjbhNotBetween(String value1, String value2) {
            addCriterion("TJBH not between", value1, value2, "tjbh");
            return (Criteria) this;
        }

        public Criteria andMcIsNull() {
            addCriterion("MC is null");
            return (Criteria) this;
        }

        public Criteria andMcIsNotNull() {
            addCriterion("MC is not null");
            return (Criteria) this;
        }

        public Criteria andMcEqualTo(String value) {
            addCriterion("MC =", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotEqualTo(String value) {
            addCriterion("MC <>", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcGreaterThan(String value) {
            addCriterion("MC >", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcGreaterThanOrEqualTo(String value) {
            addCriterion("MC >=", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcLessThan(String value) {
            addCriterion("MC <", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcLessThanOrEqualTo(String value) {
            addCriterion("MC <=", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcLike(String value) {
            addCriterion("MC like", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotLike(String value) {
            addCriterion("MC not like", value, "mc");
            return (Criteria) this;
        }

        public Criteria andMcIn(List<String> values) {
            addCriterion("MC in", values, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotIn(List<String> values) {
            addCriterion("MC not in", values, "mc");
            return (Criteria) this;
        }

        public Criteria andMcBetween(String value1, String value2) {
            addCriterion("MC between", value1, value2, "mc");
            return (Criteria) this;
        }

        public Criteria andMcNotBetween(String value1, String value2) {
            addCriterion("MC not between", value1, value2, "mc");
            return (Criteria) this;
        }

        public Criteria andCKykdrqIsNull() {
            addCriterion("C_KYKDRQ is null");
            return (Criteria) this;
        }

        public Criteria andCKykdrqIsNotNull() {
            addCriterion("C_KYKDRQ is not null");
            return (Criteria) this;
        }

        public Criteria andCKykdrqEqualTo(LocalDateTime value) {
            addCriterion("C_KYKDRQ =", value, "cKykdrq");
            return (Criteria) this;
        }

        public Criteria andCKykdrqNotEqualTo(LocalDateTime value) {
            addCriterion("C_KYKDRQ <>", value, "cKykdrq");
            return (Criteria) this;
        }

        public Criteria andCKykdrqGreaterThan(LocalDateTime value) {
            addCriterion("C_KYKDRQ >", value, "cKykdrq");
            return (Criteria) this;
        }

        public Criteria andCKykdrqGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("C_KYKDRQ >=", value, "cKykdrq");
            return (Criteria) this;
        }

        public Criteria andCKykdrqLessThan(LocalDateTime value) {
            addCriterion("C_KYKDRQ <", value, "cKykdrq");
            return (Criteria) this;
        }

        public Criteria andCKykdrqLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("C_KYKDRQ <=", value, "cKykdrq");
            return (Criteria) this;
        }

        public Criteria andCKykdrqIn(List<LocalDateTime> values) {
            addCriterion("C_KYKDRQ in", values, "cKykdrq");
            return (Criteria) this;
        }

        public Criteria andCKykdrqNotIn(List<LocalDateTime> values) {
            addCriterion("C_KYKDRQ not in", values, "cKykdrq");
            return (Criteria) this;
        }

        public Criteria andCKykdrqBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("C_KYKDRQ between", value1, value2, "cKykdrq");
            return (Criteria) this;
        }

        public Criteria andCKykdrqNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("C_KYKDRQ not between", value1, value2, "cKykdrq");
            return (Criteria) this;
        }

        public Criteria andCMdflIsNull() {
            addCriterion("C_MDFL is null");
            return (Criteria) this;
        }

        public Criteria andCMdflIsNotNull() {
            addCriterion("C_MDFL is not null");
            return (Criteria) this;
        }

        public Criteria andCMdflEqualTo(String value) {
            addCriterion("C_MDFL =", value, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andCMdflNotEqualTo(String value) {
            addCriterion("C_MDFL <>", value, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andCMdflGreaterThan(String value) {
            addCriterion("C_MDFL >", value, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andCMdflGreaterThanOrEqualTo(String value) {
            addCriterion("C_MDFL >=", value, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andCMdflLessThan(String value) {
            addCriterion("C_MDFL <", value, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andCMdflLessThanOrEqualTo(String value) {
            addCriterion("C_MDFL <=", value, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andCMdflLike(String value) {
            addCriterion("C_MDFL like", value, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andCMdflNotLike(String value) {
            addCriterion("C_MDFL not like", value, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andCMdflIn(List<String> values) {
            addCriterion("C_MDFL in", values, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andCMdflNotIn(List<String> values) {
            addCriterion("C_MDFL not in", values, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andCMdflBetween(String value1, String value2) {
            addCriterion("C_MDFL between", value1, value2, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andCMdflNotBetween(String value1, String value2) {
            addCriterion("C_MDFL not between", value1, value2, "cMdfl");
            return (Criteria) this;
        }

        public Criteria andTjTagIsNull() {
            addCriterion("TJ_TAG is null");
            return (Criteria) this;
        }

        public Criteria andTjTagIsNotNull() {
            addCriterion("TJ_TAG is not null");
            return (Criteria) this;
        }

        public Criteria andTjTagEqualTo(String value) {
            addCriterion("TJ_TAG =", value, "tjTag");
            return (Criteria) this;
        }

        public Criteria andTjTagNotEqualTo(String value) {
            addCriterion("TJ_TAG <>", value, "tjTag");
            return (Criteria) this;
        }

        public Criteria andTjTagGreaterThan(String value) {
            addCriterion("TJ_TAG >", value, "tjTag");
            return (Criteria) this;
        }

        public Criteria andTjTagGreaterThanOrEqualTo(String value) {
            addCriterion("TJ_TAG >=", value, "tjTag");
            return (Criteria) this;
        }

        public Criteria andTjTagLessThan(String value) {
            addCriterion("TJ_TAG <", value, "tjTag");
            return (Criteria) this;
        }

        public Criteria andTjTagLessThanOrEqualTo(String value) {
            addCriterion("TJ_TAG <=", value, "tjTag");
            return (Criteria) this;
        }

        public Criteria andTjTagLike(String value) {
            addCriterion("TJ_TAG like", value, "tjTag");
            return (Criteria) this;
        }

        public Criteria andTjTagNotLike(String value) {
            addCriterion("TJ_TAG not like", value, "tjTag");
            return (Criteria) this;
        }

        public Criteria andTjTagIn(List<String> values) {
            addCriterion("TJ_TAG in", values, "tjTag");
            return (Criteria) this;
        }

        public Criteria andTjTagNotIn(List<String> values) {
            addCriterion("TJ_TAG not in", values, "tjTag");
            return (Criteria) this;
        }

        public Criteria andTjTagBetween(String value1, String value2) {
            addCriterion("TJ_TAG between", value1, value2, "tjTag");
            return (Criteria) this;
        }

        public Criteria andTjTagNotBetween(String value1, String value2) {
            addCriterion("TJ_TAG not between", value1, value2, "tjTag");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressIsNull() {
            addCriterion("C_DOWNLOADADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressIsNotNull() {
            addCriterion("C_DOWNLOADADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressEqualTo(String value) {
            addCriterion("C_DOWNLOADADDRESS =", value, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressNotEqualTo(String value) {
            addCriterion("C_DOWNLOADADDRESS <>", value, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressGreaterThan(String value) {
            addCriterion("C_DOWNLOADADDRESS >", value, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressGreaterThanOrEqualTo(String value) {
            addCriterion("C_DOWNLOADADDRESS >=", value, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressLessThan(String value) {
            addCriterion("C_DOWNLOADADDRESS <", value, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressLessThanOrEqualTo(String value) {
            addCriterion("C_DOWNLOADADDRESS <=", value, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressLike(String value) {
            addCriterion("C_DOWNLOADADDRESS like", value, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressNotLike(String value) {
            addCriterion("C_DOWNLOADADDRESS not like", value, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressIn(List<String> values) {
            addCriterion("C_DOWNLOADADDRESS in", values, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressNotIn(List<String> values) {
            addCriterion("C_DOWNLOADADDRESS not in", values, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressBetween(String value1, String value2) {
            addCriterion("C_DOWNLOADADDRESS between", value1, value2, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCDownloadaddressNotBetween(String value1, String value2) {
            addCriterion("C_DOWNLOADADDRESS not between", value1, value2, "cDownloadaddress");
            return (Criteria) this;
        }

        public Criteria andCMdlxIsNull() {
            addCriterion("C_MDLX is null");
            return (Criteria) this;
        }

        public Criteria andCMdlxIsNotNull() {
            addCriterion("C_MDLX is not null");
            return (Criteria) this;
        }

        public Criteria andCMdlxEqualTo(BigDecimal value) {
            addCriterion("C_MDLX =", value, "cMdlx");
            return (Criteria) this;
        }

        public Criteria andCMdlxNotEqualTo(BigDecimal value) {
            addCriterion("C_MDLX <>", value, "cMdlx");
            return (Criteria) this;
        }

        public Criteria andCMdlxGreaterThan(BigDecimal value) {
            addCriterion("C_MDLX >", value, "cMdlx");
            return (Criteria) this;
        }

        public Criteria andCMdlxGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("C_MDLX >=", value, "cMdlx");
            return (Criteria) this;
        }

        public Criteria andCMdlxLessThan(BigDecimal value) {
            addCriterion("C_MDLX <", value, "cMdlx");
            return (Criteria) this;
        }

        public Criteria andCMdlxLessThanOrEqualTo(BigDecimal value) {
            addCriterion("C_MDLX <=", value, "cMdlx");
            return (Criteria) this;
        }

        public Criteria andCMdlxIn(List<BigDecimal> values) {
            addCriterion("C_MDLX in", values, "cMdlx");
            return (Criteria) this;
        }

        public Criteria andCMdlxNotIn(List<BigDecimal> values) {
            addCriterion("C_MDLX not in", values, "cMdlx");
            return (Criteria) this;
        }

        public Criteria andCMdlxBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("C_MDLX between", value1, value2, "cMdlx");
            return (Criteria) this;
        }

        public Criteria andCMdlxNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("C_MDLX not between", value1, value2, "cMdlx");
            return (Criteria) this;
        }

        public Criteria andSpjxflzIsNull() {
            addCriterion("SPJXFLZ is null");
            return (Criteria) this;
        }

        public Criteria andSpjxflzIsNotNull() {
            addCriterion("SPJXFLZ is not null");
            return (Criteria) this;
        }

        public Criteria andSpjxflzEqualTo(String value) {
            addCriterion("SPJXFLZ =", value, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSpjxflzNotEqualTo(String value) {
            addCriterion("SPJXFLZ <>", value, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSpjxflzGreaterThan(String value) {
            addCriterion("SPJXFLZ >", value, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSpjxflzGreaterThanOrEqualTo(String value) {
            addCriterion("SPJXFLZ >=", value, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSpjxflzLessThan(String value) {
            addCriterion("SPJXFLZ <", value, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSpjxflzLessThanOrEqualTo(String value) {
            addCriterion("SPJXFLZ <=", value, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSpjxflzLike(String value) {
            addCriterion("SPJXFLZ like", value, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSpjxflzNotLike(String value) {
            addCriterion("SPJXFLZ not like", value, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSpjxflzIn(List<String> values) {
            addCriterion("SPJXFLZ in", values, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSpjxflzNotIn(List<String> values) {
            addCriterion("SPJXFLZ not in", values, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSpjxflzBetween(String value1, String value2) {
            addCriterion("SPJXFLZ between", value1, value2, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSpjxflzNotBetween(String value1, String value2) {
            addCriterion("SPJXFLZ not between", value1, value2, "spjxflz");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeIsNull() {
            addCriterion("SUBSELLPRICETYPE is null");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeIsNotNull() {
            addCriterion("SUBSELLPRICETYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeEqualTo(String value) {
            addCriterion("SUBSELLPRICETYPE =", value, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeNotEqualTo(String value) {
            addCriterion("SUBSELLPRICETYPE <>", value, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeGreaterThan(String value) {
            addCriterion("SUBSELLPRICETYPE >", value, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBSELLPRICETYPE >=", value, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeLessThan(String value) {
            addCriterion("SUBSELLPRICETYPE <", value, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeLessThanOrEqualTo(String value) {
            addCriterion("SUBSELLPRICETYPE <=", value, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeLike(String value) {
            addCriterion("SUBSELLPRICETYPE like", value, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeNotLike(String value) {
            addCriterion("SUBSELLPRICETYPE not like", value, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeIn(List<String> values) {
            addCriterion("SUBSELLPRICETYPE in", values, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeNotIn(List<String> values) {
            addCriterion("SUBSELLPRICETYPE not in", values, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeBetween(String value1, String value2) {
            addCriterion("SUBSELLPRICETYPE between", value1, value2, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andSubsellpricetypeNotBetween(String value1, String value2) {
            addCriterion("SUBSELLPRICETYPE not between", value1, value2, "subsellpricetype");
            return (Criteria) this;
        }

        public Criteria andCMdspIsNull() {
            addCriterion("C_MDSP is null");
            return (Criteria) this;
        }

        public Criteria andCMdspIsNotNull() {
            addCriterion("C_MDSP is not null");
            return (Criteria) this;
        }

        public Criteria andCMdspEqualTo(String value) {
            addCriterion("C_MDSP =", value, "cMdsp");
            return (Criteria) this;
        }

        public Criteria andCMdspNotEqualTo(String value) {
            addCriterion("C_MDSP <>", value, "cMdsp");
            return (Criteria) this;
        }

        public Criteria andCMdspGreaterThan(String value) {
            addCriterion("C_MDSP >", value, "cMdsp");
            return (Criteria) this;
        }

        public Criteria andCMdspGreaterThanOrEqualTo(String value) {
            addCriterion("C_MDSP >=", value, "cMdsp");
            return (Criteria) this;
        }

        public Criteria andCMdspLessThan(String value) {
            addCriterion("C_MDSP <", value, "cMdsp");
            return (Criteria) this;
        }

        public Criteria andCMdspLessThanOrEqualTo(String value) {
            addCriterion("C_MDSP <=", value, "cMdsp");
            return (Criteria) this;
        }

        public Criteria andCMdspLike(String value) {
            addCriterion("C_MDSP like", value, "cMdsp");
            return (Criteria) this;
        }

        public Criteria andCMdspNotLike(String value) {
            addCriterion("C_MDSP not like", value, "cMdsp");
            return (Criteria) this;
        }

        public Criteria andCMdspIn(List<String> values) {
            addCriterion("C_MDSP in", values, "cMdsp");
            return (Criteria) this;
        }

        public Criteria andCMdspNotIn(List<String> values) {
            addCriterion("C_MDSP not in", values, "cMdsp");
            return (Criteria) this;
        }

        public Criteria andCMdspBetween(String value1, String value2) {
            addCriterion("C_MDSP between", value1, value2, "cMdsp");
            return (Criteria) this;
        }

        public Criteria andCMdspNotBetween(String value1, String value2) {
            addCriterion("C_MDSP not between", value1, value2, "cMdsp");
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