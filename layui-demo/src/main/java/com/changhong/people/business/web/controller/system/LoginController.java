package com.changhong.people.business.web.controller.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.changhong.people.business.entity.system.SysUser;
import com.changhong.people.common.utils.Const;
import com.changhong.people.common.utils.shiro.ShiroUtils;

/**
 * 
 * @ClassName：LoginController
 * @Description：登录
 * @Author：zhengjing
 * @Date：2017年5月24日下午1:57:42
 * @version：1.0.0
 */
@Controller
public class LoginController {
	
	 
	/**
	 * 
	 * @MethodName：loginSubmit
	 * @param username
	 * @param password
	 * @param validateCode
	 * @param model
	 * @return
	 * @ReturnType：String
	 * @Description：提交登录
	 * @Creator：zhengjing
	 * @CreateTime：2017年5月24日下午11:06:40
	 * @Modifier：
	 * @ModifyTime：
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    public String loginSubmit(String username,String password,String validateCode,Model model,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("account", username);
		/*//获取session中的秘钥
		String privateKey = (String)request.getSession().getAttribute(Const.PRIVATEKEY_IN_SESSION);
		//编码后的密码进行解码
		String newPassword = RSAUtils.getInstance().decryptByPrivateKey(password, privateKey);
		password = StringUtils.isNotBlank(newPassword) ? newPassword : "";*/
		
    	UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    	Subject user = SecurityUtils.getSubject();
    	String error = null;
    	if(!validateCode.equalsIgnoreCase(ShiroUtils.getValidateCode())){
    		error = "验证码不正确!";
    	}
    	try {
    		user.login(token);
		} catch (LockedAccountException e) {
			error = "用户已被锁定不能登录，请与联系管理员!";
		} catch (ExcessiveAttemptsException e) {
			error = "输入密码错误超过5次,锁定1分钟!";
		} catch (UnknownAccountException e) {
			error = "用户名或者密码错误!";//error = "用户名不存在!";
		} catch (IncorrectCredentialsException e) {
			error = "用户名或者密码错误!";//error = "密码错误!";
		} catch (AuthenticationException e) {
			error = "用户名或密码错误!";
		} catch (Exception e) {
			error = "登录异常，请联系管理员!";
		}
    	model.addAttribute("error", error);
    	SysUser users = new SysUser();
    	users.setAccount(username);
    	users.setPassword(password);
    	model.addAttribute("admin", users);
    	return error == null ?"redirect:/":Const.SYSTEM + "/login";
    }
    
    /**
     * 
     * @MethodName：login
     * @return
     * @ReturnType：String
     * @Description：登录页面
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午11:06:26
     * @Modifier：
     * @ModifyTime：
     */
    @RequestMapping(value = "login", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
    public String login() {
    	return Const.SYSTEM + "/login";
    }
    
	/*@RequestMapping(value = "customerServiceLogin", method = RequestMethod.GET, produces = "text/html; charset=utf-8")
	public String customerServiceLogin(@RequestParam(value = "cle_phone", required = true) String customerPhone,
			@RequestParam(value = "user_num", required = true) String username,
			@RequestParam(value = "signature", required = true) String signature, Model model) {
		username = "custs" + username;
		String password = "custs.8888";
		//TODO 签名验证 signature
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject user = SecurityUtils.getSubject();
		String error = null;
		
		 * if(!validateCode.equalsIgnoreCase(ShiroUtils.getValidateCode())){
		 * error = "验证码不正确!"; }
		 
		try {
			user.login(token);
		} catch (LockedAccountException e) {
			error = "用户已被锁定不能登录，请与联系管理员!";
		} catch (ExcessiveAttemptsException e) {
			error = "输入密码错误超过5次,锁定1分钟!";
		} catch (UnknownAccountException e) {
			error = "用户名或者密码错误!";// error = "用户名不存在!";
		} catch (IncorrectCredentialsException e) {
			error = "用户名或者密码错误!";// error = "密码错误!";
		} catch (AuthenticationException e) {
			error = "用户名或密码错误!";
		} catch (Exception e) {
			error = "登录异常，请联系管理员!";
		}
		model.addAttribute("error", error);
		SysUser users = new SysUser();
		users.setAccount(username);
		users.setPassword(password);
		model.addAttribute("admin", users);
		return error == null ? "redirect:/" : Const.SYSTEM + "/login";
	}*/
}
