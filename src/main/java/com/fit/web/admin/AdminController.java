package com.fit.web.admin;

import com.fit.base.BaseController;
import com.fit.entity.MenuNode;
import com.fit.service.MenuNodeService;
import com.fit.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String index(HttpServletRequest request, Model model) {
        List<MenuNode> menus = menuService.getUserMenuNodes(Arrays.asList(Long.valueOf("1")), request);
        model.addAttribute("menus", menus);
        return "admin/index.html";
    }

    @GetMapping("/welcome")
    public String main(Model model) {
        model.addAttribute("os", SystemUtil.getOsInfo());
        return "admin/welcome.html";
    }
}