package com.job;

import org.joda.time.DateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJobScheduler implements Job {
    private final static Logger LOG = LoggerFactory.getLogger(HelloJobScheduler.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //推迟5秒后执行任务
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.info("2222");
        System.out.println("Hello quartz"+ System.currentTimeMillis()/1000);

    }
}
