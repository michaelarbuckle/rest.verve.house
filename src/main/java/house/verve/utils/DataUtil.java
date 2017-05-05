package house.verve.utils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

public class DataUtil {

	
	public static String getTimePointString(Date dt)
	{
		SimpleDateFormat sdf = new SimpleDateFormat ("'h'H'_'m");
		String fieldName = sdf.format(dt);	
		String min = fieldName.substring(4);
		min = getClosestQuarterHour(min);
		StringBuilder sb = (new StringBuilder()).append(fieldName.substring(0,4)).append(min);
		
		return sb.toString();
	}
	
	public static String getClosestQuarterHour(String min)
	{
		
		int m = Integer.decode(min);
		
		if (m >= 0 && m < 15)
		{
			return "0";
		} else if (m >= 15 && m < 30)
		{
		    return "15";
		} else if (m >= 30 && m < 45)
		{
			return "30";
		} else if (m >= 45 && m < 60)
		{
			return "45";
		} 
		
		return "0";
		
	}
	
	
	
	public static String getDateTimeString()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm.ss.SSS"); //dateFormat = @"yyyy-MM-dd HH:mm:ss.SSS ";
		Date d = new Date();
		return sdf.format(d);
	}
	public static String getDateTimeString(Date d)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm.ss.SSS"); //dateFormat = @"yyyy-MM-dd HH:mm:ss.SSS ";
		return sdf.format(d);
	}
	public static Date parseDateTimeString(String s)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm.ss.SSS"); //dateFormat = @"yyyy-MM-dd HH:mm:ss.SSS ";
		Date d = null;
		try {
			  d = sdf.parse(s);
		}catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return d;
	}
	public static Date getDateTime(String s)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd HH:mm.ss"); //dateFormat = @"yyyy-MM-dd HH:mm:ss.SSS ";
		Date d = null;
		try {
			  d = sdf.parse(s);
		}catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return d;
	}
	public static Date getMidnight()
	{
		Date dt = null;
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd HH:mm.ss"); //dateFormat = @"yyyy-MM-dd HH:mm:ss.SSS ";
		try { 
			dt = sdf.parse("20170422 00:00.00");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return dt;
	}
	public static List<Date> getDateSeries(Date start, int intervalInSeconds, long count)
	{
		List<Date> list = new ArrayList<Date>();
		Calendar c = GregorianCalendar.getInstance();
		c.setTime(start);
		
		for (int i=0;i<count;i++)
		{
			c.add(Calendar.SECOND,intervalInSeconds);
			list.add(c.getTime());
		}
		
		return list;
	}
	
	public static Double getRandomDouble(Double min, Double max)
	{
	// nextInt is normally exclusive of the top value,
	// so add 1 to make it inclusive
	return round(ThreadLocalRandom.current().nextDouble(min, max ),2);
	 
	}
	  public static boolean isNumeric(String str)
	{
		  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
		}
	  public static double round(double value, int places) {
		    if (places < 0) throw new IllegalArgumentException();

		    BigDecimal bd = new BigDecimal(value);
		    bd = bd.setScale(places, RoundingMode.HALF_UP);
		    return bd.doubleValue();
		}
}
