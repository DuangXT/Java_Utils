package com.duangxt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author duangxt
 * @Title: TimeUtil.java
 * @Description: 时间处理工具类
 * @version V2.0
 */
public class TimeUtils {

    protected TimeUtils(){}

    /** yyyy-MM */
    public static final String F_YYYY_MM = "yyyy-MM";
    /** yyyy/MM */
    public static final String F_YYYY_MM_SPTIT = "yyyy/MM";

    /** yyyy-MM-dd */
    public static final String F_YYYY_MM_DD = "yyyy-MM-dd";
    /** yyyy/MM/dd */
    public static final String F_YYYY_MM_DD_SPRIT = "yyyy/MM/dd";

    /** yyyy-MM-dd HH:mm:ss */
    public static final String F_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /** yyyy/MM/dd HH:mm:ss */
    public static final String F_YYYY_MM_DD_HH_MM_SS_SPRIT = "yyyy/MM/dd HH:mm:ss";

    /** yyyy-MM-dd HH:mm */
    public static final String F_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    /** yyyy/MM/dd HH:mm */
    public static final String F_YYYY_MM_DD_HH_MM_SPRIT = "yyyy/MM/dd HH:mm";

    /** yyyyMMdd */
    public static final String F_YYYYMMDD = "yyyyMMdd";

    /** yyyy-MM-dd HH */
    public static final String F_YYYY_MM_DD_HH = "yyyy-MM-dd HH";

    /** yyyyMMdd HH:mm:ss */
    public static final String F_YYYYMMDD_HH_MM_SS = "yyyyMMdd HH:mm:ss";

