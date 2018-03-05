package com.changhong.people.business.web.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.changhong.people.business.web.validation.annotation.Money;
/**
 * 
 *	类名称：MoneyValidator
 *	类描述：校验金额格式
 *	创建人：
 *	创建时间：2017年2月27日下午3:57:01
 *	修改人：
 *	修改时间：
 *	修改备注：
 */
public class MoneyValidator implements ConstraintValidator<Money, Double> {  
	   
    private String moneyReg = "^\\d+(\\.\\d{1,2})?$";//表示金额的正则表达式  
    private Pattern moneyPattern = Pattern.compile(moneyReg);  
     
    @Override
	public void initialize(Money money) {  
       // TODO Auto-generated method stub  
        
    }  
   
    @Override
	public boolean isValid(Double value, ConstraintValidatorContext arg1) {  
       // TODO Auto-generated method stub  
       if (value == null) {
		return true;
	}  
       return moneyPattern.matcher(value.toString()).matches();  
    }  
   
}  
