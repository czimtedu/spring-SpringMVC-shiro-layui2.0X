package com.changhong.people.common.utils.quartz;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 
 *	类名称：JobManager
 *	类描述：
 *	创建人：
 *	创建时间：2017年1月17日下午2:29:54
 *	修改人：
 *	修改时间：
 *	修改备注：
 *	备注：基于quartz2.2.0版本
 */
@Component
public class QuartzManager {
    private static final Logger log = LoggerFactory.getLogger(QuartzManager.class);
    
    @Resource
    private StdScheduler scheduler;
    
	/**
     * 启动调度器（全部）
     */
    public void startSched(){
    	try {
    		scheduler.start();
		} catch (Exception e) {
			log.error("启动失败{}",e.getMessage());
			e.printStackTrace();
		}
    }
    /**
     * 关闭调度器（全部：一般随web服务器停止而停止）
     */
    public void shutdownSched(){
    	try {
    		scheduler.shutdown();
		} catch (Exception e) {
			log.error("关闭失败{}",e.getMessage());
			e.printStackTrace();
		}
    }
    
    /**
     * 获取计划中的任务列表
     * @return
     * @throws SchedulerException 
     */
    public List<ScheduleJob> getPlanJobList() throws SchedulerException{
        List<ScheduleJob> jobList = new ArrayList();
        try {
        	for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
    	       	  String jobName = jobKey.getName();
    	       	  String jobGroup = jobKey.getGroup();
    	       	  List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
	    	      	for (Trigger trigger : triggers){
	        			ScheduleJob scheduleJob = new ScheduleJob();
	        			//封装scheduleJob对象
	        			this.wrapScheduleJob(scheduleJob,scheduler,jobKey,trigger);
	        			jobList.add(scheduleJob);
	        		}
                }
            }
        } catch (SchedulerException e) {
            log.error("获取计划任务列表失败{}",e.getMessage());
            throw new RuntimeException("获取计划任务列表失败",e);
        }
     
        return jobList;
    }

    /**
     * 获取正在运行的任务列表
     * @return
     */
    public List<ScheduleJob> getRunningJobList(){
        List<JobExecutionContext> executingJobList = scheduler.getCurrentlyExecutingJobs();
        List<ScheduleJob> jobList = new ArrayList(executingJobList.size());
        if(executingJobList != null && executingJobList.size()>0){
	        for(JobExecutionContext executingJob : executingJobList){
	            ScheduleJob scheduleJob = new ScheduleJob();
	            JobDetail jobDetail = executingJob.getJobDetail();
	            JobKey jobKey = jobDetail.getKey();
	            Trigger trigger = executingJob.getTrigger();
	            this.wrapScheduleJob(scheduleJob,scheduler,jobKey,trigger);
	            jobList.add(scheduleJob);
	        }
        }
        return jobList;
    }

   

    /**
     * 新增任务（复杂表达式任务CRON）
     * @param scheduleJob
     */
    public void addJob(ScheduleJob scheduleJob){
        try {
        	//1.校验任务是否重复
            TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if(trigger != null){
                throw new Exception("添加任务失败,任务名称已重复");
            }
            //2.创建任务
            JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(scheduleJob.getJobName(),scheduleJob.getJobGroup()).build();
            jobDetail.getJobDataMap().put("scheduleJob", scheduleJob);
            //3.创建调度器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
            trigger = TriggerBuilder.newTrigger().withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup()).withSchedule(cronScheduleBuilder).build();
            //添加任务、调度器
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("添加任务失败: {}",e.getMessage());
            throw new RuntimeException("添加任务失败",e);
        }

    }

    /**
     * 暂停任务
     * @param scheduleJob
     */
    public void pauseJob(ScheduleJob scheduleJob){
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
           log.error("暂停任务失败: {}", e.getMessage());
           throw new RuntimeException("暂停任务失败",e);
        }
    }

    /**
     * 恢复任务执行
     * @param scheduleJob
     */
    public void resumeJob(ScheduleJob scheduleJob){
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("恢复任务失败: {}", e.getMessage());
            throw new RuntimeException("恢复任务失败",e);
        }
    }

    /**
     * 删除任务
     * @param scheduleJob
     */
    public void deleteJob(ScheduleJob scheduleJob){
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
        	scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.error("删除任务失败: {}", e.getMessage());
            throw new RuntimeException("删除任务失败",e);
        }
    }

    /**
     * 立刻执行任务（仅执行一次）
     * @param scheduleJob
     */
    public void runJobOnce(ScheduleJob scheduleJob){
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            scheduler.triggerJob(jobKey);
        } catch (Exception e) {
            log.error("只运行一次任务失败: {}", e.getMessage());
            throw new RuntimeException("只运行一次任务失败",e);
        }
    }

    /**
     * 修改任务执行表达式（corn）
     * @param scheduleJob
     */
    public void updateJobCronExpression(ScheduleJob scheduleJob){
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
        try {
            CronTrigger cronTrigger = (CronTrigger)scheduler.getTrigger(triggerKey);

            //表达式调度构建器
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());

            //按新的cronExpression表达式重新构建trigger
            cronTrigger = cronTrigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();

            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, cronTrigger);
        } catch (SchedulerException e) {
            log.error("修改任务执行表达式失败: {}", e.getMessage());
            throw new RuntimeException("修改任务执行表达式失败",e);
        }
    }
    
    
    /*******************************内部方法*********************************************************/
    
    /**
     * 封装ScheduleJob对象
     * @param scheduleJob
     * @param scheduler
     * @param jobKey
     * @param trigger
     */
    private void wrapScheduleJob(ScheduleJob scheduleJob,Scheduler scheduler,JobKey jobKey,Trigger trigger){
        try {
        	//job名、jobGroup名
            scheduleJob.setJobName(jobKey.getName());
            scheduleJob.setJobGroup(jobKey.getGroup());
            //JobDetail
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            ScheduleJob job = (ScheduleJob)jobDetail.getJobDataMap().get("scheduleJob");
            scheduleJob.setDesc(job.getDesc());//job描述
            //获取触发器状态
            Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
            scheduleJob.setJobStatus(triggerState.name());//job状态（触发器状态）
            if(trigger instanceof CronTrigger){
                CronTrigger cronTrigger = (CronTrigger)trigger;
                String cronExpression = cronTrigger.getCronExpression();
                scheduleJob.setCronExpression(cronExpression);
            }
        } catch (SchedulerException e) {
            log.error("获取触发器状态失败: {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }


}