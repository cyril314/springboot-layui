package com.fit.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import com.fit.util.JSONUtil;

/**
 * @AUTO 控制层基类
 * @FILE BaseController.java
 * @DATE 2018-3-23 下午2:38:18
 * @Author AIM
 */
@Slf4j
public class BaseController {

    protected String getJsonObject(Object obj) {
        return JSONUtil.parseObject2Json(obj);
    }

    /**
     * 将数据保存到session
     *
     * @param res
     * @param key   存入session键名
     * @param value 存入session值
     */
    protected void setValue2Session(HttpServletRequest res, String key, Object value) {
        res.getSession().setAttribute(key, value);
    }

    /**
     * 从session中获取数据
     *
     * @param res
     * @param key 取出session键名
     */
    protected Object getValueFromSession(HttpServletRequest res, String key) {
        return res.getSession().getAttribute(key);
    }

    /**
     * 将数据从session中删除
     *
     * @param res
     * @param key 删除session键名
     */
    protected void removeValueFromSession(HttpServletRequest res, String key) {
        res.getSession().removeAttribute(key);
    }

    /**
     * 将json数据写到客户端
     */
    protected void writeJson(HttpServletResponse response, String json) {
        try {
            log.info("服务器返回信息：==>" + json);
            this.getPrintWriter(response).write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据以json格式写到客户端
     */
    protected void writeObjectAsJson(HttpServletResponse response, Object obj) {
        try {
            log.info("服务器返回信息：==>" + this.getJsonObject(obj));
            this.getPrintWriter(response).write(this.getJsonObject(obj));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取书写器
     */
    protected PrintWriter getPrintWriter(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        return response.getWriter();
    }

    /**
     * 不为空转换为INT
     *
     * @param s
     */
    protected int null2Int(Object s) {
        int v = 0;
        if (s != null) try {
            v = Integer.parseInt(s.toString());
        } catch (Exception localException) {
        }
        return v;
    }

    /**
     * <将obj转换为string，如果obj为null则返回defaultVal>
     *
     * @param obj        需要转换为string的对象
     * @param defaultVal 默认值
     */
    protected String null2String(Object obj, String defaultVal) {
        return (obj != null) ? obj.toString().trim() : defaultVal;
    }

    /**
     * 获取请求的所有参数
     */
    protected Map<String, Object> getRequestParamsMap(HttpServletRequest request) {
        // 返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try {
            // 参数Map
            Map<String, String[]> properties = request.getParameterMap();
            String value = "";
            for (Iterator<?> iter = properties.entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry<?, ?> element = (Map.Entry<?, ?>) iter.next();
                Object strKey = element.getKey();
                Object strObj = element.getValue();
                if (null == strObj) {
                    value = "";
                } else if (strObj instanceof String[]) {
                    String[] values = (String[]) strObj;
                    for (int i = 0; i < values.length; i++) { // 用于请求参数中有多个相同名称
                        value = values[i] + ",";
                    }
                    value = value.substring(0, value.length() - 1);
                } else {
                    value = strObj.toString();// 用于请求参数中请求参数名唯一
                }
                returnMap.put(strKey.toString(), value);
            }
        } catch (Exception e) {
            log.error("getRequestParamsMap错误信息：{}", e);
        }
        return returnMap;
    }

    /**
     * 判断对象或对象数组中每一个对象是否不为空
     */
    protected boolean isNotNullOrEmpty(Object obj) {
        return !isNullOrEmpty(obj);
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     */
    protected boolean isNullOrEmpty(Object obj) {
        if (obj == null || "".equals(obj)) return true;

        if (obj instanceof CharSequence) return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection) return ((Collection<?>) obj).isEmpty();

        if (obj instanceof Map) return ((Map<?, ?>) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }
}