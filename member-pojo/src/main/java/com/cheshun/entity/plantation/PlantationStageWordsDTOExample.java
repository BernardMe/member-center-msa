package com.cheshun.entity.plantation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlantationStageWordsDTOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlantationStageWordsDTOExample() {
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

        public Criteria andWordsIdIsNull() {
            addCriterion("words_id is null");
            return (Criteria) this;
        }

        public Criteria andWordsIdIsNotNull() {
            addCriterion("words_id is not null");
            return (Criteria) this;
        }

        public Criteria andWordsIdEqualTo(Integer value) {
            addCriterion("words_id =", value, "wordsId");
            return (Criteria) this;
        }

        public Criteria andWordsIdNotEqualTo(Integer value) {
            addCriterion("words_id <>", value, "wordsId");
            return (Criteria) this;
        }

        public Criteria andWordsIdGreaterThan(Integer value) {
            addCriterion("words_id >", value, "wordsId");
            return (Criteria) this;
        }

        public Criteria andWordsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("words_id >=", value, "wordsId");
            return (Criteria) this;
        }

        public Criteria andWordsIdLessThan(Integer value) {
            addCriterion("words_id <", value, "wordsId");
            return (Criteria) this;
        }

        public Criteria andWordsIdLessThanOrEqualTo(Integer value) {
            addCriterion("words_id <=", value, "wordsId");
            return (Criteria) this;
        }

        public Criteria andWordsIdIn(List<Integer> values) {
            addCriterion("words_id in", values, "wordsId");
            return (Criteria) this;
        }

        public Criteria andWordsIdNotIn(List<Integer> values) {
            addCriterion("words_id not in", values, "wordsId");
            return (Criteria) this;
        }

        public Criteria andWordsIdBetween(Integer value1, Integer value2) {
            addCriterion("words_id between", value1, value2, "wordsId");
            return (Criteria) this;
        }

        public Criteria andWordsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("words_id not between", value1, value2, "wordsId");
            return (Criteria) this;
        }

        public Criteria andStageIdIsNull() {
            addCriterion("stage_id is null");
            return (Criteria) this;
        }

        public Criteria andStageIdIsNotNull() {
            addCriterion("stage_id is not null");
            return (Criteria) this;
        }

        public Criteria andStageIdEqualTo(Integer value) {
            addCriterion("stage_id =", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdNotEqualTo(Integer value) {
            addCriterion("stage_id <>", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdGreaterThan(Integer value) {
            addCriterion("stage_id >", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("stage_id >=", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdLessThan(Integer value) {
            addCriterion("stage_id <", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdLessThanOrEqualTo(Integer value) {
            addCriterion("stage_id <=", value, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdIn(List<Integer> values) {
            addCriterion("stage_id in", values, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdNotIn(List<Integer> values) {
            addCriterion("stage_id not in", values, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdBetween(Integer value1, Integer value2) {
            addCriterion("stage_id between", value1, value2, "stageId");
            return (Criteria) this;
        }

        public Criteria andStageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("stage_id not between", value1, value2, "stageId");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumIsNull() {
            addCriterion("used_drip_num is null");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumIsNotNull() {
            addCriterion("used_drip_num is not null");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumEqualTo(Short value) {
            addCriterion("used_drip_num =", value, "usedDripNum");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumNotEqualTo(Short value) {
            addCriterion("used_drip_num <>", value, "usedDripNum");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumGreaterThan(Short value) {
            addCriterion("used_drip_num >", value, "usedDripNum");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumGreaterThanOrEqualTo(Short value) {
            addCriterion("used_drip_num >=", value, "usedDripNum");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumLessThan(Short value) {
            addCriterion("used_drip_num <", value, "usedDripNum");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumLessThanOrEqualTo(Short value) {
            addCriterion("used_drip_num <=", value, "usedDripNum");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumIn(List<Short> values) {
            addCriterion("used_drip_num in", values, "usedDripNum");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumNotIn(List<Short> values) {
            addCriterion("used_drip_num not in", values, "usedDripNum");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumBetween(Short value1, Short value2) {
            addCriterion("used_drip_num between", value1, value2, "usedDripNum");
            return (Criteria) this;
        }

        public Criteria andUsedDripNumNotBetween(Short value1, Short value2) {
            addCriterion("used_drip_num not between", value1, value2, "usedDripNum");
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

        public Criteria andWordsTextIsNull() {
            addCriterion("words_text is null");
            return (Criteria) this;
        }

        public Criteria andWordsTextIsNotNull() {
            addCriterion("words_text is not null");
            return (Criteria) this;
        }

        public Criteria andWordsTextEqualTo(String value) {
            addCriterion("words_text =", value, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsTextNotEqualTo(String value) {
            addCriterion("words_text <>", value, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsTextGreaterThan(String value) {
            addCriterion("words_text >", value, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsTextGreaterThanOrEqualTo(String value) {
            addCriterion("words_text >=", value, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsTextLessThan(String value) {
            addCriterion("words_text <", value, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsTextLessThanOrEqualTo(String value) {
            addCriterion("words_text <=", value, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsTextLike(String value) {
            addCriterion("words_text like", value, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsTextNotLike(String value) {
            addCriterion("words_text not like", value, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsTextIn(List<String> values) {
            addCriterion("words_text in", values, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsTextNotIn(List<String> values) {
            addCriterion("words_text not in", values, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsTextBetween(String value1, String value2) {
            addCriterion("words_text between", value1, value2, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsTextNotBetween(String value1, String value2) {
            addCriterion("words_text not between", value1, value2, "wordsText");
            return (Criteria) this;
        }

        public Criteria andWordsSortIsNull() {
            addCriterion("words_sort is null");
            return (Criteria) this;
        }

        public Criteria andWordsSortIsNotNull() {
            addCriterion("words_sort is not null");
            return (Criteria) this;
        }

        public Criteria andWordsSortEqualTo(Integer value) {
            addCriterion("words_sort =", value, "wordsSort");
            return (Criteria) this;
        }

        public Criteria andWordsSortNotEqualTo(Integer value) {
            addCriterion("words_sort <>", value, "wordsSort");
            return (Criteria) this;
        }

        public Criteria andWordsSortGreaterThan(Integer value) {
            addCriterion("words_sort >", value, "wordsSort");
            return (Criteria) this;
        }

        public Criteria andWordsSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("words_sort >=", value, "wordsSort");
            return (Criteria) this;
        }

        public Criteria andWordsSortLessThan(Integer value) {
            addCriterion("words_sort <", value, "wordsSort");
            return (Criteria) this;
        }

        public Criteria andWordsSortLessThanOrEqualTo(Integer value) {
            addCriterion("words_sort <=", value, "wordsSort");
            return (Criteria) this;
        }

        public Criteria andWordsSortIn(List<Integer> values) {
            addCriterion("words_sort in", values, "wordsSort");
            return (Criteria) this;
        }

        public Criteria andWordsSortNotIn(List<Integer> values) {
            addCriterion("words_sort not in", values, "wordsSort");
            return (Criteria) this;
        }

        public Criteria andWordsSortBetween(Integer value1, Integer value2) {
            addCriterion("words_sort between", value1, value2, "wordsSort");
            return (Criteria) this;
        }

        public Criteria andWordsSortNotBetween(Integer value1, Integer value2) {
            addCriterion("words_sort not between", value1, value2, "wordsSort");
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