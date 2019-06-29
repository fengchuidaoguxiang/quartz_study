package com.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobListener implements JobListener {
    @Override
    public String getName() {
        String simpleName = this.getClass().getSimpleName();
        System.out.println("监听器的名称:"+ simpleName);
        return "hell Listener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        String name = context.getJobDetail().getKey().getName();
        System.out.println("job的名称是：" + name +"      Scheduler在JobDetail将要被执行时调用的方法");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println("job的名称是："+name+"，Scheduler在JobDetail即将被执行，但是被TriggerListener否决时，会调用此方法");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException e) {
        String name = context.getJobDetail().getKey().getName();
        System.out.println("job的名称是：" + name + ",Scheduler在JobDetail被执行之后调用这个方法");

    }
}
