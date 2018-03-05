package com.changhong.people.business.service.system;

import java.util.List;

import com.changhong.people.business.entity.system.Resource;
import com.changhong.people.business.entity.system.SysUser;
import com.changhong.people.common.service.IBaseService;

/**
 * 
 * @ClassName：IResourceService
 * @Description：资源
 * @Author：zhengjing
 * @Date：2017年5月24日下午1:20:19
 * @version：1.0.0
 */
public interface IResourceService extends IBaseService<Resource>{
	//获取用菜单资源
	public List<Resource> getMenus(SysUser admin)throws Exception ;
	//根据角色Id获取权限资源
	public List<Resource> getPermissionsByRoleIds(String roleids) throws Exception;
	//获取资源的tree列表
	public List<Resource> getTreelist() throws Exception;
	//获取资源的tree列表
	public void getTreeList(List<Resource> returnList, Long l, Boolean flag) throws Exception;
	//获取用户所拥有的功能按钮
	public List<Resource> getByUser(Long resId,SysUser user) throws Exception;
	//设置查询编号
	public void setSserchSnByResource(Resource resource) throws Exception;
}
