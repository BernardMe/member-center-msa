package com.cheshun.dto.employee;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QwEmployeesDTOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QwEmployeesDTOExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andGuidIsNull() {
            addCriterion("GUID is null");
            return (Criteria) this;
        }

        public Criteria andGuidIsNotNull() {
            addCriterion("GUID is not null");
            return (Criteria) this;
        }

        public Criteria andGuidEqualTo(String value) {
            addCriterion("GUID =", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotEqualTo(String value) {
            addCriterion("GUID <>", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThan(String value) {
            addCriterion("GUID >", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidGreaterThanOrEqualTo(String value) {
            addCriterion("GUID >=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThan(String value) {
            addCriterion("GUID <", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLessThanOrEqualTo(String value) {
            addCriterion("GUID <=", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidLike(String value) {
            addCriterion("GUID like", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotLike(String value) {
            addCriterion("GUID not like", value, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidIn(List<String> values) {
            addCriterion("GUID in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotIn(List<String> values) {
            addCriterion("GUID not in", values, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidBetween(String value1, String value2) {
            addCriterion("GUID between", value1, value2, "guid");
            return (Criteria) this;
        }

        public Criteria andGuidNotBetween(String value1, String value2) {
            addCriterion("GUID not between", value1, value2, "guid");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIsNull() {
            addCriterion("MOBILEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIsNotNull() {
            addCriterion("MOBILEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andMobilephoneEqualTo(String value) {
            addCriterion("MOBILEPHONE =", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotEqualTo(String value) {
            addCriterion("MOBILEPHONE <>", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneGreaterThan(String value) {
            addCriterion("MOBILEPHONE >", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILEPHONE >=", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLessThan(String value) {
            addCriterion("MOBILEPHONE <", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLessThanOrEqualTo(String value) {
            addCriterion("MOBILEPHONE <=", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneLike(String value) {
            addCriterion("MOBILEPHONE like", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotLike(String value) {
            addCriterion("MOBILEPHONE not like", value, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneIn(List<String> values) {
            addCriterion("MOBILEPHONE in", values, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotIn(List<String> values) {
            addCriterion("MOBILEPHONE not in", values, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneBetween(String value1, String value2) {
            addCriterion("MOBILEPHONE between", value1, value2, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andMobilephoneNotBetween(String value1, String value2) {
            addCriterion("MOBILEPHONE not between", value1, value2, "mobilephone");
            return (Criteria) this;
        }

        public Criteria andDepartcodeIsNull() {
            addCriterion("DEPARTCODE is null");
            return (Criteria) this;
        }

        public Criteria andDepartcodeIsNotNull() {
            addCriterion("DEPARTCODE is not null");
            return (Criteria) this;
        }

        public Criteria andDepartcodeEqualTo(String value) {
            addCriterion("DEPARTCODE =", value, "departcode");
            return (Criteria) this;
        }

        public Criteria andDepartcodeNotEqualTo(String value) {
            addCriterion("DEPARTCODE <>", value, "departcode");
            return (Criteria) this;
        }

        public Criteria andDepartcodeGreaterThan(String value) {
            addCriterion("DEPARTCODE >", value, "departcode");
            return (Criteria) this;
        }

        public Criteria andDepartcodeGreaterThanOrEqualTo(String value) {
            addCriterion("DEPARTCODE >=", value, "departcode");
            return (Criteria) this;
        }

        public Criteria andDepartcodeLessThan(String value) {
            addCriterion("DEPARTCODE <", value, "departcode");
            return (Criteria) this;
        }

        public Criteria andDepartcodeLessThanOrEqualTo(String value) {
            addCriterion("DEPARTCODE <=", value, "departcode");
            return (Criteria) this;
        }

        public Criteria andDepartcodeLike(String value) {
            addCriterion("DEPARTCODE like", value, "departcode");
            return (Criteria) this;
        }

        public Criteria andDepartcodeNotLike(String value) {
            addCriterion("DEPARTCODE not like", value, "departcode");
            return (Criteria) this;
        }

        public Criteria andDepartcodeIn(List<String> values) {
            addCriterion("DEPARTCODE in", values, "departcode");
            return (Criteria) this;
        }

        public Criteria andDepartcodeNotIn(List<String> values) {
            addCriterion("DEPARTCODE not in", values, "departcode");
            return (Criteria) this;
        }

        public Criteria andDepartcodeBetween(String value1, String value2) {
            addCriterion("DEPARTCODE between", value1, value2, "departcode");
            return (Criteria) this;
        }

        public Criteria andDepartcodeNotBetween(String value1, String value2) {
            addCriterion("DEPARTCODE not between", value1, value2, "departcode");
            return (Criteria) this;
        }

        public Criteria andDingidIsNull() {
            addCriterion("DINGID is null");
            return (Criteria) this;
        }

        public Criteria andDingidIsNotNull() {
            addCriterion("DINGID is not null");
            return (Criteria) this;
        }

        public Criteria andDingidEqualTo(String value) {
            addCriterion("DINGID =", value, "dingid");
            return (Criteria) this;
        }

        public Criteria andDingidNotEqualTo(String value) {
            addCriterion("DINGID <>", value, "dingid");
            return (Criteria) this;
        }

        public Criteria andDingidGreaterThan(String value) {
            addCriterion("DINGID >", value, "dingid");
            return (Criteria) this;
        }

        public Criteria andDingidGreaterThanOrEqualTo(String value) {
            addCriterion("DINGID >=", value, "dingid");
            return (Criteria) this;
        }

        public Criteria andDingidLessThan(String value) {
            addCriterion("DINGID <", value, "dingid");
            return (Criteria) this;
        }

        public Criteria andDingidLessThanOrEqualTo(String value) {
            addCriterion("DINGID <=", value, "dingid");
            return (Criteria) this;
        }

        public Criteria andDingidLike(String value) {
            addCriterion("DINGID like", value, "dingid");
            return (Criteria) this;
        }

        public Criteria andDingidNotLike(String value) {
            addCriterion("DINGID not like", value, "dingid");
            return (Criteria) this;
        }

        public Criteria andDingidIn(List<String> values) {
            addCriterion("DINGID in", values, "dingid");
            return (Criteria) this;
        }

        public Criteria andDingidNotIn(List<String> values) {
            addCriterion("DINGID not in", values, "dingid");
            return (Criteria) this;
        }

        public Criteria andDingidBetween(String value1, String value2) {
            addCriterion("DINGID between", value1, value2, "dingid");
            return (Criteria) this;
        }

        public Criteria andDingidNotBetween(String value1, String value2) {
            addCriterion("DINGID not between", value1, value2, "dingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidIsNull() {
            addCriterion("DEPARTDINGID is null");
            return (Criteria) this;
        }

        public Criteria andDepartdingidIsNotNull() {
            addCriterion("DEPARTDINGID is not null");
            return (Criteria) this;
        }

        public Criteria andDepartdingidEqualTo(String value) {
            addCriterion("DEPARTDINGID =", value, "departdingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidNotEqualTo(String value) {
            addCriterion("DEPARTDINGID <>", value, "departdingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidGreaterThan(String value) {
            addCriterion("DEPARTDINGID >", value, "departdingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidGreaterThanOrEqualTo(String value) {
            addCriterion("DEPARTDINGID >=", value, "departdingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidLessThan(String value) {
            addCriterion("DEPARTDINGID <", value, "departdingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidLessThanOrEqualTo(String value) {
            addCriterion("DEPARTDINGID <=", value, "departdingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidLike(String value) {
            addCriterion("DEPARTDINGID like", value, "departdingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidNotLike(String value) {
            addCriterion("DEPARTDINGID not like", value, "departdingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidIn(List<String> values) {
            addCriterion("DEPARTDINGID in", values, "departdingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidNotIn(List<String> values) {
            addCriterion("DEPARTDINGID not in", values, "departdingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidBetween(String value1, String value2) {
            addCriterion("DEPARTDINGID between", value1, value2, "departdingid");
            return (Criteria) this;
        }

        public Criteria andDepartdingidNotBetween(String value1, String value2) {
            addCriterion("DEPARTDINGID not between", value1, value2, "departdingid");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("STATE like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("STATE not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("STATE not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("CREATETIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("CREATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(LocalDateTime value) {
            addCriterion("CREATETIME =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(LocalDateTime value) {
            addCriterion("CREATETIME <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(LocalDateTime value) {
            addCriterion("CREATETIME >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("CREATETIME >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(LocalDateTime value) {
            addCriterion("CREATETIME <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("CREATETIME <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<LocalDateTime> values) {
            addCriterion("CREATETIME in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<LocalDateTime> values) {
            addCriterion("CREATETIME not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("CREATETIME between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("CREATETIME not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("UPDATETIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("UPDATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(LocalDateTime value) {
            addCriterion("UPDATETIME =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(LocalDateTime value) {
            addCriterion("UPDATETIME <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(LocalDateTime value) {
            addCriterion("UPDATETIME >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("UPDATETIME >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(LocalDateTime value) {
            addCriterion("UPDATETIME <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("UPDATETIME <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<LocalDateTime> values) {
            addCriterion("UPDATETIME in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<LocalDateTime> values) {
            addCriterion("UPDATETIME not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("UPDATETIME between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("UPDATETIME not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIsNull() {
            addCriterion("UPDATEUSER is null");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIsNotNull() {
            addCriterion("UPDATEUSER is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateuserEqualTo(String value) {
            addCriterion("UPDATEUSER =", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserNotEqualTo(String value) {
            addCriterion("UPDATEUSER <>", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserGreaterThan(String value) {
            addCriterion("UPDATEUSER >", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATEUSER >=", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserLessThan(String value) {
            addCriterion("UPDATEUSER <", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserLessThanOrEqualTo(String value) {
            addCriterion("UPDATEUSER <=", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserLike(String value) {
            addCriterion("UPDATEUSER like", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserNotLike(String value) {
            addCriterion("UPDATEUSER not like", value, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserIn(List<String> values) {
            addCriterion("UPDATEUSER in", values, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserNotIn(List<String> values) {
            addCriterion("UPDATEUSER not in", values, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserBetween(String value1, String value2) {
            addCriterion("UPDATEUSER between", value1, value2, "updateuser");
            return (Criteria) this;
        }

        public Criteria andUpdateuserNotBetween(String value1, String value2) {
            addCriterion("UPDATEUSER not between", value1, value2, "updateuser");
            return (Criteria) this;
        }

        public Criteria andDingzwIsNull() {
            addCriterion("DINGZW is null");
            return (Criteria) this;
        }

        public Criteria andDingzwIsNotNull() {
            addCriterion("DINGZW is not null");
            return (Criteria) this;
        }

        public Criteria andDingzwEqualTo(String value) {
            addCriterion("DINGZW =", value, "dingzw");
            return (Criteria) this;
        }

        public Criteria andDingzwNotEqualTo(String value) {
            addCriterion("DINGZW <>", value, "dingzw");
            return (Criteria) this;
        }

        public Criteria andDingzwGreaterThan(String value) {
            addCriterion("DINGZW >", value, "dingzw");
            return (Criteria) this;
        }

        public Criteria andDingzwGreaterThanOrEqualTo(String value) {
            addCriterion("DINGZW >=", value, "dingzw");
            return (Criteria) this;
        }

        public Criteria andDingzwLessThan(String value) {
            addCriterion("DINGZW <", value, "dingzw");
            return (Criteria) this;
        }

        public Criteria andDingzwLessThanOrEqualTo(String value) {
            addCriterion("DINGZW <=", value, "dingzw");
            return (Criteria) this;
        }

        public Criteria andDingzwLike(String value) {
            addCriterion("DINGZW like", value, "dingzw");
            return (Criteria) this;
        }

        public Criteria andDingzwNotLike(String value) {
            addCriterion("DINGZW not like", value, "dingzw");
            return (Criteria) this;
        }

        public Criteria andDingzwIn(List<String> values) {
            addCriterion("DINGZW in", values, "dingzw");
            return (Criteria) this;
        }

        public Criteria andDingzwNotIn(List<String> values) {
            addCriterion("DINGZW not in", values, "dingzw");
            return (Criteria) this;
        }

        public Criteria andDingzwBetween(String value1, String value2) {
            addCriterion("DINGZW between", value1, value2, "dingzw");
            return (Criteria) this;
        }

        public Criteria andDingzwNotBetween(String value1, String value2) {
            addCriterion("DINGZW not between", value1, value2, "dingzw");
            return (Criteria) this;
        }

        public Criteria andComparetimeIsNull() {
            addCriterion("COMPARETIME is null");
            return (Criteria) this;
        }

        public Criteria andComparetimeIsNotNull() {
            addCriterion("COMPARETIME is not null");
            return (Criteria) this;
        }

        public Criteria andComparetimeEqualTo(Long value) {
            addCriterion("COMPARETIME =", value, "comparetime");
            return (Criteria) this;
        }

        public Criteria andComparetimeNotEqualTo(Long value) {
            addCriterion("COMPARETIME <>", value, "comparetime");
            return (Criteria) this;
        }

        public Criteria andComparetimeGreaterThan(Long value) {
            addCriterion("COMPARETIME >", value, "comparetime");
            return (Criteria) this;
        }

        public Criteria andComparetimeGreaterThanOrEqualTo(Long value) {
            addCriterion("COMPARETIME >=", value, "comparetime");
            return (Criteria) this;
        }

        public Criteria andComparetimeLessThan(Long value) {
            addCriterion("COMPARETIME <", value, "comparetime");
            return (Criteria) this;
        }

        public Criteria andComparetimeLessThanOrEqualTo(Long value) {
            addCriterion("COMPARETIME <=", value, "comparetime");
            return (Criteria) this;
        }

        public Criteria andComparetimeIn(List<Long> values) {
            addCriterion("COMPARETIME in", values, "comparetime");
            return (Criteria) this;
        }

        public Criteria andComparetimeNotIn(List<Long> values) {
            addCriterion("COMPARETIME not in", values, "comparetime");
            return (Criteria) this;
        }

        public Criteria andComparetimeBetween(Long value1, Long value2) {
            addCriterion("COMPARETIME between", value1, value2, "comparetime");
            return (Criteria) this;
        }

        public Criteria andComparetimeNotBetween(Long value1, Long value2) {
            addCriterion("COMPARETIME not between", value1, value2, "comparetime");
            return (Criteria) this;
        }

        public Criteria andIsleafIsNull() {
            addCriterion("ISLEAF is null");
            return (Criteria) this;
        }

        public Criteria andIsleafIsNotNull() {
            addCriterion("ISLEAF is not null");
            return (Criteria) this;
        }

        public Criteria andIsleafEqualTo(Double value) {
            addCriterion("ISLEAF =", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafNotEqualTo(Double value) {
            addCriterion("ISLEAF <>", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafGreaterThan(Double value) {
            addCriterion("ISLEAF >", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafGreaterThanOrEqualTo(Double value) {
            addCriterion("ISLEAF >=", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafLessThan(Double value) {
            addCriterion("ISLEAF <", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafLessThanOrEqualTo(Double value) {
            addCriterion("ISLEAF <=", value, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafIn(List<Double> values) {
            addCriterion("ISLEAF in", values, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafNotIn(List<Double> values) {
            addCriterion("ISLEAF not in", values, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafBetween(Double value1, Double value2) {
            addCriterion("ISLEAF between", value1, value2, "isleaf");
            return (Criteria) this;
        }

        public Criteria andIsleafNotBetween(Double value1, Double value2) {
            addCriterion("ISLEAF not between", value1, value2, "isleaf");
            return (Criteria) this;
        }

        public Criteria andEnglishnameIsNull() {
            addCriterion("ENGLISHNAME is null");
            return (Criteria) this;
        }

        public Criteria andEnglishnameIsNotNull() {
            addCriterion("ENGLISHNAME is not null");
            return (Criteria) this;
        }

        public Criteria andEnglishnameEqualTo(String value) {
            addCriterion("ENGLISHNAME =", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameNotEqualTo(String value) {
            addCriterion("ENGLISHNAME <>", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameGreaterThan(String value) {
            addCriterion("ENGLISHNAME >", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameGreaterThanOrEqualTo(String value) {
            addCriterion("ENGLISHNAME >=", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameLessThan(String value) {
            addCriterion("ENGLISHNAME <", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameLessThanOrEqualTo(String value) {
            addCriterion("ENGLISHNAME <=", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameLike(String value) {
            addCriterion("ENGLISHNAME like", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameNotLike(String value) {
            addCriterion("ENGLISHNAME not like", value, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameIn(List<String> values) {
            addCriterion("ENGLISHNAME in", values, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameNotIn(List<String> values) {
            addCriterion("ENGLISHNAME not in", values, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameBetween(String value1, String value2) {
            addCriterion("ENGLISHNAME between", value1, value2, "englishname");
            return (Criteria) this;
        }

        public Criteria andEnglishnameNotBetween(String value1, String value2) {
            addCriterion("ENGLISHNAME not between", value1, value2, "englishname");
            return (Criteria) this;
        }

        public Criteria andOthernameIsNull() {
            addCriterion("OTHERNAME is null");
            return (Criteria) this;
        }

        public Criteria andOthernameIsNotNull() {
            addCriterion("OTHERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andOthernameEqualTo(String value) {
            addCriterion("OTHERNAME =", value, "othername");
            return (Criteria) this;
        }

        public Criteria andOthernameNotEqualTo(String value) {
            addCriterion("OTHERNAME <>", value, "othername");
            return (Criteria) this;
        }

        public Criteria andOthernameGreaterThan(String value) {
            addCriterion("OTHERNAME >", value, "othername");
            return (Criteria) this;
        }

        public Criteria andOthernameGreaterThanOrEqualTo(String value) {
            addCriterion("OTHERNAME >=", value, "othername");
            return (Criteria) this;
        }

        public Criteria andOthernameLessThan(String value) {
            addCriterion("OTHERNAME <", value, "othername");
            return (Criteria) this;
        }

        public Criteria andOthernameLessThanOrEqualTo(String value) {
            addCriterion("OTHERNAME <=", value, "othername");
            return (Criteria) this;
        }

        public Criteria andOthernameLike(String value) {
            addCriterion("OTHERNAME like", value, "othername");
            return (Criteria) this;
        }

        public Criteria andOthernameNotLike(String value) {
            addCriterion("OTHERNAME not like", value, "othername");
            return (Criteria) this;
        }

        public Criteria andOthernameIn(List<String> values) {
            addCriterion("OTHERNAME in", values, "othername");
            return (Criteria) this;
        }

        public Criteria andOthernameNotIn(List<String> values) {
            addCriterion("OTHERNAME not in", values, "othername");
            return (Criteria) this;
        }

        public Criteria andOthernameBetween(String value1, String value2) {
            addCriterion("OTHERNAME between", value1, value2, "othername");
            return (Criteria) this;
        }

        public Criteria andOthernameNotBetween(String value1, String value2) {
            addCriterion("OTHERNAME not between", value1, value2, "othername");
            return (Criteria) this;
        }

        public Criteria andFrankIsNull() {
            addCriterion("FRANK is null");
            return (Criteria) this;
        }

        public Criteria andFrankIsNotNull() {
            addCriterion("FRANK is not null");
            return (Criteria) this;
        }

        public Criteria andFrankEqualTo(Long value) {
            addCriterion("FRANK =", value, "frank");
            return (Criteria) this;
        }

        public Criteria andFrankNotEqualTo(Long value) {
            addCriterion("FRANK <>", value, "frank");
            return (Criteria) this;
        }

        public Criteria andFrankGreaterThan(Long value) {
            addCriterion("FRANK >", value, "frank");
            return (Criteria) this;
        }

        public Criteria andFrankGreaterThanOrEqualTo(Long value) {
            addCriterion("FRANK >=", value, "frank");
            return (Criteria) this;
        }

        public Criteria andFrankLessThan(Long value) {
            addCriterion("FRANK <", value, "frank");
            return (Criteria) this;
        }

        public Criteria andFrankLessThanOrEqualTo(Long value) {
            addCriterion("FRANK <=", value, "frank");
            return (Criteria) this;
        }

        public Criteria andFrankIn(List<Long> values) {
            addCriterion("FRANK in", values, "frank");
            return (Criteria) this;
        }

        public Criteria andFrankNotIn(List<Long> values) {
            addCriterion("FRANK not in", values, "frank");
            return (Criteria) this;
        }

        public Criteria andFrankBetween(Long value1, Long value2) {
            addCriterion("FRANK between", value1, value2, "frank");
            return (Criteria) this;
        }

        public Criteria andFrankNotBetween(Long value1, Long value2) {
            addCriterion("FRANK not between", value1, value2, "frank");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorIsNull() {
            addCriterion("DIRECT_SUPERVISOR is null");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorIsNotNull() {
            addCriterion("DIRECT_SUPERVISOR is not null");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorEqualTo(String value) {
            addCriterion("DIRECT_SUPERVISOR =", value, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorNotEqualTo(String value) {
            addCriterion("DIRECT_SUPERVISOR <>", value, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorGreaterThan(String value) {
            addCriterion("DIRECT_SUPERVISOR >", value, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorGreaterThanOrEqualTo(String value) {
            addCriterion("DIRECT_SUPERVISOR >=", value, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorLessThan(String value) {
            addCriterion("DIRECT_SUPERVISOR <", value, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorLessThanOrEqualTo(String value) {
            addCriterion("DIRECT_SUPERVISOR <=", value, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorLike(String value) {
            addCriterion("DIRECT_SUPERVISOR like", value, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorNotLike(String value) {
            addCriterion("DIRECT_SUPERVISOR not like", value, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorIn(List<String> values) {
            addCriterion("DIRECT_SUPERVISOR in", values, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorNotIn(List<String> values) {
            addCriterion("DIRECT_SUPERVISOR not in", values, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorBetween(String value1, String value2) {
            addCriterion("DIRECT_SUPERVISOR between", value1, value2, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andDirectSupervisorNotBetween(String value1, String value2) {
            addCriterion("DIRECT_SUPERVISOR not between", value1, value2, "directSupervisor");
            return (Criteria) this;
        }

        public Criteria andOtherdepartIsNull() {
            addCriterion("OTHERDEPART is null");
            return (Criteria) this;
        }

        public Criteria andOtherdepartIsNotNull() {
            addCriterion("OTHERDEPART is not null");
            return (Criteria) this;
        }

        public Criteria andOtherdepartEqualTo(String value) {
            addCriterion("OTHERDEPART =", value, "otherdepart");
            return (Criteria) this;
        }

        public Criteria andOtherdepartNotEqualTo(String value) {
            addCriterion("OTHERDEPART <>", value, "otherdepart");
            return (Criteria) this;
        }

        public Criteria andOtherdepartGreaterThan(String value) {
            addCriterion("OTHERDEPART >", value, "otherdepart");
            return (Criteria) this;
        }

        public Criteria andOtherdepartGreaterThanOrEqualTo(String value) {
            addCriterion("OTHERDEPART >=", value, "otherdepart");
            return (Criteria) this;
        }

        public Criteria andOtherdepartLessThan(String value) {
            addCriterion("OTHERDEPART <", value, "otherdepart");
            return (Criteria) this;
        }

        public Criteria andOtherdepartLessThanOrEqualTo(String value) {
            addCriterion("OTHERDEPART <=", value, "otherdepart");
            return (Criteria) this;
        }

        public Criteria andOtherdepartLike(String value) {
            addCriterion("OTHERDEPART like", value, "otherdepart");
            return (Criteria) this;
        }

        public Criteria andOtherdepartNotLike(String value) {
            addCriterion("OTHERDEPART not like", value, "otherdepart");
            return (Criteria) this;
        }

        public Criteria andOtherdepartIn(List<String> values) {
            addCriterion("OTHERDEPART in", values, "otherdepart");
            return (Criteria) this;
        }

        public Criteria andOtherdepartNotIn(List<String> values) {
            addCriterion("OTHERDEPART not in", values, "otherdepart");
            return (Criteria) this;
        }

        public Criteria andOtherdepartBetween(String value1, String value2) {
            addCriterion("OTHERDEPART between", value1, value2, "otherdepart");
            return (Criteria) this;
        }

        public Criteria andOtherdepartNotBetween(String value1, String value2) {
            addCriterion("OTHERDEPART not between", value1, value2, "otherdepart");
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