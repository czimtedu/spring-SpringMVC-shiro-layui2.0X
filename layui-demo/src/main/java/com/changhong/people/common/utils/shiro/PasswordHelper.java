package com.changhong.people.common.utils.shiro;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

import com.changhong.people.business.entity.system.SysUser;
/**
 * 
 *	类名称：PasswordHelper
 *	类描述：加密处理类
 *	创建人：
 *	创建时间：2017年2月21日下午5:44:52
 *	修改人：
 *	修改时间：
 *	修改备注：
 */
@Component
public final class PasswordHelper {
	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	private static final String ALGORITHMNAME = "md5";
	private static final int HASHITERATIONS = 2;
	
	public static final String INIT_PASSWORD = "password_123";//初始密码
	
	public static SysUser encryptPassword(SysUser user) {
		String salt=randomNumberGenerator.nextBytes().toHex();
		user.setSalt(salt);
		String md5Password = new SimpleHash(ALGORITHMNAME, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), HASHITERATIONS).toHex();
		user.setPassword(md5Password); 
		return user;
	}
	

	public static boolean checkPassword(SysUser admin,String password) {
		String md5Password = new SimpleHash(ALGORITHMNAME, password, ByteSource.Util.bytes(admin.getCredentialsSalt()), HASHITERATIONS).toHex();
		if(md5Password.equals(admin.getPassword())){
			return true;
		}
		return false;
	}
	
}
