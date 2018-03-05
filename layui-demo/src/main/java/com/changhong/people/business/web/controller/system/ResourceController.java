package com.changhong.people.business.web.controller.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.people.business.entity.system.Resource;
import com.changhong.people.business.entity.system.Role;
import com.changhong.people.business.query.system.ResourceQuery;
import com.changhong.people.business.service.system.IResourceService;
import com.changhong.people.business.service.system.IRoleService;
import com.changhong.people.business.web.annotation.SysLog;
import com.changhong.people.business.web.controller.base.BaseController;
import com.changhong.people.common.utils.AjaxResult;
import com.changhong.people.common.utils.Const;
import com.changhong.people.common.utils.page.PageResult;

/**
 * 
 *	类名称：ResourceController
 *	类描述：资源
 *	创建人：
 *	创建时间：2016年11月10日下午3:21:58
 *	修改人：
 *	修改时间：
 *	修改备注：
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController{

    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IRoleService roleService;
    private Resource resource;
    /**
     * 
     *	方法名：listUI
     *	@param model
     *	@return
     *	返回类型：String
     *	说明：列表UI
     *	创建人：
     * 	创建日期：2016年11月10日下午3:22:51
     *	修改人：
     *	修改日期：
     * @throws Exception 
     */
    @Override
	@RequestMapping("/listUI")
    public String listUI(Model model) throws Exception {
        return Const.SYSTEM + "/resource/list";
    }
    /**
     * 
     *	方法名：treeList
     *	@return
     *	返回类型：String
     *	说明：列表UI
     *	创建人：
     * 	创建日期：2016年11月10日下午3:22:51
     *	修改人：
     *	修改日期：
     * @throws Exception 
     */
    @RequestMapping("/treeList")
    @ResponseBody
    public List<Resource> treeList() throws Exception {
    	List<Resource> resourceList = null;
    	try {
    		resourceList = resourceService.getTreelist();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resourceList;
    }
	/**
	 * 
	 *	方法名：getTreeList
	 *  @param flag  true所有，false只是菜单
	 *	@return
	 *	@throws Exception
	 *	返回类型：List<Resources>
	 *	说明：获取树形列表
	 *	创建人：
	 * 	创建日期：2016-6-24上午9:55:33
	 *	修改人：
	 *	修改日期：
	 */
	@RequestMapping("/getTreeList")
	@ResponseBody
	public List<Resource> getTreeList(Boolean flag) throws Exception{
		List<Resource> returnList = new ArrayList<>();
		try {
			resourceService.getTreeList(returnList,0L,flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnList;
	}
    /**
     * 
     *	方法名：treeGridList
     *	@param model
     *	@return
     *	@throws Exception
     *	返回类型：Map<String,Object>
     *	说明：
     *	创建人：
     * 	创建日期：2017年2月23日下午10:26:50
     *	修改人：
     *	修改日期：
     */
	@RequestMapping("/treeGridList")
	@ResponseBody
	public PageResult<Resource> treeGridList(Model model) throws Exception{
		PageResult<Resource> pageResult = null;
	    try {
	    	pageResult = new PageResult<>();
	    	pageResult.setData(resourceService.getTreelist());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageResult;
	}
    
    /**
     * 
     *	方法名：addUI
     *	@param model
     *	@return
     *	@throws Exception
     *	返回类型：String
     *	说明：新增UI
     *	创建人：
     * 	创建日期：2016年11月10日下午3:29:40
     *	修改人：
     *	修改日期：
     */
    @RequestMapping("/addUI")
    public String addUI(Model model) throws Exception {
    	resource = new Resource();
        model.addAttribute("resource", resource);
        model.addAttribute("resList", treeList());
        return Const.SYSTEM + "/resource/edit";
    }
    /**
     * 
     *	方法名：add
     *	@param resource
     *	@return
     *	返回类型：String
     *	说明：新增
     *	创建人：
     * 	创建日期：2016年11月10日下午3:29:54
     *	修改人：
     *	修改日期：
     * @throws Exception 
     */
    @SysLog(module="系统管理_资源管理",methods="新增")
    @RequiresPermissions("resource_add")
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxResult add(@Valid Resource resource,BindingResult bindingResult) throws Exception {
    	AjaxResult ajaxResult = null;
    	try {
    		if(bindingResult.hasErrors()){
				ajaxResult = new AjaxResult(false, bindingResult.getAllErrors().get(0).getDefaultMessage());
			}else{
	    		resource.setCreateTime(new Date());//创建时间
	    		resourceService.setSserchSnByResource(resource);//设置查询编号
	    		resourceService.save(resource);
	    		ajaxResult = new AjaxResult(true,"操作成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(false,"操作失败!");
		}
        return ajaxResult;
    }
    
    /**
     * 
     *	方法名：editUI
     *	@param id
     *	@param model
     *	@return
     *	@throws Exception
     *	返回类型：String
     *	说明：修改UI
     *	创建人：
     * 	创建日期：2016年11月10日下午3:30:08
     *	修改人：
     *	修改日期：
     */
    @RequestMapping("/editUI/{id}")
    public String editUI(@PathVariable("id") Long id, Model model) throws Exception {
    	resource = resourceService.getById(id);
        model.addAttribute("resource", resource);
        model.addAttribute("resList", treeList());
        return Const.SYSTEM + "/resource/edit";
    }
    /**
     * 
     *	方法名：edit
     *	@param resource
     *	@return
     *	返回类型：String
     *	说明：修改
     *	创建人：
     * 	创建日期：2016年11月10日下午3:30:21
     *	修改人：
     *	修改日期：
     * @throws Exception 
     */
    @SysLog(module="系统管理_资源管理",methods="编辑")
    @ResponseBody
    @RequiresPermissions("resource_edit")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public AjaxResult edit(@Valid Resource resource,BindingResult bindingResult) throws Exception {
    	AjaxResult ajaxResult = null;
    	try {
    		if(bindingResult.hasErrors()){
				ajaxResult = new AjaxResult(false, bindingResult.getAllErrors().get(0).getDefaultMessage());
			}else{
	    		resource.setUpdateTime(new Date());				//修改时间
	    		resourceService.setSserchSnByResource(resource);//设置查询编号
	    		resourceService.update(resource);
	    		ajaxResult = new AjaxResult(true, "操作成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(false, "操作失败!");
		}
        return ajaxResult;
    }
    /**
     * 
     *	方法名：delete
     *	@param id
     *	@return
     *	@throws Exception
     *	返回类型：String
     *	说明：删除
     *	创建人：
     * 	创建日期：2016年11月10日下午3:30:37
     *	修改人：
     *	修改日期：
     */
    @SysLog(module="系统管理_资源管理",methods="删除")
    @ResponseBody
    @RequestMapping("/delete/{id}")
    public AjaxResult delete(@PathVariable("id") Long id) throws Exception {
    	AjaxResult ajaxResult = null;
    	try {
    		if(id == null){
    			ajaxResult = new AjaxResult(false, "操作失败!");
    		}else{
    			//校验角色是否在使用，若在使用，则直接角色列表返回给用户
    			List<Role> roleList = roleService.getByResourceId(id);
    			if(roleList != null && roleList.size()>0){
    				StringBuffer releNameStr = new StringBuffer();
    				for(int i=0;i<roleList.size();i++){
    					if(i>0) {
							releNameStr.append(",");
						}
    					releNameStr.append(roleList.get(i).getRoleName());
    				}
    				ajaxResult = new AjaxResult(false, "操作失败!",releNameStr);
    			}else{
    				//执行删除
    				resourceService.deleteById(id);
    				ajaxResult = new AjaxResult(true, "操作成功!");
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(false, "操作成功!");
		}
        return ajaxResult;
    }
    /**
     * 
     *	方法名：getPermissionsByRoleId
     *	@param id
     *	@return
     *	@throws Exception
     *	返回类型：List<Resource>
     *	说明：根据角色Id获取角色权限
     *	创建人：
     * 	创建日期：2016年12月9日下午6:05:21
     *	修改人：
     *	修改日期：
     */
    @ResponseBody
    @RequestMapping("getPermissionsByRoleId")
    public AjaxResult getPermissionsByRoleId(String roleId) throws Exception {
    	AjaxResult ajaxResult =  null ;
    	try {
    		List<Resource> mypermision  = resourceService.getPermissionsByRoleIds(roleId);
    		ajaxResult = new AjaxResult(true, mypermision);;
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult = new AjaxResult(false,"查询失败!");;
		}
    	return ajaxResult;
    }
    /**
     * 
     *	方法名：pageList
     *	@param resourceQuery
     *	@param model
     *	@return
     *	@throws Exception
     *	返回类型：PageResult<Resource>
     *	说明：
     *	创建人：
     * 	创建日期：2017年3月6日上午11:22:21
     *	修改人：
     *	修改日期：
     */
    @RequestMapping("/pagelist")
    @ResponseBody
    public PageResult<Resource> pageList(ResourceQuery resourceQuery,Model model) throws Exception {
    	PageResult<Resource> resourceList = null;
    	try {
    		resourceList = resourceService.findPage(resourceQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return resourceList;
    }
    /**
     * 
     *	方法名：isResKeyExist
     *	@param resKey
     *	@return
     *	@throws Exception
     *	返回类型：AjaxResult
     *	说明：唯一标识是否存在
     *	创建人：
     * 	创建日期：2017年3月8日下午4:25:08
     *	修改人：
     *	修改日期：
     */
    @RequestMapping("/isResKeyExist")
    @ResponseBody
    public AjaxResult isResKeyExist(String resKey,String resId) throws Exception{
    	HashMap<String, Object> params = new HashMap<>();
    	List<Resource> resList = null;
    	Boolean result = null;
    	if(StringUtils.isNotBlank(resKey)){
    		params.put("resKey", resKey);
    		resList = resourceService.getByWhere(params);
    		if(StringUtils.isNotBlank(resId)){
    			result = (resList !=null && resList.size()>0 && !resId.equals(resList.get(0).getId().toString()))?true:false;
    		}else{
    			result = (resList !=null && resList.size()>0)?true:false;
    		}
    	}
    	return new AjaxResult(result, null);
    }


}
