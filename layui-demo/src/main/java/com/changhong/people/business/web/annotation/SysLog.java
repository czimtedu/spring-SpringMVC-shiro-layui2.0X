package com.changhong.people.business.web.annotation;  
  
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;  
  
/**
 * 
 *	类名称：SystemLog
 *	类描述：系统日志_自定义注解（方法注解）
 *	创建人：
 *	创建时间：2016-6-24上午8:59:37
 *	修改人：
 *	修改时间：
 *	修改备注：
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Documented  
public  @interface SysLog {  
  
	String module()  default "";  		//模块名称：如 ：系统管理
	String methods()  default "";  		//操作方法：如：新增用户
    String description()  default "";  	//描述
  
  
}  
  
