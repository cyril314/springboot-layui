package com.fit.service;

import com.fit.base.BaseCrudService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fit.entity.SysUserEntity;
import com.fit.util.BeanUtils;
import com.fit.util.OftenUtil;
import com.fit.dao.SysUserDao;
import com.alibaba.fastjson.JSONObject;

/**
 * 用户表
 * 
 * @author aim
 * @date 2018-03-24 12:55:40
 */
@Service
public class SysUserService extends BaseCrudService<SysUserDao, SysUserEntity> {

	/**
	 * 保存/更新
	 */
	@Transactional
	public JSONObject saveOrUpdate(SysUserEntity user) {
		JSONObject result = new JSONObject();
		try {
			if (OftenUtil.isNotEmpty(user.getId())) {
				SysUserEntity oldUser = this.get(user.getId());
				BeanUtils.copyProperties(user, oldUser);
				BeanUtils.beanAttributeValueTrim(oldUser);
				this.update(oldUser);
				result.put("msg", "更新成功");
				result.put("code", 200);
			} else {
				BeanUtils.beanAttributeValueTrim(user);
				this.save(user);
				result.put("msg", "保存成功");
				result.put("code", 200);
			}
		} catch (Exception e) {
			result.put("msg", "保存或更新失败");
			result.put("code", 201);
			throw new RuntimeException();
		}

		return result;
	}
}
