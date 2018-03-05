package com.changhong.people.business.entity.system;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.changhong.people.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @ClassName：SysUser
 * @Description：
 * @author：yuanyong
 * @Date：2017年5月15日上午10:27:19
 * @version：1.0.0
 */

public class SysUser  extends BaseEntity{

	private static final long serialVersionUID = 9111270698891013963L;
	//-----------------基本信息---------------
	private String account;							//账号
	private String name;							//姓名
	private String mobile;							//手机号
	private String password;						//密码
	private Integer status;							//状态(0启用，1禁用)
	private String salt; 			        		//盐（加密私盐）
	private Boolean locked = Boolean.FALSE; 		//状态（1.true锁定，0.false正常）
	private Date createTime;						//创建时间
	private Date updateTime;						//修改时间
	private String remark;						    //备注
	private Integer passStatus;    //状态(0初始，1改动)
	//-----------------关联对象---------------
	private Long roleId;							//角色ID
	private String code;						//城市编号
    private Role role; 								//子角色集合对象
    
    
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getPassStatus() {
		return passStatus;
	}
	public void setPassStatus(Integer passStatus) {
		this.passStatus = passStatus;
	}
	public String getCredentialsSalt() {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		String sdate = sdf.format(createTime);
		return sdate + salt;
	}
    public String getAccount() {
    	return account;
    }
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
    
}
