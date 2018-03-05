package com.changhong.people.business.query.system;

import com.changhong.people.common.utils.page.ObjectQuery;


/**
 * 
 * @ClassName：RoleQuery
 * @Description：角色
 * @Author：zhengjing
 * @Date：2017年5月24日下午4:03:55
 * @version：1.0.0
 */
public class RoleQuery extends ObjectQuery {
	private static final long serialVersionUID = -6515513819850480553L;
	// --------------------------------查询对象属性----------------------------
	private Long id;
	private String roleName; // 角色名称
	private String roleKey;
	private String column; // 排序字段
	private String sort; // desc/aes //desc/aes
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	public String getRoleKey() {
		return roleKey;
	}

	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		if(column.equals("updateTime")){
			this.column = "update_time";
		}else if(column.equals("createTime")){
			this.column = "create_time";
		}else{
			this.column = column;
		}
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}


	// ------------------------------------------------------------------------

}
