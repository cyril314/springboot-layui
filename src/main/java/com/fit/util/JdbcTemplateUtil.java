package com.fit.util;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @AUTO Spring JDBC 查询工具
 * @Author AIM
 * @DATE 2019/5/31
 */
public class JdbcTemplateUtil {

	/**
	 * 查询数据返回List<Map>
	 * 
	 * @param jdbcTemplate
	 *            SpringJdbc对象
	 * @param sql
	 *            查询语句
	 * @param obj
	 *            不定参数
	 */
	public static List<Map<String, Object>> queryForListMap(JdbcTemplate jdbcTemplate, String sql, Object... obj) {
		return jdbcTemplate.queryForList(sql, obj);
	}

	/**
	 * 查询数据返回List<Map>
	 * 
	 * @param jdbcTemplate
	 *            SpringJdbc对象
	 * @param sql
	 *            查询语句
	 * @param params
	 *            查询参数集合
	 */
	public static List<Map<String, Object>> queryForListMap(JdbcTemplate jdbcTemplate, String sql, List<String> params) {
		return queryForListMap(jdbcTemplate, sql, params.toArray(new Object[params.size()]));
	}

	/**
	 * 查询对象列表(基础类型)
	 * 
	 * @param jdbcTemplate
	 *            SpringJdbc对象
	 * @param sql
	 *            查询语句
	 * @param javaBean
	 *            返回基础类型对象
	 * @param obj
	 *            不定参数
	 */
	public static <T> List<T> queryForList(JdbcTemplate jdbcTemplate, String sql, Class<T> javaBean, Object... obj) {
		return jdbcTemplate.queryForList(sql, obj, javaBean);
	}

	/**
	 * 查询对象列表(实体类型)
	 * 
	 * @param jdbcTemplate
	 *            SpringJdbc对象
	 * @param sql
	 *            查询语句
	 * @param params
	 *            查询参数集合
	 * @param javaBean
	 *            返回实体类型对象
	 */
	public static <T> List<T> queryForList(JdbcTemplate jdbcTemplate, String sql, List<?> params, Class<T> javaBean) {
		return queryForList(jdbcTemplate, sql, javaBean, params.toArray(new Object[params.size()]));
	}

	/**
	 * 查询对象列表(实体类型)
	 * 
	 * @param jdbcTemplate
	 *            SpringJdbc对象
	 * @param sql
	 *            查询语句
	 * @param javaBean
	 *            返回实体类型对象
	 * @param obj
	 *            不定参数
	 */
	public static <T> List<T> queryForListBean(JdbcTemplate jdbcTemplate, String sql, Class<T> javaBean, Object... obj) {
		return jdbcTemplate.query(sql, obj, BeanPropertyRowMapper.newInstance(javaBean));
	}

	/**
	 * 查询对象列表(实体类型)
	 * 
	 * @param jdbcTemplate
	 *            SpringJdbc对象
	 * @param sql
	 *            查询语句
	 * @param params
	 *            查询参数集合
	 * @param javaBean
	 *            返回实体类型对象
	 */
	public static <T> List<T> queryForListBean(JdbcTemplate jdbcTemplate, String sql, List<?> params, Class<T> javaBean) {
		return queryForListBean(jdbcTemplate, sql, javaBean, params.toArray(new Object[params.size()]));
	}

	/**
	 * 查询指定对象结果(基础类型)
	 * 
	 * @param jdbcTemplate
	 *            SpringJdbc对象
	 * @param sql
	 *            查询语句
	 * @param javaBean
	 *            返回基础类型对象
	 * @param obj
	 *            不定参数
	 */
	public static <T> T queryForObject(JdbcTemplate jdbcTemplate, String sql, Class<T> javaBean, Object... obj) {
		return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(javaBean), obj);
	}

	/**
	 * 查询指定对象结果(基础类型)
	 * 
	 * @param jdbcTemplate
	 *            SpringJdbc对象
	 * @param sql
	 *            查询语句
	 * @param params
	 *            参数集合
	 * @param javaBean
	 *            返回基础类型对象
	 */
	public static <T> T queryForObject(JdbcTemplate jdbcTemplate, String sql, List<?> params, Class<T> javaBean) {
		return queryForObject(jdbcTemplate, sql, javaBean, params.toArray(new Object[params.size()]));
	}

	/**
	 * 更新操作
	 * 
	 * @param jdbcTemplate
	 *            SpringJdbc对象
	 * @param sql
	 *            操作语句
	 * @param obj
	 *            不定参数
	 */
	public static int update(JdbcTemplate jdbcTemplate, String sql, Object... obj) {
		return jdbcTemplate.update(sql, obj);
	}

	/**
	 * 更新操作
	 * 
	 * @param jdbcTemplate
	 *            SpringJdbc对象
	 * @param params
	 *            参数集合
	 * @param sql
	 *            操作语句
	 */
	public static int update(JdbcTemplate jdbcTemplate, String sql, List<?> params) {
		return update(jdbcTemplate, sql, params.toArray(new Object[params.size()]));
	}
}
