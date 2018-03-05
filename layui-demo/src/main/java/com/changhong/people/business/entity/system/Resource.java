package com.changhong.people.business.entity.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.changhong.people.common.entity.BaseEntity;


/**
 * 
 *	类名称：Resource
 *	类描述：资源
 *	创建人：
 *	创建时间：2016年11月8日下午5:53:25
 *	修改人：
 *	修改时间：
 *	修改备注：
 */
public class Resource extends BaseEntity {
	private static final long serialVersionUID = 1L;
	//---------------------基本信息--------------------------
	@Length(max=10,message="资源名称长度不能超过10个字符!")
	@NotEmpty(message="资源名称不能为空!")
	private String resName;									//资源名称
	@NotNull(message="父级菜单不能为空!")
	private Long parentId;									//父级ID
	private String searchSn;					    		//查询编号（层级关系）
	@Length(max=20,min=4,message ="唯一标识长度为4~20个字符!")
	@NotEmpty(message="唯一标识不能为空!")
	private String resKey;  								//资源标识（唯一标识）
	@NotEmpty(message="菜单类型不能为空!")
	private String type;									//菜单类型（munu导航菜单、button菜单按钮）
	@NotEmpty(message="URL地址不能为空!")
	private String resUrl;									//URL地址
	@NotNull(message="等级不能为空!")
	private Integer level;									//等级
	private String icon;									//小图标
	private Boolean ishide = Boolean.FALSE;					//是否隐藏（1是，0否）
	private Date createTime;             					//创建时间
    private Date updateTime;             					//修改时间
	private String btnHtml;						    		//按钮html代码
	@Length(max=5,message ="描述长度不能超过180个字符!")
	private String description;								//描述
	private Integer btnType;							    //功能按钮类型（1.功能按钮、2.超链接）
	//---------------------关联对象--------------------------
	private String childrenIds ;							//子菜单Ids
	private List<Resource> children = new ArrayList<>();	//子菜单（资源）对象
	private Boolean chkDisabled = false;					// 设置禁用节点
	private Boolean checked = false;						//设置选中节点checked

	//-----------------------------------------------------------------------------------------------------
	
	public Integer getBtnType() {
		return btnType;
	}
	public void setBtnType(Integer btnType) {
		this.btnType = btnType;
	}
	public String getResName() {
		return resName;
	}
	public String getSearchSn() {
		return searchSn;
	}
	public void setSearchSn(String searchSn) {
		this.searchSn = searchSn;
	}
	public String getBtnHtml() {
		return btnHtml;
	}
	public void setBtnHtml(String btnHtml) {
		this.btnHtml = btnHtml;
	}
	public String getName() {
		return resName;
	}
	public String getText() {
		return resName;
	}
	public String getChildrenIds() {
		return childrenIds;
	}
	public void setChildrenIds(String childrenIds) {
		this.childrenIds = childrenIds;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getResKey() {
		return resKey;
	}
	public void setResKey(String resKey) {
		this.resKey = resKey;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResUrl() {
		return resUrl;
	}
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Boolean getIshide() {
		return ishide;
	}
	public void setIshide(Boolean ishide) {
		this.ishide = ishide;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Resource> getChildren() {
		return children;
	}
	public void setChildren(List<Resource> children) {
		this.children = children;
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
	public boolean areRootNode() {
		return this.parentId == 0;
	}
	public Boolean getChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(Boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	

}
