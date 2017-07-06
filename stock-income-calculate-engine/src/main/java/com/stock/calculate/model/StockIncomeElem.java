package com.stock.calculate.model;

import java.math.BigDecimal;

/**
 * 股票收益.
 *
 * @author bowen.yan
 * @since 2016-12-07
 */
public class StockIncomeElem extends IncomeElemBase {
    private String stopFlag;
    private String lockFlag;
    private BigDecimal remainAmount;
    private BigDecimal tradeFrozenAmount;
    private BigDecimal onRoadAmount;
    private BigDecimal frozenAmount;
    private BigDecimal untradeAmount;
    private BigDecimal onRoadEnableAmount;
    private BigDecimal hkOnRoadAmount;

    public String getStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    public String getLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag;
    }

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public BigDecimal getTradeFrozenAmount() {
        return tradeFrozenAmount;
    }

    public void setTradeFrozenAmount(BigDecimal tradeFrozenAmount) {
        this.tradeFrozenAmount = tradeFrozenAmount;
    }

    public BigDecimal getOnRoadAmount() {
        return onRoadAmount;
    }

    public void setOnRoadAmount(BigDecimal onRoadAmount) {
        this.onRoadAmount = onRoadAmount;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public BigDecimal getUntradeAmount() {
        return untradeAmount;
    }

    public void setUntradeAmount(BigDecimal untradeAmount) {
        this.untradeAmount = untradeAmount;
    }

    public BigDecimal getOnRoadEnableAmount() {
        return onRoadEnableAmount;
    }

    public void setOnRoadEnableAmount(BigDecimal onRoadEnableAmount) {
        this.onRoadEnableAmount = onRoadEnableAmount;
    }

    public BigDecimal getHkOnRoadAmount() {
        return hkOnRoadAmount;
    }

    public void setHkOnRoadAmount(BigDecimal hkOnRoadAmount) {
        this.hkOnRoadAmount = hkOnRoadAmount;
    }

    @Override
    public String toString() {
        return super.toString() + "|" +
            "StockIncome{" +
            ", stopFlag='" + stopFlag + '\'' +
            ", lockFlag='" + lockFlag + '\'' +
            ", remainAmount=" + remainAmount +
            ", tradeFrozenAmount=" + tradeFrozenAmount +
            ", onRoadAmount=" + onRoadAmount +
            ", frozenAmount=" + frozenAmount +
            ", untradeAmount=" + untradeAmount +
            ", onRoadEnableAmount=" + onRoadEnableAmount +
            ", hkOnRoadAmount=" + hkOnRoadAmount +
            '}';
    }
}