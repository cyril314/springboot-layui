package com.fit.util;

import com.fit.entity.SysDict;
import com.fit.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/11/27
 */
@Component("dict")
public class DictUtil {
    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<Map<String, Object>> getType(String dictType) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT s.`ID` as dictId,s.`NAME`,s.`CODE`,s.`SIGN` FROM `sys_dict` s ");
        sb.append(" INNER JOIN `sys_dict` d ON s.`PID` = d.`ID` ");
        sb.append(" WHERE d.`CODE`=? ").append(" ORDER BY s.`SORT` ASC");
        List<Map<String, Object>> maps = JdbcTemplateUtil.queryForListMap(jdbcTemplate, sb.toString(), dictType);
        return maps;
    }

    public Map<String, Object> getTypeForMap(String dictType) {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> mapList = this.getType(dictType);
        for (Map<String, Object> map : mapList) {
            result.put(map.get("code").toString(), map.get("name"));
        }
        return result;
    }
}