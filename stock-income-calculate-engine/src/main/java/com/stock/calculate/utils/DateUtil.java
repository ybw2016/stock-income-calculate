package com.stock.calculate.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期处理类.
 *
 * @author bowen.yan
 */
public class DateUtil {
    public static final String EXCHANGE_TRADE_TIME_START = "09:15:00";
    public static final String EXCHANGE_TRADE_TIME_OVER = "15:00:00";
    public static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_SHORT_TIME = "yyyy-MM-dd";
    public static final String PATTERN_INIT_DATE = "yyyyMMdd";
    public static final String PATTERN_HHMMSS = "HHmmss";
    /**
     * 一天包含的毫秒数.
     */
    public static final long ONE_DAY_TIME_MS = 24 * 60 * 60 * 1000L;
    public static final long SECONDS_PRE_DAY = 24L * 60 * 60;

    public static String toShortTime(Date date) {
        return new SimpleDateFormat(PATTERN_SHORT_TIME).format(date);
    }

    public static String toShortTime(int initDate) {
        return convertFormat(String.valueOf(initDate), "yyyyMMdd", "yyyy-MM-dd");
    }

    public static Date toShortTime(String inputDate) {
        return parse(inputDate, PATTERN_SHORT_TIME);
    }

    public static int toShortTimeInt(String inputDate) {
        return Integer.parseInt(inputDate.replace("-", ""));
    }

    public static String toLongTime(Date date) {
        DateFormat format = new SimpleDateFormat(PATTERN_DEFAULT);
        return format.format(date);
    }

    public static Date toLongTime(String inputDate) {
        return parse(inputDate, PATTERN_DEFAULT);
    }

    /**
     * 日期字符串转换.
     * 转换格式: yyyyMMdd --> yyyy-MM-dd
     *
     * @param dateFrom 源日期
     * @return 目标格式日期
     */
    public static String getFormatDate(String dateFrom) {
        if (StringUtils.isEmpty(dateFrom) || dateFrom.length() != 8) {
            return null;
        }
        String dateTo = dateFrom.substring(0, 4) + "-" + dateFrom.substring(4, 6) + "-" + dateFrom.substring(6);
        return dateTo;
    }

    public static Date getDefaultDate() {
        return getCurrent();
    }

