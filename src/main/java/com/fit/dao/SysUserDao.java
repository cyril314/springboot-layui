package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserDao extends BaseCrudDao<SysUser> {
}