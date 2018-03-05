package com.changhong.people.business.service.system;

import com.changhong.people.business.entity.system.SystemDictionary;
import com.changhong.people.common.service.IBaseService;

public interface ISystemDictionaryService extends IBaseService<SystemDictionary> {
	Integer getCodeCount(String code);

}
