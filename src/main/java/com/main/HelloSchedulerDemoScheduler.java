package com.main;

import com.job.HelloJobCornTrigger;
import com.job.HelloJobScheduler;
import org.joda.time.DateTime;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class HelloSchedulerDemoScheduler {

    public static void main(String[] args) throws Exception {
        // 1. 调度器（Scheduler）,从工厂中获取调度的实例
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        // 2. 任务实例（JobDetail）
        JobDetail jobDetail = JobBuilder.newJob(HelloJobScheduler.class)//加载任务类，与HelloJob类完成绑定，要求HelloJob类实现Job接口
                .withIdentity("job1","group1")//参数1：任务的名称（唯一实例）； 参数2：任务组的名称（默认组：DEAFAULT）
                .build();
        // 3.触发器（Trigger）
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","triggerGroup1")//参数1：触发器的名称（唯一实例）； 参数2：触发器组的名称
                .startNow()//马上开始
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))//日历 每秒去执行
                .build();
        //让调度器关联任务和触发器，保证按照触发器定义的条件执行任务
        Date date = scheduler.scheduleJob(jobDetail, trigger);
        DateTime dateTime = new DateTime(date);
        System.out.println("调度器的开始时间是：" + dateTime);
        //启动
        scheduler.start();
        //Scheduler执行2秒后挂起
        Thread.sleep(2000L);
        //挂起
//        scheduler.standby();
        //关闭
        scheduler.shutdown(false);
        //Scheduler执行5秒后自动开启
        Thread.sleep(5000L );
        //重新启动
        scheduler.start();
        //关闭
//      scheduler.shutdown();
    }
}
