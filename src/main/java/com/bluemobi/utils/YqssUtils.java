package com.bluemobi.utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;


public class YqssUtils {

	public static final int LEAP_YEAR_DAYS = 366;
	public static final int NO_LEAP_YEAR_DAYS = 365;

	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static int RESIDUE_DAY = 15; // 默认还款日期

	static {
		String rd = PropertiesUtils.getPropertiesValues("repayment_date_day","phyy.properties");
		if (StringUtils.isNotBlank(rd)) {
			RESIDUE_DAY = Integer.parseInt(rd);
		}
	}

	/**
	 * 
     * @Title: numberFormat
     * @Description: 保留价格两位小数
     * @param @param number
     * @param @return    参数
     * @return double    返回类型
     * @throws
	 */
	public static double numberFormat(double number) {
		return Double.parseDouble(new DecimalFormat("#.00").format(number));
	}
	
	/**
	 * 
     * @Title: countRate
     * @Description: 计算月供
     * @param @param rate
     * @param @param stage
     * @param @return    参数
     * @return double    返回类型
     * @throws
	 */
	public static double countRate(double rate,int stage,double money) {
		if(stage == 0) {return money;}
		return (money / stage * 1.0) + (money * (rate/100.0));
	}
	
	/**
	 * 
     * @Title: countRate0
     * @Description: 计算月供
     * @param @param stage
     * @param @param money
     * @param @return    参数
     * @return double    返回类型
     * @throws
	 */
	public static double countRate0(String residueDate,int stage,double money) {
		if(stage == 0) return money;
		double rate = DBUtils.getRate();
		int exceedDays = residueDay(residueDate);
		double surplus = 0;
		if(exceedDays < 0) {
			surplus = getInterest(residueDate, money, rate, stage);
		}
		return (money / stage * 1.0) + (money * (rate/100.0)) + surplus;
	}
	
	/**
	 * 计算过期利息
	 * @param residueDay
	 * @param rate
	 * @return
	 */
	public static double getInterest(String residueDate,double money,double rate,int stage){
		int exceedDays = residueDay(residueDate);
		double overdue = DBUtils.getRate0();
		if(exceedDays < 0) {
			return exceedDays * overdue * -1;
		}
		return 0;
	}
	
	
	/**
	 * 计算剩余还款天数
	 * 
	 * @param strDate
	 * @return
	 */
	public static int residueDay(String residueDate) {
		if(StringUtils.isBlank(residueDate)) return -1;
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
		calendar.add(Calendar.MONTH,1);
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
	 * 
     * @Title: borrowResidueDate
     * @Description: 计算借款还款时间
     * @param @return    参数
     * @return String    返回类型
     * @throws
	 */
	public static String borrowResidueDate(int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return DateUtils.toString(calendar.getTime(), DEFAULT_FORMAT);
	}
	
	/**
	 * 判断日期是否在当前月
	 * @param date
	 * @return
	 */
	public static boolean isThisMonth(long date,String nextDate,int month) {
		
		long nextDateLong = DateUtils.stringToLong(nextDate, DEFAULT_FORMAT);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(nextDateLong));
		c.add(Calendar.MONTH, month * -1);
		long preDateLong = c.getTimeInMillis();
		
		c.add(Calendar.MONTH, (month+1) * -1);
		long prepreDateLong = c.getTimeInMillis();
		
		if(date < preDateLong && date > prepreDateLong) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param lastDate 最后还款时间
	 * @param nextDate 下次还款时间
	 * @return
	 */
	public static int exceedMonth(long lastDate,String nextDate) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(lastDate));
		int lasdMonth = calendar.get(Calendar.MONTH);
		calendar.setTime(DateUtils.parse(nextDate, DEFAULT_FORMAT));
		
		int nextMonth = calendar.get(Calendar.MONTH);
		return nextMonth - lasdMonth;
	}
	
	
	/**
     * 计算两个日期之间的月数
     * @param time1 下次还款时间
     * @param time2 最后还款时间
     * @return
     * @throws ParseException
     * @author sg/2014-6-26/上午10:09:01
     */
    public static int getMonthSpace(long time1, long time2){
        int result = 0;
        Date date1 = null;
        Date date2 = null;
         
        date1 = new Date(time1);
        date2 = new Date(time2);
         
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        result = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR))*12+(c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH));
        int dayMonths = c2.get(Calendar.DAY_OF_MONTH)-c1.get(Calendar.DAY_OF_MONTH);
        if(dayMonths<0){
            result--;
        }else if(dayMonths>0){
            result ++;
        }else{//日期相同,时间不同情况
            int hourDays = c2.get(Calendar.HOUR_OF_DAY)-c1.get(Calendar.HOUR_OF_DAY);
            if(hourDays>0){//判断小时
                result++;
            }else if(hourDays==0){//判断分钟
                int minHours = c2.get(Calendar.MINUTE)-c1.get(Calendar.MINUTE);
                if(minHours>0){
                    result++;
                }else if(minHours==0){//判断秒
                    int secMins = c2.get(Calendar.SECOND)-c1.get(Calendar.SECOND);
                    if(secMins>0){
                        result++;
                    }else if(secMins==0){//判断毫秒
                        int millSecs = c2.get(Calendar.MILLISECOND )-c1.get(Calendar.MILLISECOND );
                        if(millSecs>0){
                            result++;
                        }
                    }
                }
            }
        }
        return  result < 0 ? result + 1 : 0;
    }
	
	/**
	 * 生成订单号
	 */
	public static String generateSn() {
		Random r = new Random();
		int random = r.nextInt(1000);
		long current = System.currentTimeMillis();
		return String.valueOf(current) + String.valueOf(random);
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
	
	 /**
     * 将json格式的字符串解析成Map对象 <li>
     * json格式：{"name":"admin","retries":"3fff","testname" :"ddd","testretries":"fffffffff"}
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> jsontoMap(Object object) {
        Map<String, Object> data = new HashMap<String, Object>();
        // 将json字符串转换成jsonObject
        JSONObject jsonObject = JSONObject.fromObject(object);
        Iterator it = jsonObject.keys();
        // 遍历jsonObject数据，添加到Map对象
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            Object value = (Object) jsonObject.get(key);
            data.put(key, value);
        }
        return data;
    }
    
    
	
	public static void main(String[] args) {
//		System.out.println(residueDay("2014-10-15 00:00:00"));
//		System.out.println(nextResidueDay("2016-02-16 13:46:23"));
//		System.out.println(firstResidueDay());
//		System.out.println(borrowResidueDate(3));
		System.out.println(countRate(2, 6, 6288));
//		System.out.println(numberFormat(100000.123456798));
//		System.out.println(countRate0(10,1000));
//		System.out.println(nextResidueDay("2016-1-30 15:09:00"));
//		System.out.println(generateSn());
//		System.out.println(totalDays("2016-01-18 00:00:00"));
//		System.out.println(countRate0("2016-01-15 00:00:00", 1, 6000));
//		System.out.println(isThisMonth(1451290808295L));
//		System.out.println(isThisMonth(1452922490570L, "2016-02-16 13:34:59", 1));
//		System.out.println(exceedMonth(System.currentTimeMillis(), "2015-12-18 00:00:00"));
//		System.out.println(getMonthSpace( DateUtils.stringToLong("2016-3-17 17:13:00", DEFAULT_FORMAT),DateUtils.stringToLong("2016-1-16 17:11:00", DEFAULT_FORMAT)));
	}
}
