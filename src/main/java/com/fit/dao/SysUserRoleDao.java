package com.fit.dao;

import com.fit.entity.SysUserRoleEntity;
import com.fit.base.BaseCrudDao;

import org.apache.ibatis.annotations.Mapper;

/**
 * 角色用户关系表
 * 
 * @author aim
 * @date 2018-03-24 12:55:40
 */
@Mapper
public interface SysUserRoleDao extends BaseCrudDao<SysUserRoleEntity> {
	
}
