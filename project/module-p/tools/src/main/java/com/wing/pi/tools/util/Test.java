package com.wing.pi.tools.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    static Logger log = LoggerFactory.getLogger(Test.class);
    public static void main(String[] args) {
        Student s = new Student();
        s.setName("01");
        s.setType("ccc");
        s.setValue("1111");
        log.info("{}",new org.apache.commons.beanutils.BeanMap(s));
    }
}
