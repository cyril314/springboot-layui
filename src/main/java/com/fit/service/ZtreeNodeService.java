package com.fit.service;

import com.fit.entity.ZTreeNode;
import com.fit.util.JdbcTemplateUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @AUTO 获取ztree节点服务
 * @Author AIM
 * @DATE 2019/4/28
 */
@Service
public class ZtreeNodeService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取部门树节点集合
     */
    public List<ZTreeNode> deptZtree() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ID AS id, PID AS parentId, SIMPLE_NAME AS NAME,");
        sb.append("(CASE WHEN (PID = 0 OR PID IS NULL) THEN 'true' ELSE 'false' END ) AS OPEN ");
        sb.append(" FROM sys_dept");

        List<ZTreeNode> list = JdbcTemplateUtil.queryForListBean(jdbcTemplate, sb.toString(), ZTreeNode.class);
        return list;
    }

    /**
     * 获取角色树节点集合
     */
    public List<ZTreeNode> roleZtree() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ID AS id, '0' AS parentId, `ROLE_NAME` AS NAME, 'true' AS OPEN FROM sys_role");

        return JdbcTemplateUtil.queryForListBean(jdbcTemplate, sb.toString(), ZTreeNode.class);
    }

    /**
     * 获取栏目树节点集合
     */
    public List<ZTreeNode> menuZtree() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT sm.`ID` AS id, IFNULL(ms.`ID`,0) AS parentId, sm.`NAME`, sm.`ID` AS `CODE`, ");
        sb.append("(CASE WHEN (sm.`PID` = 0 OR sm.`PID` IS NULL) THEN 'true' ELSE 'false' END ) AS OPEN, ");
        sb.append("sm.`LEVELS`  FROM `sys_resources` sm LEFT JOIN `sys_resources` ms ON sm.`PID` = ms.`ID`");

        return JdbcTemplateUtil.queryForListBean(jdbcTemplate, sb.toString(), ZTreeNode.class);
    }

    public List<ZTreeNode> dictZtree() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT `ID` AS id,`PID` AS parentId,`NAME`,");
        sb.append("'false' AS OPEN ");
        sb.append(" FROM `sys_dict`");

        return JdbcTemplateUtil.queryForListBean(jdbcTemplate, sb.toString(), ZTreeNode.class);
    }
}