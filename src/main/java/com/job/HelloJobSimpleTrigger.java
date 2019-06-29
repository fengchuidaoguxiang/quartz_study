package com.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJobSimpleTrigger implements Job {
    private final static Logger LOG = LoggerFactory.getLogger(HelloJobSimpleTrigger.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Hello quartz"+ System.currentTimeMillis()/1000);
    }
}