    /**
     * 取当前日期.
     * 格式: yyyy-MM-dd
     *
     * @return 获取今天
     */
    public static String getToday() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(getCurrent());
    }

    /**
     * 获取今天.
     */
    public static Date getCurrent() {
        return new Date();
    }

    public static int getTodayInt() {
        return getIntDateFrom(getCurrent());
    }

    public static int getIntDateFrom(Date bizDate) {
        return Integer.parseInt(format(bizDate, "yyyyMMdd"));
    }

    public static Date getFromIntDate(int initDate) {
        return parse(String.valueOf(initDate), PATTERN_INIT_DATE);
    }

    public static String getCurrentLongTime() {
        DateFormat format = new SimpleDateFormat(PATTERN_DEFAULT);
        return format.format(getCurrent());
    }

    /**
     * 根据日期取星期.
     *
     * @param dateStr yyyy-MM-dd
     * @return 星期一 ~ 星期日
     */
    public static String getWeek(String dateStr) {
        Date date = parse(dateStr, "yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return new SimpleDateFormat("EEEE", Locale.SIMPLIFIED_CHINESE).format(cal.getTime());
    }

    /**
     * 本周第一天.
     *
     * @param date 日期
     */
    public static boolean isFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == 2;
    }

    /**
     * 本周第一天.
     *
     * @param date 日期
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DAY_OF_MONTH, -1); //解决周日会出现 并到下一周的情况
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return cal.getTime();
    }

    /**
     * 本周最后一天.
     *
     * @param date 日期
     */
    public static boolean isEndDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == 1;
    }

    /**
     * 本月第一天.
     *
     * @param date 日期
     */
    public static boolean isFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }

    /**
     * 本月第一天.
     *
     * @param date 日期
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 本月最后一天.
     *
     * @param date 日期
     */
    public static boolean isEndDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currDay = calendar.get(Calendar.DAY_OF_MONTH);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return currDay == lastDay;
    }

    /**
     * 获取偏离今天的天数.
     *
     * @param offsetDays 偏离天数
     */
    public static Date getOffsetToday(int offsetDays) {
        return getOffsetDate(getCurrent(), offsetDays);
    }

    /**
     * 得到偏离现在天数的时间  +几天后, -几天前.
     *
     * @param date       日期
     * @param offsetDays 偏离天数
     */
    public static Date getOffsetDate(Date date, int offsetDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + offsetDays);
        return calendar.getTime();
    }

    /**
     * 得到偏离现在天数的时间  +几天后, -几天前.
     *
     * @param date       日期 yyyy-MM-dd
     * @param offsetDays 偏离天数
     * @return yyyy-MM-dd
     */
    public static String getOffsetDate(String date, int offsetDays) {
        return toShortTime(getOffsetDate(parse(date, PATTERN_SHORT_TIME), offsetDays));
    }

    /**
     * 获取凌晨时间.
     *
     * @return yyyy-MM-dd 24:00:00
     */
    public static Date getTodayEndTime() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = getCurrent();
            return format.parse(format.format(now).substring(0, 10) + " 24:00:00");
        } catch (Exception ex) {
            //log.error("parser tomorrow beginning error", ex);
            return null;
        }
    }

    /**
     * 获取缓存的总秒数(24:00:00过期).
     *
     * @return 返回到24:00:00的秒数
     */
    public static long getTodayEndExpireSeconds() {
        Date now = getCurrent();
        Date todayEnd = DateUtil.getTodayEndTime();
        return getSecondsOfTimePeriod(now, todayEnd);
    }

    /**
     * 获取两个时间的时间差.
     *
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @return 秒
     */
    public static long getSecondsOfTimePeriod(Date startTime, Date endTime) {
        return (endTime.getTime() - startTime.getTime()) / 1000;
    }

    /**
     * 获取缓存的总秒数(多少天对应的总秒数).
     *
     * @return 返回的秒数
     */
    public static long getExpireSecondsOfDays(int keepDays) {
        return keepDays * SECONDS_PRE_DAY;
    }

    /**
     * 得到偏离现在分钟数的时间  +几分钟后, -几分钟前.
     *
     * @param date          日期
     * @param offsetMinutes 偏离分钟数
     */
    public static Date getOffsetMinutes(Date date, int offsetMinutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + offsetMinutes);
        return calendar.getTime();
    }

    /**
     * 获取当天开盘时间.
     *
     * @return date
     */
    public static Date getExchangeTradeTimeStartTime() {
        String tradeStartTime = String.format("%s %s", getToday(), EXCHANGE_TRADE_TIME_START);
        return parse(tradeStartTime, PATTERN_DEFAULT);
    }

    /**
     * 获取业务日期当天的开盘时间.
     *
     * @param bizDate 业务日期
     * @return date
     */
    public static Date getExchangeTradeTimeStartTime(Date bizDate) {
        String tradeStartTime = String.format("%s %s", DateUtil.toShortTime(bizDate), EXCHANGE_TRADE_TIME_START);
        return parse(tradeStartTime, PATTERN_DEFAULT);
    }

    public static boolean isExchangeTradeTimeStarted() {
        return isExchangeTradeTimeStarted(Calendar.getInstance().getTime());
    }

    /**
     * 判断交易日有没有开市.
     *
     * @param date 交易日
     * @return 是否开市
     */
    public static boolean isExchangeTradeTimeStarted(Date date) {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        String timeStr = format.format(date);
        return timeStr.compareTo(EXCHANGE_TRADE_TIME_START) > 0;
    }

    public static boolean isExchangeTradeTimeEnded() {
        return isExchangeTradeTimeEnded(Calendar.getInstance().getTime());
    }

    /**
     * 判断交易日有没有闭市.
     *
     * @param date 交易日
     * @return 是否收市
     */
    public static boolean isExchangeTradeTimeEnded(Date date) {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        String timeStr = format.format(date);
        return timeStr.compareTo(EXCHANGE_TRADE_TIME_OVER) > 0;
    }

    /**
     * 是否是盘中交易时间.
     *
     * @return boolean
     */
    public static boolean isExchTradeTime() {
        return isExchangeTradeTimeStarted() && !isExchangeTradeTimeEnded();
    }

    /**
     * 日期字符串 -> 日期.
     *
     * @param inputDate 输入日期, 如: "2016-09-22 18:00:21"
     * @param format    转换格式, 如: yyyy-MM-dd HH:mm:ss
     */
    public static Date parse(String inputDate, String format) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(inputDate);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 日期格式化.
     *
     * @param date         输入日期, 如: 2016-09-22 18:00:21
     * @param formatPatten 转换格式, 如: yyyy-MM-dd HH:mm:ss
     */
    public static String format(Date date, String formatPatten) {
        DateFormat formatter = new SimpleDateFormat(formatPatten);
        return formatter.format(date);
    }

    /**
     * 日期格式转换.
     *
     * @param dateStr       现有日期串
     * @param srcPattern    现有格式
     * @param targetPattern 目标格式
     * @return String
     */
    public static String convertFormat(String dateStr, String srcPattern, String targetPattern) {
        Date date = parse(dateStr, srcPattern);
        return format(date, targetPattern);
    }

    /**
     * 获取指定日期字符串.
     *
     * @param format 返回的日期格式
     * @return 目标格式日期
     */
    public static String getDateString(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 获取指定日期字符串.
     *
     * @param changeDate 当前日期增量 可以为负值
     * @param format     返回的日期格式
     * @return 目标格式日期
     */
    public static String getDateString(int changeDate, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, changeDate);

        DateFormat df = new SimpleDateFormat(format);
        return df.format(calendar.getTime());
    }

    /**
     * 判断时间1 晚于 时间2.
     *
     * @param time1 时间1
     * @param time2 时间2
     */
    public static boolean isLaterThan(Date time1, Date time2) {
        return time1.getTime() > time2.getTime();
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒.
     *
     * @param startTime 时间参数 1 格式：1990-01-01 12:00:00
     * @param endTime   时间参数 2 格式：2009-01-01 12:00:00
     * @return 天时分秒字符串
     */
    public static String getTimeStatisticsString(Date startTime, Date endTime) {
        long[] statistics = getTimeStatistics(startTime, endTime);
        StringBuilder timeAppender = new StringBuilder();
        if (statistics[0] > 0) {
            timeAppender.append(statistics[0] + "天");
        }
        if (statistics[1] > 0) {
            timeAppender.append(statistics[1] + "时");
        }
        if (statistics[2] > 0) {
            timeAppender.append(statistics[2] + "分");
        }
        if (statistics[3] >= 0) {
            timeAppender.append(statistics[3] + "秒");
        }
        return timeAppender.toString();
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒.
     *
     * @param startTime 时间参数 1 格式：1990-01-01 12:00:00
     * @param endTime   时间参数 2 格式：2009-01-01 12:00:00
     * @return 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getTimeStatistics(Date startTime, Date endTime) {
        long time1 = startTime.getTime();
        long time2 = endTime.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        long days = diff / (24 * 60 * 60 * 1000);
        long hours = (diff / (60 * 60 * 1000) - days * 24);
        long minutes = ((diff / (60 * 1000)) - days * 24 * 60 - hours * 60);
        long seconds = (diff / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60);
        return new long[]{days, hours, minutes, seconds};
    }
}
