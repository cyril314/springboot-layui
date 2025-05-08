package com.fit.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.fit.base.BaseController;
import com.fit.base.PatternAndView;
import com.fit.entity.SysUserEntity;
import com.fit.service.SysUserService;
import com.alibaba.fastjson.JSONObject;

/**
 * 用户表
 * 
 * @author aim
 * @date 2018-03-24 12:55:40
 */
@Controller
@RequestMapping("/admin/user")
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysUserService;

	@GetMapping("/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		return new PatternAndView("admin/user/userList", request, response);
	}

	/**
	 * 列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public String listDate(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		Map<String, Object> map = getRequestParamsMap(request);
		map.put("offset", (null2Int(map.get("page")) - 1) * null2Int(map.get("limit")));
		List<SysUserEntity> listAll = sysUserService.findListAll(map);
		int findCount = sysUserService.findCount(map);
		result.put("data", listAll);
		result.put("code", 200);
		result.put("count", findCount);
		return result.toJSONString();
	}

	/**
	 * 添加/编辑
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response, Long id) {
		ModelAndView pav = new PatternAndView("admin/user/userEdit", request, response);
		if (isNotNullOrEmpty(id)) {
			SysUserEntity sysUserEntity = sysUserService.get(id);
			pav.addObject("user", sysUserEntity);
		}
		return pav;
	}

	/**
	 * 保存/更新
	 */
	@PostMapping("/save")
	@ResponseBody
	public String saveOrUpdate(SysUserEntity user) {
		return this.sysUserService.saveOrUpdate(user).toJSONString();
	}

	/**
	 * @param id
	 */
	@RequestMapping("/delete")
	public String delete(Long id) {
		sysUserService.delete(id);
		return "redirect:list";
	}
}
