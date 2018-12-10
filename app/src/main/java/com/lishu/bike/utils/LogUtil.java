package com.lishu.bike.utils;

import android.util.Log;

import com.lishu.bike.constant.AppConfig;

/**
 * 日志输出 工具类
 *
 * Android的Log等级通常有五类，按照日志级别由低到高分别是Verbose、Debug、Info、Warning、Error。
 * Info、Warning、Error等级的Log在普通调试中不随意滥用，存在发布版本中。
 * 在开发调试版本中，才会显示全部等级。
 */
public class LogUtil {

	// 下面四个是默认TAG的函数
	public static void v(String msg) {
		if (AppConfig.LOG_LEVEL <= 1)
			Log.v(AppConfig.TAG, msg);
	}

	public static void d(String msg) {
		if (AppConfig.LOG_LEVEL <= 2)
			Log.d(AppConfig.TAG, msg);
	}

	public static void i(String msg) {
		if (AppConfig.LOG_LEVEL <= 3)
			Log.i(AppConfig.TAG, msg);
	}

	public static void w(String msg) {
		if (AppConfig.LOG_LEVEL <= 4)
			Log.w(AppConfig.TAG, msg);
	}

	public static void e(String msg) {
		if (AppConfig.LOG_LEVEL <= 5)
			Log.e(AppConfig.TAG, msg);
	}

	// 下面是传入自定义TAG的函数
	public static void i(String TAG, String msg) {
		if (AppConfig.LOG_LEVEL <= 1)
			Log.v(TAG, msg);
	}

	public static void d(String TAG, String msg) {
		if (AppConfig.LOG_LEVEL <= 2)
			Log.d(TAG, msg);
	}

	public static void e(String TAG, String msg) {
		if (AppConfig.LOG_LEVEL <= 3)
			Log.i(TAG, msg);
	}

	public static void w(String TAG, String msg) {
		if (AppConfig.LOG_LEVEL <= 4)
			Log.w(TAG, msg);
	}

	public static void v(String TAG, String msg) {
		if (AppConfig.LOG_LEVEL <= 5)
			Log.e(TAG, msg);
	}
}
