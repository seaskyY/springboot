package com.tc.pes.api.model;

import java.util.ArrayList;
import java.util.List;

public class ReceptionEvaluateChildCriteria extends PageCriteria {
    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ReceptionEvaluateChildCriteria() {
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

        public Criteria andReceptionEvaluateChildIdIsNull() {
            addCriterion("reception_evaluate_child_id is null");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateChildIdIsNotNull() {
            addCriterion("reception_evaluate_child_id is not null");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateChildIdEqualTo(Long value) {
            addCriterion("reception_evaluate_child_id =", value, "receptionEvaluateChildId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateChildIdNotEqualTo(Long value) {
            addCriterion("reception_evaluate_child_id <>", value, "receptionEvaluateChildId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateChildIdGreaterThan(Long value) {
            addCriterion("reception_evaluate_child_id >", value, "receptionEvaluateChildId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateChildIdGreaterThanOrEqualTo(Long value) {
            addCriterion("reception_evaluate_child_id >=", value, "receptionEvaluateChildId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateChildIdLessThan(Long value) {
            addCriterion("reception_evaluate_child_id <", value, "receptionEvaluateChildId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateChildIdLessThanOrEqualTo(Long value) {
            addCriterion("reception_evaluate_child_id <=", value, "receptionEvaluateChildId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateChildIdIn(List<Long> values) {
            addCriterion("reception_evaluate_child_id in", values, "receptionEvaluateChildId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateChildIdNotIn(List<Long> values) {
            addCriterion("reception_evaluate_child_id not in", values, "receptionEvaluateChildId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateChildIdBetween(Long value1, Long value2) {
            addCriterion("reception_evaluate_child_id between", value1, value2, "receptionEvaluateChildId");
            return (Criteria) this;
        }

        public Criteria andReceptionEvaluateChildIdNotBetween(Long value1, Long value2) {
            addCriterion("reception_evaluate_child_id not between", value1, value2, "receptionEvaluateChildId");
            return (Criteria) this;
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

        public Criteria andEvaluateLevelIsNull() {
            addCriterion("evaluate_level is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateLevelIsNotNull() {
            addCriterion("evaluate_level is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateLevelEqualTo(Short value) {
            addCriterion("evaluate_level =", value, "evaluateLevel");
            return (Criteria) this;
        }

        public Criteria andEvaluateLevelNotEqualTo(Short value) {
            addCriterion("evaluate_level <>", value, "evaluateLevel");
            return (Criteria) this;
        }

        public Criteria andEvaluateLevelGreaterThan(Short value) {
            addCriterion("evaluate_level >", value, "evaluateLevel");
            return (Criteria) this;
        }

        public Criteria andEvaluateLevelGreaterThanOrEqualTo(Short value) {
            addCriterion("evaluate_level >=", value, "evaluateLevel");
            return (Criteria) this;
        }

        public Criteria andEvaluateLevelLessThan(Short value) {
            addCriterion("evaluate_level <", value, "evaluateLevel");
            return (Criteria) this;
        }

        public Criteria andEvaluateLevelLessThanOrEqualTo(Short value) {
            addCriterion("evaluate_level <=", value, "evaluateLevel");
            return (Criteria) this;
        }

        public Criteria andEvaluateLevelIn(List<Short> values) {
            addCriterion("evaluate_level in", values, "evaluateLevel");
            return (Criteria) this;
        }

        public Criteria andEvaluateLevelNotIn(List<Short> values) {
            addCriterion("evaluate_level not in", values, "evaluateLevel");
            return (Criteria) this;
        }

        public Criteria andEvaluateLevelBetween(Short value1, Short value2) {
            addCriterion("evaluate_level between", value1, value2, "evaluateLevel");
            return (Criteria) this;
        }

        public Criteria andEvaluateLevelNotBetween(Short value1, Short value2) {
            addCriterion("evaluate_level not between", value1, value2, "evaluateLevel");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeIsNull() {
            addCriterion("evaluate_type is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeIsNotNull() {
            addCriterion("evaluate_type is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeEqualTo(Short value) {
            addCriterion("evaluate_type =", value, "evaluateType");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeNotEqualTo(Short value) {
            addCriterion("evaluate_type <>", value, "evaluateType");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeGreaterThan(Short value) {
            addCriterion("evaluate_type >", value, "evaluateType");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("evaluate_type >=", value, "evaluateType");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeLessThan(Short value) {
            addCriterion("evaluate_type <", value, "evaluateType");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeLessThanOrEqualTo(Short value) {
            addCriterion("evaluate_type <=", value, "evaluateType");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeIn(List<Short> values) {
            addCriterion("evaluate_type in", values, "evaluateType");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeNotIn(List<Short> values) {
            addCriterion("evaluate_type not in", values, "evaluateType");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeBetween(Short value1, Short value2) {
            addCriterion("evaluate_type between", value1, value2, "evaluateType");
            return (Criteria) this;
        }

        public Criteria andEvaluateTypeNotBetween(Short value1, Short value2) {
            addCriterion("evaluate_type not between", value1, value2, "evaluateType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}