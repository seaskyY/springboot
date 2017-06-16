package com.tc.pes.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceptionEvaluateCriteria extends PageCriteria {
    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReceptionEvaluateCriteria() {
        oredCriteria = new ArrayList<Criteria>();
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

        public Criteria andReceptionEvaluateIdIsNull() {
            addCriterion("reception_evaluate_id is null");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateIdIsNotNull() {
            addCriterion("reception_evaluate_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateIdEqualTo(Long value) {
            addCriterion("reception_evaluate_id =", value, "receptionEvaluateId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateIdNotEqualTo(Long value) {
            addCriterion("reception_evaluate_id <>", value, "receptionEvaluateId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateIdGreaterThan(Long value) {
            addCriterion("reception_evaluate_id >", value, "receptionEvaluateId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("reception_evaluate_id >=", value, "receptionEvaluateId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateIdLessThan(Long value) {
            addCriterion("reception_evaluate_id <", value, "receptionEvaluateId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateIdLessThanOrEqualTo(Long value) {
            addCriterion("reception_evaluate_id <=", value, "receptionEvaluateId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateIdIn(List<Long> values) {
            addCriterion("reception_evaluate_id in", values, "receptionEvaluateId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateIdNotIn(List<Long> values) {
            addCriterion("reception_evaluate_id not in", values, "receptionEvaluateId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateIdBetween(Long value1, Long value2) {
            addCriterion("reception_evaluate_id between", value1, value2, "receptionEvaluateId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateIdNotBetween(Long value1, Long value2) {
            addCriterion("reception_evaluate_id not between", value1, value2, "receptionEvaluateId");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkIsNull() {
            addCriterion("evaluate_remark is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkIsNotNull() {
            addCriterion("evaluate_remark is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkEqualTo(String value) {
            addCriterion("evaluate_remark =", value, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkNotEqualTo(String value) {
            addCriterion("evaluate_remark <>", value, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkGreaterThan(String value) {
            addCriterion("evaluate_remark >", value, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("evaluate_remark >=", value, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkLessThan(String value) {
            addCriterion("evaluate_remark <", value, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkLessThanOrEqualTo(String value) {
            addCriterion("evaluate_remark <=", value, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkLike(String value) {
            addCriterion("evaluate_remark like", value, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkNotLike(String value) {
            addCriterion("evaluate_remark not like", value, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkIn(List<String> values) {
            addCriterion("evaluate_remark in", values, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkNotIn(List<String> values) {
            addCriterion("evaluate_remark not in", values, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkBetween(String value1, String value2) {
            addCriterion("evaluate_remark between", value1, value2, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkNotBetween(String value1, String value2) {
            addCriterion("evaluate_remark not between", value1, value2, "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andReceptionIdIsNull() {
            addCriterion("reception_id is null");
            return (Criteria) this;
        }

        public Criteria andReceptionIdIsNotNull() {
            addCriterion("reception_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceptionIdEqualTo(Long value) {
            addCriterion("reception_id =", value, "receptionId");
            return (Criteria) this;
        }

        public Criteria andReceptionIdNotEqualTo(Long value) {
            addCriterion("reception_id <>", value, "receptionId");
            return (Criteria) this;
        }

        public Criteria andReceptionIdGreaterThan(Long value) {
            addCriterion("reception_id >", value, "receptionId");
            return (Criteria) this;
        }

        public Criteria andReceptionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("reception_id >=", value, "receptionId");
            return (Criteria) this;
        }

        public Criteria andReceptionIdLessThan(Long value) {
            addCriterion("reception_id <", value, "receptionId");
            return (Criteria) this;
        }

        public Criteria andReceptionIdLessThanOrEqualTo(Long value) {
            addCriterion("reception_id <=", value, "receptionId");
            return (Criteria) this;
        }

        public Criteria andReceptionIdIn(List<Long> values) {
            addCriterion("reception_id in", values, "receptionId");
            return (Criteria) this;
        }

        public Criteria andReceptionIdNotIn(List<Long> values) {
            addCriterion("reception_id not in", values, "receptionId");
            return (Criteria) this;
        }

        public Criteria andReceptionIdBetween(Long value1, Long value2) {
            addCriterion("reception_id between", value1, value2, "receptionId");
            return (Criteria) this;
        }

        public Criteria andReceptionIdNotBetween(Long value1, Long value2) {
            addCriterion("reception_id not between", value1, value2, "receptionId");
            return (Criteria) this;
        }

        public Criteria andPatientIdIsNull() {
            addCriterion("patient_id is null");
            return (Criteria) this;
        }

        public Criteria andPatientIdIsNotNull() {
            addCriterion("patient_id is not null");
            return (Criteria) this;
        }

        public Criteria andPatientIdEqualTo(Long value) {
            addCriterion("patient_id =", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotEqualTo(Long value) {
            addCriterion("patient_id <>", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdGreaterThan(Long value) {
            addCriterion("patient_id >", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdGreaterThanOrEqualTo(Long value) {
            addCriterion("patient_id >=", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLessThan(Long value) {
            addCriterion("patient_id <", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdLessThanOrEqualTo(Long value) {
            addCriterion("patient_id <=", value, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdIn(List<Long> values) {
            addCriterion("patient_id in", values, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotIn(List<Long> values) {
            addCriterion("patient_id not in", values, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdBetween(Long value1, Long value2) {
            addCriterion("patient_id between", value1, value2, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientIdNotBetween(Long value1, Long value2) {
            addCriterion("patient_id not between", value1, value2, "patientId");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNull() {
            addCriterion("patient_name is null");
            return (Criteria) this;
        }

        public Criteria andPatientNameIsNotNull() {
            addCriterion("patient_name is not null");
            return (Criteria) this;
        }

        public Criteria andPatientNameEqualTo(String value) {
            addCriterion("patient_name =", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotEqualTo(String value) {
            addCriterion("patient_name <>", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThan(String value) {
            addCriterion("patient_name >", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameGreaterThanOrEqualTo(String value) {
            addCriterion("patient_name >=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThan(String value) {
            addCriterion("patient_name <", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLessThanOrEqualTo(String value) {
            addCriterion("patient_name <=", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameLike(String value) {
            addCriterion("patient_name like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotLike(String value) {
            addCriterion("patient_name not like", value, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameIn(List<String> values) {
            addCriterion("patient_name in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotIn(List<String> values) {
            addCriterion("patient_name not in", values, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameBetween(String value1, String value2) {
            addCriterion("patient_name between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andPatientNameNotBetween(String value1, String value2) {
            addCriterion("patient_name not between", value1, value2, "patientName");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNull() {
            addCriterion("doctor_id is null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIsNotNull() {
            addCriterion("doctor_id is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorIdEqualTo(Long value) {
            addCriterion("doctor_id =", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotEqualTo(Long value) {
            addCriterion("doctor_id <>", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThan(Long value) {
            addCriterion("doctor_id >", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("doctor_id >=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThan(Long value) {
            addCriterion("doctor_id <", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdLessThanOrEqualTo(Long value) {
            addCriterion("doctor_id <=", value, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdIn(List<Long> values) {
            addCriterion("doctor_id in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotIn(List<Long> values) {
            addCriterion("doctor_id not in", values, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdBetween(Long value1, Long value2) {
            addCriterion("doctor_id between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorIdNotBetween(Long value1, Long value2) {
            addCriterion("doctor_id not between", value1, value2, "doctorId");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNull() {
            addCriterion("doctor_name is null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIsNotNull() {
            addCriterion("doctor_name is not null");
            return (Criteria) this;
        }

        public Criteria andDoctorNameEqualTo(String value) {
            addCriterion("doctor_name =", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotEqualTo(String value) {
            addCriterion("doctor_name <>", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThan(String value) {
            addCriterion("doctor_name >", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameGreaterThanOrEqualTo(String value) {
            addCriterion("doctor_name >=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThan(String value) {
            addCriterion("doctor_name <", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLessThanOrEqualTo(String value) {
            addCriterion("doctor_name <=", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLike(String value) {
            addCriterion("doctor_name like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotLike(String value) {
            addCriterion("doctor_name not like", value, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameIn(List<String> values) {
            addCriterion("doctor_name in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotIn(List<String> values) {
            addCriterion("doctor_name not in", values, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameBetween(String value1, String value2) {
            addCriterion("doctor_name between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameNotBetween(String value1, String value2) {
            addCriterion("doctor_name not between", value1, value2, "doctorName");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIsNull() {
            addCriterion("office_id is null");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIsNotNull() {
            addCriterion("office_id is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeIdEqualTo(Long value) {
            addCriterion("office_id =", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotEqualTo(Long value) {
            addCriterion("office_id <>", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdGreaterThan(Long value) {
            addCriterion("office_id >", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("office_id >=", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdLessThan(Long value) {
            addCriterion("office_id <", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdLessThanOrEqualTo(Long value) {
            addCriterion("office_id <=", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIn(List<Long> values) {
            addCriterion("office_id in", values, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotIn(List<Long> values) {
            addCriterion("office_id not in", values, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdBetween(Long value1, Long value2) {
            addCriterion("office_id between", value1, value2, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotBetween(Long value1, Long value2) {
            addCriterion("office_id not between", value1, value2, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeNameIsNull() {
            addCriterion("office_name is null");
            return (Criteria) this;
        }

        public Criteria andOfficeNameIsNotNull() {
            addCriterion("office_name is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeNameEqualTo(String value) {
            addCriterion("office_name =", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameNotEqualTo(String value) {
            addCriterion("office_name <>", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameGreaterThan(String value) {
            addCriterion("office_name >", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameGreaterThanOrEqualTo(String value) {
            addCriterion("office_name >=", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameLessThan(String value) {
            addCriterion("office_name <", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameLessThanOrEqualTo(String value) {
            addCriterion("office_name <=", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameLike(String value) {
            addCriterion("office_name like", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameNotLike(String value) {
            addCriterion("office_name not like", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameIn(List<String> values) {
            addCriterion("office_name in", values, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameNotIn(List<String> values) {
            addCriterion("office_name not in", values, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameBetween(String value1, String value2) {
            addCriterion("office_name between", value1, value2, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameNotBetween(String value1, String value2) {
            addCriterion("office_name not between", value1, value2, "officeName");
            return (Criteria) this;
        }

        public Criteria andHospitalIdIsNull() {
            addCriterion("hospital_id is null");
            return (Criteria) this;
        }

        public Criteria andHospitalIdIsNotNull() {
            addCriterion("hospital_id is not null");
            return (Criteria) this;
        }

        public Criteria andHospitalIdEqualTo(Long value) {
            addCriterion("hospital_id =", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdNotEqualTo(Long value) {
            addCriterion("hospital_id <>", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdGreaterThan(Long value) {
            addCriterion("hospital_id >", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdGreaterThanOrEqualTo(Long value) {
            addCriterion("hospital_id >=", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdLessThan(Long value) {
            addCriterion("hospital_id <", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdLessThanOrEqualTo(Long value) {
            addCriterion("hospital_id <=", value, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdIn(List<Long> values) {
            addCriterion("hospital_id in", values, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdNotIn(List<Long> values) {
            addCriterion("hospital_id not in", values, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdBetween(Long value1, Long value2) {
            addCriterion("hospital_id between", value1, value2, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andHospitalIdNotBetween(Long value1, Long value2) {
            addCriterion("hospital_id not between", value1, value2, "hospitalId");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdIsNull() {
            addCriterion("parent_hospital_id is null");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdIsNotNull() {
            addCriterion("parent_hospital_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdEqualTo(Long value) {
            addCriterion("parent_hospital_id =", value, "parentHospitalId");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdNotEqualTo(Long value) {
            addCriterion("parent_hospital_id <>", value, "parentHospitalId");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdGreaterThan(Long value) {
            addCriterion("parent_hospital_id >", value, "parentHospitalId");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_hospital_id >=", value, "parentHospitalId");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdLessThan(Long value) {
            addCriterion("parent_hospital_id <", value, "parentHospitalId");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_hospital_id <=", value, "parentHospitalId");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdIn(List<Long> values) {
            addCriterion("parent_hospital_id in", values, "parentHospitalId");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdNotIn(List<Long> values) {
            addCriterion("parent_hospital_id not in", values, "parentHospitalId");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdBetween(Long value1, Long value2) {
            addCriterion("parent_hospital_id between", value1, value2, "parentHospitalId");
            return (Criteria) this;
        }

        public Criteria andParentHospitalIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_hospital_id not between", value1, value2, "parentHospitalId");
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

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andEvaluateRemarkLikeInsensitive(String value) {
            addCriterion("upper(evaluate_remark) like", value.toUpperCase(), "evaluateRemark");
            return (Criteria) this;
        }

        public Criteria andPatientNameLikeInsensitive(String value) {
            addCriterion("upper(patient_name) like", value.toUpperCase(), "patientName");
            return (Criteria) this;
        }

        public Criteria andDoctorNameLikeInsensitive(String value) {
            addCriterion("upper(doctor_name) like", value.toUpperCase(), "doctorName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameLikeInsensitive(String value) {
            addCriterion("upper(office_name) like", value.toUpperCase(), "officeName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}