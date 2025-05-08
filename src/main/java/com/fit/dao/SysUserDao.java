package com.fit.dao;

import com.fit.entity.SysUserEntity;
import com.fit.base.BaseCrudDao;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author aim
 * @date 2018-03-24 12:55:40
 */
@Mapper
public interface SysUserDao extends BaseCrudDao<SysUserEntity> {
	
}
