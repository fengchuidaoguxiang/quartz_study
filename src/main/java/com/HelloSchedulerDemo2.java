package com;

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
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(2).repeatForever())
                .build();

        // schedule it
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();


    }
}
