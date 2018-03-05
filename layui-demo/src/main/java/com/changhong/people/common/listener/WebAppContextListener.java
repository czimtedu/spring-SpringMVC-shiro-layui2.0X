package com.changhong.people.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.changhong.people.common.utils.Const;

/**
 * 
 *	类名称：WebAppContextListener
 *	类描述：
 *	创建人：
 *	创建时间：2017年3月23日下午5:42:39
 *	修改人：
 *	修改时间：
 *	修改备注：
 *  
 */
@WebListener
public class WebAppContextListener implements ServletContextListener {

	/**
     * web启动时执行
     * sevlet上下文获取Spring容器
     */
    @Override
	public void contextInitialized(ServletContextEvent sce)  { 
    	Const.WEB_APP_CONTEXT = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
    }

	/**
     * web销毁执行
     */
    @Override
	public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}
