package com.cheshun.entity.plantation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlantationHerbDTOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlantationHerbDTOExample() {
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

        public Criteria andHerbIdIsNull() {
            addCriterion("herb_id is null");
            return (Criteria) this;
        }

        public Criteria andHerbIdIsNotNull() {
            addCriterion("herb_id is not null");
            return (Criteria) this;
        }

        public Criteria andHerbIdEqualTo(Integer value) {
            addCriterion("herb_id =", value, "herbId");
            return (Criteria) this;
        }

        public Criteria andHerbIdNotEqualTo(Integer value) {
            addCriterion("herb_id <>", value, "herbId");
            return (Criteria) this;
        }

        public Criteria andHerbIdGreaterThan(Integer value) {
            addCriterion("herb_id >", value, "herbId");
            return (Criteria) this;
        }

        public Criteria andHerbIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("herb_id >=", value, "herbId");
            return (Criteria) this;
        }

        public Criteria andHerbIdLessThan(Integer value) {
            addCriterion("herb_id <", value, "herbId");
            return (Criteria) this;
        }

        public Criteria andHerbIdLessThanOrEqualTo(Integer value) {
            addCriterion("herb_id <=", value, "herbId");
            return (Criteria) this;
        }

        public Criteria andHerbIdIn(List<Integer> values) {
            addCriterion("herb_id in", values, "herbId");
            return (Criteria) this;
        }

        public Criteria andHerbIdNotIn(List<Integer> values) {
            addCriterion("herb_id not in", values, "herbId");
            return (Criteria) this;
        }

        public Criteria andHerbIdBetween(Integer value1, Integer value2) {
            addCriterion("herb_id between", value1, value2, "herbId");
            return (Criteria) this;
        }

        public Criteria andHerbIdNotBetween(Integer value1, Integer value2) {
            addCriterion("herb_id not between", value1, value2, "herbId");
            return (Criteria) this;
        }

        public Criteria andHerbNameIsNull() {
            addCriterion("herb_name is null");
            return (Criteria) this;
        }

        public Criteria andHerbNameIsNotNull() {
            addCriterion("herb_name is not null");
            return (Criteria) this;
        }

        public Criteria andHerbNameEqualTo(String value) {
            addCriterion("herb_name =", value, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbNameNotEqualTo(String value) {
            addCriterion("herb_name <>", value, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbNameGreaterThan(String value) {
            addCriterion("herb_name >", value, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbNameGreaterThanOrEqualTo(String value) {
            addCriterion("herb_name >=", value, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbNameLessThan(String value) {
            addCriterion("herb_name <", value, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbNameLessThanOrEqualTo(String value) {
            addCriterion("herb_name <=", value, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbNameLike(String value) {
            addCriterion("herb_name like", value, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbNameNotLike(String value) {
            addCriterion("herb_name not like", value, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbNameIn(List<String> values) {
            addCriterion("herb_name in", values, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbNameNotIn(List<String> values) {
            addCriterion("herb_name not in", values, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbNameBetween(String value1, String value2) {
            addCriterion("herb_name between", value1, value2, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbNameNotBetween(String value1, String value2) {
            addCriterion("herb_name not between", value1, value2, "herbName");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlIsNull() {
            addCriterion("herb_img_url is null");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlIsNotNull() {
            addCriterion("herb_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlEqualTo(String value) {
            addCriterion("herb_img_url =", value, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlNotEqualTo(String value) {
            addCriterion("herb_img_url <>", value, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlGreaterThan(String value) {
            addCriterion("herb_img_url >", value, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("herb_img_url >=", value, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlLessThan(String value) {
            addCriterion("herb_img_url <", value, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlLessThanOrEqualTo(String value) {
            addCriterion("herb_img_url <=", value, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlLike(String value) {
            addCriterion("herb_img_url like", value, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlNotLike(String value) {
            addCriterion("herb_img_url not like", value, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlIn(List<String> values) {
            addCriterion("herb_img_url in", values, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlNotIn(List<String> values) {
            addCriterion("herb_img_url not in", values, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlBetween(String value1, String value2) {
            addCriterion("herb_img_url between", value1, value2, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgUrlNotBetween(String value1, String value2) {
            addCriterion("herb_img_url not between", value1, value2, "herbImgUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlIsNull() {
            addCriterion("herb_img_detail_url is null");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlIsNotNull() {
            addCriterion("herb_img_detail_url is not null");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlEqualTo(String value) {
            addCriterion("herb_img_detail_url =", value, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlNotEqualTo(String value) {
            addCriterion("herb_img_detail_url <>", value, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlGreaterThan(String value) {
            addCriterion("herb_img_detail_url >", value, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlGreaterThanOrEqualTo(String value) {
            addCriterion("herb_img_detail_url >=", value, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlLessThan(String value) {
            addCriterion("herb_img_detail_url <", value, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlLessThanOrEqualTo(String value) {
            addCriterion("herb_img_detail_url <=", value, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlLike(String value) {
            addCriterion("herb_img_detail_url like", value, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlNotLike(String value) {
            addCriterion("herb_img_detail_url not like", value, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlIn(List<String> values) {
            addCriterion("herb_img_detail_url in", values, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlNotIn(List<String> values) {
            addCriterion("herb_img_detail_url not in", values, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlBetween(String value1, String value2) {
            addCriterion("herb_img_detail_url between", value1, value2, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andHerbImgDetailUrlNotBetween(String value1, String value2) {
            addCriterion("herb_img_detail_url not between", value1, value2, "herbImgDetailUrl");
            return (Criteria) this;
        }

        public Criteria andStageNumIsNull() {
            addCriterion("stage_num is null");
            return (Criteria) this;
        }

        public Criteria andStageNumIsNotNull() {
            addCriterion("stage_num is not null");
            return (Criteria) this;
        }

        public Criteria andStageNumEqualTo(Byte value) {
            addCriterion("stage_num =", value, "stageNum");
            return (Criteria) this;
        }

        public Criteria andStageNumNotEqualTo(Byte value) {
            addCriterion("stage_num <>", value, "stageNum");
            return (Criteria) this;
        }

        public Criteria andStageNumGreaterThan(Byte value) {
            addCriterion("stage_num >", value, "stageNum");
            return (Criteria) this;
        }

        public Criteria andStageNumGreaterThanOrEqualTo(Byte value) {
            addCriterion("stage_num >=", value, "stageNum");
            return (Criteria) this;
        }

        public Criteria andStageNumLessThan(Byte value) {
            addCriterion("stage_num <", value, "stageNum");
            return (Criteria) this;
        }

        public Criteria andStageNumLessThanOrEqualTo(Byte value) {
            addCriterion("stage_num <=", value, "stageNum");
            return (Criteria) this;
        }

        public Criteria andStageNumIn(List<Byte> values) {
            addCriterion("stage_num in", values, "stageNum");
            return (Criteria) this;
        }

        public Criteria andStageNumNotIn(List<Byte> values) {
            addCriterion("stage_num not in", values, "stageNum");
            return (Criteria) this;
        }

        public Criteria andStageNumBetween(Byte value1, Byte value2) {
            addCriterion("stage_num between", value1, value2, "stageNum");
            return (Criteria) this;
        }

        public Criteria andStageNumNotBetween(Byte value1, Byte value2) {
            addCriterion("stage_num not between", value1, value2, "stageNum");
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

        public Criteria andHerbSortIsNull() {
            addCriterion("herb_sort is null");
            return (Criteria) this;
        }

        public Criteria andHerbSortIsNotNull() {
            addCriterion("herb_sort is not null");
            return (Criteria) this;
        }

        public Criteria andHerbSortEqualTo(Integer value) {
            addCriterion("herb_sort =", value, "herbSort");
            return (Criteria) this;
        }

        public Criteria andHerbSortNotEqualTo(Integer value) {
            addCriterion("herb_sort <>", value, "herbSort");
            return (Criteria) this;
        }

        public Criteria andHerbSortGreaterThan(Integer value) {
            addCriterion("herb_sort >", value, "herbSort");
            return (Criteria) this;
        }

        public Criteria andHerbSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("herb_sort >=", value, "herbSort");
            return (Criteria) this;
        }

        public Criteria andHerbSortLessThan(Integer value) {
            addCriterion("herb_sort <", value, "herbSort");
            return (Criteria) this;
        }

        public Criteria andHerbSortLessThanOrEqualTo(Integer value) {
            addCriterion("herb_sort <=", value, "herbSort");
            return (Criteria) this;
        }

        public Criteria andHerbSortIn(List<Integer> values) {
            addCriterion("herb_sort in", values, "herbSort");
            return (Criteria) this;
        }

        public Criteria andHerbSortNotIn(List<Integer> values) {
            addCriterion("herb_sort not in", values, "herbSort");
            return (Criteria) this;
        }

        public Criteria andHerbSortBetween(Integer value1, Integer value2) {
            addCriterion("herb_sort between", value1, value2, "herbSort");
            return (Criteria) this;
        }

        public Criteria andHerbSortNotBetween(Integer value1, Integer value2) {
            addCriterion("herb_sort not between", value1, value2, "herbSort");
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