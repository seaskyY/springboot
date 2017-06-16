package com.tc.pes.model;

import java.io.Serializable;

public class PeArchive extends PojoWithPkSet implements Serializable {
    private Long archiveId;

    private Long patientId;

    private static final long serialVersionUID = 1L;

    public PeArchive(Long archiveId, Long patientId) {
        this.archiveId = archiveId;
        this.patientId = patientId;
    }

    public PeArchive() {
        super();
    }

    public Long getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Long archiveId) {
        this.archiveId = archiveId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    @Override
    public void setPk(Long pkId) {
        archiveId = pkId;
    }
}