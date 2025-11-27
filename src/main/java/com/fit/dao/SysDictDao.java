package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.SysDict;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDictDao extends BaseCrudDao<SysDict> {
}