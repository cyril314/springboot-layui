package com.fit.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fit.base.BaseController;
import com.fit.base.PatternAndView;
import com.fit.entity.MenuNode;
import com.fit.service.MenuNodeService;
import com.fit.util.SystemUtil;

/**
 * @AUTO
 * @FILE IndexController.java
 * @DATE 2018-3-23 下午10:32:27
 * @Author AIM
 */
@Slf4j
@Controller
public class IndexController extends BaseController {

    @Autowired
    private MenuNodeService menuService;

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @GetMapping(value = {"/", "/index"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        log.info("访问首页!!!");
        ModelAndView pav = new PatternAndView("admin/index", request, response);
        List<MenuNode> menus = menuService.getUserMenuNodes(Arrays.asList(Long.valueOf("1")), request);
        pav.addObject("menus", menus);
        return pav;
    }

    @GetMapping("/main")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView pav = new PatternAndView("admin/welcome", request, response);
        pav.addObject("os", SystemUtil.getOsInfo());
        return pav;
    }
}