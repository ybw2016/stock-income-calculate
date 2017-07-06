package com.stock.calculate.model;

import java.math.BigDecimal;

/**
 * 收益计算所需的基本元素.
 *
 * @author bowen.yan
 * @since 2016-12-11
 */
public class IncomeElement {
    private boolean isFirstDayOfWeek = false;
    private boolean isFirstDayOfMonth = false;
    // 收益计算必需数据
    private BigDecimal prevAsset = BigDecimal.ZERO;
    private BigDecimal asset = BigDecimal.ZERO;

    // 收益计算法计算时直接传入收益值;  总资产计算法计算时该值被方法FormulaBase.getAllIncomeBalance()重写
    private BigDecimal incomeBalance = BigDecimal.ZERO;
    // 需要刨掉的收益类型（不管哪种计算方式，都需要刨掉，如：新股第一天市值增长44%）
    private BigDecimal removeIncomeBalance = BigDecimal.ZERO;
    // 不需要计算的投资品种（使用[总资产相减法]时，需要去掉资产类型的盈亏，如去掉：基金、债券、回购）
    private BigDecimal excludeSecurityIncomeBalance = BigDecimal.ZERO;

    private BigDecimal bankTransIn = BigDecimal.ZERO;
    private BigDecimal bankTransOut = BigDecimal.ZERO;
    private BigDecimal prevWeekIncome = BigDecimal.ZERO;
    private BigDecimal prevMonthIncome = BigDecimal.ZERO;
    private BigDecimal prevTotalIncome = BigDecimal.ZERO;

    public BigDecimal getPrevAsset() {
        return prevAsset;
    }

    public void setPrevAsset(BigDecimal prevAsset) {
        this.prevAsset = prevAsset;
    }

    public BigDecimal getAsset() {
        return asset;
    }

    public void setAsset(BigDecimal asset) {
        this.asset = asset;
    }

    public BigDecimal getIncomeBalance() {
        return incomeBalance;
    }

    public void setIncomeBalance(BigDecimal incomeBalance) {
        this.incomeBalance = incomeBalance;
    }

    public BigDecimal getRemoveIncomeBalance() {
        return removeIncomeBalance;
    }

    public void setRemoveIncomeBalance(BigDecimal removeIncomeBalance) {
        this.removeIncomeBalance = removeIncomeBalance;
    }

    public BigDecimal getExcludeSecurityIncomeBalance() {
        return excludeSecurityIncomeBalance;
    }

    public void setExcludeSecurityIncomeBalance(BigDecimal excludeSecurityIncomeBalance) {
        this.excludeSecurityIncomeBalance = excludeSecurityIncomeBalance;
    }

    public BigDecimal getBankTransIn() {
        return bankTransIn;
    }

    public void setBankTransIn(BigDecimal bankTransIn) {
        this.bankTransIn = bankTransIn;
    }

    public BigDecimal getBankTransOut() {
        return bankTransOut;
    }

    public void setBankTransOut(BigDecimal bankTransOut) {
        this.bankTransOut = bankTransOut;
    }

    public BigDecimal getPrevWeekIncome() {
        return prevWeekIncome;
    }

    public void setPrevWeekIncome(BigDecimal prevWeekIncome) {
        this.prevWeekIncome = prevWeekIncome;
    }

    public BigDecimal getPrevMonthIncome() {
        return prevMonthIncome;
    }

    public void setPrevMonthIncome(BigDecimal prevMonthIncome) {
        this.prevMonthIncome = prevMonthIncome;
    }

    public BigDecimal getPrevTotalIncome() {
        return prevTotalIncome;
    }

    public void setPrevTotalIncome(BigDecimal prevTotalIncome) {
        this.prevTotalIncome = prevTotalIncome;
    }

    public boolean isFirstDayOfWeek() {
        return isFirstDayOfWeek;
    }

    public void setFirstDayOfWeek(boolean firstDayOfWeek) {
        isFirstDayOfWeek = firstDayOfWeek;
    }

    public boolean isFirstDayOfMonth() {
        return isFirstDayOfMonth;
    }

    public void setFirstDayOfMonth(boolean firstDayOfMonth) {
        isFirstDayOfMonth = firstDayOfMonth;
    }

    /**
     * 计算收益的分母.
     *
     * @return data
     */
    public BigDecimal getDividend() {
        return prevAsset.add(getBankTransIn());
    }
}
