package com.changhong.people.business.web.controller.system;

import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.changhong.people.common.utils.AjaxResult;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

//@Controller
@RequestMapping(value = "/memcache")
public class MemcacheController {
	
	@Autowired
	MemcachedClient  memcachedClient ;
	
	@RequestMapping(value = "/index")
	@ResponseBody
	public AjaxResult index(Model model) {
		try {
			// 设置/获取  
			memcachedClient.set("zlex", 36000, "set/get");
			//System.out.println(memcachedClient.get("zlex"));
			// 替换  
			memcachedClient.replace("zlex", 36000, "replace");
			//System.out.println(memcachedClient.get("zlex"));
			// 移除  
			memcachedClient.delete("zlex");
			
			//System.out.println(memcachedClient.get("zlex"));
			
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemcachedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return new AjaxResult(true,"成功了");
	}
}
