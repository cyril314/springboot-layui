package com.fit.config.shiro;

import com.fit.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author AIM
 * @Des 自定义用户拦截器
 * @DATE 2018/3/6
 */
@Slf4j
public class DefaultUserFilter extends UserFilter {

    @Override
    protected void redirectToLogin(ServletRequest req, ServletResponse resp) throws IOException {
        log.debug("=== 访问DefaultUserFilter =====>redirectToLogin");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String loginUrl = getLoginUrl();
        String uri = request.getRequestURI();
        if (uri.startsWith("/admin") || uri.startsWith("/admin/")) {
            loginUrl = WebUtil.ADMIN_LOGIN_URL;
        }
        log.info("访问用户拦截器重定向到登录接口：{}", "跳转到=>" + loginUrl);
        WebUtils.issueRedirect(request, response, loginUrl);
    }
}