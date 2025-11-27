package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDeptDao extends BaseCrudDao<SysDept> {
}