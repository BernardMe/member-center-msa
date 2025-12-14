package com.cheshun.entity.project.customer.survey;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SurveyPaperDTOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SurveyPaperDTOExample() {
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

        public Criteria andPaperIdIsNull() {
            addCriterion("paper_id is null");
            return (Criteria) this;
        }

        public Criteria andPaperIdIsNotNull() {
            addCriterion("paper_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaperIdEqualTo(Integer value) {
            addCriterion("paper_id =", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotEqualTo(Integer value) {
            addCriterion("paper_id <>", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdGreaterThan(Integer value) {
            addCriterion("paper_id >", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("paper_id >=", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdLessThan(Integer value) {
            addCriterion("paper_id <", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdLessThanOrEqualTo(Integer value) {
            addCriterion("paper_id <=", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdIn(List<Integer> values) {
            addCriterion("paper_id in", values, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotIn(List<Integer> values) {
            addCriterion("paper_id not in", values, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdBetween(Integer value1, Integer value2) {
            addCriterion("paper_id between", value1, value2, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotBetween(Integer value1, Integer value2) {
            addCriterion("paper_id not between", value1, value2, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperNameIsNull() {
            addCriterion("paper_name is null");
            return (Criteria) this;
        }

        public Criteria andPaperNameIsNotNull() {
            addCriterion("paper_name is not null");
            return (Criteria) this;
        }

        public Criteria andPaperNameEqualTo(String value) {
            addCriterion("paper_name =", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotEqualTo(String value) {
            addCriterion("paper_name <>", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThan(String value) {
            addCriterion("paper_name >", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameGreaterThanOrEqualTo(String value) {
            addCriterion("paper_name >=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThan(String value) {
            addCriterion("paper_name <", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLessThanOrEqualTo(String value) {
            addCriterion("paper_name <=", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameLike(String value) {
            addCriterion("paper_name like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotLike(String value) {
            addCriterion("paper_name not like", value, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameIn(List<String> values) {
            addCriterion("paper_name in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotIn(List<String> values) {
            addCriterion("paper_name not in", values, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameBetween(String value1, String value2) {
            addCriterion("paper_name between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andPaperNameNotBetween(String value1, String value2) {
            addCriterion("paper_name not between", value1, value2, "paperName");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIsNull() {
            addCriterion("question_num is null");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIsNotNull() {
            addCriterion("question_num is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionNumEqualTo(Integer value) {
            addCriterion("question_num =", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotEqualTo(Integer value) {
            addCriterion("question_num <>", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumGreaterThan(Integer value) {
            addCriterion("question_num >", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("question_num >=", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumLessThan(Integer value) {
            addCriterion("question_num <", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumLessThanOrEqualTo(Integer value) {
            addCriterion("question_num <=", value, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumIn(List<Integer> values) {
            addCriterion("question_num in", values, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotIn(List<Integer> values) {
            addCriterion("question_num not in", values, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumBetween(Integer value1, Integer value2) {
            addCriterion("question_num between", value1, value2, "questionNum");
            return (Criteria) this;
        }

        public Criteria andQuestionNumNotBetween(Integer value1, Integer value2) {
            addCriterion("question_num not between", value1, value2, "questionNum");
            return (Criteria) this;
        }

        public Criteria andRecordNumIsNull() {
            addCriterion("record_num is null");
            return (Criteria) this;
        }

        public Criteria andRecordNumIsNotNull() {
            addCriterion("record_num is not null");
            return (Criteria) this;
        }

        public Criteria andRecordNumEqualTo(Integer value) {
            addCriterion("record_num =", value, "recordNum");
            return (Criteria) this;
        }

        public Criteria andRecordNumNotEqualTo(Integer value) {
            addCriterion("record_num <>", value, "recordNum");
            return (Criteria) this;
        }

        public Criteria andRecordNumGreaterThan(Integer value) {
            addCriterion("record_num >", value, "recordNum");
            return (Criteria) this;
        }

        public Criteria andRecordNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("record_num >=", value, "recordNum");
            return (Criteria) this;
        }

        public Criteria andRecordNumLessThan(Integer value) {
            addCriterion("record_num <", value, "recordNum");
            return (Criteria) this;
        }

        public Criteria andRecordNumLessThanOrEqualTo(Integer value) {
            addCriterion("record_num <=", value, "recordNum");
            return (Criteria) this;
        }

        public Criteria andRecordNumIn(List<Integer> values) {
            addCriterion("record_num in", values, "recordNum");
            return (Criteria) this;
        }

        public Criteria andRecordNumNotIn(List<Integer> values) {
            addCriterion("record_num not in", values, "recordNum");
            return (Criteria) this;
        }

        public Criteria andRecordNumBetween(Integer value1, Integer value2) {
            addCriterion("record_num between", value1, value2, "recordNum");
            return (Criteria) this;
        }

        public Criteria andRecordNumNotBetween(Integer value1, Integer value2) {
            addCriterion("record_num not between", value1, value2, "recordNum");
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

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(LocalDateTime value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(LocalDateTime value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(LocalDateTime value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(LocalDateTime value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<LocalDateTime> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<LocalDateTime> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(LocalDateTime value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(LocalDateTime value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(LocalDateTime value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(LocalDateTime value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<LocalDateTime> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<LocalDateTime> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Byte value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Byte value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Byte value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Byte value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Byte value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Byte> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Byte> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Byte value1, Byte value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeIsNull() {
            addCriterion("small_program_code is null");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeIsNotNull() {
            addCriterion("small_program_code is not null");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeEqualTo(String value) {
            addCriterion("small_program_code =", value, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeNotEqualTo(String value) {
            addCriterion("small_program_code <>", value, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeGreaterThan(String value) {
            addCriterion("small_program_code >", value, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeGreaterThanOrEqualTo(String value) {
            addCriterion("small_program_code >=", value, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeLessThan(String value) {
            addCriterion("small_program_code <", value, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeLessThanOrEqualTo(String value) {
            addCriterion("small_program_code <=", value, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeLike(String value) {
            addCriterion("small_program_code like", value, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeNotLike(String value) {
            addCriterion("small_program_code not like", value, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeIn(List<String> values) {
            addCriterion("small_program_code in", values, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeNotIn(List<String> values) {
            addCriterion("small_program_code not in", values, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeBetween(String value1, String value2) {
            addCriterion("small_program_code between", value1, value2, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andSmallProgramCodeNotBetween(String value1, String value2) {
            addCriterion("small_program_code not between", value1, value2, "smallProgramCode");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponIsNull() {
            addCriterion("is_issue_coupon is null");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponIsNotNull() {
            addCriterion("is_issue_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponEqualTo(Byte value) {
            addCriterion("is_issue_coupon =", value, "isIssueCoupon");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponNotEqualTo(Byte value) {
            addCriterion("is_issue_coupon <>", value, "isIssueCoupon");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponGreaterThan(Byte value) {
            addCriterion("is_issue_coupon >", value, "isIssueCoupon");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_issue_coupon >=", value, "isIssueCoupon");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponLessThan(Byte value) {
            addCriterion("is_issue_coupon <", value, "isIssueCoupon");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponLessThanOrEqualTo(Byte value) {
            addCriterion("is_issue_coupon <=", value, "isIssueCoupon");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponIn(List<Byte> values) {
            addCriterion("is_issue_coupon in", values, "isIssueCoupon");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponNotIn(List<Byte> values) {
            addCriterion("is_issue_coupon not in", values, "isIssueCoupon");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponBetween(Byte value1, Byte value2) {
            addCriterion("is_issue_coupon between", value1, value2, "isIssueCoupon");
            return (Criteria) this;
        }

        public Criteria andIsIssueCouponNotBetween(Byte value1, Byte value2) {
            addCriterion("is_issue_coupon not between", value1, value2, "isIssueCoupon");
            return (Criteria) this;
        }

        public Criteria andCouponNoIsNull() {
            addCriterion("coupon_no is null");
            return (Criteria) this;
        }

        public Criteria andCouponNoIsNotNull() {
            addCriterion("coupon_no is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNoEqualTo(String value) {
            addCriterion("coupon_no =", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotEqualTo(String value) {
            addCriterion("coupon_no <>", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoGreaterThan(String value) {
            addCriterion("coupon_no >", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_no >=", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLessThan(String value) {
            addCriterion("coupon_no <", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLessThanOrEqualTo(String value) {
            addCriterion("coupon_no <=", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoLike(String value) {
            addCriterion("coupon_no like", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotLike(String value) {
            addCriterion("coupon_no not like", value, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoIn(List<String> values) {
            addCriterion("coupon_no in", values, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotIn(List<String> values) {
            addCriterion("coupon_no not in", values, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoBetween(String value1, String value2) {
            addCriterion("coupon_no between", value1, value2, "couponNo");
            return (Criteria) this;
        }

        public Criteria andCouponNoNotBetween(String value1, String value2) {
            addCriterion("coupon_no not between", value1, value2, "couponNo");
            return (Criteria) this;
        }

        public Criteria andIsDirectedIsNull() {
            addCriterion("is_directed is null");
            return (Criteria) this;
        }

        public Criteria andIsDirectedIsNotNull() {
            addCriterion("is_directed is not null");
            return (Criteria) this;
        }

        public Criteria andIsDirectedEqualTo(Byte value) {
            addCriterion("is_directed =", value, "isDirected");
            return (Criteria) this;
        }

        public Criteria andIsDirectedNotEqualTo(Byte value) {
            addCriterion("is_directed <>", value, "isDirected");
            return (Criteria) this;
        }

        public Criteria andIsDirectedGreaterThan(Byte value) {
            addCriterion("is_directed >", value, "isDirected");
            return (Criteria) this;
        }

        public Criteria andIsDirectedGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_directed >=", value, "isDirected");
            return (Criteria) this;
        }

        public Criteria andIsDirectedLessThan(Byte value) {
            addCriterion("is_directed <", value, "isDirected");
            return (Criteria) this;
        }

        public Criteria andIsDirectedLessThanOrEqualTo(Byte value) {
            addCriterion("is_directed <=", value, "isDirected");
            return (Criteria) this;
        }

        public Criteria andIsDirectedIn(List<Byte> values) {
            addCriterion("is_directed in", values, "isDirected");
            return (Criteria) this;
        }

        public Criteria andIsDirectedNotIn(List<Byte> values) {
            addCriterion("is_directed not in", values, "isDirected");
            return (Criteria) this;
        }

        public Criteria andIsDirectedBetween(Byte value1, Byte value2) {
            addCriterion("is_directed between", value1, value2, "isDirected");
            return (Criteria) this;
        }

        public Criteria andIsDirectedNotBetween(Byte value1, Byte value2) {
            addCriterion("is_directed not between", value1, value2, "isDirected");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyIsNull() {
            addCriterion("is_export_dely is null");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyIsNotNull() {
            addCriterion("is_export_dely is not null");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyEqualTo(Byte value) {
            addCriterion("is_export_dely =", value, "isExportDely");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyNotEqualTo(Byte value) {
            addCriterion("is_export_dely <>", value, "isExportDely");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyGreaterThan(Byte value) {
            addCriterion("is_export_dely >", value, "isExportDely");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_export_dely >=", value, "isExportDely");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyLessThan(Byte value) {
            addCriterion("is_export_dely <", value, "isExportDely");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyLessThanOrEqualTo(Byte value) {
            addCriterion("is_export_dely <=", value, "isExportDely");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyIn(List<Byte> values) {
            addCriterion("is_export_dely in", values, "isExportDely");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyNotIn(List<Byte> values) {
            addCriterion("is_export_dely not in", values, "isExportDely");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyBetween(Byte value1, Byte value2) {
            addCriterion("is_export_dely between", value1, value2, "isExportDely");
            return (Criteria) this;
        }

        public Criteria andIsExportDelyNotBetween(Byte value1, Byte value2) {
            addCriterion("is_export_dely not between", value1, value2, "isExportDely");
            return (Criteria) this;
        }

        public Criteria andForewordTextIsNull() {
            addCriterion("foreword_text is null");
            return (Criteria) this;
        }

        public Criteria andForewordTextIsNotNull() {
            addCriterion("foreword_text is not null");
            return (Criteria) this;
        }

        public Criteria andForewordTextEqualTo(String value) {
            addCriterion("foreword_text =", value, "forewordText");
            return (Criteria) this;
        }

        public Criteria andForewordTextNotEqualTo(String value) {
            addCriterion("foreword_text <>", value, "forewordText");
            return (Criteria) this;
        }

        public Criteria andForewordTextGreaterThan(String value) {
            addCriterion("foreword_text >", value, "forewordText");
            return (Criteria) this;
        }

        public Criteria andForewordTextGreaterThanOrEqualTo(String value) {
            addCriterion("foreword_text >=", value, "forewordText");
            return (Criteria) this;
        }

        public Criteria andForewordTextLessThan(String value) {
            addCriterion("foreword_text <", value, "forewordText");
            return (Criteria) this;
        }

        public Criteria andForewordTextLessThanOrEqualTo(String value) {
            addCriterion("foreword_text <=", value, "forewordText");
            return (Criteria) this;
        }

        public Criteria andForewordTextLike(String value) {
            addCriterion("foreword_text like", value, "forewordText");
            return (Criteria) this;
        }

        public Criteria andForewordTextNotLike(String value) {
            addCriterion("foreword_text not like", value, "forewordText");
            return (Criteria) this;
        }

        public Criteria andForewordTextIn(List<String> values) {
            addCriterion("foreword_text in", values, "forewordText");
            return (Criteria) this;
        }

        public Criteria andForewordTextNotIn(List<String> values) {
            addCriterion("foreword_text not in", values, "forewordText");
            return (Criteria) this;
        }

        public Criteria andForewordTextBetween(String value1, String value2) {
            addCriterion("foreword_text between", value1, value2, "forewordText");
            return (Criteria) this;
        }

        public Criteria andForewordTextNotBetween(String value1, String value2) {
            addCriterion("foreword_text not between", value1, value2, "forewordText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextIsNull() {
            addCriterion("epilogue_text is null");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextIsNotNull() {
            addCriterion("epilogue_text is not null");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextEqualTo(String value) {
            addCriterion("epilogue_text =", value, "epilogueText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextNotEqualTo(String value) {
            addCriterion("epilogue_text <>", value, "epilogueText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextGreaterThan(String value) {
            addCriterion("epilogue_text >", value, "epilogueText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextGreaterThanOrEqualTo(String value) {
            addCriterion("epilogue_text >=", value, "epilogueText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextLessThan(String value) {
            addCriterion("epilogue_text <", value, "epilogueText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextLessThanOrEqualTo(String value) {
            addCriterion("epilogue_text <=", value, "epilogueText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextLike(String value) {
            addCriterion("epilogue_text like", value, "epilogueText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextNotLike(String value) {
            addCriterion("epilogue_text not like", value, "epilogueText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextIn(List<String> values) {
            addCriterion("epilogue_text in", values, "epilogueText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextNotIn(List<String> values) {
            addCriterion("epilogue_text not in", values, "epilogueText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextBetween(String value1, String value2) {
            addCriterion("epilogue_text between", value1, value2, "epilogueText");
            return (Criteria) this;
        }

        public Criteria andEpilogueTextNotBetween(String value1, String value2) {
            addCriterion("epilogue_text not between", value1, value2, "epilogueText");
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