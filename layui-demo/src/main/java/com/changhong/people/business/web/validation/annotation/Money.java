package com.changhong.people.business.web.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.changhong.people.business.web.validation.MoneyValidator;
/**
 * 
 *	类名称：Money
 *	类描述：自定义@Money注解
 *	创建人：
 *	创建时间：2017年2月27日下午3:56:30
 *	修改人：
 *	修改时间：
 *	修改备注：
 */
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=MoneyValidator.class)
public @interface Money {  
    
    String message() default "不是金额形式";  
     
    Class<?>[] groups() default {};  
     
    Class<? extends Payload>[] payload() default {};  
   
} 
