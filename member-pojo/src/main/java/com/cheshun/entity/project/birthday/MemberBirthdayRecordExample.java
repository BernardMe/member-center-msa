package com.cheshun.entity.project.birthday;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberBirthdayRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberBirthdayRecordExample() {
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

        public Criteria andChoiceCouponNoIsNull() {
            addCriterion("choice_coupon_no is null");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoIsNotNull() {
            addCriterion("choice_coupon_no is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoEqualTo(String value) {
            addCriterion("choice_coupon_no =", value, "choiceCouponNo");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoNotEqualTo(String value) {
            addCriterion("choice_coupon_no <>", value, "choiceCouponNo");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoGreaterThan(String value) {
            addCriterion("choice_coupon_no >", value, "choiceCouponNo");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoGreaterThanOrEqualTo(String value) {
            addCriterion("choice_coupon_no >=", value, "choiceCouponNo");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoLessThan(String value) {
            addCriterion("choice_coupon_no <", value, "choiceCouponNo");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoLessThanOrEqualTo(String value) {
            addCriterion("choice_coupon_no <=", value, "choiceCouponNo");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoLike(String value) {
            addCriterion("choice_coupon_no like", value, "choiceCouponNo");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoNotLike(String value) {
            addCriterion("choice_coupon_no not like", value, "choiceCouponNo");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoIn(List<String> values) {
            addCriterion("choice_coupon_no in", values, "choiceCouponNo");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoNotIn(List<String> values) {
            addCriterion("choice_coupon_no not in", values, "choiceCouponNo");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoBetween(String value1, String value2) {
            addCriterion("choice_coupon_no between", value1, value2, "choiceCouponNo");
            return (Criteria) this;
        }

        public Criteria andChoiceCouponNoNotBetween(String value1, String value2) {
            addCriterion("choice_coupon_no not between", value1, value2, "choiceCouponNo");
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

        public Criteria andBirthdayUpTimeIsNull() {
            addCriterion("birthday_up_time is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayUpTimeIsNotNull() {
            addCriterion("birthday_up_time is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayUpTimeEqualTo(LocalDateTime value) {
            addCriterion("birthday_up_time =", value, "birthdayUpTime");
            return (Criteria) this;
        }

        public Criteria andBirthdayUpTimeNotEqualTo(LocalDateTime value) {
            addCriterion("birthday_up_time <>", value, "birthdayUpTime");
            return (Criteria) this;
        }

        public Criteria andBirthdayUpTimeGreaterThan(LocalDateTime value) {
            addCriterion("birthday_up_time >", value, "birthdayUpTime");
            return (Criteria) this;
        }

        public Criteria andBirthdayUpTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("birthday_up_time >=", value, "birthdayUpTime");
            return (Criteria) this;
        }

        public Criteria andBirthdayUpTimeLessThan(LocalDateTime value) {
            addCriterion("birthday_up_time <", value, "birthdayUpTime");
            return (Criteria) this;
        }

        public Criteria andBirthdayUpTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("birthday_up_time <=", value, "birthdayUpTime");
            return (Criteria) this;
        }

        public Criteria andBirthdayUpTimeIn(List<LocalDateTime> values) {
            addCriterion("birthday_up_time in", values, "birthdayUpTime");
            return (Criteria) this;
        }

        public Criteria andBirthdayUpTimeNotIn(List<LocalDateTime> values) {
            addCriterion("birthday_up_time not in", values, "birthdayUpTime");
            return (Criteria) this;
        }

        public Criteria andBirthdayUpTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("birthday_up_time between", value1, value2, "birthdayUpTime");
            return (Criteria) this;
        }

        public Criteria andBirthdayUpTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("birthday_up_time not between", value1, value2, "birthdayUpTime");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayIsNull() {
            addCriterion("input_birthday is null");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayIsNotNull() {
            addCriterion("input_birthday is not null");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayEqualTo(String value) {
            addCriterion("input_birthday =", value, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayNotEqualTo(String value) {
            addCriterion("input_birthday <>", value, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayGreaterThan(String value) {
            addCriterion("input_birthday >", value, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("input_birthday >=", value, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayLessThan(String value) {
            addCriterion("input_birthday <", value, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayLessThanOrEqualTo(String value) {
            addCriterion("input_birthday <=", value, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayLike(String value) {
            addCriterion("input_birthday like", value, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayNotLike(String value) {
            addCriterion("input_birthday not like", value, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayIn(List<String> values) {
            addCriterion("input_birthday in", values, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayNotIn(List<String> values) {
            addCriterion("input_birthday not in", values, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayBetween(String value1, String value2) {
            addCriterion("input_birthday between", value1, value2, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andInputBirthdayNotBetween(String value1, String value2) {
            addCriterion("input_birthday not between", value1, value2, "inputBirthday");
            return (Criteria) this;
        }

        public Criteria andReceivedIsNull() {
            addCriterion("received is null");
            return (Criteria) this;
        }

        public Criteria andReceivedIsNotNull() {
            addCriterion("received is not null");
            return (Criteria) this;
        }

        public Criteria andReceivedEqualTo(Byte value) {
            addCriterion("received =", value, "received");
            return (Criteria) this;
        }

        public Criteria andReceivedNotEqualTo(Byte value) {
            addCriterion("received <>", value, "received");
            return (Criteria) this;
        }

        public Criteria andReceivedGreaterThan(Byte value) {
            addCriterion("received >", value, "received");
            return (Criteria) this;
        }

        public Criteria andReceivedGreaterThanOrEqualTo(Byte value) {
            addCriterion("received >=", value, "received");
            return (Criteria) this;
        }

        public Criteria andReceivedLessThan(Byte value) {
            addCriterion("received <", value, "received");
            return (Criteria) this;
        }

        public Criteria andReceivedLessThanOrEqualTo(Byte value) {
            addCriterion("received <=", value, "received");
            return (Criteria) this;
        }

        public Criteria andReceivedIn(List<Byte> values) {
            addCriterion("received in", values, "received");
            return (Criteria) this;
        }

        public Criteria andReceivedNotIn(List<Byte> values) {
            addCriterion("received not in", values, "received");
            return (Criteria) this;
        }

        public Criteria andReceivedBetween(Byte value1, Byte value2) {
            addCriterion("received between", value1, value2, "received");
            return (Criteria) this;
        }

        public Criteria andReceivedNotBetween(Byte value1, Byte value2) {
            addCriterion("received not between", value1, value2, "received");
            return (Criteria) this;
        }

        public Criteria andReceiveDateIsNull() {
            addCriterion("receive_date is null");
            return (Criteria) this;
        }

        public Criteria andReceiveDateIsNotNull() {
            addCriterion("receive_date is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveDateEqualTo(LocalDateTime value) {
            addCriterion("receive_date =", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateNotEqualTo(LocalDateTime value) {
            addCriterion("receive_date <>", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateGreaterThan(LocalDateTime value) {
            addCriterion("receive_date >", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("receive_date >=", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateLessThan(LocalDateTime value) {
            addCriterion("receive_date <", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("receive_date <=", value, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateIn(List<LocalDateTime> values) {
            addCriterion("receive_date in", values, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateNotIn(List<LocalDateTime> values) {
            addCriterion("receive_date not in", values, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("receive_date between", value1, value2, "receiveDate");
            return (Criteria) this;
        }

        public Criteria andReceiveDateNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("receive_date not between", value1, value2, "receiveDate");
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