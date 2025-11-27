package com.fit.web.front;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fit.base.BaseController;
import com.fit.entity.MenuNode;
import com.fit.service.MenuNodeService;

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

    @GetMapping(value = {"/", "/index"})
    public String index(HttpServletRequest request, Model model) {
        List<MenuNode> menus = menuService.getUserMenuNodes(Arrays.asList(Long.valueOf("1")), request);
        model.addAttribute("menus", menus);
        return "front/index";
    }
}