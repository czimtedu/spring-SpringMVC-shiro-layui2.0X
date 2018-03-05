package com.changhong.people.business.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.people.business.entity.system.Role;
import com.changhong.people.business.entity.system.SysUser;
import com.changhong.people.business.query.system.SysUserQuery;
import com.changhong.people.business.service.system.IRoleService;
import com.changhong.people.business.service.system.ISysUserService;
import com.changhong.people.business.web.controller.base.BaseController;
import com.changhong.people.common.utils.AjaxResult;
import com.changhong.people.common.utils.Const;
import com.changhong.people.common.utils.LogUtils;
import com.changhong.people.common.utils.excel.ExcelDownLoadListParamBean;
import com.changhong.people.common.utils.excel.WriteExcelUtil;
import com.changhong.people.common.utils.page.PageResult;
import com.changhong.people.common.utils.shiro.PasswordHelper;
import com.changhong.people.common.utils.shiro.ShiroUtils;

/**
 * 
 * @ClassName SysUserController
 * @Description 系统用户
 * @author yuanyong
 * @Date 2017年5月17日 下午2:50:41
 * @version 1.0.0
 */
@Controller
@RequestMapping("/sysuser")
public class SysUserController extends BaseController {
	
	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private IRoleService roleService;

	private static final Logger logger = LoggerFactory.getLogger(SysUserController.class);

	/**
	 * 
	 * @MethodName：listUI
	 * @param model
	 * @return
	 * @throws Exception
	 * @ReturnType：String
	 * @Description：列表UI
	 * @Creator：yuanyong
	 * @CreateTime：2017年5月24日下午2:46:58 @Modifier： @ModifyTime：
	 */
	@Override
	@RequestMapping("/listUI")
	public String listUI(Model model) throws Exception {
		LogUtils.info(logger, "管理员管理列表进入，入参{}", model);
		try {
		} catch (Exception e) {
			LogUtils.error(logger, "管理员管理列表-获取城市列表失败");
		}
		return Const.SYSTEM + "/user/list";
	}

	/**
	 * 
	 * @Description 跳转到修改密码页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editPassUI/{id}")
	public String toupdatePassword(Model model) throws Exception {
		return Const.SYSTEM + "/user/editPass";
	}

	/**
	 * 
	 * @Description 导出管理员管理数据
	 * @param adminQuery
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	/*@RequestMapping("/exportadmin")
	public void exportadmin(SysUserQuery sysUserQuery, HttpServletRequest request, HttpServletResponse response,
			String fileName) throws Exception {
		LogUtils.info(logger, "管理员管理导出进入，入参{}", sysUserQuery);
		List<SysUserVo> list = null;
		try {
			// 获取当前登录用户
			list = sysUserService.getByWhereVo(sysUserQuery);
			WriteExcelUtil.toExcel(list, request, response, fileName);// "管理员管理列表"
			// WriteExcelUtil.toExcel(list, head, request, response, list.size(),
			// "管理员管理数据");
			LogUtils.writeOperateLog("管理员管理", "管理员管理Excel导出");
			LogUtils.info(logger, "管理员导出成功");
		} catch (Exception e) {
			LogUtils.error(logger,"管理员管理-导出失败!服务器异常", e);
			LogUtils.error(logger, "管理员导出失败");
		}
	}*/

