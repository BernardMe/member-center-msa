package com.cheshun.entity.plantation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PlantationHerbStageDTOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PlantationHerbStageDTOExample() {
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

        public Criteria andStageNameIsNull() {
            addCriterion("stage_name is null");
            return (Criteria) this;
        }

        public Criteria andStageNameIsNotNull() {
            addCriterion("stage_name is not null");
            return (Criteria) this;
        }

        public Criteria andStageNameEqualTo(String value) {
            addCriterion("stage_name =", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotEqualTo(String value) {
            addCriterion("stage_name <>", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameGreaterThan(String value) {
            addCriterion("stage_name >", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameGreaterThanOrEqualTo(String value) {
            addCriterion("stage_name >=", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLessThan(String value) {
            addCriterion("stage_name <", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLessThanOrEqualTo(String value) {
            addCriterion("stage_name <=", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameLike(String value) {
            addCriterion("stage_name like", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotLike(String value) {
            addCriterion("stage_name not like", value, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameIn(List<String> values) {
            addCriterion("stage_name in", values, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotIn(List<String> values) {
            addCriterion("stage_name not in", values, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameBetween(String value1, String value2) {
            addCriterion("stage_name between", value1, value2, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageNameNotBetween(String value1, String value2) {
            addCriterion("stage_name not between", value1, value2, "stageName");
            return (Criteria) this;
        }

        public Criteria andStageImageIsNull() {
            addCriterion("stage_image is null");
            return (Criteria) this;
        }

        public Criteria andStageImageIsNotNull() {
            addCriterion("stage_image is not null");
            return (Criteria) this;
        }

        public Criteria andStageImageEqualTo(String value) {
            addCriterion("stage_image =", value, "stageImage");
            return (Criteria) this;
        }

        public Criteria andStageImageNotEqualTo(String value) {
            addCriterion("stage_image <>", value, "stageImage");
            return (Criteria) this;
        }

        public Criteria andStageImageGreaterThan(String value) {
            addCriterion("stage_image >", value, "stageImage");
            return (Criteria) this;
        }

        public Criteria andStageImageGreaterThanOrEqualTo(String value) {
            addCriterion("stage_image >=", value, "stageImage");
            return (Criteria) this;
        }

        public Criteria andStageImageLessThan(String value) {
            addCriterion("stage_image <", value, "stageImage");
            return (Criteria) this;
        }

        public Criteria andStageImageLessThanOrEqualTo(String value) {
            addCriterion("stage_image <=", value, "stageImage");
            return (Criteria) this;
        }

        public Criteria andStageImageLike(String value) {
            addCriterion("stage_image like", value, "stageImage");
            return (Criteria) this;
        }

        public Criteria andStageImageNotLike(String value) {
            addCriterion("stage_image not like", value, "stageImage");
            return (Criteria) this;
        }

        public Criteria andStageImageIn(List<String> values) {
            addCriterion("stage_image in", values, "stageImage");
            return (Criteria) this;
        }

        public Criteria andStageImageNotIn(List<String> values) {
            addCriterion("stage_image not in", values, "stageImage");
            return (Criteria) this;
        }

        public Criteria andStageImageBetween(String value1, String value2) {
            addCriterion("stage_image between", value1, value2, "stageImage");
            return (Criteria) this;
        }

        public Criteria andStageImageNotBetween(String value1, String value2) {
            addCriterion("stage_image not between", value1, value2, "stageImage");
            return (Criteria) this;
        }

        public Criteria andDripCapacityIsNull() {
            addCriterion("drip_capacity is null");
            return (Criteria) this;
        }

        public Criteria andDripCapacityIsNotNull() {
            addCriterion("drip_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andDripCapacityEqualTo(Short value) {
            addCriterion("drip_capacity =", value, "dripCapacity");
            return (Criteria) this;
        }

        public Criteria andDripCapacityNotEqualTo(Short value) {
            addCriterion("drip_capacity <>", value, "dripCapacity");
            return (Criteria) this;
        }

        public Criteria andDripCapacityGreaterThan(Short value) {
            addCriterion("drip_capacity >", value, "dripCapacity");
            return (Criteria) this;
        }

        public Criteria andDripCapacityGreaterThanOrEqualTo(Short value) {
            addCriterion("drip_capacity >=", value, "dripCapacity");
            return (Criteria) this;
        }

        public Criteria andDripCapacityLessThan(Short value) {
            addCriterion("drip_capacity <", value, "dripCapacity");
            return (Criteria) this;
        }

        public Criteria andDripCapacityLessThanOrEqualTo(Short value) {
            addCriterion("drip_capacity <=", value, "dripCapacity");
            return (Criteria) this;
        }

        public Criteria andDripCapacityIn(List<Short> values) {
            addCriterion("drip_capacity in", values, "dripCapacity");
            return (Criteria) this;
        }

        public Criteria andDripCapacityNotIn(List<Short> values) {
            addCriterion("drip_capacity not in", values, "dripCapacity");
            return (Criteria) this;
        }

        public Criteria andDripCapacityBetween(Short value1, Short value2) {
            addCriterion("drip_capacity between", value1, value2, "dripCapacity");
            return (Criteria) this;
        }

        public Criteria andDripCapacityNotBetween(Short value1, Short value2) {
            addCriterion("drip_capacity not between", value1, value2, "dripCapacity");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityIsNull() {
            addCriterion("water_time_capacity is null");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityIsNotNull() {
            addCriterion("water_time_capacity is not null");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityEqualTo(Short value) {
            addCriterion("water_time_capacity =", value, "waterTimeCapacity");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityNotEqualTo(Short value) {
            addCriterion("water_time_capacity <>", value, "waterTimeCapacity");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityGreaterThan(Short value) {
            addCriterion("water_time_capacity >", value, "waterTimeCapacity");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityGreaterThanOrEqualTo(Short value) {
            addCriterion("water_time_capacity >=", value, "waterTimeCapacity");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityLessThan(Short value) {
            addCriterion("water_time_capacity <", value, "waterTimeCapacity");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityLessThanOrEqualTo(Short value) {
            addCriterion("water_time_capacity <=", value, "waterTimeCapacity");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityIn(List<Short> values) {
            addCriterion("water_time_capacity in", values, "waterTimeCapacity");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityNotIn(List<Short> values) {
            addCriterion("water_time_capacity not in", values, "waterTimeCapacity");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityBetween(Short value1, Short value2) {
            addCriterion("water_time_capacity between", value1, value2, "waterTimeCapacity");
            return (Criteria) this;
        }

        public Criteria andWaterTimeCapacityNotBetween(Short value1, Short value2) {
            addCriterion("water_time_capacity not between", value1, value2, "waterTimeCapacity");
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

        public Criteria andRewardIdIsNull() {
            addCriterion("reward_id is null");
            return (Criteria) this;
        }

        public Criteria andRewardIdIsNotNull() {
            addCriterion("reward_id is not null");
            return (Criteria) this;
        }

        public Criteria andRewardIdEqualTo(Long value) {
            addCriterion("reward_id =", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdNotEqualTo(Long value) {
            addCriterion("reward_id <>", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdGreaterThan(Long value) {
            addCriterion("reward_id >", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdGreaterThanOrEqualTo(Long value) {
            addCriterion("reward_id >=", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdLessThan(Long value) {
            addCriterion("reward_id <", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdLessThanOrEqualTo(Long value) {
            addCriterion("reward_id <=", value, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdIn(List<Long> values) {
            addCriterion("reward_id in", values, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdNotIn(List<Long> values) {
            addCriterion("reward_id not in", values, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdBetween(Long value1, Long value2) {
            addCriterion("reward_id between", value1, value2, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardIdNotBetween(Long value1, Long value2) {
            addCriterion("reward_id not between", value1, value2, "rewardId");
            return (Criteria) this;
        }

        public Criteria andRewardTypeIsNull() {
            addCriterion("reward_type is null");
            return (Criteria) this;
        }

        public Criteria andRewardTypeIsNotNull() {
            addCriterion("reward_type is not null");
            return (Criteria) this;
        }

        public Criteria andRewardTypeEqualTo(Byte value) {
            addCriterion("reward_type =", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeNotEqualTo(Byte value) {
            addCriterion("reward_type <>", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeGreaterThan(Byte value) {
            addCriterion("reward_type >", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("reward_type >=", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeLessThan(Byte value) {
            addCriterion("reward_type <", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeLessThanOrEqualTo(Byte value) {
            addCriterion("reward_type <=", value, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeIn(List<Byte> values) {
            addCriterion("reward_type in", values, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeNotIn(List<Byte> values) {
            addCriterion("reward_type not in", values, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeBetween(Byte value1, Byte value2) {
            addCriterion("reward_type between", value1, value2, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("reward_type not between", value1, value2, "rewardType");
            return (Criteria) this;
        }

        public Criteria andRewardNameIsNull() {
            addCriterion("reward_name is null");
            return (Criteria) this;
        }

        public Criteria andRewardNameIsNotNull() {
            addCriterion("reward_name is not null");
            return (Criteria) this;
        }

        public Criteria andRewardNameEqualTo(String value) {
            addCriterion("reward_name =", value, "rewardName");
            return (Criteria) this;
        }

        public Criteria andRewardNameNotEqualTo(String value) {
            addCriterion("reward_name <>", value, "rewardName");
            return (Criteria) this;
        }

        public Criteria andRewardNameGreaterThan(String value) {
            addCriterion("reward_name >", value, "rewardName");
            return (Criteria) this;
        }

        public Criteria andRewardNameGreaterThanOrEqualTo(String value) {
            addCriterion("reward_name >=", value, "rewardName");
            return (Criteria) this;
        }

        public Criteria andRewardNameLessThan(String value) {
            addCriterion("reward_name <", value, "rewardName");
            return (Criteria) this;
        }

        public Criteria andRewardNameLessThanOrEqualTo(String value) {
            addCriterion("reward_name <=", value, "rewardName");
            return (Criteria) this;
        }

        public Criteria andRewardNameLike(String value) {
            addCriterion("reward_name like", value, "rewardName");
            return (Criteria) this;
        }

        public Criteria andRewardNameNotLike(String value) {
            addCriterion("reward_name not like", value, "rewardName");
            return (Criteria) this;
        }

        public Criteria andRewardNameIn(List<String> values) {
            addCriterion("reward_name in", values, "rewardName");
            return (Criteria) this;
        }

        public Criteria andRewardNameNotIn(List<String> values) {
            addCriterion("reward_name not in", values, "rewardName");
            return (Criteria) this;
        }

        public Criteria andRewardNameBetween(String value1, String value2) {
            addCriterion("reward_name between", value1, value2, "rewardName");
            return (Criteria) this;
        }

        public Criteria andRewardNameNotBetween(String value1, String value2) {
            addCriterion("reward_name not between", value1, value2, "rewardName");
            return (Criteria) this;
        }

        public Criteria andStageSortIsNull() {
            addCriterion("stage_sort is null");
            return (Criteria) this;
        }

        public Criteria andStageSortIsNotNull() {
            addCriterion("stage_sort is not null");
            return (Criteria) this;
        }

        public Criteria andStageSortEqualTo(Integer value) {
            addCriterion("stage_sort =", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortNotEqualTo(Integer value) {
            addCriterion("stage_sort <>", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortGreaterThan(Integer value) {
            addCriterion("stage_sort >", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("stage_sort >=", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortLessThan(Integer value) {
            addCriterion("stage_sort <", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortLessThanOrEqualTo(Integer value) {
            addCriterion("stage_sort <=", value, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortIn(List<Integer> values) {
            addCriterion("stage_sort in", values, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortNotIn(List<Integer> values) {
            addCriterion("stage_sort not in", values, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortBetween(Integer value1, Integer value2) {
            addCriterion("stage_sort between", value1, value2, "stageSort");
            return (Criteria) this;
        }

        public Criteria andStageSortNotBetween(Integer value1, Integer value2) {
            addCriterion("stage_sort not between", value1, value2, "stageSort");
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