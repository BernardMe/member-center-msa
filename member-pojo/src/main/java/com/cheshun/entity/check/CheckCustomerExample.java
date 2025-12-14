package com.cheshun.entity.check;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheckCustomerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CheckCustomerExample() {
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

        public Criteria andCheckIdIsNull() {
            addCriterion("check_id is null");
            return (Criteria) this;
        }

        public Criteria andCheckIdIsNotNull() {
            addCriterion("check_id is not null");
            return (Criteria) this;
        }

        public Criteria andCheckIdEqualTo(Integer value) {
            addCriterion("check_id =", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdNotEqualTo(Integer value) {
            addCriterion("check_id <>", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdGreaterThan(Integer value) {
            addCriterion("check_id >", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_id >=", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdLessThan(Integer value) {
            addCriterion("check_id <", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdLessThanOrEqualTo(Integer value) {
            addCriterion("check_id <=", value, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdIn(List<Integer> values) {
            addCriterion("check_id in", values, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdNotIn(List<Integer> values) {
            addCriterion("check_id not in", values, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdBetween(Integer value1, Integer value2) {
            addCriterion("check_id between", value1, value2, "checkId");
            return (Criteria) this;
        }

        public Criteria andCheckIdNotBetween(Integer value1, Integer value2) {
            addCriterion("check_id not between", value1, value2, "checkId");
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

        public Criteria andActivityNameIsNull() {
            addCriterion("activity_name is null");
            return (Criteria) this;
        }

        public Criteria andActivityNameIsNotNull() {
            addCriterion("activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andActivityNameEqualTo(String value) {
            addCriterion("activity_name =", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotEqualTo(String value) {
            addCriterion("activity_name <>", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThan(String value) {
            addCriterion("activity_name >", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("activity_name >=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThan(String value) {
            addCriterion("activity_name <", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLessThanOrEqualTo(String value) {
            addCriterion("activity_name <=", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameLike(String value) {
            addCriterion("activity_name like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotLike(String value) {
            addCriterion("activity_name not like", value, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameIn(List<String> values) {
            addCriterion("activity_name in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotIn(List<String> values) {
            addCriterion("activity_name not in", values, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameBetween(String value1, String value2) {
            addCriterion("activity_name between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andActivityNameNotBetween(String value1, String value2) {
            addCriterion("activity_name not between", value1, value2, "activityName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneIsNull() {
            addCriterion("customer_phone is null");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneIsNotNull() {
            addCriterion("customer_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneEqualTo(String value) {
            addCriterion("customer_phone =", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotEqualTo(String value) {
            addCriterion("customer_phone <>", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneGreaterThan(String value) {
            addCriterion("customer_phone >", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("customer_phone >=", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneLessThan(String value) {
            addCriterion("customer_phone <", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneLessThanOrEqualTo(String value) {
            addCriterion("customer_phone <=", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneLike(String value) {
            addCriterion("customer_phone like", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotLike(String value) {
            addCriterion("customer_phone not like", value, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneIn(List<String> values) {
            addCriterion("customer_phone in", values, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotIn(List<String> values) {
            addCriterion("customer_phone not in", values, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneBetween(String value1, String value2) {
            addCriterion("customer_phone between", value1, value2, "customerPhone");
            return (Criteria) this;
        }

        public Criteria andCustomerPhoneNotBetween(String value1, String value2) {
            addCriterion("customer_phone not between", value1, value2, "customerPhone");
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

        public Criteria andCustomerAddressIsNull() {
            addCriterion("customer_address is null");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIsNotNull() {
            addCriterion("customer_address is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressEqualTo(String value) {
            addCriterion("customer_address =", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotEqualTo(String value) {
            addCriterion("customer_address <>", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressGreaterThan(String value) {
            addCriterion("customer_address >", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("customer_address >=", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLessThan(String value) {
            addCriterion("customer_address <", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLessThanOrEqualTo(String value) {
            addCriterion("customer_address <=", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLike(String value) {
            addCriterion("customer_address like", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotLike(String value) {
            addCriterion("customer_address not like", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIn(List<String> values) {
            addCriterion("customer_address in", values, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotIn(List<String> values) {
            addCriterion("customer_address not in", values, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressBetween(String value1, String value2) {
            addCriterion("customer_address between", value1, value2, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotBetween(String value1, String value2) {
            addCriterion("customer_address not between", value1, value2, "customerAddress");
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

        public Criteria andScreenshotUrlIsNull() {
            addCriterion("screenshot_url is null");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlIsNotNull() {
            addCriterion("screenshot_url is not null");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlEqualTo(String value) {
            addCriterion("screenshot_url =", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlNotEqualTo(String value) {
            addCriterion("screenshot_url <>", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlGreaterThan(String value) {
            addCriterion("screenshot_url >", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlGreaterThanOrEqualTo(String value) {
            addCriterion("screenshot_url >=", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlLessThan(String value) {
            addCriterion("screenshot_url <", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlLessThanOrEqualTo(String value) {
            addCriterion("screenshot_url <=", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlLike(String value) {
            addCriterion("screenshot_url like", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlNotLike(String value) {
            addCriterion("screenshot_url not like", value, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlIn(List<String> values) {
            addCriterion("screenshot_url in", values, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlNotIn(List<String> values) {
            addCriterion("screenshot_url not in", values, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlBetween(String value1, String value2) {
            addCriterion("screenshot_url between", value1, value2, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andScreenshotUrlNotBetween(String value1, String value2) {
            addCriterion("screenshot_url not between", value1, value2, "screenshotUrl");
            return (Criteria) this;
        }

        public Criteria andSubmitCountIsNull() {
            addCriterion("submit_count is null");
            return (Criteria) this;
        }

        public Criteria andSubmitCountIsNotNull() {
            addCriterion("submit_count is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitCountEqualTo(Byte value) {
            addCriterion("submit_count =", value, "submitCount");
            return (Criteria) this;
        }

        public Criteria andSubmitCountNotEqualTo(Byte value) {
            addCriterion("submit_count <>", value, "submitCount");
            return (Criteria) this;
        }

        public Criteria andSubmitCountGreaterThan(Byte value) {
            addCriterion("submit_count >", value, "submitCount");
            return (Criteria) this;
        }

        public Criteria andSubmitCountGreaterThanOrEqualTo(Byte value) {
            addCriterion("submit_count >=", value, "submitCount");
            return (Criteria) this;
        }

        public Criteria andSubmitCountLessThan(Byte value) {
            addCriterion("submit_count <", value, "submitCount");
            return (Criteria) this;
        }

        public Criteria andSubmitCountLessThanOrEqualTo(Byte value) {
            addCriterion("submit_count <=", value, "submitCount");
            return (Criteria) this;
        }

        public Criteria andSubmitCountIn(List<Byte> values) {
            addCriterion("submit_count in", values, "submitCount");
            return (Criteria) this;
        }

        public Criteria andSubmitCountNotIn(List<Byte> values) {
            addCriterion("submit_count not in", values, "submitCount");
            return (Criteria) this;
        }

        public Criteria andSubmitCountBetween(Byte value1, Byte value2) {
            addCriterion("submit_count between", value1, value2, "submitCount");
            return (Criteria) this;
        }

        public Criteria andSubmitCountNotBetween(Byte value1, Byte value2) {
            addCriterion("submit_count not between", value1, value2, "submitCount");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(Byte value) {
            addCriterion("audit_status =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(Byte value) {
            addCriterion("audit_status <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(Byte value) {
            addCriterion("audit_status >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("audit_status >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(Byte value) {
            addCriterion("audit_status <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(Byte value) {
            addCriterion("audit_status <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<Byte> values) {
            addCriterion("audit_status in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<Byte> values) {
            addCriterion("audit_status not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(Byte value1, Byte value2) {
            addCriterion("audit_status between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("audit_status not between", value1, value2, "auditStatus");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(LocalDateTime value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(LocalDateTime value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(LocalDateTime value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<LocalDateTime> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
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