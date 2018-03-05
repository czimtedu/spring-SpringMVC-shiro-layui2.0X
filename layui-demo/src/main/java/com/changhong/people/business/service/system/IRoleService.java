package com.changhong.people.business.service.system;

import java.util.List;

import com.changhong.people.business.entity.system.Role;
import com.changhong.people.common.service.IBaseService;
/**
 * 
 * @ClassName：IRoleService
 * @Description：角色
 * @Author：zhengjing
 * @Date：2017年5月24日下午10:45:45
 * @version：1.0.0
 */
public interface IRoleService extends IBaseService<Role>{
	//根据资源获取角色对象
	List<Role> getByResourceId(Long resourceId);
	public Role getByRoleName(String roleName)throws Exception;
}
