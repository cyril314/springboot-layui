package com.fit.config.shiro;

import com.fit.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashSet;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @Des 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT RID AS `role_id` FROM `sys_user` WHERE `USERNAME`=?", username);
        HashSet<String> roleSet = new HashSet<String>();
        while (rowSet.next()) {
            roleSet.add(rowSet.getString("role_id"));
        }
        authorizationInfo.setRoles(roleSet);
        if (roleSet.isEmpty()) {
            authorizationInfo.setStringPermissions(Collections.EMPTY_SET);
        } else {
            authorizationInfo.setStringPermissions(roleSet);
        }
        return authorizationInfo;
    }

    /**
     * @Des 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        if (StringUtils.isEmpty(username)) {
            throw new IncorrectCredentialsException("username is null!");
        } else if (StringUtils.isEmpty(token.getCredentials())) {
            throw new IncorrectCredentialsException("password is null!");
        }
        SimpleAccount authenticationInfo = null;
        SysUser user = jdbcTemplate.queryForObject("SELECT * FROM `sys_user` WHERE `USERNAME`=?", new BeanPropertyRowMapper<>(SysUser.class), username);
        if (StringUtils.isEmpty(user)) {
            authenticationInfo = new SimpleAccount("", "", username);
        } else {
            authenticationInfo = new SimpleAccount(user.getId(), user.getPassword(), user.getUsername());
        }
        return authenticationInfo;
    }

    private String generateCredentialsSalt(SysUser user) {
        return user.getCtime().toString().trim() + user.getUsername().toString().trim();
    }

    /**
     * @Des 更新用户授权信息缓存
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    /**
     * @Author AIM
     * @Des 清除所有用户授权信息缓存.
     * @DATE 2018/2/14
     */
    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * <p>
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            Session session = subject.getSession();
            log.debug("Session默认超时时间为[{}]毫秒", session.getTimeout());
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}