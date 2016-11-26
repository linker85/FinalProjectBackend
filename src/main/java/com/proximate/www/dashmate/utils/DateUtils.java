package com.proximate.www.dashmate.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtils {
	public static TimeZone timeZone = TimeZone.getTimeZone("America/Mexico_City");
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	//public static TimeZone timeZone = TimeZone.getTimeZone("Asia/Karachi");
	
	public static java.sql.Date getDate(){
		java.sql.Date sysdate = new java.sql.Date(getDateInTimeZone(new Date(), timeZone).getTime());
		return sysdate;
	}
	
	public static java.sql.Timestamp getDateTime(){
		java.sql.Timestamp sysdate = new java.sql.Timestamp(getDateInTimeZone(new Date(), timeZone).getTime());
		return sysdate;
	}
	
	public static String getFormat(Date date, String dataFormat){
		return new SimpleDateFormat(dataFormat).format(date);
	}
	
	/**
	 * Regresa un timespamp a partir de una fecha
	 * @param fecha yyyy-MM-dd
	 * @param hora HH:mm
	 * @return si algo fallo se regresa el timestamp con fecha y hora del sistema
	 */
	public static java.sql.Timestamp getDate(String fecha, String hora){
		SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try{
			if((fecha != null && !fecha.equals("")) && (hora != null && !hora.equals(""))){
				//Si hay fecha
				String tmp = fecha.concat(" ").concat(hora);
				Date date = fechaFormat.parse(tmp);
				java.sql.Timestamp sysdate = new java.sql.Timestamp(getDateInTimeZone(date, timeZone).getTime());
				return sysdate;
			}
			else{
				return getDateTime();
			}
		}
		catch(Exception e){
			return getDateTime();
		}	
	}
	
	/**
	 * Convierte una fecha a a timestamp
	 * @param fecha la fecha
	 * @param dateFormat el formato en que esta la fecha
	 * @return si algo fallo se regresa el timestamp con fecha y hora del sistema
	 */
	public static java.sql.Timestamp convert(String fecha, String dateFormat){
		SimpleDateFormat fechaFormat = new SimpleDateFormat(dateFormat);
		try{
			if((fecha != null && !fecha.equals(""))){
				//Si hay fecha
				String tmp = fecha;
				Date date = fechaFormat.parse(tmp);
				java.sql.Timestamp sysdate = new java.sql.Timestamp(getDateInTimeZone(date, timeZone).getTime());
				return sysdate;
			}
			else{
				return getDateTime();
			}
		}
		catch(Exception e){
			return getDateTime();
		}	
	}
	
	/**
	 * Convierte una fecha yyyy-MM-dd a un formato especifico
	 * @param fecha en formato yyyy-MM-dd
	 * @param dateFormat el formato en que regresara la fecha
	 * @return si algo sale mal, regresa la fecha actual en yyyy-MM-dd
	 */
	public static String format(String fecha, String dateFormat){
		SimpleDateFormat fechaFormat = new SimpleDateFormat(dateFormat);
		try{
			if((fecha != null && !fecha.equals(""))){
				//Si hay fecha
				Date dateO = format.parse(fecha);
				return fechaFormat.format(dateO);
			}
			else{
				return getFecha();
			}
		}
		catch(Exception e){
			return getFecha();
		}	
	}
	
	/**
	 * Regresa un timespamp a partir de una fecha
	 * @param fecha yyyy-MM-dd
	 * @param hora HH:mm
	 * @return si algo fallo se regresa el timestamp con fecha y hora del sistema
	 */
	public static java.sql.Date toDate(String fecha, String dateFormat){
		SimpleDateFormat fechaFormat = new SimpleDateFormat(dateFormat);
		try{
			if((fecha != null && !fecha.equals(""))){
				//Si hay fecha
				String tmp = fecha;
				Date date = fechaFormat.parse(tmp);
				java.sql.Date sysdate = new java.sql.Date(getDateInTimeZone(date, timeZone).getTime());
				return sysdate;
			}
			else{
				return null;
			}
		}
		catch(Exception e){
			return getDate();
		}	
	}
	
	public static Date getDate(java.sql.Timestamp timestamp){
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
	
	public static Date getDateCurrent(){
		return getDateInTimeZone(new Date(), timeZone);
	}
	
	public static String getFecha(){
		return format.format(new Date());
	}
	
	public static String getFechaDays(int days){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return format.format(calendar.getTime());
	}
	/**
	 * Agrega dias a una fecha que este en formato YYYY-MM-DD
	 * @param dateInitial
	 * @param days
	 * @return la nueva fecha en YYYY-MM-DDD
	 */
	public static String addDaysToDate(String dateInitial, int days){
		Date fechaact;
		if((dateInitial != null && !dateInitial.equals(""))){
			//Si hay fecha
			String tmp = dateInitial;
			Date date;
			try {
				date = format.parse(tmp);
				fechaact = date;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fechaact = getDate();
			}
			
		}
		else{
			fechaact = getDate();
		}		
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaact);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		
		if(calendar.getTimeInMillis() > getDate().getTime()){
			return format.format(getDate().getTime());
		}
		else{
			return format.format(calendar.getTime());	
		}
	}
	
	public static String getHorasMinSeg(int segundos) {   
        int num,hor,min,seg;  
        num=segundos;  
        hor=num/3600;  
        min=(num-(3600*hor))/60;  
        seg=num-((hor*3600)+(min*60));  
        return hor+"h "+min+"m "+seg+"s";  
     }
}
