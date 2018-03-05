package com.changhong.people.business.service.impl.system;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.changhong.people.business.dao.system.ISystemDictionaryDao;
import com.changhong.people.business.entity.system.SystemDictionary;
import com.changhong.people.business.service.system.ISystemDictionaryService;
import com.changhong.people.common.service.impl.BaseServiceImpl;


@Service
public class SystemDictionaryServiceImpl extends BaseServiceImpl<SystemDictionary> implements ISystemDictionaryService {
	@Autowired
	private ISystemDictionaryDao systemDictionaryDao;
	@Override
	public Integer getCodeCount(String code) {
		Integer codeCount = systemDictionaryDao.getCodeCount(code);
		return codeCount;
	}


}
