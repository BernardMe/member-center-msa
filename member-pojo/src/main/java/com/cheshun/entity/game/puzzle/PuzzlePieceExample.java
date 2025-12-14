package com.cheshun.entity.game.puzzle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PuzzlePieceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PuzzlePieceExample() {
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

        public Criteria andPieceIdIsNull() {
            addCriterion("piece_id is null");
            return (Criteria) this;
        }

        public Criteria andPieceIdIsNotNull() {
            addCriterion("piece_id is not null");
            return (Criteria) this;
        }

        public Criteria andPieceIdEqualTo(Integer value) {
            addCriterion("piece_id =", value, "pieceId");
            return (Criteria) this;
        }

        public Criteria andPieceIdNotEqualTo(Integer value) {
            addCriterion("piece_id <>", value, "pieceId");
            return (Criteria) this;
        }

        public Criteria andPieceIdGreaterThan(Integer value) {
            addCriterion("piece_id >", value, "pieceId");
            return (Criteria) this;
        }

        public Criteria andPieceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("piece_id >=", value, "pieceId");
            return (Criteria) this;
        }

        public Criteria andPieceIdLessThan(Integer value) {
            addCriterion("piece_id <", value, "pieceId");
            return (Criteria) this;
        }

        public Criteria andPieceIdLessThanOrEqualTo(Integer value) {
            addCriterion("piece_id <=", value, "pieceId");
            return (Criteria) this;
        }

        public Criteria andPieceIdIn(List<Integer> values) {
            addCriterion("piece_id in", values, "pieceId");
            return (Criteria) this;
        }

        public Criteria andPieceIdNotIn(List<Integer> values) {
            addCriterion("piece_id not in", values, "pieceId");
            return (Criteria) this;
        }

        public Criteria andPieceIdBetween(Integer value1, Integer value2) {
            addCriterion("piece_id between", value1, value2, "pieceId");
            return (Criteria) this;
        }

        public Criteria andPieceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("piece_id not between", value1, value2, "pieceId");
            return (Criteria) this;
        }

        public Criteria andCoverIdIsNull() {
            addCriterion("cover_id is null");
            return (Criteria) this;
        }

        public Criteria andCoverIdIsNotNull() {
            addCriterion("cover_id is not null");
            return (Criteria) this;
        }

        public Criteria andCoverIdEqualTo(Integer value) {
            addCriterion("cover_id =", value, "coverId");
            return (Criteria) this;
        }

        public Criteria andCoverIdNotEqualTo(Integer value) {
            addCriterion("cover_id <>", value, "coverId");
            return (Criteria) this;
        }

        public Criteria andCoverIdGreaterThan(Integer value) {
            addCriterion("cover_id >", value, "coverId");
            return (Criteria) this;
        }

        public Criteria andCoverIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cover_id >=", value, "coverId");
            return (Criteria) this;
        }

        public Criteria andCoverIdLessThan(Integer value) {
            addCriterion("cover_id <", value, "coverId");
            return (Criteria) this;
        }

        public Criteria andCoverIdLessThanOrEqualTo(Integer value) {
            addCriterion("cover_id <=", value, "coverId");
            return (Criteria) this;
        }

        public Criteria andCoverIdIn(List<Integer> values) {
            addCriterion("cover_id in", values, "coverId");
            return (Criteria) this;
        }

        public Criteria andCoverIdNotIn(List<Integer> values) {
            addCriterion("cover_id not in", values, "coverId");
            return (Criteria) this;
        }

        public Criteria andCoverIdBetween(Integer value1, Integer value2) {
            addCriterion("cover_id between", value1, value2, "coverId");
            return (Criteria) this;
        }

        public Criteria andCoverIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cover_id not between", value1, value2, "coverId");
            return (Criteria) this;
        }

        public Criteria andPieceNameIsNull() {
            addCriterion("piece_name is null");
            return (Criteria) this;
        }

        public Criteria andPieceNameIsNotNull() {
            addCriterion("piece_name is not null");
            return (Criteria) this;
        }

        public Criteria andPieceNameEqualTo(String value) {
            addCriterion("piece_name =", value, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceNameNotEqualTo(String value) {
            addCriterion("piece_name <>", value, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceNameGreaterThan(String value) {
            addCriterion("piece_name >", value, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceNameGreaterThanOrEqualTo(String value) {
            addCriterion("piece_name >=", value, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceNameLessThan(String value) {
            addCriterion("piece_name <", value, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceNameLessThanOrEqualTo(String value) {
            addCriterion("piece_name <=", value, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceNameLike(String value) {
            addCriterion("piece_name like", value, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceNameNotLike(String value) {
            addCriterion("piece_name not like", value, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceNameIn(List<String> values) {
            addCriterion("piece_name in", values, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceNameNotIn(List<String> values) {
            addCriterion("piece_name not in", values, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceNameBetween(String value1, String value2) {
            addCriterion("piece_name between", value1, value2, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceNameNotBetween(String value1, String value2) {
            addCriterion("piece_name not between", value1, value2, "pieceName");
            return (Criteria) this;
        }

        public Criteria andPieceUrlIsNull() {
            addCriterion("piece_url is null");
            return (Criteria) this;
        }

        public Criteria andPieceUrlIsNotNull() {
            addCriterion("piece_url is not null");
            return (Criteria) this;
        }

        public Criteria andPieceUrlEqualTo(String value) {
            addCriterion("piece_url =", value, "pieceUrl");
            return (Criteria) this;
        }

        public Criteria andPieceUrlNotEqualTo(String value) {
            addCriterion("piece_url <>", value, "pieceUrl");
            return (Criteria) this;
        }

        public Criteria andPieceUrlGreaterThan(String value) {
            addCriterion("piece_url >", value, "pieceUrl");
            return (Criteria) this;
        }

        public Criteria andPieceUrlGreaterThanOrEqualTo(String value) {
            addCriterion("piece_url >=", value, "pieceUrl");
            return (Criteria) this;
        }

        public Criteria andPieceUrlLessThan(String value) {
            addCriterion("piece_url <", value, "pieceUrl");
            return (Criteria) this;
        }

        public Criteria andPieceUrlLessThanOrEqualTo(String value) {
            addCriterion("piece_url <=", value, "pieceUrl");
            return (Criteria) this;
        }

        public Criteria andPieceUrlLike(String value) {
            addCriterion("piece_url like", value, "pieceUrl");
            return (Criteria) this;
        }

        public Criteria andPieceUrlNotLike(String value) {
            addCriterion("piece_url not like", value, "pieceUrl");
            return (Criteria) this;
        }

        public Criteria andPieceUrlIn(List<String> values) {
            addCriterion("piece_url in", values, "pieceUrl");
            return (Criteria) this;
        }

        public Criteria andPieceUrlNotIn(List<String> values) {
            addCriterion("piece_url not in", values, "pieceUrl");
            return (Criteria) this;
        }

        public Criteria andPieceUrlBetween(String value1, String value2) {
            addCriterion("piece_url between", value1, value2, "pieceUrl");
            return (Criteria) this;
        }

        public Criteria andPieceUrlNotBetween(String value1, String value2) {
            addCriterion("piece_url not between", value1, value2, "pieceUrl");
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

        public Criteria andSortEqualTo(Byte value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Byte value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Byte value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Byte value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Byte value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Byte value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Byte> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Byte> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Byte value1, Byte value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Byte value1, Byte value2) {
            addCriterion("sort not between", value1, value2, "sort");
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