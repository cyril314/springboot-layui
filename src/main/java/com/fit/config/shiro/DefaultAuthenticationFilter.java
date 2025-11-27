package com.fit.config.shiro;

import com.fit.util.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.interfaces.RSAPrivateKey;

/**
 * @Author AIM
 * @Des 自定义表单认证拦截器
 * @DATE 2018/3/5
 */
@Slf4j
public class DefaultAuthenticationFilter extends FormAuthenticationFilter {
    /**
     * 是否需要验证码
     */
    public static final String CAPTCHA_REQUIRED_KEY = "shiroCaptchaRequired";
    /**
     * 验证码错误次数
     */
    public static final String CAPTCHA_ERROR_COUNT_KEY = "shiroCaptchaErrorCount";

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, DEFAULT_CAPTCHA_PARAM);
    }

    /**
     * @Des 执行登录验证
     */
    @Override
    protected boolean executeLogin(ServletRequest servletRequest, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String ip = WebUtil.getRemoteAddr(request);// 访问IP
        String loginName = this.getUsername(request);// 登录用户名
        String loginPwd = this.getPassword(request); // 登录用户密码
        boolean rememberMe = this.isRememberMe(request);// 记住我
        String captcha = this.getCaptcha(request);
        log.info("准备登陆用户 => {}", loginName);
        HttpSession session = getSession(request);
        // 登录时，session会失效，先将错误次数取出
        Integer errorCount = (Integer) session.getAttribute(CAPTCHA_ERROR_COUNT_KEY);
        if (errorCount == null) {
            errorCount = 0;
        }
        // 登录时，session会失效，先将SavedRequest取出
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        // 权限登录验证
        DefaultVerifyToken token = getToken(loginName, loginPwd, rememberMe, session);
        try {
            if (token == null) {
                String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken must be created in order to execute a login attempt.";
                throw new IllegalStateException(msg);
            }

            setLoginUrl(request.getRequestURI());
            // 验证码验证逻辑
            if (request.getRequestURI().startsWith("/admin") || request.getRequestURI().startsWith("/admin/")) {
                token.setLoginType(LoginType.ADMIN.toString());
                Object code = session.getAttribute("code");
                if (code == null) {
                    throw new AuthenticationException("验证码错误");
                } else {
                    if (captcha == null || !code.toString().toLowerCase().equals(captcha.toLowerCase())) {
                        return onLoginFailure(token, new AuthenticationException("验证码错误"), request, response);
                    } else {
                        session.removeAttribute(CAPTCHA_REQUIRED_KEY);
                        session.removeAttribute(CAPTCHA_ERROR_COUNT_KEY);
                    }
                }
            } else {
                token.setLoginType(LoginType.CUSTOM.toString());
            }
            // 创建登录
            Subject subject = getSubject(request, response);
            // 防止session fixation attack(会话固定攻击)，让旧session失效
            if (subject.getSession(false) != null) {
                subject.logout();
            }

            subject.login(token);
            // 重要：不要调用 session.invalidate()，这会销毁当前session
            // session.invalidate();

            // 将SavedRequest放回session
            if (savedRequest != null) {
                WebUtils.saveRequest(request);
            }
            return super.onLoginSuccess(token, subject, request, response);
        } catch (Exception e) {
            log.error("登录失败: {}", e.getMessage(), e);
            // 将错误次数放回session
            session.setAttribute(CAPTCHA_ERROR_COUNT_KEY, errorCount + 1);
            // 处理异常类型
            if (e instanceof AuthenticationException) {
                return onLoginFailure(token, (AuthenticationException) e, request, response);
            } else {
                return onLoginFailure(token, new AuthenticationException("登录失败，请稍后重试"), request, response);
            }
        }
    }

    /**
     * 修改getToken方法，传入session参数
     */
    public static DefaultVerifyToken getToken(String username, String reqPwd, boolean rememberMe, HttpSession session) {
        DefaultVerifyToken token = null;
        try {
            // 从传入的session中获取私钥，而不是从SecurityUtils获取
            RSAPrivateKey privateKey = (RSAPrivateKey) session.getAttribute("privateKey");
            if (privateKey == null) {
                log.error("RSA私钥不存在，请检查密钥生成逻辑");
                throw new AuthenticationException("系统错误，请刷新页面重试");
            }
            token = new DefaultVerifyToken(username, reqPwd, rememberMe);
            // 登录成功后清除私钥，确保一次性使用
            session.removeAttribute("privateKey");
        } catch (Exception e) {
            token = new DefaultVerifyToken(username, reqPwd, rememberMe);
        }
        return token;
    }

    private HttpSession getSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }
        return session;
    }

    /**
     * @Des 登录失败后的回调
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        log.debug("=== 登录失败 ===> {}", e.getMessage());
        HttpServletRequest hsr = (HttpServletRequest) request;
        HttpSession session = hsr.getSession();
        // 设置错误消息
        session.setAttribute("errorMsg", getErrorMessage(e));
        try {
            // 使用重定向，将 POST 改为 GET
            WebUtils.issueRedirect(hsr, response, getLoginUrl());
        } catch (Exception ex) {
            log.error("重定向失败: {}", ex.getMessage(), ex);
            throw new RuntimeException(ex);
        }
        return false;
    }

    /**
     * 获取友好的错误消息
     */
    private String getErrorMessage(AuthenticationException e) {
        if (e instanceof LockedAccountException) {
            return "账户已被锁定";
        } else if (e instanceof IncorrectCredentialsException) {
            return "用户名或密码错误";
        } else if (e instanceof CredentialsException) {
            return "登录失败，请检查用户名和密码";
        } else if (e instanceof DisabledAccountException) {
            return "账户已被禁用";
        } else if (e instanceof ExcessiveAttemptsException) {
            return "登录失败次数过多，请稍后再试";
        } else if (e instanceof UnknownAccountException) {
            return "用户名不存在";
        } else {
            return e.getMessage() != null ? e.getMessage() : "登录失败，请稍后重试";
        }
    }

    /**
     * @Des 发起成功重定向
     */
    @Override
    protected void issueSuccessRedirect(ServletRequest req, ServletResponse resp) throws Exception {
        log.debug("=== 登录成功重定向 ===>");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String successUrl = getSuccessUrl();

        if (request.getRequestURI().startsWith("/admin") || request.getRequestURI().startsWith("/admin/")) {
            // 后台直接返回首页
            successUrl = WebUtil.ADMIN_URL + WebUtil.ADMIN_MAIN_URL;
            // 清除SavedRequest
            WebUtils.getAndClearSavedRequest(request);
            WebUtils.issueRedirect(request, response, successUrl, null, true);
        } else {
            WebUtils.redirectToSavedRequest(request, response, successUrl);
        }
    }

    /**
     * @Des 当前请求是否是登录请求
     */
    @Override
    protected boolean isLoginRequest(ServletRequest req, ServletResponse resp) {
        return pathsMatch(WebUtil.LOGIN_URL, req) || pathsMatch(WebUtil.ADMIN_LOGIN_URL, req);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // 检查是否是支持的登录路径且是POST请求
        if (isLoginRequest(request, response) && "POST".equalsIgnoreCase(httpRequest.getMethod())) {
            return executeLogin(request, response);
        }
        return super.onAccessDenied(request, response);
    }
}