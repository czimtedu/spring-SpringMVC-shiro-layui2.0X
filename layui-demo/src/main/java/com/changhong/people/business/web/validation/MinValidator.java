package com.changhong.people.business.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.changhong.people.business.web.validation.annotation.Min;

/**
 * 
 *	类名称：MinValidator
 *	类描述：校验最小值
 *	创建人：
 *	创建时间：2017年2月27日下午4:02:36
 *	修改人：
 *	修改时间：
 *	修改备注：
 */
public class MinValidator implements ConstraintValidator<Min, Integer> {  
   
    private int minValue;  
     
    @Override
	public void initialize(Min min) {  
       // TODO Auto-generated method stub  
       //把Min限制类型的属性value赋值给当前ConstraintValidator的成员变量minValue  
       minValue = min.value();  
    }  
   
    @Override
	public boolean isValid(Integer value, ConstraintValidatorContext arg1) {  
       // TODO Auto-generated method stub  
       //在这里我们就可以通过当前ConstraintValidator的成员变量minValue访问到当前限制类型Min的value属性了  
       return value >= minValue;  
    }  
   
}  