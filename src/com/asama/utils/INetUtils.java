package com.asama.utils;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class INetUtils {
	
	public static Date str2Date(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date datesql = null;;
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
}
