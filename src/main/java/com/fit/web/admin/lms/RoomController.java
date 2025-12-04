package com.fit.web.admin.lms;

import com.fit.base.AjaxResult;
import com.fit.base.BaseController;
import com.fit.entity.LmsRoom;
import com.fit.entity.SysFiles;
import com.fit.service.LmsRoomService;
import com.fit.service.SysFilesService;
import com.fit.util.BeanUtil;
import com.fit.util.DateUtils;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @AUTO 考卷控制器
 * @Author AIM
 * @DATE 2019/4/26
 */
@Controller
@RequestMapping("/admin/lms/room")
public class RoomController extends BaseController {

    private static String PREFIX = "/admin/lms/room/";

    @Autowired
    private LmsRoomService service;
    @Autowired
    private SysFilesService filesService;

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
        List<LmsRoom> list = this.service.findList(params);
        int count = this.service.findCount(params);
        return AjaxResult.tables(count, list);
    }

    /**
     * 添加编辑页面
     */
    @GetMapping("/edit")
    public String editView(Long id, Model model) {
        if (OftenUtil.isNotEmpty(id)) {
            LmsRoom bean = this.service.get(id);
            model.addAttribute("bean", bean);
            SysFiles sysFiles = this.filesService.get(bean.getImgId());
            String imgData = "/images/exam.png";
            String path = String.format("/%s/%s/%s", uploadDir, DateUtils.data4ToShortStr(sysFiles.getCtime()), sysFiles.getFileName());
            if (sysFiles != null && Files.exists(Paths.get(System.getProperty("user.dir") + path))) {
                imgData = path;
            }
            model.addAttribute("image", imgData);
        }
        return PREFIX + "edit";
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ResponseBody
    public Object save(LmsRoom bean) {
        LmsRoom entity = this.service.get(bean.getId());
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
        if (null == entity) {
            bean.setCtime(new Date());
            bean.setCuser(userId);
            this.service.save(bean);
        } else {
            BeanUtil.copyProperties(bean, entity);
            entity.setEtime(new Date());
            entity.setEuser(userId);
            this.service.update(entity);
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