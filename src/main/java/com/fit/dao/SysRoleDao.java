package com.fit.dao;

import com.fit.entity.SysRoleEntity;
import com.fit.base.BaseCrudDao;

import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表
 * 
 * @author aim
 * @date 2018-03-24 12:54:23
 */
@Mapper
public interface SysRoleDao extends BaseCrudDao<SysRoleEntity> {
	
}
