package com.main;

import com.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloSchedulerDemo2 {

    public static void main(String[] args) throws Exception {

        JobDetail job = JobBuilder.newJob(HelloJob.class)
                .withIdentity("dummyJobName", "group1").build();


        // Trigger the job to run on the next round minute
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName", "group1")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
//                    SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(3))
//                        SimpleScheduleBuilder.repeatSecondlyForever(1).withRepeatCount(3))//5秒后重复执行,只重复执行4次（默认值是0）
                .build();

        // schedule it
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();


    }
}
