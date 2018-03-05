package com.changhong.people.common.utils.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import com.changhong.people.business.entity.system.SysUser;
import com.changhong.people.common.utils.Const;

public class ShiroUtils {
	
	/**
	 * session中获取登录用户对象
	 */
	public static SysUser getUserInSession() {
		Object obj = SecurityUtils.getSubject().getSession().getAttribute(Const.USER_IN_SESSION);
		return obj == null ? null : (SysUser) obj;
	}
	/**
	 * session中获取登录用户ID
	 */
	public static String getUserIdInSession() {
		Object obj = SecurityUtils.getSubject().getSession().getAttribute(Const.USERID_IN_SESSION);
		return obj == null ? null : (String) obj;
	}
	/**
	 * session中获取登录用户账号
	 */
	public static String getUserNameInSession() {
		Object obj = SecurityUtils.getSubject().getSession().getAttribute(Const.USERNAME_IN_SESSION);
		return obj == null ? null : (String) obj;
	}
	
	/**
	 * 设置登录账号的的对象到session中
	 */
	public static void setUserInSession(SysUser sysUser) {
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(Const.USER_IN_SESSION, sysUser);
		session.setAttribute(Const.USERID_IN_SESSION, sysUser.getId());
		session.setAttribute(Const.USERNAME_IN_SESSION, sysUser.getAccount());
	}
	
	/**
	 * 设置验证码session中
	 */
	public static void setValidateCode(String validateCode) {
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute(Const.VALIDATECODE_IN_SESSION, validateCode);
	}
	
	/**
	 * 获取session中验证码
	 */
	public static String getValidateCode() {
		
		return (String)SecurityUtils.getSubject().getSession().getAttribute(Const.VALIDATECODE_IN_SESSION);
	}
	
	
}