	/**
	 * 
	 * @MethodName：getCount
	 * @param query
	 * @return
	 * @ReturnType：AjaxResult @Description：
	 * @Creator：yuanyong
	 * @CreateTime：2017年5月20日下午3:19:50 @Modifier： @ModifyTime：
	 */
	@ResponseBody
	@RequestMapping("/getCount")
	public AjaxResult getCount(SysUserQuery sysUserQuery) {
		AjaxResult ajaxResult = null;
		try {
			// 获取当前登录用户
			String fileName = "管理员管理列表";
			Integer totalCount = sysUserService.findCount(sysUserQuery);
			Integer pageSize = 10000;// 一个Excel文件放多少条（来自配置文件）
			List<ExcelDownLoadListParamBean> list = WriteExcelUtil.getExcelDownLoadListParam(fileName, totalCount,
					pageSize);
			if (list != null && list.size() > 0) {
				Map<String, Object> map = new HashMap<>();
				map.put("list", list);
				map.put("totle", totalCount);
				ajaxResult = new AjaxResult(true, "请求成功!", map);
				LogUtils.info(logger, "请求成功!");
			} else {
				ajaxResult = new AjaxResult(false, "无数据，请重新查询再导出。");
				LogUtils.info(logger, "请求失败 !");
			}
		} catch (Exception e) {
			LogUtils.error(logger, e.getMessage(), e);
			ajaxResult = new AjaxResult(false, "请求失败 !");
		}
		return ajaxResult;
	}

	/**
	 * @Description 管理员管理分页数据
	 * @param adminQuery
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/pagelist")
	@ResponseBody
	public PageResult<SysUser> pageList(SysUserQuery sysUserQuery) throws Exception {
		LogUtils.info(logger, "管理员管理查询进入，入参{}", sysUserQuery);
		PageResult<SysUser> adminList = null;
		try {
			// 获取当前登录用户
			SysUser admin = ShiroUtils.getUserInSession();
			
			if ("admin".equals(admin.getAccount())) {
				sysUserQuery.setIsAdmin(true);
			}else{
				sysUserQuery.setIsAdmin(false);
			}
			adminList = sysUserService.findPage(sysUserQuery);
			LogUtils.info(logger, "管理员查询成功");
		} catch (Exception e) {
			LogUtils.error(logger,"管理员管理-查询失败", e);
			LogUtils.error(logger, "管理员查询失败");
		}
		return adminList;
	}

	/**
	 * @Description 编辑管理员信息
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public AjaxResult edit(SysUser sysuser) throws Exception {
		LogUtils.info(logger, "管理员管理编辑进入，入参{}", sysuser);
		AjaxResult ajaxResult = null;
		SysUser admin1 = ShiroUtils.getUserInSession();
		try {
			sysUserService.update(sysuser);
			
			//更新session中的sysuser
			SysUser refresh = sysUserService.getById(sysuser.getId());
			if(refresh.getAccount().equals(admin1.getAccount())){
				ShiroUtils.setUserInSession(refresh);
			}
			
			LogUtils.writeOperateLog("管理员管理", admin1.getName() + "编辑管理员" + sysuser.getName() + "属性",
					admin1.getAccount());
			ajaxResult = new AjaxResult(true, "操作成功!");
			LogUtils.info(logger, "管理员编辑成功");
		} catch (Exception e) {
			ajaxResult = new AjaxResult(false, "操作失败!");
			LogUtils.error(logger,"管理员管理-编辑失败", e);
			LogUtils.error(logger, "管理员编辑失败");
		}
		return ajaxResult;
	}

	/**
	 * @MethodName：statusUI
	 * @param id
	 * @param model
	 * @return
	 * @ReturnType：String
	 * @Description：修改管理员状态页面跳转
	 * @Creator：yuanyong
	 * @CreateTime：2017年5月20日下午12:00:15 @Modifier： @ModifyTime：
	 */
	@RequestMapping("/statusUI")
	public String userStatusUI(Long id, Integer status, Model model) throws Exception {
		model.addAttribute("id", id);
		model.addAttribute("status", status);
		return Const.SYSTEM + "/user/userStatus";
	}

