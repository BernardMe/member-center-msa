package com.cheshun.entity.project.couplets;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberCoupletsRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberCoupletsRecordExample() {
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

        public Criteria andRecordIdIsNull() {
            addCriterion("record_id is null");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("record_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecordIdEqualTo(Integer value) {
            addCriterion("record_id =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(Integer value) {
            addCriterion("record_id <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(Integer value) {
            addCriterion("record_id >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("record_id >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(Integer value) {
            addCriterion("record_id <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("record_id <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<Integer> values) {
            addCriterion("record_id in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<Integer> values) {
            addCriterion("record_id not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("record_id between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("record_id not between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Integer value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Integer value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Integer value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Integer value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Integer value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Integer> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Integer> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Integer value1, Integer value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoIsNull() {
            addCriterion("member_card_no is null");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoIsNotNull() {
            addCriterion("member_card_no is not null");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoEqualTo(String value) {
            addCriterion("member_card_no =", value, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoNotEqualTo(String value) {
            addCriterion("member_card_no <>", value, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoGreaterThan(String value) {
            addCriterion("member_card_no >", value, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("member_card_no >=", value, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoLessThan(String value) {
            addCriterion("member_card_no <", value, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoLessThanOrEqualTo(String value) {
            addCriterion("member_card_no <=", value, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoLike(String value) {
            addCriterion("member_card_no like", value, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoNotLike(String value) {
            addCriterion("member_card_no not like", value, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoIn(List<String> values) {
            addCriterion("member_card_no in", values, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoNotIn(List<String> values) {
            addCriterion("member_card_no not in", values, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoBetween(String value1, String value2) {
            addCriterion("member_card_no between", value1, value2, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberCardNoNotBetween(String value1, String value2) {
            addCriterion("member_card_no not between", value1, value2, "memberCardNo");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNull() {
            addCriterion("member_name is null");
            return (Criteria) this;
        }

        public Criteria andMemberNameIsNotNull() {
            addCriterion("member_name is not null");
            return (Criteria) this;
        }

        public Criteria andMemberNameEqualTo(String value) {
            addCriterion("member_name =", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotEqualTo(String value) {
            addCriterion("member_name <>", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThan(String value) {
            addCriterion("member_name >", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("member_name >=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThan(String value) {
            addCriterion("member_name <", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLessThanOrEqualTo(String value) {
            addCriterion("member_name <=", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameLike(String value) {
            addCriterion("member_name like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotLike(String value) {
            addCriterion("member_name not like", value, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameIn(List<String> values) {
            addCriterion("member_name in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotIn(List<String> values) {
            addCriterion("member_name not in", values, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameBetween(String value1, String value2) {
            addCriterion("member_name between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberNameNotBetween(String value1, String value2) {
            addCriterion("member_name not between", value1, value2, "memberName");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneIsNull() {
            addCriterion("member_phone is null");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneIsNotNull() {
            addCriterion("member_phone is not null");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneEqualTo(String value) {
            addCriterion("member_phone =", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotEqualTo(String value) {
            addCriterion("member_phone <>", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneGreaterThan(String value) {
            addCriterion("member_phone >", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("member_phone >=", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLessThan(String value) {
            addCriterion("member_phone <", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLessThanOrEqualTo(String value) {
            addCriterion("member_phone <=", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneLike(String value) {
            addCriterion("member_phone like", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotLike(String value) {
            addCriterion("member_phone not like", value, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneIn(List<String> values) {
            addCriterion("member_phone in", values, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotIn(List<String> values) {
            addCriterion("member_phone not in", values, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneBetween(String value1, String value2) {
            addCriterion("member_phone between", value1, value2, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andMemberPhoneNotBetween(String value1, String value2) {
            addCriterion("member_phone not between", value1, value2, "memberPhone");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andFirstLineIsNull() {
            addCriterion("first_line is null");
            return (Criteria) this;
        }

        public Criteria andFirstLineIsNotNull() {
            addCriterion("first_line is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLineEqualTo(String value) {
            addCriterion("first_line =", value, "firstLine");
            return (Criteria) this;
        }

        public Criteria andFirstLineNotEqualTo(String value) {
            addCriterion("first_line <>", value, "firstLine");
            return (Criteria) this;
        }

        public Criteria andFirstLineGreaterThan(String value) {
            addCriterion("first_line >", value, "firstLine");
            return (Criteria) this;
        }

        public Criteria andFirstLineGreaterThanOrEqualTo(String value) {
            addCriterion("first_line >=", value, "firstLine");
            return (Criteria) this;
        }

        public Criteria andFirstLineLessThan(String value) {
            addCriterion("first_line <", value, "firstLine");
            return (Criteria) this;
        }

        public Criteria andFirstLineLessThanOrEqualTo(String value) {
            addCriterion("first_line <=", value, "firstLine");
            return (Criteria) this;
        }

        public Criteria andFirstLineLike(String value) {
            addCriterion("first_line like", value, "firstLine");
            return (Criteria) this;
        }

        public Criteria andFirstLineNotLike(String value) {
            addCriterion("first_line not like", value, "firstLine");
            return (Criteria) this;
        }

        public Criteria andFirstLineIn(List<String> values) {
            addCriterion("first_line in", values, "firstLine");
            return (Criteria) this;
        }

        public Criteria andFirstLineNotIn(List<String> values) {
            addCriterion("first_line not in", values, "firstLine");
            return (Criteria) this;
        }

        public Criteria andFirstLineBetween(String value1, String value2) {
            addCriterion("first_line between", value1, value2, "firstLine");
            return (Criteria) this;
        }

        public Criteria andFirstLineNotBetween(String value1, String value2) {
            addCriterion("first_line not between", value1, value2, "firstLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineIsNull() {
            addCriterion("second_line is null");
            return (Criteria) this;
        }

        public Criteria andSecondLineIsNotNull() {
            addCriterion("second_line is not null");
            return (Criteria) this;
        }

        public Criteria andSecondLineEqualTo(String value) {
            addCriterion("second_line =", value, "secondLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineNotEqualTo(String value) {
            addCriterion("second_line <>", value, "secondLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineGreaterThan(String value) {
            addCriterion("second_line >", value, "secondLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineGreaterThanOrEqualTo(String value) {
            addCriterion("second_line >=", value, "secondLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineLessThan(String value) {
            addCriterion("second_line <", value, "secondLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineLessThanOrEqualTo(String value) {
            addCriterion("second_line <=", value, "secondLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineLike(String value) {
            addCriterion("second_line like", value, "secondLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineNotLike(String value) {
            addCriterion("second_line not like", value, "secondLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineIn(List<String> values) {
            addCriterion("second_line in", values, "secondLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineNotIn(List<String> values) {
            addCriterion("second_line not in", values, "secondLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineBetween(String value1, String value2) {
            addCriterion("second_line between", value1, value2, "secondLine");
            return (Criteria) this;
        }

        public Criteria andSecondLineNotBetween(String value1, String value2) {
            addCriterion("second_line not between", value1, value2, "secondLine");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollIsNull() {
            addCriterion("horizontal_scroll is null");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollIsNotNull() {
            addCriterion("horizontal_scroll is not null");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollEqualTo(String value) {
            addCriterion("horizontal_scroll =", value, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollNotEqualTo(String value) {
            addCriterion("horizontal_scroll <>", value, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollGreaterThan(String value) {
            addCriterion("horizontal_scroll >", value, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollGreaterThanOrEqualTo(String value) {
            addCriterion("horizontal_scroll >=", value, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollLessThan(String value) {
            addCriterion("horizontal_scroll <", value, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollLessThanOrEqualTo(String value) {
            addCriterion("horizontal_scroll <=", value, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollLike(String value) {
            addCriterion("horizontal_scroll like", value, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollNotLike(String value) {
            addCriterion("horizontal_scroll not like", value, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollIn(List<String> values) {
            addCriterion("horizontal_scroll in", values, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollNotIn(List<String> values) {
            addCriterion("horizontal_scroll not in", values, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollBetween(String value1, String value2) {
            addCriterion("horizontal_scroll between", value1, value2, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andHorizontalScrollNotBetween(String value1, String value2) {
            addCriterion("horizontal_scroll not between", value1, value2, "horizontalScroll");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeIsNull() {
            addCriterion("service_waiter_code is null");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeIsNotNull() {
            addCriterion("service_waiter_code is not null");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeEqualTo(String value) {
            addCriterion("service_waiter_code =", value, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeNotEqualTo(String value) {
            addCriterion("service_waiter_code <>", value, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeGreaterThan(String value) {
            addCriterion("service_waiter_code >", value, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeGreaterThanOrEqualTo(String value) {
            addCriterion("service_waiter_code >=", value, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeLessThan(String value) {
            addCriterion("service_waiter_code <", value, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeLessThanOrEqualTo(String value) {
            addCriterion("service_waiter_code <=", value, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeLike(String value) {
            addCriterion("service_waiter_code like", value, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeNotLike(String value) {
            addCriterion("service_waiter_code not like", value, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeIn(List<String> values) {
            addCriterion("service_waiter_code in", values, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeNotIn(List<String> values) {
            addCriterion("service_waiter_code not in", values, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeBetween(String value1, String value2) {
            addCriterion("service_waiter_code between", value1, value2, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterCodeNotBetween(String value1, String value2) {
            addCriterion("service_waiter_code not between", value1, value2, "serviceWaiterCode");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameIsNull() {
            addCriterion("service_waiter_name is null");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameIsNotNull() {
            addCriterion("service_waiter_name is not null");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameEqualTo(String value) {
            addCriterion("service_waiter_name =", value, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameNotEqualTo(String value) {
            addCriterion("service_waiter_name <>", value, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameGreaterThan(String value) {
            addCriterion("service_waiter_name >", value, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameGreaterThanOrEqualTo(String value) {
            addCriterion("service_waiter_name >=", value, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameLessThan(String value) {
            addCriterion("service_waiter_name <", value, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameLessThanOrEqualTo(String value) {
            addCriterion("service_waiter_name <=", value, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameLike(String value) {
            addCriterion("service_waiter_name like", value, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameNotLike(String value) {
            addCriterion("service_waiter_name not like", value, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameIn(List<String> values) {
            addCriterion("service_waiter_name in", values, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameNotIn(List<String> values) {
            addCriterion("service_waiter_name not in", values, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameBetween(String value1, String value2) {
            addCriterion("service_waiter_name between", value1, value2, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceWaiterNameNotBetween(String value1, String value2) {
            addCriterion("service_waiter_name not between", value1, value2, "serviceWaiterName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeIsNull() {
            addCriterion("service_store_code is null");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeIsNotNull() {
            addCriterion("service_store_code is not null");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeEqualTo(String value) {
            addCriterion("service_store_code =", value, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeNotEqualTo(String value) {
            addCriterion("service_store_code <>", value, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeGreaterThan(String value) {
            addCriterion("service_store_code >", value, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeGreaterThanOrEqualTo(String value) {
            addCriterion("service_store_code >=", value, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeLessThan(String value) {
            addCriterion("service_store_code <", value, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeLessThanOrEqualTo(String value) {
            addCriterion("service_store_code <=", value, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeLike(String value) {
            addCriterion("service_store_code like", value, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeNotLike(String value) {
            addCriterion("service_store_code not like", value, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeIn(List<String> values) {
            addCriterion("service_store_code in", values, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeNotIn(List<String> values) {
            addCriterion("service_store_code not in", values, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeBetween(String value1, String value2) {
            addCriterion("service_store_code between", value1, value2, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreCodeNotBetween(String value1, String value2) {
            addCriterion("service_store_code not between", value1, value2, "serviceStoreCode");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameIsNull() {
            addCriterion("service_store_name is null");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameIsNotNull() {
            addCriterion("service_store_name is not null");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameEqualTo(String value) {
            addCriterion("service_store_name =", value, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameNotEqualTo(String value) {
            addCriterion("service_store_name <>", value, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameGreaterThan(String value) {
            addCriterion("service_store_name >", value, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("service_store_name >=", value, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameLessThan(String value) {
            addCriterion("service_store_name <", value, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameLessThanOrEqualTo(String value) {
            addCriterion("service_store_name <=", value, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameLike(String value) {
            addCriterion("service_store_name like", value, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameNotLike(String value) {
            addCriterion("service_store_name not like", value, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameIn(List<String> values) {
            addCriterion("service_store_name in", values, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameNotIn(List<String> values) {
            addCriterion("service_store_name not in", values, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameBetween(String value1, String value2) {
            addCriterion("service_store_name between", value1, value2, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andServiceStoreNameNotBetween(String value1, String value2) {
            addCriterion("service_store_name not between", value1, value2, "serviceStoreName");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddIsNull() {
            addCriterion("is_qvx_add is null");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddIsNotNull() {
            addCriterion("is_qvx_add is not null");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddEqualTo(Byte value) {
            addCriterion("is_qvx_add =", value, "isQvxAdd");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddNotEqualTo(Byte value) {
            addCriterion("is_qvx_add <>", value, "isQvxAdd");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddGreaterThan(Byte value) {
            addCriterion("is_qvx_add >", value, "isQvxAdd");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_qvx_add >=", value, "isQvxAdd");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddLessThan(Byte value) {
            addCriterion("is_qvx_add <", value, "isQvxAdd");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddLessThanOrEqualTo(Byte value) {
            addCriterion("is_qvx_add <=", value, "isQvxAdd");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddIn(List<Byte> values) {
            addCriterion("is_qvx_add in", values, "isQvxAdd");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddNotIn(List<Byte> values) {
            addCriterion("is_qvx_add not in", values, "isQvxAdd");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddBetween(Byte value1, Byte value2) {
            addCriterion("is_qvx_add between", value1, value2, "isQvxAdd");
            return (Criteria) this;
        }

        public Criteria andIsQvxAddNotBetween(Byte value1, Byte value2) {
            addCriterion("is_qvx_add not between", value1, value2, "isQvxAdd");
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