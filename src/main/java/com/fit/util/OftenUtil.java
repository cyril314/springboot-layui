package com.fit.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @AUTO 常用工具类
 * @Author AIM
 * @DATE 2019/4/18
 */
public class OftenUtil {

	/**
	 * 判断是否空或长度为0对象
	 */
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		} else {
			if (o instanceof String) {
				if (o.toString().trim().equals("")) {
					return true;
				}
			} else if (o instanceof List) {
				if (((List<?>) o).size() == 0) {
					return true;
				}
			} else if (o instanceof Map) {
				if (((Map<?, ?>) o).size() == 0) {
					return true;
				}
			} else if (o instanceof Set) {
				if (((Set<?>) o).size() == 0) {
					return true;
				}
			} else if (o instanceof Object[]) {
				if (((Object[]) ((Object[]) o)).length == 0) {
					return true;
				}
			} else if (o instanceof int[]) {
				if (((int[]) ((int[]) o)).length == 0) {
					return true;
				}
			} else if (o instanceof long[] && ((long[]) ((long[]) o)).length == 0) {
				return true;
			}

			return false;
		}
	}

	public static boolean isEmpty(Object... os) {
		Object[] obj = os;
		for (int i = 0; i < os.length; ++i) {
			Object o = obj[i];
			if (isEmpty(o)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断不为空或长度为不为0的对象
	 */
	public static boolean isNotEmpty(Object o) {
		return !isEmpty(o);
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str != null && (strLen = str.length()) != 0) {
			for (int i = 0; i < strLen; ++i) {
				if (!Character.isWhitespace(str.charAt(i))) {
					return false;
				}
			}

			return true;
		} else {
			return true;
		}
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 当前系统下的临时文件路径
	 */
	public static String getTempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static boolean isEmpty(CharSequence string) {
		return string == null || string.length() == 0;
	}

	public static boolean containsOnlyWhitespaces(CharSequence string) {
		int size = string.length();

		for (int i = 0; i < size; ++i) {
			char c = string.charAt(i);
			if (!(c <= ' ')) {
				return false;
			}
		}

		return true;
	}

	public static boolean isNullOrBlank(CharSequence str) {
		return str == null || containsOnlyWhitespaces(str);
	}

}
