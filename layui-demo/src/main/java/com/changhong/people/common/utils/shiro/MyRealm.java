
package com.changhong.people.common.utils.shiro;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.changhong.people.business.entity.system.Resource;
import com.changhong.people.business.entity.system.SysUser;
import com.changhong.people.business.service.system.IResourceService;
import com.changhong.people.business.service.system.ISysUserService;

/**
 * 
 * @ClassName：MyRealm
 * @Description：授权、认证
 * @Author：zhengjing
 * @Date：2017年5月24日下午1:58:36
 * @version：1.0.0
 */
public class MyRealm extends AuthorizingRealm {
	
	private Logger LOGGER = LoggerFactory.getLogger(MyRealm.class);

	@Autowired
	private IResourceService resourceService;

	@Autowired
    private ISysUserService sysUserService;

	/**
	 * 
	 * @MethodName：doGetAuthorizationInfo
	 * @param principals
	 * @return
	 * @ReturnType：AuthorizationInfo
	 * @Description：授权
	 * @Creator：zhengjing
	 * @CreateTime：2017年5月24日下午2:28:34
	 * @Modifier：
	 * @ModifyTime：
	 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
        	Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("account", username);
        	SysUser sysUser = sysUserService.getByUserName(hashMap);
    		List<Resource> resources = resourceService.getPermissionsByRoleIds(sysUser.getRoleId().toString());
    		authorizationInfo.addRole(sysUser.getRole().getRoleKey()); 						 //角色授权
        	
        	for (Resource resource : resources) {
        		authorizationInfo.addStringPermission(resource.getResKey());                 //权限（资源）授权
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return authorizationInfo;
    }

	/**
	 * 
	 * @MethodName：doGetAuthenticationInfo
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 * @ReturnType：AuthenticationInfo
	 * @Description：登录认证
	 * @Creator：zhengjing
	 * @CreateTime：2017年5月24日下午2:04:43
	 * @Modifier：
	 * @ModifyTime：
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
		String username = (String) token.getPrincipal();
		SysUser sysuser = null;
		try {
			sysuser = sysUserService.getByUserNameStruts(username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AuthenticationException(); //查询失败
		}
		if(sysuser ==null){
			throw new UnknownAccountException();// 没找到帐号(该账号不存在)
		}else{
			
			AuthenticationInfo authenticationinfo = null;
			if(sysuser !=null) {
				if ("1".equals(sysuser.getLocked())) {
					throw new LockedAccountException(); // 帐号被锁定
				}
				// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
				authenticationinfo = new SimpleAuthenticationInfo(
						sysuser.getAccount(),  //输入的账号
						sysuser.getPassword(), // 密码
						ByteSource.Util.bytes(sysuser.getCredentialsSalt()), //token: salt=username+salt
						getName() // realm name
						);
				
				// 认证成功：则把用户信息放入session中
				if(authenticationinfo != null){
					ShiroUtils.setUserInSession(sysuser);
				}
			}
			return authenticationinfo;
			
		}

	}
	/**
     * 更新用户授权信息缓存.
     */
	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
     * 更新用户信息缓存.
     */
	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	/**
	 * 清除用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	/**
	 * 清除用户信息缓存.
	 */
	public void clearAllCachedAuthenticationInfo() {
		 getAuthenticationCache().clear();
	}
	
	/**
	 * 清空所有缓存
	 */
	@Override
	public void clearCache(PrincipalCollection principals) {
		 super.clearCache(principals);
	}


	/**
	 * 清空所有认证缓存
	 */
	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

}