package com.fit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

/**
 * json util
 */
public class JSONUtil {
	static {
		// 默认解析方式,将Feature的一些相关属性进行叠加..
		int features = 0;
		features |= Feature.AutoCloseSource.getMask();
		features |= Feature.InternFieldNames.getMask();
		// features |= Feature.UseBigDecimal.getMask();
		features |= Feature.AllowUnQuotedFieldNames.getMask();
		features |= Feature.AllowSingleQuotes.getMask();
		features |= Feature.AllowArbitraryCommas.getMask();
		features |= Feature.SortFeidFastMatch.getMask();
		features |= Feature.IgnoreNotMatch.getMask();
		JSONObject.DEFAULT_PARSER_FEATURE = features;
	}

	/**
	 * 将String封装为JSON
	 */
	public static JSONArray parseStrToJSONArray(String data) {
		if (data == null) {
			return null;
		}
		try {
			return JSONArray.parseArray(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将String类型数据转换为JSON
	 */
	public static JSONObject parseStrToJSONObject(String data) {
		if (OftenUtil.isEmpty(data)) {
			return null;
		}
		try {
			return JSONObject.parseObject(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取json中指定的key的值
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	public static String jsonFormatByKey(JSONObject json, String key) {
		if (OftenUtil.isEmpty(key)) {
			return null;
		}
		if (json == null) {
			return null;
		}
		try {
			if (!json.containsKey(key)) {
				return null;
			}
			return json.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将json转换为java bean
	 * 
	 * @param data
	 * @param clazz
	 * @return
	 */
	public static <T> T parseJSONDataToObject(String data, Class<T> clazz) {
		if (OftenUtil.isEmpty(data) || clazz == null) {
			return null;
		}
		try {
			return JSONObject.parseObject(data, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将java bean封装为json
	 * 
	 * @param data
	 * @return
	 */
	public static JSONObject packageDataToJSON(Object data) {
		if (data == null) {
			return null;
		}
		try {
			String s = JSONObject.toJSONString(data);
			return JSONObject.parseObject(s);
			// return (JSONObject) JSONObject.toJSON(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * JSON对象转Map
	 * 
	 * @param data
	 */
	public static Map<String, Object> parseJson2Map(String data) {
		if (data == null) {
			return null;
		}
		try {
			return JSONObject.parseObject(data, new TypeReference<Map<String, Object>>() {
			});
		} catch (Exception e) {
			return null;
		}
	}

	public static Map<String, String> parseJsonToMap(String data) {
		if (data == null) {
			return null;
		}
		try {
			return JSONObject.parseObject(data, new TypeReference<Map<String, String>>() {
			});
		} catch (Exception e) {
			return null;
		}
	}

	public static Map<String, Object> parseJsonToMapObj(String data) {
		if (data == null) {
			return null;
		}
		try {
			return JSONObject.parseObject(data, new TypeReference<Map<String, Object>>() {
			});
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Object对象转JSON
	 */
	public static String parseObject2Json(Object data) {
		if (data == null) {
			return null;
		}
		try {
			return JSON.toJSONString(data);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @param data
	 *            传入JSON数据
	 * @return JSON转List<Map>
	 */
	public static List<Map<String, Object>> parseJson2List(String data) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			JSONArray array = JSONObject.parseArray(data);
			for (int j = 0; j < array.size(); j++) {
				JSONObject jsonObject = array.getJSONObject(j);

				Map<String, Object> map = new HashMap<>();
				Iterator<String> it = jsonObject.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next().toString().trim();
					Object value = jsonObject.get(key);
					map.put(key, value);
				}
				list.add(map);
			}
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * @param data
	 *            传入JSON数据
	 * @return JSON转List<Map>
	 */
	public static List<Object> parseJsonToList(String data) {
		return parseJsonToList(data, Object.class);
	}

	public static <T> List<T> parseJsonToList(String data, Class<T> clazz) {
		List<T> list = new ArrayList<T>();
		try {
			JSONArray array = JSONObject.parseArray(data);
			if (array == null || array.isEmpty()) {
				return list;// nerver return null
			}
			list = JSONObject.parseArray(array.toJSONString(), clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}