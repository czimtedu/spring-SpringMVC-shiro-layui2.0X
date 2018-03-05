package com.changhong.people.business.service.system;

import java.util.Map;

import com.changhong.people.business.entity.system.SysUser;
import com.changhong.people.business.query.system.SysUserQuery;
import com.changhong.people.common.service.IBaseService;
/**
 * @ClassName ISysUserService
 * @Description TODO
 * @author yuanyong
 * @Date 2017年5月26日 上午8:51:12
 * @version 1.0.0
 */
public interface ISysUserService  extends IBaseService<SysUser>{
	//根据用户账号获取用户
	public SysUser getByUserName(Map<String, Object> hashMap) throws Exception;
	public void updatePass(SysUser user)throws Exception;
	//登录验证
    SysUser getByUserNameStruts(String username)throws Exception;
    public void updateStruts(SysUser user)throws Exception;
	
	/**
	 * 判断一个账户名是否重复
	 * @param account
	 * @param id
	 * @return
	 */
	boolean judgeExistOfUser(SysUserQuery query);
	
}
