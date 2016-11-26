package com.proximate.www.pushmate.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {

	public static TimeZone timeZone = TimeZone.getTimeZone("America/Mexico_City");
	// public static TimeZone timeZone = TimeZone.getTimeZone("Asia/Karachi");

	public static java.sql.Timestamp getDate() {
		java.sql.Timestamp sysdate = new java.sql.Timestamp(getDateInTimeZone(new Date(), timeZone).getTime());
		return sysdate;
	}

	/**
	 * Regresa un timespamp a partir de una fecha
	 * 
	 * @param fecha
	 *            yyyy-MM-dd
	 * @param hora
	 *            HH:mm
	 * @return si algo fallo se regresa el timestamp con fecha y hora del
	 *         sistema
	 */
	public static java.sql.Timestamp getDate(String fecha, String hora) {
		SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			if ((fecha != null && !fecha.equals("")) && (hora != null && !hora.equals(""))) {
				// Si hay fecha
				String tmp = fecha.concat(" ").concat(hora);
				Date date = fechaFormat.parse(tmp);
				java.sql.Timestamp sysdate = new java.sql.Timestamp(getDateInTimeZone(date, timeZone).getTime());
				return sysdate;
			} else {
				return getDate();
			}
		} catch (Exception e) {
			return getDate();
		}
	}

	public static java.sql.Timestamp getDateFechaHora(String fechaHora) {
		SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			if (fechaHora != null && !fechaHora.equals("")) {
				// Si hay fecha
				String tmp = fechaHora;
				Date date = fechaFormat.parse(tmp);
				java.sql.Timestamp sysdate = new java.sql.Timestamp(getDateInTimeZone(date, timeZone).getTime());
				return sysdate;
			} else {
				return getDate();
			}
		} catch (Exception e) {
			return getDate();
		}
	}

	public static Date getDate(java.sql.Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance(timeZone);
		calendar.setTimeInMillis(timestamp.getTime());
		return calendar.getTime();
	}

	public static Date getDateInTimeZone(Date currentDate, TimeZone timeZoneId) {
		Calendar mbCal = new GregorianCalendar(timeZoneId);
		mbCal.setTimeInMillis(currentDate.getTime());
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, mbCal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, mbCal.get(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, mbCal.get(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, mbCal.get(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, mbCal.get(Calendar.MINUTE));
		cal.set(Calendar.SECOND, mbCal.get(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, mbCal.get(Calendar.MILLISECOND));
		return cal.getTime();
	}

	public static Date getDateCurrent() {
		return getDateInTimeZone(new Date(), timeZone);
	}

}
