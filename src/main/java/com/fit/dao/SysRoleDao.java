package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleDao extends BaseCrudDao<SysRole> {
}