package com.fit.web.admin.lms;

import com.fit.base.AjaxResult;
import com.fit.base.BaseController;
import com.fit.entity.LmsSubjectAnswers;
import com.fit.service.LmsSubjectAnswersService;
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
@RequestMapping("/admin/lms/subject/answer")
public class SubjectAnswersController extends BaseController {

    private static String PREFIX = "/admin/lms/subject/";

    @Autowired
    private LmsSubjectAnswersService subjectAnswersService;

    /**
     * 答案列表页面
     */
    @GetMapping("/list")
    public String answers() {
        return PREFIX + "answers";
    }

    /**
     * 答案查询列表
     */
    @PostMapping("/list")
    @ResponseBody
    public Object answers(HttpServletRequest request) {
        Map<String, Object> params = WebUtil.getRequestMap(request);
        List<LmsSubjectAnswers> list = this.subjectAnswersService.findList(params);
        int count = this.subjectAnswersService.findCount(params);
        return AjaxResult.tables(count, list);
    }

    /**
     * 添加编辑页面
     */
    @GetMapping("/edit")
    public String editView(Long id, Model model) {
        if (OftenUtil.isNotEmpty(id)) {
            LmsSubjectAnswers answers = this.subjectAnswersService.get(id);
            model.addAttribute("answers", answers);
        }
        return PREFIX + "answerEdit";
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ResponseBody
    public Object save(LmsSubjectAnswers answers) {
        LmsSubjectAnswers subjectAnswers = this.subjectAnswersService.get(answers.getId());
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
        if (null == subjectAnswersService) {
            answers.setCtime(new Date());
            answers.setCuser(userId);
            this.subjectAnswersService.save(answers);
        } else {
            BeanUtil.copyProperties(answers, subjectAnswers);
            subjectAnswers.setEtime(new Date());
            subjectAnswers.setEuser(userId);
            this.subjectAnswersService.update(subjectAnswers);
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
            this.subjectAnswersService.batchDelete(ids.split(","));
            return AjaxResult.success();
        } else {
            return AjaxResult.error("参数异常");
        }
    }
}