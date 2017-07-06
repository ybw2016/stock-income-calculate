package com.stock.calculate.utils;

import java.util.Date;

/**
 * 运行时长统计工具.
 *
 * @author bowen.yan
 * @since 2017-03-31
 */
public class TimeTracker {
    private Date startTime;
    private Date endTime;

    private TimeTracker() {
    }

    public Date getStartTime() {
        return (startTime == null) ? null : new Date(startTime.getTime());
    }

    private void setStartTime(Date startTime) {
        this.startTime = (startTime == null) ? null : new Date(startTime.getTime());
    }

    public Date getEndTime() {
        return (endTime == null) ? null : new Date(endTime.getTime());
    }

    private void setEndTime(Date endTime) {
        this.endTime = (endTime == null) ? null : new Date(endTime.getTime());
    }

    /**
     * 创建实例并开始计时.
     *
     * @return instance
     */
    public static TimeTracker createAndStartTimer() {
        TimeTracker timeTracker = new TimeTracker();
        timeTracker.setStartTime(new Date());
        return timeTracker;
    }

    /**
     * 设置结束时间点，并统计耗时.
     *
     * @return XX天XX时XX分XX秒
     */
    public String stopAndGetTimeStatistics() {
        stopTimer();
        return DateUtil.getTimeStatisticsString(startTime, endTime);
    }

    public void stopTimer() {
        this.setEndTime(new Date());
    }

    public void reset() {
        setStartTime(new Date());
    }

    public String getTimeStatistics() {
        return DateUtil.getTimeStatisticsString(startTime, endTime);
    }
}