package com.fit.service;

import com.fit.util.JSONUtil;
import com.fit.util.JdbcTemplateUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2019/6/3
 */
@Service("dict")
public class DictService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getExamTypes() {
        String sb = "SELECT t.`ID`,t.`NAME` FROM `lms_exam_type` t";
        return JdbcTemplateUtil.queryForListMap(jdbcTemplate, sb);
    }

    public String getExamTypeName(Long id) {
        String sb = "SELECT t.`NAME` FROM `lms_exam_type` t where t.`ID` = ?";
        return JdbcTemplateUtil.queryForObject(jdbcTemplate, sb, String.class, id);
    }
}