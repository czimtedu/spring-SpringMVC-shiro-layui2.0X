package com.changhong.people.business.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.changhong.people.business.entity.system.Resource;
import com.changhong.people.business.entity.system.SysUser;
import com.changhong.people.business.service.system.IResourceService;
import com.changhong.people.common.utils.Const;
import com.changhong.people.common.utils.shiro.ShiroUtils;

/**
 * 
 * @ClassName：MainController
 * @Description：主入口（进入主界面）
 * @Author：zhengjing
 * @Date：2017年5月24日下午11:05:33
 * @version：1.0.0
 */
@Controller
public class MainController {

    @Autowired
    private IResourceService resourceService;
    
    /**
     * 
     *	方法名：index
     *	@param model
     *	@return
     *	返回类型：String
     *	说明：
     *	创建人：
     * 	创建日期：2016年11月10日下午2:38:28
     *	修改人：
     *	修改日期：
     * @throws Exception 
     */
    @RequestMapping("/")
    public String index(Model model) throws Exception {
    	SysUser user = ShiroUtils.getUserInSession();
		List<Resource>	menus = resourceService.getMenus(user);
        model.addAttribute("menus", menus);
        return Const.SYSTEM + "/main";
    } 
    
    /**
     * 
     *	方法名：welcome
     *	@return
     *	返回类型：String
     *	说明：欢迎页面（首页中间主要部分内容）
     *	创建人：
     * 	创建日期：2016年11月8日下午6:07:48
     *	修改人：
     *	修改日期：
     */
    @RequestMapping("/welcome")
    public String welcome() {
        return Const.SYSTEM + "/welcome";
    }
    /**
     * 
     * @MethodName：unauthorized
     * @return
     * @ReturnType：String
     * @Description：无权限
     * @Creator：zhengjing
     * @CreateTime：2017年5月25日下午5:00:28
     * @Modifier：
     * @ModifyTime：
     */
    @RequestMapping("/unauthorized")
    public String unauthorized() {
    	return Const.SYSTEM + "/unauthorized";
    }

}
