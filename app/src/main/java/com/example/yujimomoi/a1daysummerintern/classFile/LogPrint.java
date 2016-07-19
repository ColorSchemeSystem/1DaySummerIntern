package com.example.yujimomoi.a1daysummerintern.classFile;

import android.util.Log;

/**
 * Created by yuji.momoi on 2016/07/19.
 */
public class LogPrint {
	private static boolean draw_flag;
	private static LogPrint log_print;

	private LogPrint(Boolean drawFlag) {
		draw_flag = drawFlag;
		log_print = null;
	}

	public static void create(Boolean drawFlag) {
		if(log_print == null) log_print = new LogPrint(true);
		drawFlag = drawFlag;
	}

	public static LogPrint getInstans() {
		if(log_print == null) log_print = new LogPrint(true);
		return log_print;
	}

	public static void logWrite(String key, String value) {
		if(draw_flag) {
			Log.d(key, value);
		}
	}
	public static void logWrite(String key, String value, Boolean drawFlag) {
		if(drawFlag) {
			Log.d(key, value);
		}
	}

	public static void setDrawFlag(Boolean drawFlag) {
		draw_flag = drawFlag;
	}
}
