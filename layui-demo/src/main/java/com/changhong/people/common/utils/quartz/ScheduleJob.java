package com.changhong.people.common.utils.quartz;
import java.io.Serializable;

import javax.validation.constraints.NotNull;
/**
 * 
 *	类名称：ScheduleJob
 *	类描述：调度任务封装对象
 *	创建人：
 *	创建时间：2017年1月19日上午11:22:56
 *	修改人：
 *	修改时间：
 *	修改备注：
 *	备注：基于quartz2.2.0版本
 */
public class ScheduleJob implements Serializable{
    private static final long serialVersionUID = 1L;
    private String jobId;			//任务ID
    @NotNull(message="{jobName.error}")
    private String jobName; 		//任务名称	
    private String jobGroup;	 	//任务分组	
    private String jobStatus;    	//任务状态
    private String cronExpression;	//任务CORN时间表达
    private String interfaceName; 	//接口名称,用于MQ进行接口回调
    private String desc;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }
}