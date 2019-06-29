package com.main;

import com.job.HelloJobCornTrigger;
import com.job.HelloJobListener;
import com.listener.MyJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.Date;

public class HelloSchedulerDemoJobListener {

    public static void main(String[] args) throws Exception {
        // 1. 调度器（Scheduler）,从工厂中获取调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //设置任务的开始时间
        Date startDate = new Date();
        //任务的开始时间推迟3秒
        startDate.setTime(startDate.getTime()+3000);
        //设置任务的结束时间
        Date endDate = new Date();
        //任务的结束时间推迟5秒
        endDate.setTime(endDate.getTime()+10000);

        // 2. 任务实例（JobDetail）
        JobDetail jobDetail = JobBuilder.newJob(HelloJobListener.class)//加载任务类，与HelloJob类完成绑定，要求HelloJob类实现Job接口
                .withIdentity("job1","group1")//参数1：任务的名称（唯一实例）； 参数2：任务组的名称（默认组：DEAFAULT）
                .build();
        // 3.触发器（Trigger）
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","triggerGroup1")//参数1：触发器的名称（唯一实例）； 参数2：触发器组的名称
                .startNow()//马上开始
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * 29 6 ?"))//日历 每5秒执行一次
                .build();
        //让调度器关联任务和触发器，保证按照触发器定义的条件执行任务
        scheduler.scheduleJob(jobDetail,trigger);

        //创建并注册一个全局的Job Listener
//        scheduler.getListenerManager().addJobListener(new MyJobListener(),EverythingMatcher.allJobs());
        //创建并注册一个局部的Job Listener:局部 指的是 指定的Job
        scheduler.getListenerManager().addJobListener(new MyJobListener(),KeyMatcher.keyEquals(JobKey.jobKey("job1","group1")));

        //启动
        scheduler.start();
        //关闭
//      scheduler.shutdown();
    }
}
