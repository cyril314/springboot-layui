package com.fit.base;

import com.fit.util.JSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @AUTO 通用控制器
 * @Author AIM
 * @DATE 2019/4/19
 */
public class BaseController {

    protected final String REDIRECT = "redirect:";
    protected final String FORWARD = "forward:";

    /**
     * 将数据保存到session
     *
     * @param key   存入session键名
     * @param value 存入session值
     */
    protected void setValue2Session(HttpServletRequest res, String key, Object value) {
        res.getSession().setAttribute(key, value);
    }

    /**
     * 从session中获取数据
     *
     * @param key 取出session键名
     */
    protected Object getValueFromSession(HttpServletRequest res, String key) {
        return res.getSession().getAttribute(key);
    }

    /**
     * 将数据从session中删除
     *
     * @param key 删除session键名
     */
    protected void removeValueFromSession(HttpServletRequest res, String key) {
        res.getSession().removeAttribute(key);
    }

    protected void writeJson(HttpServletResponse response, Object data) {
        writeJson(response, JSONUtil.parseObject2Json(data));
    }

    /**
     * 将json数据写到客户端
     */
    protected void writeJson(HttpServletResponse response, String json) {
        try {
            this.getPrintWriter(response).write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取书写器
     */
    protected PrintWriter getPrintWriter(HttpServletResponse response) throws IOException {
        //response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        return response.getWriter();
    }
}