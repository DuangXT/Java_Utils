package com.duangxt.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author duangxt
 * @Title: DateUtils.java
 * @Description: 时间处理工具类
 * @date 2018-12-27 00:44:39
 * @version V2.0
 */
public class DateUtils extends TimeUtils{

    private DateUtils(){}

    private final static SimpleDateFormat SDF_YEAR = new SimpleDateFormat("yyyy");

    public final static SimpleDateFormat SDF_STAND_YMD = new SimpleDateFormat(F_YYYY_MM_DD);

    /** 时间格式   年-月-日  时-分-秒 */
    public final static SimpleDateFormat SDF_STAND_TIME =new SimpleDateFormat(F_YYYY_MM_DD_HH_MM_SS);
    /** 时间格式 年-月-日  时-分-秒 */
    public final static DateFormat TIME_FORMAT_YMD_HMS = new SimpleDateFormat(F_YYYY_MM_DD_HH_MM_SS);

    /** 时间格式    yyMMdd  XX年-XX月-XX日 */
    public final static SimpleDateFormat SDF_YYMMDD_STR =new SimpleDateFormat("yyMMdd");
    /** 时间格式   年月日 */
    public final static SimpleDateFormat SDF_SERIA_YDM_STR =new SimpleDateFormat(F_YYYYMMDD);
    /** 时间格式   年月日时分秒 */
    private final static SimpleDateFormat SDF_SERIA_STAND_STR = new SimpleDateFormat("yyyyMMddHHmmss");
    /** 时间格式   yyMMddHHmmssSSS（年月日时分秒毫秒） */
    public static final SimpleDateFormat SDF_SERIA_LONG_STR = new SimpleDateFormat("yyMMddHHmmssSSS");

    /** 时间格式  时-分-秒 */
    public final static SimpleDateFormat SDF_HMS =new SimpleDateFormat("HH:mm:ss");

    /** 时间格式   mmssSSS（分秒毫秒） */
    public static final SimpleDateFormat SDF_SERIA_MSS_STR = new SimpleDateFormat("mmssSSS");

