package com.tc.pes.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tc.pes.api.model.ReceptionEvaluate;
import com.tc.pes.api.service.HelloService;
import com.tc.pes.provider.dao.ReceptionEvaluateDao;

@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private ReceptionEvaluateDao receptionEvaluateDao;
    public String hello() {

        // TODO Auto-generated method stub
        return "HelloWorld";
    }

    public ReceptionEvaluate getReceptionEvaluate(Long id) {
        return receptionEvaluateDao.selectById(id);
    }

    public String getReceptionEvaluateStr(Long id) {

        ReceptionEvaluate v=getReceptionEvaluate(id);
        return v.getDoctorName();
    }

}
