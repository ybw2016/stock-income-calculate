package com.stock.calculate.model;

import com.stock.calculate.utils.DataUtil;

import java.math.BigDecimal;

/**
 * 收益计算基类模型.
 *
 * @author bowen.yan
 * @author bowen.yan
 * @since 2017-01-05
 */
public class IncomeCalcBase {
    private Integer initDate;
    private String userId;
    private String assetType;
    private BigDecimal prevAsset = BigDecimal.ZERO;
    private BigDecimal asset = BigDecimal.ZERO;
    // 需要刨掉的总资产，如：1. 分红送配中多余的市值，其实需要刨掉; 2. SZ新股申购丢失的总资产，需要加回;
    private BigDecimal removeAsset = BigDecimal.ZERO;
    private BigDecimal addBackAsset = BigDecimal.ZERO;
    // 盈亏金额
    // 1. 资产相减法： 盈亏金额 = （今日总资产 + 出金 - 入金 - 昨日总资产）
    // 2. 盈亏相加法： 盈亏金额 = Σ（A股盈亏 + 基金盈亏 + 债券盈亏 + ...）
    private BigDecimal incomeBalance = BigDecimal.ZERO;
    // 需要刨掉的盈亏，如：新股第一天市值增长44%）
    private BigDecimal removeIncomeBalance = BigDecimal.ZERO;
    private BigDecimal bankTransIn = BigDecimal.ZERO;
    private BigDecimal bankTransOut = BigDecimal.ZERO;
    private BigDecimal assetAsIn = BigDecimal.ZERO;
    private BigDecimal assetAsOut = BigDecimal.ZERO;
    private BigDecimal dayIncome = BigDecimal.ZERO;
    private BigDecimal weekIncome = BigDecimal.ZERO;
    private BigDecimal monthIncome = BigDecimal.ZERO;
    private BigDecimal totalIncome = BigDecimal.ZERO;
    private BigDecimal realAsset = BigDecimal.ZERO;
    private BigDecimal netValue = BigDecimal.ZERO;
    private BigDecimal allBankTrans = BigDecimal.ZERO;
    private String status;
    private String remark = "";

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

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

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

    public BigDecimal getRemoveAsset() {
        return removeAsset;
    }

    public void setRemoveAsset(BigDecimal removeAsset) {
        this.removeAsset = removeAsset;
    }

    public BigDecimal getAddBackAsset() {
        return addBackAsset;
    }

    public void setAddBackAsset(BigDecimal addBackAsset) {
        this.addBackAsset = addBackAsset;
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

    public BigDecimal getAssetAsIn() {
        return assetAsIn;
    }

    public void setAssetAsIn(BigDecimal assetAsIn) {
        this.assetAsIn = assetAsIn;
    }

    public BigDecimal getAssetAsOut() {
        return assetAsOut;
    }

    public void setAssetAsOut(BigDecimal assetAsOut) {
        this.assetAsOut = assetAsOut;
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

    public BigDecimal getRealAsset() {
        return realAsset;
    }

    public void setRealAsset(BigDecimal realAsset) {
        this.realAsset = realAsset;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    public BigDecimal getAllBankTrans() {
        return allBankTrans;
    }

    public void setAllBankTrans(BigDecimal allBankTrans) {
        this.allBankTrans = allBankTrans;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void appendSameSecurityTypeRemark(String newRemark) {
        appendRemark(newRemark, JoinChar.INNER_LIST_JOIN_CHAR);
    }

    public void appendDiffSecurityTypeRemark(String newRemark) {
        appendRemark(newRemark, JoinChar.CROSS_LIST_JOIN_CHAR);
    }

    public void appendRemark(String newRemark, String joinChar) {
        this.remark = DataUtil.concatString(remark, newRemark, joinChar);
    }

    @Override
    public String toString() {
        return "IncomeCalcBase{" +
            "initDate=" + initDate +
            ", userId='" + userId + '\'' +
            ", assetType=" + assetType +
            ", prevAsset=" + prevAsset +
            ", asset=" + asset +
            ", removeAsset=" + removeAsset +
            ", addBackAsset=" + addBackAsset +
            ", incomeBalance=" + incomeBalance +
            ", removeIncomeBalance=" + removeIncomeBalance +
            ", bankTransIn=" + bankTransIn +
            ", bankTransOut=" + bankTransOut +
            ", assetAsIn=" + assetAsIn +
            ", assetAsOut=" + assetAsOut +
            ", dayIncome=" + dayIncome +
            ", weekIncome=" + weekIncome +
            ", monthIncome=" + monthIncome +
            ", totalIncome=" + totalIncome +
            ", realAsset=" + realAsset +
            ", netValue=" + netValue +
            ", allBankTrans=" + allBankTrans +
            ", status='" + status + '\'' +
            ", remark='" + remark + '\'' +
            '}';
    }
}