    /**
     * 获取YYYY格式
     * 
     * @return
     */
    public static String getYear() {
        return SDF_YEAR.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     * 
     * @return
     */
    public static String getDay() {
        return SDF_STAND_YMD.format(new Date());
    }

    public static String getToDay(){ return getDay(); }
    /**
     * 获取YYYYMMDD格式
     * 
     * @return
     */
    public static String getTimeYMD(){
        return SDF_SERIA_YDM_STR.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     * 
     * @return
     */
    public static String getTime() {
        return SDF_STAND_TIME.format(new Date());
    }
    public static String getNowTime(){ return getTime(); }
    public static String getNowTime(String timeTypeStr) {
        return new SimpleDateFormat(timeTypeStr).format(new Date());
    }
    
    /**
     * 获取YYYYMMDDHHmmss格式
     * 
     * @return
     */
    public static String getAllString() {
        return SDF_SERIA_STAND_STR.format(new Date());
    }

    /**
     * 日期比较，如果s&gt;=e 返回true 否则返回false
     *
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if(fomatDate(s)==null||fomatDate(e)==null){
            return false;
        }
        return fomatDate(s).getTime() >=fomatDate(e).getTime();
    }

    /**
     * 格式化日期
     * 
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *  yyyy-MM-dd
     * @return
     */
    public static boolean isValidDate_yMd(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * 校验日期是否合法
     *  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean isValidDate_yMdHms(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * 校验日期是否合法
     *  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean isValidDate_Hms(String s) {
        DateFormat fmt = new SimpleDateFormat("HH:mm:ss");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static int getDiffYear(String startTime,String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long aa=0;
            int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }
      /**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        
            try {
                beginDate = format.parse(beginDateStr);
                endDate= format.parse(endDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);
        
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        
        return dateStr;
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String date2Str(Date date){
        return date2Str(date,"yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式，字符串转日期
     * @param date
     * @return
     */
    public static Date str2Date(String date, String format){
        if(null!=date&&!"".equals(date)){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Date str2Date(String date){
        return str2Date(date,F_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 按照参数format的格式，日期转字符串
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date,String format){
        if(date!=null)
            return new SimpleDateFormat(format).format(date);
        return "";
    }

    /**
     * 把时间根据时、分、秒转换为时间段
     * @param StrDate
     */
    public static String getTimes(String StrDate){
        String resultTimes = "";

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now;

        try {
            now = new Date();
            java.util.Date date=df.parse(StrDate);
            long times = now.getTime()-date.getTime();
            long day  =  times/(24*60*60*1000);
            long hour = (times/(60*60*1000)-day*24);
            long min  = ((times/(60*1000))-day*24*60-hour*60);
            long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);

            StringBuffer sb = new StringBuffer();
            if(hour>0 )
                sb.append(hour+"小时前");
            else if(min>0)
                sb.append(min+"分钟前");
            else sb.append(sec+"秒前");

            resultTimes = sb.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return resultTimes;
    }

    /**
     * 将指定的日期转换成Unix时间戳
     * @param date String 需要转换的日期 yyyy-MM-dd HH:mm:ss
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date) {
        long timestamp = 0;
        try {
            timestamp = SDF_STAND_TIME.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 将指定的日期转换成Unix时间戳
     * @param date String 需要转换的日期 yyyy-MM-dd
     * @return long 时间戳
     */
    public static long dateToUnixTimestamp(String date, String dateFormat) {
        long timestamp = 0;
        try {
            timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * 获得本年有多少天
     *
     * @return int 当年的天数
     */
    public static int getMaxYear() {
        Calendar cd = Calendar.getInstance();
        cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
        cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
        int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
        return MaxYear;
    }

    /**
     * 计算当前日期的前后多少天
     *
     * @param num 前为负,后为正
     * @return String 日期
     */
    public static String getCountDate(int num) {
        String str = "";
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, num);
        str = SDF_STAND_YMD.format(c.getTime());
        return str;
    }

    /**
     * 计算当月最后一天,返回字符串
     *
     * @return String 当月的最后一天
     */
    public static String getDefaultDay() {
        String str = "";
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        str = SDF_STAND_YMD.format(lastDate.getTime());
        return str;
    }

    /**
     * 上月第一天
     *
     * @return String 上月第一天
     */
    public static String getPreviousMonthFirst() {
        String str = "";
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        str = SDF_STAND_YMD.format(lastDate.getTime());
        return str;
    }

    /**
     * 获取当月第一天
     *
     * @return String 当月第一天
     */
    public static String getFirstDayOfMonth() {
        String str = "";
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        str = SDF_STAND_YMD.format(lastDate.getTime());
        return str;
    }

    /**
     * 获得当前日期与本周日相差的天数
     *
     * @return int 相差的天数
     */
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) return 0;
        return 1 - dayOfWeek;
    }

    /**
     * 获得本周星期日的日期
     *
     * @return String 本周星期日的日期
     */
    public static String getCurrentWeekday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        String preMonday = SDF_STAND_YMD.format(monday);
        return preMonday;
    }

    /**
     * 将字符串转换成日期(yyyy-MM-dd 时:分:秒)
     *
     * @param dateString 日期字符串
     * @return Date 字符串转换的日期
     */
    public static Date setDateLong(String dateString) {
        try {
            return SDF_STAND_TIME.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用当前时间加/减指定天数得到指定时间格式
     * @param day 正数为加，负数为减
     * @param format 时间格式
     * @return String
     */
    public static String addDay(int day, String format){
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, day);
        return sf.format(c.getTime());
    }

    /**
     * 字符串转换为Timestamp日期 
     * @param dateString 日期格式字符串，格式必须为yyyy-mm-dd hh:mm:ss
     * @return Date 转换后的日期
     */
    public static Timestamp stringToTimestamp(String dateString) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

}
