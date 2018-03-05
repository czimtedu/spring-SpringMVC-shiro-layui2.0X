package com.changhong.people.business.service.impl.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.people.business.dao.system.IResourceDao;
import com.changhong.people.business.entity.system.Resource;
import com.changhong.people.business.entity.system.SysUser;
import com.changhong.people.business.service.system.IResourceService;
import com.changhong.people.common.service.impl.BaseServiceImpl;

/**
 * 
 * @ClassName：ResourceServiceImpl
 * @Description：资源
 * @Author：zhengjing
 * @Date：2017年5月24日下午7:50:58
 * @version：1.0.0
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource>implements IResourceService  {

    @Autowired
    private IResourceDao resourceDao;
    /**
     * 
     * @MethodName：getPermissionsByRoleIds
     * @param roleIds
     * @return
     * @throws Exception
     * @ReturnType：List<Resource>
     * @Description：获取角色资源
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午10:52:46
     * @Modifier：
     * @ModifyTime：
     */
	@Override
	public List<Resource> getPermissionsByRoleIds(String roleIds) throws Exception {
		HashMap<String, Object> params = new HashMap<String,Object>();
		params.put("roleIds", roleIds);
		return resourceDao.getPermissionsByRoleIds(params);
	}
    /**
     * 
     * @MethodName：getMenus
     * @param sysuser
     * @return
     * @throws Exception
     * @ReturnType：List<Resource>
     * @Description：获取用户菜单
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午10:52:27
     * @Modifier：
     * @ModifyTime：
     */
	@Override
	public List<Resource> getMenus(SysUser sysuser) throws Exception {
		  List<Resource> userResourcesList = getUserMenuResource(sysuser); //用户菜单资源
		  List<Resource> myMenuList = new ArrayList<>();                   //用户菜单列表
		  for(Resource resource : userResourcesList){
			 if(resource.areRootNode()){ //从根开始：parentId==0
				 List<Resource> childrenList = getChrildrenNode(resource,userResourcesList);
				 myMenus(childrenList,userResourcesList);
				 myMenuList.add(resource);
			 }
		}
		return myMenuList;
	}
	/**
	 * 
	 * @MethodName：getTreelist
	 * @return
	 * @throws Exception
	 * @ReturnType：List<Resource>
	 * @Description：获取树形列表
	 * @Creator：zhengjing
	 * @CreateTime：2017年5月24日下午10:53:18
	 * @Modifier：
	 * @ModifyTime：
	 */
	@Override
	public List<Resource> getTreelist() throws Exception {
		List<Resource> userResourcesList = super.getAll(); //用户菜单资源
		List<Resource> myMenuList = new ArrayList<>();     //用户菜单列表
		for(Resource resource : userResourcesList){
			if(resource.areRootNode()){ //从根开始：parentId==0
				List<Resource> childrenList = getChrildrenNode(resource,userResourcesList);
				myMenus(childrenList,userResourcesList);
				myMenuList.add(resource);
			}
		}
		return myMenuList;
	}
	
    /**
     * 
     * @MethodName：getUserMenuResource
     * @param sysuser
     * @return
     * @throws Exception
     * @ReturnType：List<Resource>
     * @Description：
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午10:54:11
     * @Modifier：
     * @ModifyTime：
     */
	private List<Resource> getUserMenuResource(SysUser sysuser) throws Exception { 
		List<Resource> userMenus =  new ArrayList<>();
		HashMap<String, Object> parame = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(sysuser.getRoleId()+"")){
			parame.put("roleIds",sysuser.getRoleId());
			List<Resource> resList = resourceDao.getPermissionsByRoleIds(parame);
			for (Resource resource : resList) {
				if(!resource.getIshide() && ("menu1".equals(resource.getType())||("menu2".equals(resource.getType())))){//有效，没有隐藏的,且菜单类型
					userMenus.add(resource);
				}
			}
		}
		return userMenus;
	}
	private void myMenus(List<Resource> childrenList,List<Resource> userResourcesList) {
		if(childrenList != null && childrenList.size()>0){
			 for (Resource resource : childrenList) {
				 childrenList = getChrildrenNode(resource,userResourcesList);
				 myMenus(childrenList,userResourcesList);
			 }
		 }
	}
    
	private List<Resource> getChrildrenNode(Resource resource,List<Resource> userResourcesList) {
		List<Resource> children =  new ArrayList<>();
		for (Resource res : userResourcesList) {
			if(res.getParentId().equals(resource.getId())){
				children.add(res);
			}
		}
		if(children !=null && children.size()>0 ){
			resource.setChildren(children);
		}
		return  children;
	}

	/**
	 * 
	 * @MethodName：getByUser
	 * @param resId
	 * @param sysuser
	 * @return
	 * @throws Exception
	 * @ReturnType：List<Resource>
	 * @Description：获取功能资源
	 * @Creator：zhengjing
	 * @CreateTime：2017年5月24日下午10:55:40
	 * @Modifier：
	 * @ModifyTime：
	 */
	@Override
	public List<Resource> getByUser(Long resId,SysUser sysuser) throws Exception {
		HashMap<String, Object> params = new HashMap<>();
		params.put("parentId", resId);
		params.put("userId", sysuser.getId());
		params.put("roleId", sysuser.getRoleId());
		return super.getByWhere(params);
	}
	
	
	/**
	 * 
	 *	方法名：setSserchSnByResource
	 *	@param resource
	 *	@throws Exception
	 *	返回类型：void
	 *	说明：设置查询编号
	 *	创建人：
	 * 	创建日期：2017年3月15日下午3:36:51
	 *	修改人：
	 *	修改日期：
	 */
	@Override
	public void setSserchSnByResource(Resource resource) throws Exception {
		Long parentId = resource.getParentId();
		if(resource.getParentId() != null && resource.getParentId()!= 0){
			Resource res = this.getById(parentId);
			String searchsn = StringUtils.join(new String[]{res.getSearchSn(),String.valueOf(parentId)}, ",");//拼接 如：0,1,11
			resource.setSearchSn(searchsn);
		}else{
			resource.setSearchSn(String.valueOf(parentId));
		}
	}
	/*****************************************************************/
	/**
	 * 
	 * @MethodName：getTreeList
	 * @param returnList 返回结果集
	 * @param id  什么层级开始封装（默认0级开始）
	 * @param flag 是否获取菜单（true 所有资源，false 只是功能资源）
	 * @throws Exception
	 * @ReturnType：void
	 * @Description： 获取树形资源对象集合
	 * @Creator：zhengjing
	 * @CreateTime：2017年5月24日下午5:09:03
	 * @Modifier：
	 * @ModifyTime：
	 */
	@Override
	public void getTreeList(List<Resource> returnList, Long id,Boolean flag) throws Exception {
		List<Resource> rooList = new ArrayList<> ();
		List<Resource> resList = super.getAll();
		for (Resource res : resList) {
			if(res.getParentId().equals(id)){
				rooList.add(res);
				returnList.add(res);
			}
		}
		getChrildrenTreeNode(resList,rooList,flag);
	}
	private void getChrildrenTreeNode(List<Resource> resList, List<Resource> rooList,Boolean flag) {
		for (Resource r : rooList) {
			List<Resource> chrildrenList = chaildTreeNode(resList, r.getId(),flag);
			if(chrildrenList != null && chrildrenList.size()>0){
				r.setChildren(chrildrenList);
				getChrildrenTreeNode(resList,chrildrenList,flag);
			}
		}
	}
	private List<Resource> chaildTreeNode(List<Resource> resList, Long id,Boolean flag) {
		List<Resource> chrildrenList = new ArrayList<> ();
		for (Resource res : resList) {
			if(flag){//获取所有资源
				if(!res.getIshide() && res.getParentId().equals(id) ){
					chrildrenList.add(res); 
				}
			}else{   //获取非功能按钮资源
				if(!res.getIshide() && res.getParentId().equals(id) && !"button".equals(res.getType())){
					chrildrenList.add(res);
				}
			}
		}
		return chrildrenList;
	}
	
}
