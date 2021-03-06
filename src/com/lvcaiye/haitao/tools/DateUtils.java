package com.lvcaiye.haitao.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;

/**
 * @fileName DateUtils.java
 * @package com.immomo.momo.android.util
 * @description 鏃ユ湡宸ュ叿绫?
 * @author 浠讳笢鍗?
 * @email 86930007@qq.com
 * @version 1.0
 */
public class DateUtils {
	/**
	 * 鑾峰彇yyyyMMdd鏍煎紡鏃ユ湡
	 * 
	 * @param time
	 * @return
	 */
	public static Date getDate(String time) {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			date = format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	
	public static String formatDate(Context context, long date) {
		@SuppressWarnings("deprecation")
		int format_flags = android.text.format.DateUtils.FORMAT_NO_NOON_MIDNIGHT
				| android.text.format.DateUtils.FORMAT_ABBREV_ALL
				| android.text.format.DateUtils.FORMAT_CAP_AMPM
				| android.text.format.DateUtils.FORMAT_SHOW_DATE
				| android.text.format.DateUtils.FORMAT_SHOW_DATE
				| android.text.format.DateUtils.FORMAT_SHOW_TIME;
		return android.text.format.DateUtils.formatDateTime(context, date,
				format_flags);
	}
	
	
}
