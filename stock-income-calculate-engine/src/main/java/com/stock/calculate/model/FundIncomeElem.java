package com.stock.calculate.model;

import java.math.BigDecimal;

/**
 * 基金收益表.
 *
 * @author bowen.yan
 * @since 2016-12-07
 */
public class FundIncomeElem extends IncomeElemBase {
    private BigDecimal remainAmount;
    private BigDecimal frozenAmount;
    private BigDecimal onRoadAmount;
    private BigDecimal onRoadBalance;

    public BigDecimal getRemainAmount() {
        return remainAmount;
    }

    public void setRemainAmount(BigDecimal remainAmount) {
        this.remainAmount = remainAmount;
    }

    public BigDecimal getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(BigDecimal frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public BigDecimal getOnRoadAmount() {
        return onRoadAmount;
    }

    public void setOnRoadAmount(BigDecimal onRoadAmount) {
        this.onRoadAmount = onRoadAmount;
    }

    public BigDecimal getOnRoadBalance() {
        return onRoadBalance;
    }

    public void setOnRoadBalance(BigDecimal onRoadBalance) {
        this.onRoadBalance = onRoadBalance;
    }

    @Override
    public String toString() {
        return super.toString() + "|" +
            "FundIncome{" +
            "remainAmount=" + remainAmount +
            ", frozenAmount=" + frozenAmount +
            ", onRoadAmount=" + onRoadAmount +
            ", onRoadBalance=" + onRoadBalance +
            '}';
    }
}