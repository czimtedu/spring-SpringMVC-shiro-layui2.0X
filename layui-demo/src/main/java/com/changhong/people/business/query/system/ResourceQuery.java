package com.changhong.people.business.query.system;

import com.changhong.people.common.utils.page.ObjectQuery;

/**
 * 
 *	类名称：ResourceQuery
 *	类描述：资源
 *	创建人：
 *	创建时间：2016年12月13日上午9:48:01
 *	修改人：
 *	修改时间：
 *	修改备注：
 */
public class ResourceQuery extends ObjectQuery {
	
	//--------------------------------查询对象属性----------------------------
	private String id;							    //资源ID
	private String resName;							//资源名称
	private String resKey;  						//资源标识（唯一标识）
	private String searchSn;  						//查询编号（层级关系）
	private String searchSnId;  						//ID
	
	private String column ;     					//排序字段
	private String sort ;       					//desc/aes
	//------------------------------------------------------------------------
	
	
	public String getResName() {
		return resName;
	}
	public String getSearchSnId() {
		return searchSnId;
	}
	public void setSearchSnId(String searchSnId) {
		this.searchSnId = searchSnId;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResKey() {
		return resKey;
	}
	public void setResKey(String resKey) {
		this.resKey = resKey;
	}
	public String getSearchSn() {
		return searchSn;
	}
	public void setSearchSn(String searchSn) {
		this.searchSn = searchSn;
	}
	
	
}