	/**
	 * @Description 修改管理员状态
	 * @param adminId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/editststus")
	public AjaxResult ststus(Long adminId, Integer status) throws Exception {
		LogUtils.info(logger, "管理员管理编辑状态进入，入参{}", adminId, status);
		SysUser admin1 = ShiroUtils.getUserInSession();
		AjaxResult ajaxResult = null;
		try {
			SysUser admin = sysUserService.getById(adminId);
			admin.setId(adminId);
			admin.setStatus(status);
			sysUserService.updateStruts(admin);
			String statusChina = "";
			if (status == 0) {
				statusChina = "启用";
			} else {
				statusChina = "禁用";
			}
			ajaxResult = new AjaxResult(true, "true");
			LogUtils.info(logger, "管理员编辑状态成功");
		} catch (Exception e) {
			ajaxResult = new AjaxResult(false, "操作失败!");
			LogUtils.error(logger,"管理员管理-修改管理员启用/禁用出错", e);
			LogUtils.error(logger, "管理员编辑状态失败");
		}
		return ajaxResult;
	}

	/**
	 * @MethodName：resetPassUI
	 * @param id
	 * @param account
	 *            账号
	 * @param model
	 * @return
	 * @ReturnType：String
	 * @Description：重置管理员密码
	 * @Creator：yuanyong
	 * @CreateTime：2017年5月20日下午12:00:15 @Modifier： @ModifyTime：
	 */
	@RequestMapping("/resetPassUI")
	public String resetPassUI(Long id, String account, Model model) throws Exception {
		model.addAttribute("id", id);
		model.addAttribute("account", account);
		model.addAttribute("init_password", PasswordHelper.INIT_PASSWORD);
		return Const.SYSTEM + "/user/resetPass";
	}

