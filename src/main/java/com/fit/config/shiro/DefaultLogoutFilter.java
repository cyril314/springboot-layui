package com.fit.config.shiro;

import com.fit.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author AIM
 * @Des
 * @DATE 2018/3/14
 */
@Slf4j
public class DefaultLogoutFilter extends LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        log.debug("=== 访问DefaultLogoutFilter =====>preHandle");
        return super.preHandle(request, response);
    }

    protected String getRedirectUrl(ServletRequest req, ServletResponse resp, Subject subject) {
        log.debug("=== 访问DefaultLogoutFilter =====>getRedirectUrl");
        HttpServletRequest request = (HttpServletRequest) req;
        String redirectUrl = getRedirectUrl();
        if (request.getRequestURI().startsWith("/admin") || request.getRequestURI().startsWith("/admin/")) {
            redirectUrl = WebUtil.ADMIN_LOGIN_URL;
        }
        return redirectUrl;
    }
}