package com.fit.web.admin.lms;

import com.fit.base.AjaxResult;
import com.fit.base.BaseController;
import com.fit.entity.LmsSubjectType;
import com.fit.entity.ZTreeNode;
import com.fit.service.LmsSubjectTypeService;
import com.fit.service.ZtreeNodeService;
import com.fit.util.BeanUtil;
import com.fit.util.OftenUtil;
import com.fit.util.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @AUTO 展示控制器
 * @Author AIM
 * @DATE 2019/4/26
 */
@Controller
@RequestMapping("/admin/lms/subject/type")
public class SubjectTypeController extends BaseController {

    private static String PREFIX = "/admin/lms/subject/";

    @Autowired
    private LmsSubjectTypeService subjectTypeService;
    @Autowired
    private ZtreeNodeService ztreeNodeService;

    /**
     * 答案列表页面
     */
    @GetMapping("/list")
    public String types() {
        return PREFIX + "typeList";
    }

    /**
     * 答案查询列表
     */
    @PostMapping("/list")
    @ResponseBody
    public Object types(HttpServletRequest request) {
        Map<String, Object> params = WebUtil.getRequestMap(request);
        List<LmsSubjectType> list = this.subjectTypeService.findList(params);
        int count = this.subjectTypeService.findCount(params);
        return AjaxResult.tables(count, list);
    }

    /**
     * 添加编辑页面
     */
    @GetMapping("/edit")
    public String editView(Long id, Model model) {
        if (OftenUtil.isNotEmpty(id)) {
            LmsSubjectType types = this.subjectTypeService.get(id);
            model.addAttribute("types", types);
        }
        return PREFIX + "typeEdit";
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ResponseBody
    public Object save(LmsSubjectType types) {
        LmsSubjectType subjectType = this.subjectTypeService.get(types.getId());
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
        if (null == subjectType) {
            types.setCtime(new Date());
            types.setCuser(userId);
            this.subjectTypeService.save(types);
        } else {
            BeanUtil.copyProperties(types, subjectType);
            subjectType.setEtime(new Date());
            subjectType.setEuser(userId);
            this.subjectTypeService.update(subjectType);
        }
        return AjaxResult.success();
    }

    /**
     * 删除
     *
     * @param ids 删除ID集合
     */
    @PostMapping("/del")
    @ResponseBody
    public Object del(String ids) {
        if (OftenUtil.isNotEmpty(ids)) {
            this.subjectTypeService.batchDelete(ids.split(","));
            return AjaxResult.success();
        } else {
            return AjaxResult.error("参数异常");
        }
    }

    /**
     * 获取tree列表
     */
    @RequestMapping("/tree")
    @ResponseBody
    public AjaxResult tree() {
        List<ZTreeNode> tree = this.ztreeNodeService.subjectTypeZtree();
        tree.add(ZTreeNode.createParent());
        return AjaxResult.tree(tree);
    }
}