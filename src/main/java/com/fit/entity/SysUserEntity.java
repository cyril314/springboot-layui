package com.fit.entity;

import com.fit.base.BaseEntity;


/**
 * 用户表
 * 
 * @author aim
 * @date 2018-03-24 12:55:40
 */
public class SysUserEntity extends BaseEntity<SysUserEntity> {
	private static final long serialVersionUID = 1L;
	
	//用户姓名
	private String name;
	//登陆用户名(登陆号)
	private String username;
	//用户密码
	private String password;
	//描述
	private String desc;
	//是否被禁用 0禁用1正常
	private Integer enabled = 0;
	//是否是超级用户 0非1是
	private Integer isys = 0;

	/**
	 * 设置：用户姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：用户姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：登陆用户名(登陆号)
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：登陆用户名(登陆号)
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：用户密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：用户密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：描述
	 */
	public String getDesc() {
		return desc;
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
	 * 设置：是否是超级用户 0非1是
	 */
	public void setIsys(Integer isys) {
		this.isys = isys;
	}
	/**
	 * 获取：是否是超级用户 0非1是
	 */
	public Integer getIsys() {
		return isys;
	}
}
