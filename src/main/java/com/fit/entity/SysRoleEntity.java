package com.fit.entity;

import com.fit.base.BaseEntity;


/**
 * 角色表
 * 
 * @author aim
 * @date 2018-03-24 12:55:40
 */
public class SysRoleEntity extends BaseEntity<SysRoleEntity> {
	private static final long serialVersionUID = 1L;
	
	//角色名字
	private String roleName;
	//角色说明
	private String roleDesc;
	//是否被禁用 0禁用1正常
	private Integer enabled;
	//是否是超级权限 0非1是
	private Integer isys;

	/**
	 * 设置：角色名字
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 获取：角色名字
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * 设置：角色说明
	 */
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	/**
	 * 获取：角色说明
	 */
	public String getRoleDesc() {
		return roleDesc;
	}
	/**
	 * 设置：是否被禁用 0禁用1正常
	 */
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	/**
	 * 获取：是否被禁用 0禁用1正常
	 */
	public Integer getEnabled() {
		return enabled;
	}
	/**
	 * 设置：是否是超级权限 0非1是
	 */
	public void setIsys(Integer isys) {
		this.isys = isys;
	}
	/**
	 * 获取：是否是超级权限 0非1是
	 */
	public Integer getIsys() {
		return isys;
	}
}
