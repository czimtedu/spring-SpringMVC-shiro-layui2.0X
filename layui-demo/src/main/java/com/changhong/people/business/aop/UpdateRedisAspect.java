package com.changhong.people.business.aop;
/*
*//**
 * All rights Reserved, Designed By changhong
 * @Title: UpdateRedisAspect.java   
 * @Package com.ybdc.yy.platform.business.web.controller.system   
 * @Description: TODO   
 * @Author: WangYongjie     
 * @Date: 2017年6月21日 下午5:32:57   
 * @Version V1.0 
 * @CopyRight: 2017 www.changhong.com Inc. All rights reserved. 
 *//*
	 
package com.changhong.oto.business.aop;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.changhong.oto.common.service.impl.RedisService;
import com.changhong.oto.common.utils.crypt.SHACoder;

*//**
 * @ClassName：UpdateRedisAspect
 * @Description：
 * @Author：WangYongjie
 * @Date：2017年6月21日下午5:32:57
 * @version：1.0.0
 *//*
@Aspect
@Component()
public class UpdateRedisAspect {
	private static final Logger logger = LoggerFactory.getLogger(UpdateRedisAspect.class);
	@Autowired
	RedisService redisService;
	
	@Autowired
	HttpServletRequest request;
	
	
	@Pointcut("execution (* com.ybdc.yy.platform.business.web.controller.system.SystemParameterConfigController.edit(..))")
	public void parameterAspect() {
	}// 定义切点 （参数编辑）

	// 定义一个环绕通知
	@Around("parameterAspect()")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		Object[] args = joinPoint.getArgs();
		//如果类型status=6，则更新redis缓存；
		if(args[2].equals("6")){
			String[] value_id = request.getParameterValues("value_id");
			String[] value = request.getParameterValues("value");
			TreeMap<String,Object> tm = new TreeMap<String,Object>();
			for(int i=0;i<value_id.length;i++){
				tm.put(value_id[i], value[i]);
			}
			 redisService.cacheValue(args[1].toString(), SHACoder.digest( tm,  "SHA-256"));
			 logger.info("更新redis缓存成功");
		}
		String redisKey= "ybKey&"+args[1];
		redisService.deleteHash("ybParameter", redisKey);
	
		
			result=joinPoint.proceed();
			return result;
	}
	

	
}
*/