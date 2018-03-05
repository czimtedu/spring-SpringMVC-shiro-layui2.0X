package com.changhong.people.business.dao.system;

import com.changhong.people.business.entity.system.SystemDictionary;
import com.changhong.people.common.dao.IBaseDao;

public interface ISystemDictionaryDao extends IBaseDao<SystemDictionary> {
	Integer getCodeCount(String code);

}
