package com.changhong.people.common.utils.shiro;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.config.Ini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.changhong.people.business.entity.system.Resource;
import com.changhong.people.business.service.system.IResourceService;

/**
 * 权限配置
 * @ClassName ChainDefinitionSectionMetaSource
 * @Description TODO
 * @author 向阳
 * @Date 2017年1月17日 下午3:58:21
 * @version 1.0.0
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {
	
	private Logger LOGGER = LoggerFactory.getLogger(ChainDefinitionSectionMetaSource.class);
	
	@Autowired
	private IResourceService resourceService; 
	
	//xml中指定的资源权限配置
	private String filterChainDefinitions = null;
	
	@Override
	public Ini.Section getObject() throws Exception {
		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		try {
			List<Resource> rsList = resourceService.getAll();
			for (Resource resources : rsList) {
				// /path=perms["user:create"]
				if (StringUtils.isNotEmpty(resources.getResUrl()+"") && StringUtils.isNotEmpty(resources.getResKey()+"")&&!resources.getResUrl().equals("/")) {
					if(StringUtils.isBlank(resources.getResUrl()) || StringUtils.isBlank(resources.getResKey())){
						continue;
					}
					String permission = "perms[" + resources.getResKey() + "]";
					LOGGER.debug("{0}======>{1}",resources.getResUrl(),permission);
					//key:resUrl，value:resKey
					section.put(resources.getResUrl(), permission);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.debug("初始化资源失败!");
		}
		return section;
	}

	
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	@Override
	public Class<?> getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
}
