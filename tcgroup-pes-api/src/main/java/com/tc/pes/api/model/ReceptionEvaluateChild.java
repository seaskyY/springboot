package com.tc.pes.api.model;

import java.io.Serializable;

public class ReceptionEvaluateChild extends PojoWithPkSet implements Serializable {
    private Long receptionEvaluateChildId;

    private Long receptionEvaluateId;

    private Short evaluateLevel;

    private Short evaluateType;

    private static final long serialVersionUID = 1L;

    public ReceptionEvaluateChild(Long receptionEvaluateChildId, Long receptionEvaluateId, Short evaluateLevel, Short evaluateType) {
        this.receptionEvaluateChildId = receptionEvaluateChildId;
        this.receptionEvaluateId = receptionEvaluateId;
        this.evaluateLevel = evaluateLevel;
        this.evaluateType = evaluateType;
    }

    public ReceptionEvaluateChild() {
        super();
    }

    public Long getReceptionEvaluateChildId() {
        return receptionEvaluateChildId;
    }

    public void setReceptionEvaluateChildId(Long receptionEvaluateChildId) {
        this.receptionEvaluateChildId = receptionEvaluateChildId;
    }

    public Long getReceptionEvaluateId() {
        return receptionEvaluateId;
    }

    public void setReceptionEvaluateId(Long receptionEvaluateId) {
        this.receptionEvaluateId = receptionEvaluateId;
    }

    public Short getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(Short evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public Short getEvaluateType() {
        return evaluateType;
    }

    public void setEvaluateType(Short evaluateType) {
        this.evaluateType = evaluateType;
    }

    @Override
    public void setPk(Long pkId) {
        receptionEvaluateChildId = pkId;
    }
}