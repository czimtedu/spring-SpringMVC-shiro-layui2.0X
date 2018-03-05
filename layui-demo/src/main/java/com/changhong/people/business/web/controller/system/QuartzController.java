package com.changhong.people.business.web.controller.system;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.people.common.utils.AjaxResult;
import com.changhong.people.common.utils.quartz.QuartzManager;
import com.changhong.people.common.utils.quartz.ScheduleJob;

//@Controller
//@RequestMapping(value = "/quartz")
public class QuartzController {
	
	//@Autowired
	private QuartzManager quartzManager;
	
   //计划列表
	@RequestMapping(value = "/planList",method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult planList() throws Exception {
		AjaxResult result = null;
		try {
			List<ScheduleJob> planJobList = quartzManager.getPlanJobList();
			result = new AjaxResult(true,"查询成功!", planJobList);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false,"查询失败!");
		}
		return result;
	}
	//运行列表
	@RequestMapping(value = "/runList",method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult runList() throws Exception {
		AjaxResult result = null;
		try {
			List<ScheduleJob> runJobList = quartzManager.getRunningJobList();
			result = new AjaxResult(true,"查询成功!", runJobList);
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false,"查询失败!");
		}
		return result;
	}
	
	//添加
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@ResponseBody
	private AjaxResult addJob(@Valid ScheduleJob scheduleJob,BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			System.out.println(bindingResult.getAllErrors().get(0).getDefaultMessage());
		}
		AjaxResult result = null;
		try {
			quartzManager.addJob(scheduleJob);
			result = new AjaxResult(true,"操作成功!!");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false,"操作失败!");
		}
		return result;
	}
	
	//修改(corn表达)
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult updateJob(ScheduleJob scheduleJob) throws Exception {
		AjaxResult result = null;
		try {
			quartzManager.updateJobCronExpression(scheduleJob);
			result = new AjaxResult(true,"操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false,"操作失败!");
		}
		return result;
	}
	
	//执行一次
	@RequestMapping(value = "/excute",method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult excuteJob(ScheduleJob scheduleJob) throws Exception {
		AjaxResult result = null;
		try {
			quartzManager.runJobOnce(scheduleJob);
			result = new AjaxResult(true,"操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false,"操作失败!");
		}
		return result;
	}
	
	//暂停
	@RequestMapping(value = "/pause",method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult pauseJob(ScheduleJob scheduleJob) throws Exception {
		AjaxResult result = null;
		try {
			quartzManager.pauseJob(scheduleJob);
			result = new AjaxResult(true,"操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false,"操作失败!");
		}
		return result;
	}
	
	//恢复
	@RequestMapping(value = "/resume",method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult resumeJob(ScheduleJob scheduleJob) throws Exception {
		AjaxResult result = null;
		try {
			quartzManager.resumeJob(scheduleJob);
			result = new AjaxResult(true,"操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false,"操作失败!");
		}
		return result;
	}
	
	//删除
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult delete(ScheduleJob scheduleJob) throws Exception {
		AjaxResult result = null;
		try {
			quartzManager.deleteJob(scheduleJob);
			result = new AjaxResult(true,"操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false,"操作失败!");
		}
		return result;
	}
	
	//启动
	@RequestMapping(value = "/start",method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult startSched() {
		AjaxResult result = null;
		try {
			quartzManager.startSched();
			result = new AjaxResult(true,"操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false,"操作失败!");
		}
		return result;
	}
	
	//关闭
	@RequestMapping(value = "/shutdown",method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult shutdown() {
 		AjaxResult result = null;
		try {
			quartzManager.shutdownSched();
			result = new AjaxResult(true,"操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result = new AjaxResult(false,"操作失败!");
		}
		return result;
	}
}
