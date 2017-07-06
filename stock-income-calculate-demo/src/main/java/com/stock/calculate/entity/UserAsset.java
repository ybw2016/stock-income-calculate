package com.stock.calculate.entity;

import java.math.BigDecimal;

/**
 * 总资产.
 *
 * @author bowen.yan
 */
public class UserAsset {
    private Integer id;
    private Integer initDate;
    private String userId;
    private BigDecimal fundBalance = BigDecimal.ZERO; //资产总值
    private BigDecimal enableBalance = BigDecimal.ZERO; //资金可用金额
    private BigDecimal marketValue = BigDecimal.ZERO; //市值

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInitDate() {

        return initDate;
    }

    public void setInitDate(Integer initDate) {
        this.initDate = initDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getFundBalance() {
        return fundBalance;
    }

    public void setFundBalance(BigDecimal fundBalance) {
        this.fundBalance = fundBalance;
    }

    public BigDecimal getEnableBalance() {
        return enableBalance;
    }

    public void setEnableBalance(BigDecimal enableBalance) {
        this.enableBalance = enableBalance;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    @Override
    public String toString() {
        return "UserAsset{" +
            "id=" + id +
            ", initDate=" + initDate +
            ", userId='" + userId + '\'' +
            ", fundBalance=" + fundBalance +
            ", enableBalance=" + enableBalance +
            ", marketValue=" + marketValue +
            '}';
    }
}