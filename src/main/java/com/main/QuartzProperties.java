package com.main;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

public class QuartzProperties {

    public static void main(String[] args) throws SchedulerException {
        //创建工厂实例
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        //创建配置工厂的属性的对象
        Properties prop = new Properties();
        //线程池的定义
//        prop.put("org.quartz.threadPool.class","org.quartz.simpl.SimpleThreadPool");
        prop.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS,"org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount","5");

        //加载上面定义的属性
        schedulerFactory.initialize(prop);
        Scheduler scheduler =  schedulerFactory.getScheduler();

        scheduler.start();
    }
}