	/**
	 * @Description 重置/修改管理员密码
	 * @param adminId
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/resetPassword")
	public AjaxResult resetPassword(Long id, String account, String password) throws Exception {
		LogUtils.info(logger, "管理员管理重置/修改密码进入，入参{}", id, account, password);
		SysUser admin = ShiroUtils.getUserInSession();
		AjaxResult ajaxResult = null;
		try {
			if (!"".equals(password) && password != null) {// 修改密码/当前用户
				admin.setPassword(password);
				admin.setPassStatus(1);
				sysUserService.updatePass(admin);
				LogUtils.writeOperateLog("管理员管理", admin.getName() + "修改密码", admin.getAccount());
			} else {// 重置密码/其他用户
				SysUser admin1 = sysUserService.getById(id);
				admin1.setPassword(PasswordHelper.INIT_PASSWORD);
				admin1.setPassStatus(0);
				sysUserService.updatePass(admin1);
				LogUtils.writeOperateLog("管理员管理", admin.getName() + "重置管理员" + admin1.getName() + "密码",
						admin1.getAccount());
			}
			ajaxResult = new AjaxResult(true, "操作成功!");
			if (admin.getId().equals(id)) {
				ajaxResult.setMsg("0");
			} else {
				ajaxResult.setMsg("1");
			}
			LogUtils.info(logger, "管理员重置/修改密码成功");
		} catch (Exception e) {
			ajaxResult = new AjaxResult(false, "操作失败!");
			LogUtils.error(logger,"管理员管理-重置/修改管理员密码出错", e);
			LogUtils.error(logger, "管理员重置/修改密码失败");
		}
		return ajaxResult;
	}

	/**
	 * @Description 判断管理员密码是否正确
	 * @param adminId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ifAdminPass")
	public AjaxResult ifAdminPass(String password) throws Exception {
		LogUtils.info(logger, "管理员验证密码是否正确进入，入参{}", password);
		AjaxResult ajaxResult = null;
		try {
			SysUser admin1 = ShiroUtils.getUserInSession();
			SysUser admin = sysUserService.getById(admin1.getId());
			if (PasswordHelper.checkPassword(admin, password)) {
				ajaxResult = new AjaxResult(true, "true");
			} else {
				ajaxResult = new AjaxResult(true, "false");
			}
			LogUtils.info(logger, "管理员验证密码成功");
		} catch (Exception e) {
			ajaxResult = new AjaxResult(false, "操作失败!");
			LogUtils.error(logger,"管理员管理-断管理员密码是否正确出错", e);
			LogUtils.error(logger, "管理员验证密码失败");
		}
		return ajaxResult;
	}

	/**
	 * @Description 跳转编辑页面
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editUI/{id}")
	public String editUI(@PathVariable("id") Long id, Model model) throws Exception {
		SysUser admin = sysUserService.getById(id);
		model.addAttribute("admin", admin);
		List<Role> roleAlls = roleService.getAll();
		model.addAttribute("roleAlls", roleAlls);
		model.addAttribute("id", id);
		if (admin.getAccount().equals("admin")) {
			return Const.SYSTEM + "/user/editAdmin";
		} else {
			return Const.SYSTEM + "/user/edit";
		}
	}

	/**
	 * @Description 跳转退出确认页面
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loginOutUI")
	public String loginOutUI(Model model) throws Exception {
		return Const.SYSTEM + "/loginOut";
	}
	
	@ResponseBody
	@RequestMapping("/getEditData")
	public AjaxResult getEditData(Long id, Model model) throws Exception {
		SysUser admin = ShiroUtils.getUserInSession();
		LogUtils.info(logger, "管理员管理-进入管理员编辑界面，账号:{}", admin.getAccount());
		AjaxResult ajaxResult = null;
		try {
			ajaxResult = new AjaxResult(true, "操作成功!");
			ajaxResult.setResultData(model);
		} catch (Exception e) {
			ajaxResult = new AjaxResult(false, "操作失败!");
			LogUtils.error(logger,"管理员管理-获取管理员编辑信息出错", e);
			LogUtils.error(logger, "取管理员编辑信息失败");
		}
		return ajaxResult;
	}

	/**
	 * @Description 跳转添加页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addUI")
	public String addUI(Model model) throws Exception {
		List<Role> roleAlls = roleService.getAll();
		model.addAttribute("roleAlls", roleAlls);
		return Const.SYSTEM + "/user/edit";
	}

	/**
	 * @Description 添加管理员管理
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public AjaxResult add(SysUser admin) throws Exception {
		LogUtils.info(logger, "管理员添加用户进入，入参{}", admin);
		AjaxResult ajaxResult = null;
		SysUser admin1 = ShiroUtils.getUserInSession();
		try {
			sysUserService.save(admin);
			LogUtils.writeOperateLog("管理员管理", admin1.getName() + "添加" + admin.getName() + "管理员", admin1.getAccount());
			ajaxResult = new AjaxResult(true, "操作成功!");
			LogUtils.info(logger, "管理员添加用户成功");
		} catch (Exception e) {
			ajaxResult = new AjaxResult(false, "操作失败!");
			LogUtils.error(logger,"管理员管理-添加系统管理员出错", e);
			LogUtils.error(logger, "管理员添加用户失败");
		}
		return ajaxResult;
	}

	/**
	 * @Description 跳转管理员详情页面
	 * @param adminId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toView")
	public String toView(Long adminId, Model model) {
		LogUtils.info(logger, "管理员详情列表进入，入参{}", adminId);
		// PasswordHelper
		try {

			model.addAttribute("admin", sysUserService.getById(adminId));
			List<Role> roleAlls = roleService.getAll();
			model.addAttribute("roleAlls", roleAlls);
			model.addAttribute("id", adminId);
			LogUtils.info(logger, "管理员详情查询列表成功");
		} catch (Exception e) {
			LogUtils.error(logger,"管理员管理-查询管理员详情页面数据出错", e);
			LogUtils.error(logger, "管理员详情查询列表失败");
		}
		return Const.SYSTEM + "/user/view";
	}

	/**
	 * @Description 判断账户是否存在
	 * @param adminId
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAccount")
	public boolean getAccount(SysUserQuery query) {
		boolean result = true;//true存在, false不存在
		if(StringUtils.isBlank(query.getAccount()))
		 {
			return !result;//不允许空校验
		}
		try {
			result = sysUserService.judgeExistOfUser(query);
		} catch (Exception e) {
			LogUtils.error(logger,e.getMessage(), e);
		}
		return !result;
	}
}
