package com.changhong.people.common.utils;

import org.springframework.context.ApplicationContext;

/**
 * 系统常量类
 * 
 * @ClassName Const
 * @Description TODO
 * @author 向阳
 * @Date 2017年1月17日 下午4:21:08
 * @version 1.0.0
 */
public final class Const {

	// -------------------------------密码匹配器_输入限制-----------------------------------
	public static int PWD_INPUT_ERROR_LIMIT = 5;// 用户密码连续输错次数限制(默认5).
	// -------------------------------SringMVC_url请求分发-----------------------------------
	public static String SYSTEM = "system"; // 后台系统
	public static String COMMON = "common"; // 公共平台
	public static String WEBUSER = "webuser"; // 前台系统
	// -------------------------------session
	// user-----------------------------------
	public static final String USER_IN_SESSION = "user_in_session"; // 登录用户
	public static final String USERID_IN_SESSION = "userid_in_session"; // 登录用户ID
	public static final String USERNAME_IN_SESSION = "username_in_session"; // 登录用户账号

	public static final String EMPLOYEE_IN_SESSION = "employee_in_session";
	public static final String EMPLOYEEID_IN_SESSION = "employeeid_in_session";
	public static final String EMPLOYEENAME_IN_SESSION = "employeename_in_session";

	public static final String VALIDATECODE_IN_SESSION = "validatecode_in_session"; // 登录验证码
	// -------------------------------spring容器-----------------------------------
	public static ApplicationContext WEB_APP_CONTEXT = null; // Spring容器（上下文）

	// 公钥key
	public final static String PRIVATEKEY_IN_SESSION = "privateKey_in_session";

}
