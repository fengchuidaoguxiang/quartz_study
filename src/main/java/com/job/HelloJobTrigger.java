package com.job;

import org.joda.time.DateTime;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJobTrigger implements Job {
    private final static Logger LOG = LoggerFactory.getLogger(HelloJobTrigger.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("2222");
        System.out.println("Hello quartz"+ System.currentTimeMillis()/1000);

        //获取jobKey、startTime、endTime
        Trigger trigger = jobExecutionContext.getTrigger();
        System.out.println("jobKey的名称："+ trigger.getJobKey().getName()+",jobKey的组名称：" + trigger.getJobKey().getGroup());
        System.out.println("任务的开始时间："+new DateTime(trigger.getStartTime())+",任务的结束时间："+ trigger.getEndTime());


    }
}
