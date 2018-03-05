package com.changhong.people.common.utils.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobDemo2 extends QuartzJobBean {
	private static int i = 0;
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName()+"------------------");
        System.out.println("不继承org.springframework.scheduling.quartz.QuartzJobBean类，每隔指定时间则触发一次,第" + ++i + "次调用进行中...");
	}

}
