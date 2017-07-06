package com.stock.calculate.model;

import java.util.Date;

/**
 * 比赛参数.
 * @author bowen.yan
 * @since 2017-07-05
 */
public class MatchParam {
    private int matchId;
    private Date bizDate;
    private String userId;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Date getBizDate() {
        return bizDate;
    }

    public void setBizDate(Date bizDate) {
        this.bizDate = bizDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "MatchParam{" +
            "matchId=" + matchId +
            ", bizDate=" + bizDate +
            ", userId='" + userId + '\'' +
            '}';
    }
}
