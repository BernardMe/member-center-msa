package com.cheshun.entity.plantation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlantationUserTaskDTOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlantationUserTaskDTOExample() {
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

        public Criteria andUserTaskIdIsNull() {
            addCriterion("user_task_id is null");
            return (Criteria) this;
        }

        public Criteria andUserTaskIdIsNotNull() {
            addCriterion("user_task_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserTaskIdEqualTo(Integer value) {
            addCriterion("user_task_id =", value, "userTaskId");
            return (Criteria) this;
        }

        public Criteria andUserTaskIdNotEqualTo(Integer value) {
            addCriterion("user_task_id <>", value, "userTaskId");
            return (Criteria) this;
        }

        public Criteria andUserTaskIdGreaterThan(Integer value) {
            addCriterion("user_task_id >", value, "userTaskId");
            return (Criteria) this;
        }

        public Criteria andUserTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_task_id >=", value, "userTaskId");
            return (Criteria) this;
        }

        public Criteria andUserTaskIdLessThan(Integer value) {
            addCriterion("user_task_id <", value, "userTaskId");
            return (Criteria) this;
        }

        public Criteria andUserTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_task_id <=", value, "userTaskId");
            return (Criteria) this;
        }

        public Criteria andUserTaskIdIn(List<Integer> values) {
            addCriterion("user_task_id in", values, "userTaskId");
            return (Criteria) this;
        }

        public Criteria andUserTaskIdNotIn(List<Integer> values) {
            addCriterion("user_task_id not in", values, "userTaskId");
            return (Criteria) this;
        }

        public Criteria andUserTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("user_task_id between", value1, value2, "userTaskId");
            return (Criteria) this;
        }

        public Criteria andUserTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_task_id not between", value1, value2, "userTaskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(Integer value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(Integer value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(Integer value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(Integer value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(Integer value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<Integer> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<Integer> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(Integer value1, Integer value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(Integer value1, Integer value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("open_id is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("open_id is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("open_id =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("open_id <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("open_id >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("open_id >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("open_id <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("open_id <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("open_id like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("open_id not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("open_id in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("open_id not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("open_id between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("open_id not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andTaskTipsIsNull() {
            addCriterion("task_tips is null");
            return (Criteria) this;
        }

        public Criteria andTaskTipsIsNotNull() {
            addCriterion("task_tips is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTipsEqualTo(String value) {
            addCriterion("task_tips =", value, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTipsNotEqualTo(String value) {
            addCriterion("task_tips <>", value, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTipsGreaterThan(String value) {
            addCriterion("task_tips >", value, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTipsGreaterThanOrEqualTo(String value) {
            addCriterion("task_tips >=", value, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTipsLessThan(String value) {
            addCriterion("task_tips <", value, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTipsLessThanOrEqualTo(String value) {
            addCriterion("task_tips <=", value, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTipsLike(String value) {
            addCriterion("task_tips like", value, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTipsNotLike(String value) {
            addCriterion("task_tips not like", value, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTipsIn(List<String> values) {
            addCriterion("task_tips in", values, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTipsNotIn(List<String> values) {
            addCriterion("task_tips not in", values, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTipsBetween(String value1, String value2) {
            addCriterion("task_tips between", value1, value2, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTipsNotBetween(String value1, String value2) {
            addCriterion("task_tips not between", value1, value2, "taskTips");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNull() {
            addCriterion("task_type is null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIsNotNull() {
            addCriterion("task_type is not null");
            return (Criteria) this;
        }

        public Criteria andTaskTypeEqualTo(Byte value) {
            addCriterion("task_type =", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotEqualTo(Byte value) {
            addCriterion("task_type <>", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThan(Byte value) {
            addCriterion("task_type >", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("task_type >=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThan(Byte value) {
            addCriterion("task_type <", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeLessThanOrEqualTo(Byte value) {
            addCriterion("task_type <=", value, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeIn(List<Byte> values) {
            addCriterion("task_type in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotIn(List<Byte> values) {
            addCriterion("task_type not in", values, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeBetween(Byte value1, Byte value2) {
            addCriterion("task_type between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("task_type not between", value1, value2, "taskType");
            return (Criteria) this;
        }

        public Criteria andTaskClassIsNull() {
            addCriterion("task_class is null");
            return (Criteria) this;
        }

        public Criteria andTaskClassIsNotNull() {
            addCriterion("task_class is not null");
            return (Criteria) this;
        }

        public Criteria andTaskClassEqualTo(Byte value) {
            addCriterion("task_class =", value, "taskClass");
            return (Criteria) this;
        }

        public Criteria andTaskClassNotEqualTo(Byte value) {
            addCriterion("task_class <>", value, "taskClass");
            return (Criteria) this;
        }

        public Criteria andTaskClassGreaterThan(Byte value) {
            addCriterion("task_class >", value, "taskClass");
            return (Criteria) this;
        }

        public Criteria andTaskClassGreaterThanOrEqualTo(Byte value) {
            addCriterion("task_class >=", value, "taskClass");
            return (Criteria) this;
        }

        public Criteria andTaskClassLessThan(Byte value) {
            addCriterion("task_class <", value, "taskClass");
            return (Criteria) this;
        }

        public Criteria andTaskClassLessThanOrEqualTo(Byte value) {
            addCriterion("task_class <=", value, "taskClass");
            return (Criteria) this;
        }

        public Criteria andTaskClassIn(List<Byte> values) {
            addCriterion("task_class in", values, "taskClass");
            return (Criteria) this;
        }

        public Criteria andTaskClassNotIn(List<Byte> values) {
            addCriterion("task_class not in", values, "taskClass");
            return (Criteria) this;
        }

        public Criteria andTaskClassBetween(Byte value1, Byte value2) {
            addCriterion("task_class between", value1, value2, "taskClass");
            return (Criteria) this;
        }

        public Criteria andTaskClassNotBetween(Byte value1, Byte value2) {
            addCriterion("task_class not between", value1, value2, "taskClass");
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

        public Criteria andIssueTypeIsNull() {
            addCriterion("issue_type is null");
            return (Criteria) this;
        }

        public Criteria andIssueTypeIsNotNull() {
            addCriterion("issue_type is not null");
            return (Criteria) this;
        }

        public Criteria andIssueTypeEqualTo(Byte value) {
            addCriterion("issue_type =", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeNotEqualTo(Byte value) {
            addCriterion("issue_type <>", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeGreaterThan(Byte value) {
            addCriterion("issue_type >", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("issue_type >=", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeLessThan(Byte value) {
            addCriterion("issue_type <", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeLessThanOrEqualTo(Byte value) {
            addCriterion("issue_type <=", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeIn(List<Byte> values) {
            addCriterion("issue_type in", values, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeNotIn(List<Byte> values) {
            addCriterion("issue_type not in", values, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeBetween(Byte value1, Byte value2) {
            addCriterion("issue_type between", value1, value2, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("issue_type not between", value1, value2, "issueType");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberIsNull() {
            addCriterion("time_drip_number is null");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberIsNotNull() {
            addCriterion("time_drip_number is not null");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberEqualTo(Byte value) {
            addCriterion("time_drip_number =", value, "timeDripNumber");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberNotEqualTo(Byte value) {
            addCriterion("time_drip_number <>", value, "timeDripNumber");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberGreaterThan(Byte value) {
            addCriterion("time_drip_number >", value, "timeDripNumber");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberGreaterThanOrEqualTo(Byte value) {
            addCriterion("time_drip_number >=", value, "timeDripNumber");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberLessThan(Byte value) {
            addCriterion("time_drip_number <", value, "timeDripNumber");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberLessThanOrEqualTo(Byte value) {
            addCriterion("time_drip_number <=", value, "timeDripNumber");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberIn(List<Byte> values) {
            addCriterion("time_drip_number in", values, "timeDripNumber");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberNotIn(List<Byte> values) {
            addCriterion("time_drip_number not in", values, "timeDripNumber");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberBetween(Byte value1, Byte value2) {
            addCriterion("time_drip_number between", value1, value2, "timeDripNumber");
            return (Criteria) this;
        }

        public Criteria andTimeDripNumberNotBetween(Byte value1, Byte value2) {
            addCriterion("time_drip_number not between", value1, value2, "timeDripNumber");
            return (Criteria) this;
        }

        public Criteria andCompleteNumIsNull() {
            addCriterion("complete_num is null");
            return (Criteria) this;
        }

        public Criteria andCompleteNumIsNotNull() {
            addCriterion("complete_num is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteNumEqualTo(Byte value) {
            addCriterion("complete_num =", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotEqualTo(Byte value) {
            addCriterion("complete_num <>", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumGreaterThan(Byte value) {
            addCriterion("complete_num >", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumGreaterThanOrEqualTo(Byte value) {
            addCriterion("complete_num >=", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumLessThan(Byte value) {
            addCriterion("complete_num <", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumLessThanOrEqualTo(Byte value) {
            addCriterion("complete_num <=", value, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumIn(List<Byte> values) {
            addCriterion("complete_num in", values, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotIn(List<Byte> values) {
            addCriterion("complete_num not in", values, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumBetween(Byte value1, Byte value2) {
            addCriterion("complete_num between", value1, value2, "completeNum");
            return (Criteria) this;
        }

        public Criteria andCompleteNumNotBetween(Byte value1, Byte value2) {
            addCriterion("complete_num not between", value1, value2, "completeNum");
            return (Criteria) this;
        }

        public Criteria andAwardDripIsNull() {
            addCriterion("award_drip is null");
            return (Criteria) this;
        }

        public Criteria andAwardDripIsNotNull() {
            addCriterion("award_drip is not null");
            return (Criteria) this;
        }

        public Criteria andAwardDripEqualTo(Byte value) {
            addCriterion("award_drip =", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripNotEqualTo(Byte value) {
            addCriterion("award_drip <>", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripGreaterThan(Byte value) {
            addCriterion("award_drip >", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripGreaterThanOrEqualTo(Byte value) {
            addCriterion("award_drip >=", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripLessThan(Byte value) {
            addCriterion("award_drip <", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripLessThanOrEqualTo(Byte value) {
            addCriterion("award_drip <=", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripIn(List<Byte> values) {
            addCriterion("award_drip in", values, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripNotIn(List<Byte> values) {
            addCriterion("award_drip not in", values, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripBetween(Byte value1, Byte value2) {
            addCriterion("award_drip between", value1, value2, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripNotBetween(Byte value1, Byte value2) {
            addCriterion("award_drip not between", value1, value2, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Byte value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Byte value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Byte value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Byte value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Byte value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Byte> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Byte> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Byte value1, Byte value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Byte value1, Byte value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
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