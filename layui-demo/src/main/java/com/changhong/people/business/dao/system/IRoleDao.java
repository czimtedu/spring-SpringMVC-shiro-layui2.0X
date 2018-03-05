package com.changhong.people.business.dao.system;

import java.util.List;
import java.util.Map;

import com.changhong.people.business.entity.system.Role;
import com.changhong.people.common.dao.IBaseDao;
/**
 * 
 * @ClassName：IRoleDao
 * @Description：角色
 * @Author：zhengjing
 * @Date：2017年5月24日下午7:49:51
 * @version：1.0.0
 */
public interface IRoleDao extends IBaseDao<Role>{
		//保存角色资源
		void insertRoleAndResource(List<Map<String, Long>> list)throws Exception;
		//删除角色资源
		void deleteRoleAndResource(Long roleId)throws Exception;
		//根据资源获取角色对象
		List<Role> getByResourceId(Long resourceId);
		Role getByRoleName(String roleName)throws Exception;
}
