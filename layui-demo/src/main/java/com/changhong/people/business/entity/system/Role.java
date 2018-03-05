package com.changhong.people.business.entity.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.changhong.people.common.entity.BaseEntity;


/**
 * 
 * 类名称：Role 类描述：角色 创建人： 创建时间：2016年11月8日下午5:53:46 修改人： 修改时间： 修改备注：
 */
public class Role extends BaseEntity {
	
	private static final long serialVersionUID = 6601338670124559952L;
	// ---------------------基本信息-----------------
	private String roleKey; 								// 角色标识（唯一标识）
	private String roleName; 								// 角色名称
	private Boolean available = Boolean.TRUE; 				// 是否可用(1.true正常，0.false无效)
	private Date createTime; 								// 创建时间
	private Date updateTime; 								// 修改时间
	private String description; 							// 描述
	// ---------------------关联对象-----------------
	private String resourceIds; 							// 拥有的资源ID
	private List<Resource> resources = new ArrayList<>(); 	// 拥有的资源集合对象

	public String getRoleKey() {
		return roleKey;
	}
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	public List<Resource> getResources() {
		return resources;
	}
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	
}
