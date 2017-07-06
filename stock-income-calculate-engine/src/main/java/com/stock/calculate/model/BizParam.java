package com.stock.calculate.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户业务数据参数.
 *
 * @author bowen.yan
 * @since 2016-12-07
 */
public class BizParam {
    private Date bizDate;
    private String userId;

    public BizParam(Date bizDate, String userId) {
        this.bizDate = (bizDate == null) ? null : new Date(bizDate.getTime());
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getBizDate() {
        return (bizDate == null) ? null : new Date(bizDate.getTime());
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = (bizDate == null) ? null : new Date(bizDate.getTime());
    }

    public int getInitDate() {
        return Integer.parseInt(getBizDateStr().replace("-", ""));
    }

    /**
     * 获取业务日期字符串.
     *
     * @return 如：2017-04-28
     */
    public String getBizDateStr() {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(bizDate);
        } catch (Exception e) {
            return "";
        }
    }

    public String getAccountPkKey() {
        return String.format("%s_%s", getInitDate(), userId);
    }

    public String getAccountKey() {
        return String.format("initDate:%s, userId:%s", getInitDate(), userId);
    }

    @Override
    public String toString() {
        return "BizParam{" +
            "userId='" + userId + '\'' +
            ", bizDate=" + bizDate +
            '}';
    }
}
