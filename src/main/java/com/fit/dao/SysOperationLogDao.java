package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.SysOperationLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysOperationLogDao extends BaseCrudDao<SysOperationLog> {
}