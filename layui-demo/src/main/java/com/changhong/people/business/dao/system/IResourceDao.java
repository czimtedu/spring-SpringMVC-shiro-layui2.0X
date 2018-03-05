package com.changhong.people.business.dao.system;

import java.util.HashMap;
import java.util.List;

import com.changhong.people.business.entity.system.Resource;
import com.changhong.people.common.dao.IBaseDao;

/**
 * 
 * @ClassName：IResourceDao
 * @Description：资源
 * @Author：zhengjing
 * @Date：2017年5月24日下午10:33:01
 * @version：1.0.0
 */
public interface IResourceDao extends IBaseDao<Resource>{
	//根据角色Id获取权限资源
	List<Resource> getPermissionsByRoleIds(HashMap<String,Object> parame) throws Exception;

	List<Resource> getByEmployeeWhere(HashMap<String, Object> params)throws Exception;

}
