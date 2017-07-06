package com.stock.calculate.model;

import java.math.BigDecimal;

/**
 * 收益计算结果.
 *
 * @author bowen.yan
 * @since 2016-12-11
 */
public class IncomeCalcResult {
    private BigDecimal incomeBalance = BigDecimal.ZERO;
    private BigDecimal dayIncome = BigDecimal.ZERO;
    private BigDecimal weekIncome = BigDecimal.ZERO;
    private BigDecimal monthIncome = BigDecimal.ZERO;
    private BigDecimal totalIncome = BigDecimal.ZERO;

    public BigDecimal getIncomeBalance() {
        return incomeBalance;
    }

    public void setIncomeBalance(BigDecimal incomeBalance) {
        this.incomeBalance = incomeBalance;
    }

    public BigDecimal getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(BigDecimal dayIncome) {
        this.dayIncome = dayIncome;
    }

    public BigDecimal getWeekIncome() {
        return weekIncome;
    }

    public void setWeekIncome(BigDecimal weekIncome) {
        this.weekIncome = weekIncome;
    }

    public BigDecimal getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(BigDecimal monthIncome) {
        this.monthIncome = monthIncome;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String toString() {
        return "IncomeCalcResult{" +
            "incomeBalance=" + incomeBalance +
            ", dayIncome=" + dayIncome +
            ", weekIncome=" + weekIncome +
            ", monthIncome=" + monthIncome +
            ", totalIncome=" + totalIncome +
            '}';
    }
}
