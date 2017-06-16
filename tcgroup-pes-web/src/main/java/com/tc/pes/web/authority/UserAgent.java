package com.tc.pes.web.authority;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.tcmc.udc.login.CasUserAgent;

import java.util.List;
import java.util.Set;

/**
 * description
 *
 * @author Tantal
 * @version v2.0.0
 * @date 2015/08/20 16:49
 */
public class UserAgent extends CasUserAgent {

    /**
     * 数据范围
     */
    private Set<Long> dataHospitalIdRanges = Sets.newHashSet();

    public UserAgent() {
        super();
    }

    public List<Long> getDataHospitalIdRanges() {
        return Lists.newArrayList(dataHospitalIdRanges);
    }

    public void setDataHospitalIdRanges(Set<Long> dataHospitalIdRanges) {
        this.dataHospitalIdRanges = dataHospitalIdRanges;
    }

    public UserAgent(Long accountId, Long staffId, String userName, Long hospitalId, Long parentHospitalId, Long officeId) {
        super(accountId, staffId, userName, hospitalId, parentHospitalId, officeId);
    }
}
