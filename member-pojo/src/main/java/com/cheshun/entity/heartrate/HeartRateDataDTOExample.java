package com.cheshun.entity.heartrate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HeartRateDataDTOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HeartRateDataDTOExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andZxdReportIdIsNull() {
            addCriterion("zxd_report_id is null");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdIsNotNull() {
            addCriterion("zxd_report_id is not null");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdEqualTo(String value) {
            addCriterion("zxd_report_id =", value, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdNotEqualTo(String value) {
            addCriterion("zxd_report_id <>", value, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdGreaterThan(String value) {
            addCriterion("zxd_report_id >", value, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdGreaterThanOrEqualTo(String value) {
            addCriterion("zxd_report_id >=", value, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdLessThan(String value) {
            addCriterion("zxd_report_id <", value, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdLessThanOrEqualTo(String value) {
            addCriterion("zxd_report_id <=", value, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdLike(String value) {
            addCriterion("zxd_report_id like", value, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdNotLike(String value) {
            addCriterion("zxd_report_id not like", value, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdIn(List<String> values) {
            addCriterion("zxd_report_id in", values, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdNotIn(List<String> values) {
            addCriterion("zxd_report_id not in", values, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdBetween(String value1, String value2) {
            addCriterion("zxd_report_id between", value1, value2, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andZxdReportIdNotBetween(String value1, String value2) {
            addCriterion("zxd_report_id not between", value1, value2, "zxdReportId");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlIsNull() {
            addCriterion("ori_data_url is null");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlIsNotNull() {
            addCriterion("ori_data_url is not null");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlEqualTo(String value) {
            addCriterion("ori_data_url =", value, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlNotEqualTo(String value) {
            addCriterion("ori_data_url <>", value, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlGreaterThan(String value) {
            addCriterion("ori_data_url >", value, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlGreaterThanOrEqualTo(String value) {
            addCriterion("ori_data_url >=", value, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlLessThan(String value) {
            addCriterion("ori_data_url <", value, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlLessThanOrEqualTo(String value) {
            addCriterion("ori_data_url <=", value, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlLike(String value) {
            addCriterion("ori_data_url like", value, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlNotLike(String value) {
            addCriterion("ori_data_url not like", value, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlIn(List<String> values) {
            addCriterion("ori_data_url in", values, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlNotIn(List<String> values) {
            addCriterion("ori_data_url not in", values, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlBetween(String value1, String value2) {
            addCriterion("ori_data_url between", value1, value2, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andOriDataUrlNotBetween(String value1, String value2) {
            addCriterion("ori_data_url not between", value1, value2, "oriDataUrl");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNull() {
            addCriterion("device_type is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNotNull() {
            addCriterion("device_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeEqualTo(Byte value) {
            addCriterion("device_type =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(Byte value) {
            addCriterion("device_type <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(Byte value) {
            addCriterion("device_type >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("device_type >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(Byte value) {
            addCriterion("device_type <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(Byte value) {
            addCriterion("device_type <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<Byte> values) {
            addCriterion("device_type in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<Byte> values) {
            addCriterion("device_type not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(Byte value1, Byte value2) {
            addCriterion("device_type between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("device_type not between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNull() {
            addCriterion("device_name is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNotNull() {
            addCriterion("device_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameEqualTo(String value) {
            addCriterion("device_name =", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotEqualTo(String value) {
            addCriterion("device_name <>", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThan(String value) {
            addCriterion("device_name >", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThanOrEqualTo(String value) {
            addCriterion("device_name >=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThan(String value) {
            addCriterion("device_name <", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThanOrEqualTo(String value) {
            addCriterion("device_name <=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLike(String value) {
            addCriterion("device_name like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotLike(String value) {
            addCriterion("device_name not like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIn(List<String> values) {
            addCriterion("device_name in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotIn(List<String> values) {
            addCriterion("device_name not in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameBetween(String value1, String value2) {
            addCriterion("device_name between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotBetween(String value1, String value2) {
            addCriterion("device_name not between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceSnIsNull() {
            addCriterion("device_sn is null");
            return (Criteria) this;
        }

        public Criteria andDeviceSnIsNotNull() {
            addCriterion("device_sn is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceSnEqualTo(String value) {
            addCriterion("device_sn =", value, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andDeviceSnNotEqualTo(String value) {
            addCriterion("device_sn <>", value, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andDeviceSnGreaterThan(String value) {
            addCriterion("device_sn >", value, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andDeviceSnGreaterThanOrEqualTo(String value) {
            addCriterion("device_sn >=", value, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andDeviceSnLessThan(String value) {
            addCriterion("device_sn <", value, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andDeviceSnLessThanOrEqualTo(String value) {
            addCriterion("device_sn <=", value, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andDeviceSnLike(String value) {
            addCriterion("device_sn like", value, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andDeviceSnNotLike(String value) {
            addCriterion("device_sn not like", value, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andDeviceSnIn(List<String> values) {
            addCriterion("device_sn in", values, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andDeviceSnNotIn(List<String> values) {
            addCriterion("device_sn not in", values, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andDeviceSnBetween(String value1, String value2) {
            addCriterion("device_sn between", value1, value2, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andDeviceSnNotBetween(String value1, String value2) {
            addCriterion("device_sn not between", value1, value2, "deviceSn");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNull() {
            addCriterion("platform is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNotNull() {
            addCriterion("platform is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformEqualTo(Byte value) {
            addCriterion("platform =", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotEqualTo(Byte value) {
            addCriterion("platform <>", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThan(Byte value) {
            addCriterion("platform >", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThanOrEqualTo(Byte value) {
            addCriterion("platform >=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThan(Byte value) {
            addCriterion("platform <", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThanOrEqualTo(Byte value) {
            addCriterion("platform <=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformIn(List<Byte> values) {
            addCriterion("platform in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotIn(List<Byte> values) {
            addCriterion("platform not in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformBetween(Byte value1, Byte value2) {
            addCriterion("platform between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotBetween(Byte value1, Byte value2) {
            addCriterion("platform not between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andLeadCountIsNull() {
            addCriterion("lead_count is null");
            return (Criteria) this;
        }

        public Criteria andLeadCountIsNotNull() {
            addCriterion("lead_count is not null");
            return (Criteria) this;
        }

        public Criteria andLeadCountEqualTo(Byte value) {
            addCriterion("lead_count =", value, "leadCount");
            return (Criteria) this;
        }

        public Criteria andLeadCountNotEqualTo(Byte value) {
            addCriterion("lead_count <>", value, "leadCount");
            return (Criteria) this;
        }

        public Criteria andLeadCountGreaterThan(Byte value) {
            addCriterion("lead_count >", value, "leadCount");
            return (Criteria) this;
        }

        public Criteria andLeadCountGreaterThanOrEqualTo(Byte value) {
            addCriterion("lead_count >=", value, "leadCount");
            return (Criteria) this;
        }

        public Criteria andLeadCountLessThan(Byte value) {
            addCriterion("lead_count <", value, "leadCount");
            return (Criteria) this;
        }

        public Criteria andLeadCountLessThanOrEqualTo(Byte value) {
            addCriterion("lead_count <=", value, "leadCount");
            return (Criteria) this;
        }

        public Criteria andLeadCountIn(List<Byte> values) {
            addCriterion("lead_count in", values, "leadCount");
            return (Criteria) this;
        }

        public Criteria andLeadCountNotIn(List<Byte> values) {
            addCriterion("lead_count not in", values, "leadCount");
            return (Criteria) this;
        }

        public Criteria andLeadCountBetween(Byte value1, Byte value2) {
            addCriterion("lead_count between", value1, value2, "leadCount");
            return (Criteria) this;
        }

        public Criteria andLeadCountNotBetween(Byte value1, Byte value2) {
            addCriterion("lead_count not between", value1, value2, "leadCount");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(Integer value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(Integer value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(Integer value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(Integer value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(Integer value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<Integer> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<Integer> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(Integer value1, Integer value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("duration not between", value1, value2, "duration");
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