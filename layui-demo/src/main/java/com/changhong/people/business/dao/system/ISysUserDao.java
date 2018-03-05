package com.changhong.people.business.dao.system;

import java.util.Map;

import com.changhong.people.business.entity.system.SysUser;
import com.changhong.people.business.query.system.SysUserQuery;
import com.changhong.people.common.dao.IBaseDao;
/**
 * @ClassName ISysUserDao
 * @Description TODO
 * @author yuanyong
 * @Date 2017年5月26日 上午8:51:28
 * @version 1.0.0
 */
public interface ISysUserDao  extends IBaseDao<SysUser>{

	//根据用户名查询对象
    SysUser getByUserName(Map<String, Object> hashMap)throws Exception;
	//登录验证
    SysUser getByUserNameStruts(String username)throws Exception;
    
	
	/**
	 * 判断账户是否重复
	 * @param account
	 * @param id
	 * @return
	 */
	long judgeExistOfUser(SysUserQuery query);
}
