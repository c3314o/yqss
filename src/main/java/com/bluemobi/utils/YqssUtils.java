package com.bluemobi.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class YqssUtils {

	public static final int LEAP_YEAR_DAYS = 366;
	public static final int NO_LEAP_YEAR_DAYS = 365;

	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static int RESIDUE_DAY = 15; // 默认还款日期

	static {
		String rd = PropertiesUtils.getPropertiesValues("repayment_date_day",
				YqssUtils.class.getResource("/").getPath() + "resource" + File.separator + "phyy.properties");
		if (StringUtils.isNotBlank(rd)) {
			RESIDUE_DAY = Integer.parseInt(rd);
		}
	}

	/**
	 * 计算剩余还款天数
	 * 
	 * @param strDate
	 * @return
	 */
	public static int residueDay(String residueDate) {
		return residueDay(DateUtils.parse(residueDate, "yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 计算剩余还款天数 为负数说明逾期
	 * 
	 * 步骤
	 * 1.判断当前年与还款年是否在同一年
	 * 2.不在同一年则在年份大的一年加上前一年的天数 
	 * 3.计算当前时间与还款时间在这一年的天数
	 * 4.用计算后的还款天数减去当前时间的天数为剩余还款的天数 
	 * 5.为负数则逾期
	 * 
	 * @param strDate
	 * @return
	 */
	public static int residueDay(Date residueDate) {

		Calendar calendar = Calendar.getInstance();
		int currentDay = calendar.get(Calendar.DAY_OF_YEAR);
		int currentYear = calendar.get(Calendar.YEAR);

		calendar.setTime(residueDate);
		int residueYear = calendar.get(Calendar.YEAR);
		int residueDay = calendar.get(Calendar.DAY_OF_YEAR);

		if (currentYear < residueYear) {
			if (isLeapYear(currentYear)) {
				residueDay += LEAP_YEAR_DAYS;
			} else {
				residueDay += NO_LEAP_YEAR_DAYS;
			}
		}
		else if (currentYear > residueYear){
			if (isLeapYear(residueYear)) {
				currentDay += LEAP_YEAR_DAYS;
			} else {
				currentDay += NO_LEAP_YEAR_DAYS;
			}
		}
		return (residueDay - currentDay);
	}

	/**
	 * 计算下个月的还款日期
	 * 
	 * @param residueDate
	 * @return
	 */
	public static String nextResidueDay(String residueDate) {
		return nextResidueDay(DateUtils.parse(residueDate, "yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 计算下个月的还款日期
	 * 
	 * @param residueDate
	 * @return
	 */
	public static String nextResidueDay(Date residueDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(residueDate);
		calendar.add(Calendar.MONTH, 1);
		return DateUtils.toString(calendar.getTime(), DEFAULT_FORMAT);
	}
	
	/**
	 * 计算第一个还款日期
	 * @return
	 */
	public static String firstResidueDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH,RESIDUE_DAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return DateUtils.toString(calendar.getTime(), DEFAULT_FORMAT);
	}

	/**
	 * 
     * @Title: totalDays
     * @Description: 计算借款总天数
     * @param @param residueDate
     * @param @return    参数
     * @return int    返回类型
     * @throws
	 */
	public static int totalDays(String borrowDateString) {
	
		Calendar calendar = Calendar.getInstance();
		int currentDay = calendar.get(Calendar.DAY_OF_YEAR);
		int currentYear = calendar.get(Calendar.YEAR);

		Date borrowDate = DateUtils.parse(borrowDateString, DEFAULT_FORMAT);
		calendar.setTime(borrowDate);
		int borrowYear = calendar.get(Calendar.YEAR);
		int borrowDay = calendar.get(Calendar.DAY_OF_YEAR);

		if (currentYear < borrowYear) {
			if (isLeapYear(currentYear)) {
				borrowDay += LEAP_YEAR_DAYS;
			} else {
				borrowDay += NO_LEAP_YEAR_DAYS;
			}
		}
		else if (currentYear > borrowYear){
			if (isLeapYear(borrowYear)) {
				currentDay += LEAP_YEAR_DAYS;
			} else {
				currentDay += NO_LEAP_YEAR_DAYS;
			}
		}
		return (currentDay - borrowDay);
	}
	
	/**
	 * 计算是否闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if (year % 4 == 0)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
//		System.out.println(residueDay("2014-10-15 00:00:00"));
//		System.out.println(nextResidueDay("2016-01-15 00:00:00"));
//		System.out.println(firstResidueDay());
	}
}
