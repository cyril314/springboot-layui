package com.fit.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.fit.service.SysRoleService;

/**
 * 角色表
 * 
 * @author aim
 * @date 2018-03-24 12:55:40
 */
@Controller
@RequestMapping("/admin/role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * @param id
	 */
	@RequestMapping("/delete")
	public String delete(Long id) {
		sysRoleService.delete(id);
		return "redirect:list.do";
	}	
}
