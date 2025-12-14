package com.cheshun.entity.plantation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlantationTaskDTOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlantationTaskDTOExample() {
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

        public Criteria andTaskClassNameIsNull() {
            addCriterion("task_class_name is null");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameIsNotNull() {
            addCriterion("task_class_name is not null");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameEqualTo(String value) {
            addCriterion("task_class_name =", value, "taskClassName");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameNotEqualTo(String value) {
            addCriterion("task_class_name <>", value, "taskClassName");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameGreaterThan(String value) {
            addCriterion("task_class_name >", value, "taskClassName");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("task_class_name >=", value, "taskClassName");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameLessThan(String value) {
            addCriterion("task_class_name <", value, "taskClassName");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameLessThanOrEqualTo(String value) {
            addCriterion("task_class_name <=", value, "taskClassName");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameLike(String value) {
            addCriterion("task_class_name like", value, "taskClassName");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameNotLike(String value) {
            addCriterion("task_class_name not like", value, "taskClassName");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameIn(List<String> values) {
            addCriterion("task_class_name in", values, "taskClassName");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameNotIn(List<String> values) {
            addCriterion("task_class_name not in", values, "taskClassName");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameBetween(String value1, String value2) {
            addCriterion("task_class_name between", value1, value2, "taskClassName");
            return (Criteria) this;
        }

        public Criteria andTaskClassNameNotBetween(String value1, String value2) {
            addCriterion("task_class_name not between", value1, value2, "taskClassName");
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

        public Criteria andAwardDripIsNull() {
            addCriterion("award_drip is null");
            return (Criteria) this;
        }

        public Criteria andAwardDripIsNotNull() {
            addCriterion("award_drip is not null");
            return (Criteria) this;
        }

        public Criteria andAwardDripEqualTo(Short value) {
            addCriterion("award_drip =", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripNotEqualTo(Short value) {
            addCriterion("award_drip <>", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripGreaterThan(Short value) {
            addCriterion("award_drip >", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripGreaterThanOrEqualTo(Short value) {
            addCriterion("award_drip >=", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripLessThan(Short value) {
            addCriterion("award_drip <", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripLessThanOrEqualTo(Short value) {
            addCriterion("award_drip <=", value, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripIn(List<Short> values) {
            addCriterion("award_drip in", values, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripNotIn(List<Short> values) {
            addCriterion("award_drip not in", values, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripBetween(Short value1, Short value2) {
            addCriterion("award_drip between", value1, value2, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andAwardDripNotBetween(Short value1, Short value2) {
            addCriterion("award_drip not between", value1, value2, "awardDrip");
            return (Criteria) this;
        }

        public Criteria andIconUrlIsNull() {
            addCriterion("icon_url is null");
            return (Criteria) this;
        }

        public Criteria andIconUrlIsNotNull() {
            addCriterion("icon_url is not null");
            return (Criteria) this;
        }

        public Criteria andIconUrlEqualTo(String value) {
            addCriterion("icon_url =", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlNotEqualTo(String value) {
            addCriterion("icon_url <>", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlGreaterThan(String value) {
            addCriterion("icon_url >", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlGreaterThanOrEqualTo(String value) {
            addCriterion("icon_url >=", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlLessThan(String value) {
            addCriterion("icon_url <", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlLessThanOrEqualTo(String value) {
            addCriterion("icon_url <=", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlLike(String value) {
            addCriterion("icon_url like", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlNotLike(String value) {
            addCriterion("icon_url not like", value, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlIn(List<String> values) {
            addCriterion("icon_url in", values, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlNotIn(List<String> values) {
            addCriterion("icon_url not in", values, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlBetween(String value1, String value2) {
            addCriterion("icon_url between", value1, value2, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andIconUrlNotBetween(String value1, String value2) {
            addCriterion("icon_url not between", value1, value2, "iconUrl");
            return (Criteria) this;
        }

        public Criteria andShowIconIsNull() {
            addCriterion("show_icon is null");
            return (Criteria) this;
        }

        public Criteria andShowIconIsNotNull() {
            addCriterion("show_icon is not null");
            return (Criteria) this;
        }

        public Criteria andShowIconEqualTo(Byte value) {
            addCriterion("show_icon =", value, "showIcon");
            return (Criteria) this;
        }

        public Criteria andShowIconNotEqualTo(Byte value) {
            addCriterion("show_icon <>", value, "showIcon");
            return (Criteria) this;
        }

        public Criteria andShowIconGreaterThan(Byte value) {
            addCriterion("show_icon >", value, "showIcon");
            return (Criteria) this;
        }

        public Criteria andShowIconGreaterThanOrEqualTo(Byte value) {
            addCriterion("show_icon >=", value, "showIcon");
            return (Criteria) this;
        }

        public Criteria andShowIconLessThan(Byte value) {
            addCriterion("show_icon <", value, "showIcon");
            return (Criteria) this;
        }

        public Criteria andShowIconLessThanOrEqualTo(Byte value) {
            addCriterion("show_icon <=", value, "showIcon");
            return (Criteria) this;
        }

        public Criteria andShowIconIn(List<Byte> values) {
            addCriterion("show_icon in", values, "showIcon");
            return (Criteria) this;
        }

        public Criteria andShowIconNotIn(List<Byte> values) {
            addCriterion("show_icon not in", values, "showIcon");
            return (Criteria) this;
        }

        public Criteria andShowIconBetween(Byte value1, Byte value2) {
            addCriterion("show_icon between", value1, value2, "showIcon");
            return (Criteria) this;
        }

        public Criteria andShowIconNotBetween(Byte value1, Byte value2) {
            addCriterion("show_icon not between", value1, value2, "showIcon");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameIsNull() {
            addCriterion("show_task_name is null");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameIsNotNull() {
            addCriterion("show_task_name is not null");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameEqualTo(String value) {
            addCriterion("show_task_name =", value, "showTaskName");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameNotEqualTo(String value) {
            addCriterion("show_task_name <>", value, "showTaskName");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameGreaterThan(String value) {
            addCriterion("show_task_name >", value, "showTaskName");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameGreaterThanOrEqualTo(String value) {
            addCriterion("show_task_name >=", value, "showTaskName");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameLessThan(String value) {
            addCriterion("show_task_name <", value, "showTaskName");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameLessThanOrEqualTo(String value) {
            addCriterion("show_task_name <=", value, "showTaskName");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameLike(String value) {
            addCriterion("show_task_name like", value, "showTaskName");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameNotLike(String value) {
            addCriterion("show_task_name not like", value, "showTaskName");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameIn(List<String> values) {
            addCriterion("show_task_name in", values, "showTaskName");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameNotIn(List<String> values) {
            addCriterion("show_task_name not in", values, "showTaskName");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameBetween(String value1, String value2) {
            addCriterion("show_task_name between", value1, value2, "showTaskName");
            return (Criteria) this;
        }

        public Criteria andShowTaskNameNotBetween(String value1, String value2) {
            addCriterion("show_task_name not between", value1, value2, "showTaskName");
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

        public Criteria andIsShortcutIsNull() {
            addCriterion("is_shortcut is null");
            return (Criteria) this;
        }

        public Criteria andIsShortcutIsNotNull() {
            addCriterion("is_shortcut is not null");
            return (Criteria) this;
        }

        public Criteria andIsShortcutEqualTo(Byte value) {
            addCriterion("is_shortcut =", value, "isShortcut");
            return (Criteria) this;
        }

        public Criteria andIsShortcutNotEqualTo(Byte value) {
            addCriterion("is_shortcut <>", value, "isShortcut");
            return (Criteria) this;
        }

        public Criteria andIsShortcutGreaterThan(Byte value) {
            addCriterion("is_shortcut >", value, "isShortcut");
            return (Criteria) this;
        }

        public Criteria andIsShortcutGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_shortcut >=", value, "isShortcut");
            return (Criteria) this;
        }

        public Criteria andIsShortcutLessThan(Byte value) {
            addCriterion("is_shortcut <", value, "isShortcut");
            return (Criteria) this;
        }

        public Criteria andIsShortcutLessThanOrEqualTo(Byte value) {
            addCriterion("is_shortcut <=", value, "isShortcut");
            return (Criteria) this;
        }

        public Criteria andIsShortcutIn(List<Byte> values) {
            addCriterion("is_shortcut in", values, "isShortcut");
            return (Criteria) this;
        }

        public Criteria andIsShortcutNotIn(List<Byte> values) {
            addCriterion("is_shortcut not in", values, "isShortcut");
            return (Criteria) this;
        }

        public Criteria andIsShortcutBetween(Byte value1, Byte value2) {
            addCriterion("is_shortcut between", value1, value2, "isShortcut");
            return (Criteria) this;
        }

        public Criteria andIsShortcutNotBetween(Byte value1, Byte value2) {
            addCriterion("is_shortcut not between", value1, value2, "isShortcut");
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