package com;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

public class HelloJob implements Job {
    private final static Logger LOG = LoggerFactory.getLogger(HelloJob.class);

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public HelloJob(){
        System.out.println("this is constructor!");
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("2222");
        System.out.println("Hello quartz"+ System.currentTimeMillis()/1000);
        //获取JobDetail的内容
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.println("工作任务的名称：" + key.getName()+"----------工作任务的组："+key.getGroup());
        System.out.println("任务类的名称（带路径）：" + jobExecutionContext.getJobDetail().getJobClass().getName());
        System.out.println("任务类的名称：" + jobExecutionContext.getJobDetail().getJobClass().getSimpleName());

        //从setter中读取message的值
        System.out.println("参数值：" + message);
        //从JobDetail对象中获取jobDataMap的数据
//        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
//        String jobDataMessage = jobDataMap.getString("message");
//        System.out.println("任务数据的参数值：" + jobDataMessage);
        //从Trigger对象中获取jobDataMap的数据
//        JobDataMap triggerDataMap2 = jobExecutionContext.getTrigger().getJobDataMap();
//        String triggerdataMsg = triggerDataMap2.getString("msg");
//        System.out.println("触发器数据的参数值：" + triggerdataMsg);

        //获取Trigger的内容
        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
        System.out.println("触发器名称：" + triggerKey.getName() + "----触发器组的名称：" + triggerKey.getGroup());

        //获取其它的内容
        System.out.println(jobExecutionContext.getJobDetail());
        System.out.println("当前任务的执行时间："+jobExecutionContext.getFireTime());
        System.out.println("下次任务的执行时间："+jobExecutionContext.getNextFireTime());

    }
}
