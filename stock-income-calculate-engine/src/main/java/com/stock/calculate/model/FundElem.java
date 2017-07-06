package com.stock.calculate.model;

import java.math.BigDecimal;

/**
 * 资金.
 *
 * @author bowen.yan
 */
public class FundElem {
    private Integer initDate;
    private String userId;
    @CalcIncomeRequired
    private BigDecimal fundBalance = BigDecimal.ZERO; //资产总值
    private BigDecimal enableBalance = BigDecimal.ZERO; //可用金额
    private BigDecimal marketValue = BigDecimal.ZERO; //市值
    private BigDecimal frozenBal = BigDecimal.ZERO; //冻结金额
    private BigDecimal onRoadBal = BigDecimal.ZERO; //在途资金金额

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

    public BigDecimal getFrozenBal() {
        return frozenBal;
    }

    public void setFrozenBal(BigDecimal frozenBal) {
        this.frozenBal = frozenBal;
    }

    public BigDecimal getOnRoadBal() {
        return onRoadBal;
    }

    public void setOnRoadBal(BigDecimal onRoadBal) {
        this.onRoadBal = onRoadBal;
    }

    @Override
    public String toString() {
        return "FundElem{" +
            "initDate=" + initDate +
            ", userId='" + userId + '\'' +
            ", fundBalance=" + fundBalance +
            ", enableBalance=" + enableBalance +
            ", marketValue=" + marketValue +
            ", frozenBal=" + frozenBal +
            ", onRoadBal=" + onRoadBal +
            '}';
    }
}