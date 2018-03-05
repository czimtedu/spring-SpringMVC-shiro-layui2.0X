package com.changhong.people.business.web.controller.system;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.changhong.people.business.entity.system.Resource;
import com.changhong.people.business.entity.system.Role;
import com.changhong.people.business.query.system.RoleQuery;
import com.changhong.people.business.service.system.IResourceService;
import com.changhong.people.business.service.system.IRoleService;
import com.changhong.people.business.web.controller.base.BaseController;
import com.changhong.people.common.utils.AjaxResult;
import com.changhong.people.common.utils.Const;
import com.changhong.people.common.utils.LogUtils;
import com.changhong.people.common.utils.page.PageResult;

/**
 * 
 * @ClassName：RoleController
 * @Description：角色
 * @Author：zhengjing
 * @Date：2017年5月24日下午7:53:59
 * @version：1.0.0
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

    
	@Autowired
    private IRoleService roleService;
    @Autowired
    IResourceService resourceService;
    private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);
    /**
     * 
     * @MethodName：listUI
     * @return
     * @throws Exception
     * @ReturnType：String
     * @Description：列表UI
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午10:58:09
     * @Modifier：
     * @ModifyTime：
     */
    @RequestMapping("/listUI")
    @Override
  	public String listUI(Model model) throws Exception{
    	LogUtils.info(logger,"角色列表进入，入参{}",model);
          return Const.SYSTEM + "/role/list";
  	}

    /**
     * 
     * @MethodName：pageList
     * @param roleQuery
     * @return
     * @throws Exception
     * @ReturnType：PageResult<Role>
     * @Description：分页查询列表
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午10:58:25
     * @Modifier：
     * @ModifyTime：
     */
    @RequestMapping("/pagelist")
	@ResponseBody
	public PageResult<Role> pageList(RoleQuery roleQuery) throws Exception {
    	LogUtils.info(logger,"角色查询进入，入参{}",roleQuery);
    	PageResult<Role> roleList = null;
	  	try {
  			roleList = roleService.findPage(roleQuery);
  			LogUtils.info(logger,"角色查询成功");
		} catch (Exception e) {
			LogUtils.error(logger,"角色管理-查询失败", e);
			LogUtils.error(logger,"角色查询失败");
		}
	  	return roleList;
	}
    /**
     * 
     * @MethodName：addUI
     * @param model
     * @return
     * @throws Exception
     * @ReturnType：String
     * @Description：新增UI
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午10:58:42
     * @Modifier：
     * @ModifyTime：
     */
    @RequestMapping("/addUI")
    public String addUI(Model model) throws Exception {
    	Role role  = new Role();
    	role.setCreateTime(new Date());
        model.addAttribute("role", role);
        return Const.SYSTEM + "/role/edit";
    }
    /**
     * 
     * @MethodName：add
     * @param role
     * @return
     * @throws Exception
     * @ReturnType：AjaxResult
     * @Description：新增
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午10:58:55
     * @Modifier：
     * @ModifyTime：
     */
    @RequiresPermissions("role_add")
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public AjaxResult add(Role role) throws Exception {
    	LogUtils.info(logger,"角色添加进入，入参{}",role);
    	AjaxResult ajaxResult = null;
    	try {
    		role.setCreateTime(new Date());
    		role.setUpdateTime(new Date());
			roleService.save(role);
			ajaxResult = new AjaxResult(true,"操作成功!");
			LogUtils.info(logger,"角色添加成功");
			LogUtils.writeOperateLog("角色管理","添加"+role.getRoleName()+"角色成功",role.getRoleName());
		} catch (Exception e) {
			ajaxResult = new AjaxResult(false,"操作失败!");
			LogUtils.error(logger,"角色管理-添加角色出错", e);
			LogUtils.error(logger,"角色添加失败");
		}
        return ajaxResult;
    }
    /**
     * 
     * @MethodName：toView
     * @param id
     * @param model
     * @return
     * @ReturnType：String
     * @Description：查看
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午10:59:31
     * @Modifier：
     * @ModifyTime：
     */
    @RequestMapping("/toView/{id}")
    public String toViewUI(@PathVariable Long id,Model model) throws Exception{
    	LogUtils.info(logger,"管理员详情列表进入，入参{}",id);
    	Role role = roleService.getById(id);
		model.addAttribute("role", role);
		List<Resource> permissions = resourceService.getPermissionsByRoleIds(role.getId().toString());
        model.addAttribute("permissions",JSON.toJSON(permissions));
		return Const.SYSTEM + "/role/toView";
    }
  
    /**
     * 
     * @MethodName：editUI
     * @param id
     * @param model
     * @return
     * @throws Exception
     * @ReturnType：String
     * @Description：编辑UI
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午10:59:45
     * @Modifier：
     * @ModifyTime：
     */
    @RequiresPermissions("role_edit")
    @RequestMapping("/editUI/{id}")
    public String editUI(@PathVariable Long id, Model model) throws Exception {
        Role role = roleService.getById(id);
		model.addAttribute("role", role);
		List<Resource> permissions = resourceService.getPermissionsByRoleIds(role.getId().toString());
        model.addAttribute("permissions",JSON.toJSON(permissions));
        return Const.SYSTEM + "/role/edit";
    }
   /**
    * 
    * @MethodName：edit
    * @param role
    * @return
    * @throws Exception
    * @ReturnType：AjaxResult
    * @Description：编辑
    * @Creator：zhengjing
    * @CreateTime：2017年5月24日下午11:00:10
    * @Modifier：
    * @ModifyTime：
    */
    @ResponseBody
    @RequiresPermissions("role_edit")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public AjaxResult edit(Role role) throws Exception {
    	LogUtils.info(logger,"角色编辑进入，入参{}",role);
    	AjaxResult ajaxResult = null;
    	try {
    		role.setUpdateTime(new Date());
    		roleService.update(role);
    		ajaxResult = new AjaxResult(true, "操作成功!");
    		LogUtils.writeOperateLog("角色管理","编辑"+role.getRoleName()+"角色成功",role.getRoleName());
    		LogUtils.info(logger,"角色编辑成功");
		} catch (Exception e) {
			ajaxResult = new AjaxResult(false, "操作失败!");
			LogUtils.error(logger,"角色管理-编辑失败", e);
			LogUtils.error(logger,"角色编辑失败");
		}
        return ajaxResult;
    }
    /**
     * 
     * @MethodName：delete
     * @param id
     * @return
     * @throws Exception
     * @ReturnType：AjaxResult
     * @Description：删除
     * @Creator：zhengjing
     * @CreateTime：2017年5月24日下午11:01:55
     * @Modifier：
     * @ModifyTime：
     */
    @ResponseBody
    @RequestMapping(value = "/delete{id}")
    public AjaxResult delete(Long id) throws Exception {
    	AjaxResult ajaxResult = null;
    	LogUtils.info(logger,"角色删除进入，入参{}",id);
    	try {
    		roleService.deleteById(id);
    		ajaxResult = new AjaxResult(true, "操作成功!");
    		LogUtils.info(logger,"角色删除成功");
    		LogUtils.writeOperateLog("角色管理","角色删除成功",id);
		} catch (Exception e) {
			ajaxResult = new AjaxResult(false, "操作失败!");
			LogUtils.error(logger,"角色管理-删除失败", e);
			LogUtils.error(logger,"角色删除失败");
		}
        return ajaxResult;
    }
    @ResponseBody
    @RequestMapping("/getRoleName")
    public AjaxResult getRoleName(String roleName,Long id){
		LogUtils.info(logger,"验证角色是否已经存在进入，入参{}",roleName);
    	AjaxResult ajaxResult = null;
    	Role role = null;
		try {
			if(!StringUtils.isBlank(roleName)){
				role = roleService.getByRoleName(roleName);
			}
			if(role != null && !role.getId().equals(id)){
	    		ajaxResult = new AjaxResult(true,"true");
			}else{
	    		ajaxResult = new AjaxResult(true,"false");
			}
			LogUtils.info(logger,"角色验证账户成功");
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.error(logger,"角色管理-判断角色是否存在出错", e);
			LogUtils.error(logger,"角色验证账户失败");
		}
		return ajaxResult;
    }
   
}
