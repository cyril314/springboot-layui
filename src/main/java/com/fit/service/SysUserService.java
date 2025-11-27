package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.SysUserDao;
import com.fit.entity.SysUser;
import org.springframework.stereotype.Service;

@Service
public class SysUserService extends BaseCrudService<SysUserDao, SysUser> {
}