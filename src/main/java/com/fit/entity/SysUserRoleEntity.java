package com.fit.entity;

import com.fit.base.BaseEntity;


/**
 * 角色用户关系表
 * 
 * @author aim
 * @date 2018-03-24 12:55:40
 */
public class SysUserRoleEntity extends BaseEntity<SysUserRoleEntity> {
	private static final long serialVersionUID = 1L;
	
	//角色ID
	private Long roleId;
	//用户ID
	private Long userId;

	/**
	 * 设置：角色ID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取：角色ID
	 */
	public Long getRoleId() {
		return roleId;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Long getUserId() {
		return userId;
	}
}
