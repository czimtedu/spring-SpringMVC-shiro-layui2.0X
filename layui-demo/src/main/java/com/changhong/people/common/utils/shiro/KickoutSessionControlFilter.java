package com.changhong.people.common.utils.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 
 * @ClassName：KickoutSessionControlFilter
 * @Description：* 思路：
 * 1.读取当前登录用户名，获取在缓存中的sessionId队列
 * 2.判断队列的长度，大于最大登录限制的时候，按踢出规则
 *  将之前的sessionId中的session域中存入kickout：true，并更新队列缓存
 * 3.判断当前登录的session域中的kickout如果为true，
 * 想将其做退出登录处理，然后再重定向到踢出登录提示页面
 * @Author：xiedong
 * @Date：2017年9月5日下午5:45:03
 * @version：1.0.0
 */
public class KickoutSessionControlFilter extends AccessControlFilter {
	
	
	private Logger LOGGER = LoggerFactory.getLogger(KickoutSessionControlFilter.class);
	
    private String kickoutUrl; 							//踢出后到的地址
    private boolean kickoutAfter = false; 				//踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
    private int maxSession = 1; 						//同一个帐号最大会话数 默认1
    private SessionManager sessionManager; 				//session管理器
    public static Cache<String, Deque<Serializable>> cache; 	//缓存

    /**
     * 访问拒绝方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
         Subject subject =  getSubject(request, response);
        
        //如果没有认证，则拒绝访问
         if(!subject.isAuthenticated() && !subject.isRemembered()) {
        	 LOGGER.info("-------KickoutSessionControlFilter------------没有认证直接退出------");
            return true;
        }

        Session session = subject.getSession();
        
        String username = (String) subject.getPrincipal(); 	//用户名
        Serializable sessionId = session.getId();			//sessionID

        //同步控制
        Deque<Serializable> deque = cache.get(username);
        if(deque == null) {    
            deque = new LinkedList<Serializable>();
            cache.put(username, deque);
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if(!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            deque.push(sessionId);
        }

        //如果队列里的sessionId数超出最大会话数，开始踢人
        while(deque.size() > maxSession) {
        	LOGGER.info("-------KickoutSessionControlFilter------------deque.size()：{}---maxSession：{}---",deque.size(),maxSession);
            Serializable kickoutSessionId = null;
            if(kickoutAfter) { 
                kickoutSessionId = deque.removeFirst();//如果踢出后者
            } else { 
                kickoutSessionId = deque.removeLast(); //否则踢出前者
            }
            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if(kickoutSession != null) {
                    kickoutSession.setAttribute("kickout", true);//设置会话的kickout属性表示踢出了
                }
            } catch (Exception e) {//ignore exception
            }
        }
        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickout") != null) {
        	LOGGER.info("-------KickoutSessionControlFilter------------踢人操作------");
            //会话被踢出了
            try {
                subject.logout();
            } catch (Exception e) { //ignore
            }
            saveRequest(request);
            WebUtils.issueRedirect(request, response, kickoutUrl);
            return false;
        }

        return true;
    }
    
    
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }
    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setMaxSession(int maxSession) {
        this.maxSession = maxSession;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    @SuppressWarnings("static-access")
	public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-kickout-session");
    }

}
