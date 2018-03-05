package com.changhong.people.business.query.system;

import java.util.List;

import com.changhong.people.common.utils.page.ObjectQuery;


/**
 * 
 * @ClassName：SysUserQuery
 * @Description：系统用户
 * @Author：zhengjing
 * @Date：2017年5月25日下午4:41:34
 * @version：1.0.0
 */
public class SysUserQuery extends ObjectQuery {

	private static final long serialVersionUID = -3817864020601888738L;
	//--------------------------------查询对象属性----------------------------
	private Long id;                                //ID
	private String account;                         //登录账号
	private String roleName;						//角色名称
	private int status;  						//是否启用
	private String column = "create_time";     					//排序字段
	private String sort = "desc";       					//desc/aes
	private Boolean isAdmin; 	   					//是否超级管理员
	
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

}
