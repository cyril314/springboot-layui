package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.LmsPaper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LmsPaperDao extends BaseCrudDao<LmsPaper> {
}