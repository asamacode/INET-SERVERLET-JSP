package com.asama.common;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class INetUtils {

	public static Date str2Date(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date datesql = null;
		;
		try {
			java.util.Date date = sdf.parse(strDate);
			datesql = new Date(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datesql;
	}

	public static Time str2Time(String strTime) {

		StringBuilder sb = new StringBuilder(strTime);
		sb.append(":00");

		return Time.valueOf(sb.toString());
	}

	public static String date2Str(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dateStr = sdf.format(date);
		return dateStr;
	}

	public static String time2Str(Time time) {
		DateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(time.getTime());
	}

	public static boolean isValidDate(String date) {
		final String DATE_FORMAT = "dd/MM/yyyy";
		try {
			DateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static boolean isValidTime(String time) {

		final String PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

		Pattern pattern = Pattern.compile(PATTERN);

		Matcher matcher = pattern.matcher(time);
		return matcher.matches();

	}
}
