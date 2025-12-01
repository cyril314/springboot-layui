package com.fit.web.admin.lms;

import com.fit.base.AjaxResult;
import com.fit.base.BaseController;
import com.fit.entity.LmsSubject;
import com.fit.entity.LmsSubjectAnswers;
import com.fit.entity.LmsSubjectType;
import com.fit.service.LmsSubjectAnswersService;
import com.fit.service.LmsSubjectService;
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
@RequestMapping("/admin/lms/subject")
public class SubjectController extends BaseController {

    private static String PREFIX = "/admin/lms/subject/";

    @Autowired
    private LmsSubjectService service;

    /**
     * 列表页面
     */
    @GetMapping("/list")
    public String index() {
        return PREFIX + "list";
    }

    /**
     * 查询列表
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(HttpServletRequest request) {
        Map<String, Object> params = WebUtil.getRequestMap(request);
        List<LmsSubject> list = this.service.findList(params);
        int count = this.service.findCount(params);
        return AjaxResult.tables(count, list);
    }

    /**
     * 添加编辑页面
     */
    @GetMapping("/edit")
    public String editView(Long id, Model model) {
        if (OftenUtil.isNotEmpty(id)) {
            LmsSubject subject = this.service.get(id);
            model.addAttribute("subject", subject);
        }
        return PREFIX + "edit";
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ResponseBody
    public Object save(LmsSubject subject) {
        LmsSubject lmSubject = this.service.get(subject.getId());
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
        if (null == lmSubject) {
            subject.setCtime(new Date());
            subject.setCuser(userId);
            this.service.save(subject);
        } else {
            BeanUtil.copyProperties(subject, lmSubject);
            lmSubject.setEtime(new Date());
            lmSubject.setEuser(userId);
            this.service.update(lmSubject);
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
            this.service.batchDelete(ids.split(","));
            return AjaxResult.success();
        } else {
            return AjaxResult.error("参数异常");
        }
    }
}