package org.xfs.scm.business.test.service.impl;

import org.springframework.stereotype.Component;
import org.xfs.scm.business.test.service.SchedualServiceHi;

@Component
public class SchedualServiceHiHystric implements SchedualServiceHi{
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
