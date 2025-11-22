package com.cheshun.common.tools.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {
	//JDK8不支持该解析格式
	public final static String CODE_DATE_TIME_FORMAT_VALUE = "yyyyMMddHHmmssSSS";
	public final static DateTimeFormatter CODE_DATE_TIME_FORMAT = DateTimeFormatter
			.ofPattern(CODE_DATE_TIME_FORMAT_VALUE);
	// 通用日期--时间格式
	public final static String USUAL_DATE_TIME_FORMAT_VALUE = "yyyy-MM-dd HH:mm:ss";
	// 通用日期--时间格式
	public final static DateTimeFormatter USUAL_DATE_TIME_FORMAT = DateTimeFormatter
			.ofPattern(USUAL_DATE_TIME_FORMAT_VALUE);
	// 常用日期-时间格式
	public final static String COMMON_DATE_TIME_FORMAT_VALUE = "yyyy-MM-dd HH:mm:ss:SSS";
	// 常用日期-时间格式
	public final static DateTimeFormatter COMMON_DATE_TIME_FORMAT = DateTimeFormatter
			.ofPattern(COMMON_DATE_TIME_FORMAT_VALUE);
	// 常用日期格式
	public final static String COMMON_DATE_FORMAT_VALUE = "yyyy-MM-dd";
	// 常用日期格式
	public final static DateTimeFormatter COMMON_DATE_FORMAT = DateTimeFormatter.ofPattern(COMMON_DATE_FORMAT_VALUE);
	// 常用时间格式
	public final static String COMMON_TIME_FORMAT_VALUE = "HH:mm:ss";
	// 常用时间格式
	public final static DateTimeFormatter COMMON_TIME_FORMAT = DateTimeFormatter.ofPattern(COMMON_TIME_FORMAT_VALUE);
	// 身份证日期格式
	public final static String IDCARD_DATE_TIME_FORMAT_VALUE = "yyyyMMdd";
	// 身份证日期格式
	public final static DateTimeFormatter IDCARD_DATE_TIME_FORMAT = DateTimeFormatter
			.ofPattern(IDCARD_DATE_TIME_FORMAT_VALUE);

	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 */
	public static long getTimestampNow() {
		return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
	}

	/**
	 * 获取指定日期的时间戳
	 * 
	 * @param dateTime
	 * @return
	 */
	public static long getTimestamp(LocalDateTime dateTime) {
		return dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
	}

	public static LocalDateTime formatDateTime(String dt) {
		return LocalDateTime.parse(dt, COMMON_DATE_TIME_FORMAT);
	}

	public static LocalDateTime formatDateTimeUseFormat(String dt, DateTimeFormatter df) {
		return LocalDateTime.parse(dt, df);
	}
	
	public static String formatDateTimeStr(LocalDateTime dt, DateTimeFormatter df) {
		return df.format(dt);
	}
	
	public static int processAge(LocalDate birthday) {
		return processAge(birthday, LocalDate.now());
	}

	public static int processAge(LocalDate birthday, LocalDate now) {
		int age = now.getYear() - birthday.getYear();
		if (now.getMonthValue() <= birthday.getMonthValue()) {
			if (now.getMonthValue() == birthday.getMonthValue()) {
				if (now.getDayOfMonth() < birthday.getDayOfMonth()) {
					age--;
				}
			} else {
				age--;
			}
		}
		return age;
	}
	
	public static boolean isBetweenDateTime(LocalDateTime time, LocalDateTime start, LocalDateTime end) {
		if(time == null || start == null || end == null) {
			return false;
		}
		if(start.isBefore(time) && end.isAfter(time)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 向前兼容
	 * @param dt
	 * @return
	 */
	public static Date toDate(LocalDateTime dt) {
		if(dt == null) {
			return null;
		}
		ZoneId zi = ZoneId.systemDefault();
		ZonedDateTime zdt = dt.atZone(zi);
		return Date.from(zdt.toInstant());
	}
	/**
	 * 向后兼容
	 * @param dt
	 * @return
	 */
	public static LocalDateTime toDateTime(Date dt) {
		if(dt == null) {
			return null;
		}
		ZoneId zi = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(dt.toInstant(), zi);
	}
}
