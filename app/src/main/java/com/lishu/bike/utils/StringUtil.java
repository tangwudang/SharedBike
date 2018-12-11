package com.lishu.bike.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil {
	
	/** 
	 * 判断字符串是否为空
	 */
	public static boolean isEmpty(CharSequence str) {
		return (str == null || str.length() == 0);
	}
	
	/**
	 * 判断是否是合法的手机号码
	 */
	public static boolean isMobileNum(String mobiles) {
		Pattern p = Pattern.compile("^((1[3-9]))\\d{9}$");
		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

}
