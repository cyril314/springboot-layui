package com.fit.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.fit.service.SysUserRoleService;

/**
 * 角色用户关系表
 * 
 * @author aim
 * @date 2018-03-24 12:55:40
 */
@Controller
@RequestMapping("/sysuserrole")
public class SysUserRoleController {

	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * @param id
	 */
	@RequestMapping("/delete")
	public String delete(Long id) {
		sysUserRoleService.delete(id);
		return "redirect:list.do";
	}	
}
