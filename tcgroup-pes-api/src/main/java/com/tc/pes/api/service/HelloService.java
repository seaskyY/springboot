package com.tc.pes.api.service;

import com.tc.pes.api.model.ReceptionEvaluate;


public interface HelloService {
    String hello();
    ReceptionEvaluate getReceptionEvaluate(Long id);
    String getReceptionEvaluateStr(Long id);
}
