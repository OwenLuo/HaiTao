package com.lvcaiye.haitao.http;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeConverter {
	static SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat otherDateFM = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat mdDateFM = new SimpleDateFormat("MM-dd");
	static SimpleDateFormat shortDateFM = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	public static String getOtherFormationTime(Date date) {
		return otherDateFM.format(date);
	}

	public static String getFormatTime(Date date) {
		// 格式化当前系统日期
		String dateTime = dateFm.format(date);
		return dateTime;
	}

	public static String getshortFormationTime(Date date) {
		return shortDateFM.format(date);
	}

	public static String getMdFormationTime(Date date) {
		return mdDateFM.format(date);
	}

	public static Date stringToDate(String str) {
		Date date = null;
		try {
			date = dateFm.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static Date otherStringToDate(String str) {
		Date date = null;
		try {
			date = otherDateFM.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	// 计算时间差
	public static String getBeapartDate(String oldDateStr) {
		String returnTime = "";
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;// 两个日期相差的毫秒数
		try {
			Date oldDate = otherStringToDate(oldDateStr);
			Calendar oldCa = Calendar.getInstance();
			oldCa.setTime(oldDate);
			Date newDate = new Date();
			Calendar newCa = Calendar.getInstance();
			newCa.setTime(newDate);
			long oldDatem = oldDate.getTime();
			long newDatem = newDate.getTime();// 当前时间
			diff = newDatem - oldDatem;
			long day = diff / nd;
			long hour = diff / nh;
			long min = diff / nm;
			long sec = diff / ns;
			if (oldCa.get(Calendar.YEAR) != newCa.get(Calendar.YEAR)) {// 大于一年的
				returnTime = getshortFormationTime(oldDate);
			} else {
				if (day < 7) {// 大于一个星期
					if (day < 1) {// 一天内
						if (hour == 0) {
							if (min == 0) {
								returnTime = sec + "秒钟前";
							} else {
								returnTime = min + "分钟前";
							}
						} else {
							returnTime = hour + "小时前";
						}
					} else {
						returnTime = day + "天前";
					}
				} else {
					returnTime = getMdFormationTime(oldDate);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return returnTime;
	}
}
