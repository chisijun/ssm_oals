/**  
 * @Title: UtilDate.java
 * @Package org.csun.nc.utils
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2017年10月17日
 */
package org.study.oals.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * ClassName: UtilDate 
 * @Description: 时间戳
 * @author puyijun
 * @date 2017.12.22
 */
public class UtilDate {

	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmssSSS";

	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String simple = "yyyy-MM-dd HH:mm:ss";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyyMMdd";

	/** 年月日(下划线) yyyy-MM-dd */
	public static final String _dtShort = "yyyy-MM-dd";

	public static final String year ="yyyy";
	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getOrderNum() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(simple);
		return df.format(date);
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtShort);
		return df.format(date);
	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getThree() {
		Random rad = new Random();
		return rad.nextInt(1000) + "";
	}
	
	/**   
	 * @MethodName: getMonthFirstDay  
	 * @Param: UtilDate  
	 * @Author: gang.lv
	 * @Date: 2013-3-22 下午07:14:34
	 * @Return:    
	 * @Descb: 获取当月的第一天
	 * @Throws:
	*/
	public static String getMonthFirstDay() {
		Calendar cal = Calendar.getInstance();
		Calendar f = (Calendar) cal.clone();
		f.clear();
		f.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		f.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		String firstday = new SimpleDateFormat("yyyy-MM-dd")
				.format(f.getTime());
		firstday = firstday +" 00:00:00";
		return firstday;

	}
	
	/**   
	 * @MethodName: getMonthLastDay  
	 * @Param: UtilDate  
	 * @Author: gang.lv
	 * @Date: 2013-3-22 下午07:14:41
	 * @Return:    
	 * @Descb: 获取当月的最后一天
	 * @Throws:
	*/
	public static String getMonthLastDay() {
		Calendar cal = Calendar.getInstance();
		Calendar l = (Calendar) cal.clone();
		l.clear();
		l.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		l.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
		l.set(Calendar.MILLISECOND, -1);
		String lastday = new SimpleDateFormat("yyyy-MM-dd").format(l.getTime());
		lastday = lastday+" 23:59:59";
		return lastday;
	}
	 
	
	/**   
	 * @MethodName: getYesterDay  
	 * @Param: UtilDate  
	 * @Author: gang.lv
	 * @Date: 2013-4-14 上午01:22:46
	 * @Return:    
	 * @Descb: 获取昨天日期
	 * @Throws:
	*/
	public static Date getYesterDay(){
		 Date date = new Date();
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.add(Calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		 return calendar.getTime();
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getDate_dtShort() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(_dtShort);
		return df.format(date);
	}
	
	public static Date getDay( Date date,int day){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(date);
		 calendar.add(Calendar.DATE,day);//把日期往后增加一天.整数往后推,负数往前移动
		 return calendar.getTime();
	}

}
