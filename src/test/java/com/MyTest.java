package com;

import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTest {

    private final static Logger LOG = LoggerFactory.getLogger(MyTest.class);
    @Test
    public void test1(){
        LOG.info("hello log4j2");
        DateTime dateTime = new DateTime();
        LOG.info(dateTime.toString());
    }
}
