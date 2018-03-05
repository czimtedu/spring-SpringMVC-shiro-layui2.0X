package com.changhong.people.common.utils.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 
 *	类名称：QuartzJobFactory
 *	类描述：定时任务运行工厂类
 *	创建人：
 *	创建时间：2017年1月19日上午11:22:24
 *	修改人：
 *	修改时间：
 *	修改备注：
 *	备注：基于quartz2.2.0版本
 *	说明：@DisallowConcurrentExecution 表示有状态job
 *       有状态的任务不能并发执行，无状态的任务可并发执行
 */
@DisallowConcurrentExecution
public class QuartzJobFactory implements Job {
    @Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
        ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
        String JobGroup = scheduleJob.getJobGroup();
        String JobName = scheduleJob.getJobName();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    	System.out.println(dateFormat.format(new Date())+"【当前执行】job组："+JobGroup+"，job名："+JobName);
    }
}