package com.main;

import com.job.HelloJobSimpleTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class HelloSchedulerDemoSimpleTrigger {

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
        JobDetail jobDetail = JobBuilder.newJob(HelloJobSimpleTrigger.class)//加载任务类，与HelloJob类完成绑定，要求HelloJob类实现Job接口
                .withIdentity("job1","group1")//参数1：任务的名称（唯一实例）； 参数2：任务组的名称（默认组：DEAFAULT）
                .build();
        // 3.触发器（Trigger）
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","triggerGroup1")//参数1：触发器的名称（唯一实例）； 参数2：触发器组的名称
                .startAt(startDate)//设置任务的开始时间
                .endAt(endDate)//设置结束时间
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withRepeatCount(3))//5秒后重复执行,只重复执行4次（默认值是0）
                .build();
        //让调度器关联任务和触发器，保证按照触发器定义的条件执行任务
        scheduler.scheduleJob(jobDetail,trigger);
        //启动
        scheduler.start();
        //关闭
//      scheduler.shutdown();
    }
}
