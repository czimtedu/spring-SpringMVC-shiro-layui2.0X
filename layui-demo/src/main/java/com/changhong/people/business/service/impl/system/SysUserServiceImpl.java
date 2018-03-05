package com.changhong.people.business.service.impl.system;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.people.business.dao.system.ISysUserDao;
import com.changhong.people.business.entity.system.SysUser;
import com.changhong.people.business.query.system.SysUserQuery;
import com.changhong.people.business.service.system.ISysUserService;
import com.changhong.people.common.service.impl.BaseServiceImpl;
import com.changhong.people.common.utils.shiro.PasswordHelper;
/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @author yuanyong
 * @Date 2017年5月26日 上午8:52:38
 * @version 1.0.0
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser>implements ISysUserService{

    @Autowired
    ISysUserDao sysUserDao;
    
	@Override
	public void save(SysUser user) throws Exception {
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setPassword(PasswordHelper.INIT_PASSWORD);
		user = PasswordHelper.encryptPassword(user);
		sysUserDao.insert(user);
    }
	//修改
	@Override
	public void update(SysUser user)throws Exception{
		user.setUpdateTime(new Date());
		sysUserDao.update(user);
	}
	//修改状态
	@Override
	public void updateStruts(SysUser user)throws Exception{
		user.setUpdateTime(new Date());
		sysUserDao.update(user);
	}
	@Override
	public void updatePass(SysUser user) throws Exception {
		user.setUpdateTime(new Date());
		user = PasswordHelper.encryptPassword(user);
		sysUserDao.update(user);
	}
    /**
     * 
     * @MethodName：getByUserName
     * @param username
     * @return
     * @throws Exception
     * @ReturnType：SysUser
     * @Description：根据账号获取用户名
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午10:57:15
     * @Modifier：
     * @ModifyTime：
     */
	@Override
	public SysUser getByUserName(Map<String, Object> hashMap) throws Exception{
        return sysUserDao.getByUserName(hashMap);
    }
	@Override
	public SysUser getByUserNameStruts(String username) throws Exception {
        return sysUserDao.getByUserNameStruts(username);
	}
	@Override
	public boolean judgeExistOfUser(SysUserQuery query) {
		return sysUserDao.judgeExistOfUser(query) != 0;
	}

}
