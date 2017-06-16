package com.tc.pes.api.model;

import java.io.Serializable;
import java.util.Date;

public class ReceptionEvaluate extends PojoWithPkSet implements Serializable {
    private Long receptionEvaluateId;

    private String evaluateRemark;

    private Long receptionId;

    private Long patientId;

    private String patientName;

    private Long doctorId;

    private String doctorName;

    private Long officeId;

    private String officeName;

    private Long hospitalId;

    private Long parentHospitalId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public ReceptionEvaluate(Long receptionEvaluateId, String evaluateRemark, Long receptionId, Long patientId, String patientName, Long doctorId, String doctorName, Long officeId, String officeName, Long hospitalId, Long parentHospitalId, Date createTime) {
        this.receptionEvaluateId = receptionEvaluateId;
        this.evaluateRemark = evaluateRemark;
        this.receptionId = receptionId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.officeId = officeId;
        this.officeName = officeName;
        this.hospitalId = hospitalId;
        this.parentHospitalId = parentHospitalId;
        this.createTime = createTime;
    }

    public ReceptionEvaluate() {
        super();
    }

    public Long getReceptionEvaluateId() {
        return receptionEvaluateId;
    }

    public void setReceptionEvaluateId(Long receptionEvaluateId) {
        this.receptionEvaluateId = receptionEvaluateId;
    }

    public String getEvaluateRemark() {
        return evaluateRemark;
    }

    public void setEvaluateRemark(String evaluateRemark) {
        this.evaluateRemark = evaluateRemark == null ? null : evaluateRemark.trim();
    }

    public Long getReceptionId() {
        return receptionId;
    }

    public void setReceptionId(Long receptionId) {
        this.receptionId = receptionId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName == null ? null : officeName.trim();
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Long getParentHospitalId() {
        return parentHospitalId;
    }

    public void setParentHospitalId(Long parentHospitalId) {
        this.parentHospitalId = parentHospitalId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public void setPk(Long pkId) {
        receptionEvaluateId = pkId;
    }
}