package com.changhong.people.business.query.system;

import com.changhong.people.common.utils.page.ObjectQuery;

public class SystemDictionaryItemQuery extends ObjectQuery {
	private static final long serialVersionUID = -748909005984674100L;
	
	
	private String code;
	private String value;


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}
	
	
}
