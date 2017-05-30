package com.app.msg.common.utils;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 功能描述： 日期/时间工具类
 */
public abstract class DateTimeUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

    public static final long dateSecond = 24 * 60 * 60 * 1000;
    private static final String SHORT_PATTERN = "yyyyMMdd";
    public static final String LONG_PATTERN = "yyyyMMddHHmmss";
    public static final String SHORT_DISPLAY_PATTERN = "yyyy-MM-dd";
    public static final String LONG_DISPLAY_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String FIRST_DATE_PATTERN = "yyyy-MM-01";

    public static final String MOVIEB_DATE_PATTERN = "yyyy年MM月dd日 HH:mm";

    public static String displayDate(Date date) {
        return format(date, SHORT_DISPLAY_PATTERN);
    }

    public static String displayDateTime(Date date) {
        return format(date, LONG_DISPLAY_PATTERN);
    }

    /**
     * 将时间戳转化为 yyyy-MM-dd HH:mm:ss
     */
    public static String formatByTimeStamp(long timeStamp) {
        SimpleDateFormat s = new SimpleDateFormat(LONG_DISPLAY_PATTERN);
        return s.format(timeStamp);
    }

    /**
     * 将日期转换为[yyyyMMddHHmmss]格式
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, LONG_PATTERN);
    }

    /**
     * 将日期转换为指定格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (Strings.isNullOrEmpty(pattern)) {
            pattern = LONG_PATTERN;
        }

        SimpleDateFormat s = new SimpleDateFormat(pattern);
        return s.format(date);
    }

    /**
     * 取得当前日期，格式为【yyyyMMdd】
     *
     * @return
     */
    public static String getCurrentDate() {
        return format(new Date(), SHORT_PATTERN);
    }

    /**
     * 按指定格式取得当前日期
     *
     * @param pattern
     * @return
     */
    public static String getCurrentDate(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 取得当前时间，格式【yyyyMMddHHmmss】
     *
     * @return
     */
    public static String getNowString() {
        return format(new Date());
    }

    /**
     * 取得指定格式的当前时间
     *
     * @param pattern
     * @return
     */
    public static String getNowString(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 取得昨天的日期
     *
     * @return
     */
    public static Date getLastDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }

    /**
     * 将时间戳转化为 Date
     */
    public static Date parse(long timeStamp) {
        SimpleDateFormat s = new SimpleDateFormat(LONG_DISPLAY_PATTERN);
        String dateStr = s.format(timeStamp);
        return parse(dateStr);
    }

    /**
     * 将格式为【yyyy-MM-dd HH:mm:ss】的字符串转换为日期
     *
     * @param date
     * @return
     */
    public static Date parse(String date) {
        try {
            return parse(date, LONG_DISPLAY_PATTERN);
        } catch (Exception e) {
            return parse(date, SHORT_DISPLAY_PATTERN);
        }
    }

    /**
     * 按指定格式，将字符串转换为日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date parse(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            logger.error("解析字符串【" + date + "】为【" + pattern + "】格式的日期对象时发生异常：", e);
        }

        return null;
    }

    /**
     * 求两个日期的天数之差
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static int getOddDateNum(String beginTime, String endTime) {
        Date dateBegin = parse(beginTime);

        if (dateBegin == null) {
            throw new IllegalArgumentException("无效的开始时间" + beginTime);
        }

        Date dateEnd = parse(endTime);

        if (dateEnd == null) {
            throw new IllegalArgumentException("无效的结束时间" + endTime);
        }

        int odd = (int) ((dateEnd.getTime() - dateBegin.getTime()) / (dateSecond));

        return odd;
    }

    /**
     * 取系统时间零点
     *
     * @return
     */
    public static Date getCurrentZeroTime() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 取指定日期零点
     *
     * @return
     */
    public static Date getDateZeroTime(Date date) {
        Calendar calendar = new Calendar.Builder().setInstant(date).build();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 取当前月份第一天日期 (yyyy-MM-dd 00:00:00)
     *
     * @return Date
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 取当前月份第一天日期 (yyyy-MM-dd 00:00:00)
     *
     * @return Date
     */
    public static Date getCurrentMothFirstDay() {
        return getMonthFirstDay(new Date());
    }


    /**
     * 取当前月份最后一天日期 (yyyy-MM-dd 23:59:59)
     *
     * @return Date
     */
    public static Date getCurrentMothLastDay() {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(getCurrentMothFirstDay());
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.SECOND, -1);

        return calendar.getTime();
    }

    /**
     * 取指定日期月份第一天日期 (yyyy-MM-dd)
     *
     * @return String
     */
    public static String getMothFirstDayString(Date date) {
        return format(date, FIRST_DATE_PATTERN);
    }

    /**
     * 取指定日期月份最后一天日期 (yyyy-MM-dd)
     *
     * @return
     */
    public static String getMonthLastDayString(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar
                .getActualMaximum(Calendar.DAY_OF_MONTH));
        return format(calendar.getTime(), SHORT_DISPLAY_PATTERN);
    }

    /**
     * 取当前月份第一天日期 (yyyy-MM-dd)
     *
     * @return String
     */
    public static String getCurrentMothFirstDayString() {
        return getMothFirstDayString(new Date());
    }

    /**
     * 根据时间戳获取是星期几
     */
    public static int getDayOfWeek(long timeStamp) {
        Date date = parse(timeStamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static void main(String args[]) {
        String date = "2015年03月20日 13:00";
        Date parseDate = DateTimeUtils.parse(date, MOVIEB_DATE_PATTERN);
        System.out.println(DateTimeUtils.displayDate(parseDate));
    }

}