    /**
     * 根据时间字符串个对应格式获得时间戳
     *
     * @param dateStr 时间字符串
     * @param format  格式
     * @return
     */
    public static long getTime(String dateStr, String format) {
        if (dateStr == null) return -1;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr).getTime();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 获取格式化的时间字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getDateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }


    /**
     * 将时间字符串转成Date
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date getStringToDate(String dateStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期增加天数，可以为负数
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    /**
     * 日期增加 hour 小时（负数为减少）
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addDaysForHour(Date date, int hour) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 日期增加 minute 分钟（负数为减少）
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addDaysForMinute(Date date, int minute) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 时间(long类型)转字符串
     *
     * @param time
     * @param format
     * @return
     */
    public static String getLongToString(long time, String format) {
        return getDateToString(new Date(time), format);
    }


    /**
     * 由字符串取long类型时间
     *
     * @param date
     * @param format
     * @return
     */
    public static Long getStringToLong(String date, String format) {
        long time = 0L;
        try {
            time = getStringToDate(date, format).getTime();
        } catch (Exception e) {
        }
        return time;
    }


    /**
     * 取指定时间前后的第N天（正数为后，负数为前）
     *
     * @param date
     * @param days
     * @return
     */
    public static Date getAfterDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }


    /**
     * 昨天
     *
     * @return
     */
    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        SimpleDateFormat sdf = new SimpleDateFormat(F_YYYY_MM_DD);
        return sdf.format(calendar.getTime());

    }


    /**
     * 取指定时间前后的第N周（正数为后，负数为前）
     *
     * @param date
     * @param week
     * @return
     */
    public static int getAfterWeeks(Date date, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // 设置每周的第一天为星期一
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 每周从周一开始
        // 上面两句代码配合，才能实现，每年度的第一个周，是包含第一个星期一的那个周。
        calendar.setMinimalDaysInFirstWeek(7); // 设置每周最少为7天
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, week);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }


    /**
     * 取指定时间前后的第N个月的某一天（正数为后，负数为前）
     *
     * @param date
     * @param months
     * @return
     */
    public static Date getAfterMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }


    /**
     * 指定时间内，取YYYY的列表
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getYYYYList(Date start, Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int startY = calendar.get(Calendar.YEAR);
        calendar.setTime(end);
        int endY = calendar.get(Calendar.YEAR);

        List<String> list = new ArrayList<>();
        for (int y = startY; y <= endY; y++) {
            list.add(String.valueOf(y));
        }
        return list;
    }


    /**
     * 指定的时间内取YYYYMM的列表
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getYYYYMMList(Date start, Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        endCalendar.set(Calendar.DAY_OF_MONTH, 1);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            if (calendar.after(endCalendar)) {
                break;
            }
            int month = calendar.get(Calendar.MONTH) + 1;
            if (month > 9) {
                list.add(String.valueOf(calendar.get(Calendar.YEAR) + String.valueOf(month)));
            } else {
                list.add(String.valueOf(calendar.get(Calendar.YEAR) + "0" + String.valueOf(month)));
            }
            calendar.add(Calendar.MONTH, 1);
        }
        return list;
    }


    /**
     * 指定的时间内取YYYY-MM的列表
     *
     * @param startMonth
     * @param endMonth
     * @return YYYY-MM
     */
    public static List<String> getYYYYMMList(String startMonth, String endMonth) {
        return getYYYYMMList(startMonth, endMonth, "/");
    }

    /**
     * 指定的时间内取YYYY-MM的列表
     *
     * @param startMonth
     * @param endMonth
     * @return YYYY-MM
     */
    public static List<String> getYYYYMMList(String startMonth, String endMonth, String split) {
        Date start = getStringToDate(startMonth, "yyyy-MM");
        Date end = getStringToDate(endMonth, "yyyy-MM");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        endCalendar.set(Calendar.DAY_OF_MONTH, 1);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            if (calendar.after(endCalendar)) break;

            int month = calendar.get(Calendar.MONTH) + 1;
            if (month > 9) list.add(String.valueOf(calendar.get(Calendar.YEAR) + split
                    + String.valueOf(month)));
            else list.add(String.valueOf(calendar.get(Calendar.YEAR) + split
                    + "0" + String.valueOf(month)));
            calendar.add(Calendar.MONTH, 1);
        }
        return list;
    }


    /**
     * 指定的时间内取YYYYMM的列表
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> getYYYYMMList2(Date start, Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        endCalendar.set(Calendar.DAY_OF_MONTH, 1);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            if (calendar.after(endCalendar)) break;

            int month = calendar.get(Calendar.MONTH) + 1;
            if (month > 9) list.add(String.valueOf(calendar.get(Calendar.YEAR) + "-" + String.valueOf(month)));
            else list.add(String.valueOf(calendar.get(Calendar.YEAR) + "-" + "0" + String.valueOf(month)));

            calendar.add(Calendar.MONTH, 1);
        }
        return list;
    }


    /**
     * 指定的时间范围内取日期列表（精确到天 yyyy-MM-dd或者yyyyMMdd或者yyyy/MM/dd）
     * <br/>
     * <pre>
     * 如果 end 日志在 start 之前，会返回空 List
     * getYYYYMMDDList(1970-10-30, 1970-09-30, yyyyMMdd) --> list.size()=0
     *
     * 可在调用前判断 start 和 end，置换参数进行获取：
     * getYYYYMMDDList(1970-09-30, 1970-10-30, yyyyMMdd) --> list.size()=30
     * </pre>
     * @param start
     * @param end
     * @return List&lt;String&gt;
     */
    public static List<String> getYYYYMMDDList(Date start, Date end, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        calendar.setTime(start);
        endCalendar.setTime(end);
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            if (calendar.after(endCalendar))
                break;
            list.add(sdf.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }


    /**
     * 取今天结束时间(23:59:59)
     *
     * @return
     */
    public static long getTodayEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime().getTime() + 999L;
    }


    public static Date addSecs(Date date, int secs) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, secs);
        return calendar.getTime();
    }

    /**
     * 日期增加月数，可以为负数
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addMonths(Date date, int months) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }

    /** 获取上一周的同一天 */
    public static Calendar getDateOfDay(Calendar date) {
        Calendar lastDate = (Calendar) date.clone();
        lastDate.add(Calendar.WEDNESDAY, -1);
        return lastDate;
    }

    public static Calendar getDateOfDay(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(dateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return getDateOfDay(c);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format(yyyy-MM-dd HH:mm:ss): " + dateStr);
        }
    }

    /**
     * 得到前n天的日期集合
     *
     * @param date 指定时间
     * @param num  只能为正整数
     * @return 时间集合 yyyy-MM-dd   不包括指定时间
     */
    public static List<String> getSomeDay(Date date, int num) {
        List<String> dates = new ArrayList<String>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        num = Math.abs(num);
        for (int i = 0; i < num; i++) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            dates.add(getDateToString(cal.getTime(), "yyyy-MM-dd"));
        }
        Collections.reverse(dates);
        return dates;
    }

    /** 获取一个月前的今天 */
    public static Date getDayByMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 取日期范围内的日期(yyyy-MM)数组
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getMonthListByRange(String startDate, String endDate) {
        List<String> months = new ArrayList<>();

        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(getStringToDate(startDate, F_YYYY_MM));
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(getStringToDate(endDate, F_YYYY_MM));

        while (!calendarStart.after(calendarEnd)) {
            months.add(getDateToString(calendarStart.getTime(), F_YYYY_MM));
            calendarStart.add(Calendar.MONTH, 1);
        }
        return months;
    }

    /**
     * 取当前日期所在月份第一天
     *
     * @param date
     * @return
     */
    public static long getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 取前日期所在月的最后一天的最后一刻
     *
     * @param date
     * @return
     */
    public static long getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime().getTime() + 999L;
    }

    /**
     * 获取指定月份的最后一天
     *
     * @param month yyyy-MM
     * @return
     */
    public static Date getLastDayOfMonth(String month) {
        Date date = getStringToDate(month, F_YYYY_MM);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    /**
     * 判定是不是今天
     *
     * @param time 时间毫秒串(<strong>注意:精确到毫秒</strong>)
     * @return
     * @author xuefei
     */
    public static boolean isToday(final Long time) {
        if (null == time) {
            return false;
        }
        Calendar target = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        target.setTimeInMillis(time);
        if (target.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                && target.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                && target.get(Calendar.DAY_OF_MONTH) == today
                .get(Calendar.DAY_OF_MONTH)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 时间范围校验
     *
     * @param time    毫秒时间
     * @param expired 过期时间
     * @return
     */
    public static boolean validTime(long time, long expired) {
        long diff = System.currentTimeMillis() - time;
        if (diff > expired || diff < -expired) {
            return false;
        }
        return true;
    }


    /** @return 两天的时间差（天） */
    public static int diffDays(Date date1, Date date2) {
        return (int) ((date1.getTime() - date2.getTime()) / 1000 / 60 / 60 / 24);
    }

    /** @return 两天的时间差（毫秒） */
    public static long diffMils(Date date1, Date date2) {
        return date1.getTime() - date2.getTime();
    }


    /**
     * 根据字符串得到table名
     *
     * @param table 如log_dau
     * @param date  2016-03-27
     * @return 最终的表名，如log_dau_20160327
     */
    public static String getTable(String table, String date) {
        Date newDate = getStringToDate(date, F_YYYY_MM_DD);
        String dateStr = getDateToString(newDate, F_YYYYMMDD);
        return table + '_' + dateStr;
    }

    /**
     * 返回2个日期之间相差的天数和小时
     *
     * @param beginDateStr
     * @param endDateStr
     * @return
     */
    public static String getDiffDayHour(String beginDateStr, String endDateStr) {
        Date beginDate = null, endDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            beginDate = sdf.parse(beginDateStr);
            endDate = sdf.parse(endDateStr);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long diff = endDate.getTime() - beginDate.getTime();

        Long days = diff / (1000 * 60 * 60 * 24);
        Long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        String ret = "";
        if (days != 0) ret += days + "天";
        if (hours != 0) ret += hours + "小时";

        return ret;
    }

    /**
     * 获取两个日期之间相差的天数，小于一天的按一天算
     *
     * @param beginDateStr
     * @param endDateStr
     * @return int 天数
     */
    public static Long getDiffDay(String beginDateStr, String endDateStr) {
        Date beginDate = null, endDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            beginDate = sdf.parse(beginDateStr);
            endDate = sdf.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Long diff = endDate.getTime() - beginDate.getTime();
        Long days = diff / (1000 * 60 * 60 * 24);
        Long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        if (hours > 0) days++;

        return days;
    }

    /**
     * 获得指定月份（yyyy-MM）的天数列表
     *
     * @param month   指定月份
     * @param pattern 输出日期格式
     * @return 返回对应日期格式的集合
     */
    public static List<String> getDateList(String month, String pattern) {
        Date date = getLastDayOfMonth(month);
        String firstDay = getLongToString(getFirstDayOfMonth(date), pattern);
        List<String> dates = getDateListByRange(firstDay, getDateToString(date, pattern), pattern);
        return dates;
    }

    /**
     * 取日期范围内的日期(yyyyMMdd)数组
     *
     * @param startDate
     * @param endDate
     * @param pattern
     * @return
     */
    public static List<String> getDateList(String startDate, String endDate, String pattern) {
        List<String> dates = new ArrayList<>();
        if (Tools.notNull(pattern)) pattern = F_YYYYMMDD;
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(getStringToDate(startDate, pattern));
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(getStringToDate(endDate, pattern));

        while (!calendarStart.after(calendarEnd)) {
            dates.add(getDateToString(calendarStart.getTime(), pattern));
            calendarStart.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dates;
    }

    /**
     * 取日期范围内的日期(yyyy-MM-dd)数组
     *
     * @param startDate
     * @param endDate
     * @param pattern
     * @return
     */
    public static List<String> getDateListByRange(String startDate, String endDate, String pattern) {
        if (Tools.notNull(pattern)) pattern = "yyyy-MM-dd";
        return getDateList(startDate, endDate, pattern);
    }


    /**
     * 根据传入的时间得到往后N天内的日期
     * @param date  指定的日期
     * @param format  格式  yyyy-MM-dd  yyyy/MM/dd yyyyMMdd ......
     * @param n 天数
     * @return 返回date到N天内的日期集合
     */
    public static List<String> getDates(String date, String format, int n){
        Date start = getStringToDate(date, format);
        Date end =  addDays(getStringToDate(date, format), n-1);
        String pattern = format;
        List<String> dates = new ArrayList<>();
        if(n>0) dates = getYYYYMMDDList(start, end, pattern);
        else dates = getYYYYMMDDList(end, start, pattern);

        return dates;
    }

    /**
     * 获取月份，i为负数取前面月份，正数取后面月份
     *
     * @param date
     * @param i
     * @return
     */
    public static String getMonth(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, i);
        return getDateToString(cal.getTime(), "yyyy-MM");
    }
}
