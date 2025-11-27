package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.base.PatternAndView;
import com.fit.entity.MenuNode;
import com.fit.service.MenuNodeService;
import com.fit.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @AUTO
 * @FILE AdminController.java
 * @DATE 2018-3-23 下午10:32:27
 * @Author AIM
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private MenuNodeService menuService;

    @GetMapping(value = {"", "/", "/index"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        log.info("访问首页!!!");
        ModelAndView pav = new PatternAndView("admin/index.html", request, response);
        List<MenuNode> menus = menuService.getUserMenuNodes(Arrays.asList(Long.valueOf("1")), request);
        pav.addObject("menus", menus);
        return pav;
    }

    @GetMapping("/welcome")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView pav = new PatternAndView("admin/welcome.html", request, response);
        pav.addObject("os", SystemUtil.getOsInfo());
        return pav;
    }
}