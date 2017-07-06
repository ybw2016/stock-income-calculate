package com.stock.calculate.model;

import java.math.BigDecimal;

/**
 * @author bowen.yan
 * @since 2016-12-11
 */
public class IncomeElemBase {
    private Integer initDate;
    private String userId;
    private String stockCode;
    private String stockName;
    private String market;
    private BigDecimal currentAmount = BigDecimal.ZERO;
    private BigDecimal buyCost = BigDecimal.ZERO;
    private BigDecimal costPrice = BigDecimal.ZERO;
    private BigDecimal prevClosePrice = BigDecimal.ZERO;
    private BigDecimal marketPrice = BigDecimal.ZERO;
    private BigDecimal marketValue = BigDecimal.ZERO;
    private BigDecimal currentCost = BigDecimal.ZERO;
    private BigDecimal enableAmount = BigDecimal.ZERO;
    private BigDecimal buyIncomeBalance = BigDecimal.ZERO;
    private BigDecimal saleIncomeBalance = BigDecimal.ZERO;
    private BigDecimal holdIncomeBalance = BigDecimal.ZERO;
    private BigDecimal bonusBalance = BigDecimal.ZERO;
    // incomeBalance = buyIncomeBalance + saleIncomeBalance + holdIncomeBalance + bonusBalance
    @CalcIncomeRequired
    private BigDecimal incomeBalance = BigDecimal.ZERO;
    @CalcIncomeRequired
    private BigDecimal removeAsset = BigDecimal.ZERO;
    // 以上买入盈亏、卖出盈亏、红利都是税后，因此feeAll字段暂未参与收益计算
    private BigDecimal feeAll = BigDecimal.ZERO;
    @CalcIncomeRequired
    private String securityIncomeTag = "";
    private String remark = "";
    private String currency = "";
    private boolean fromFundSerial = false;

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

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public BigDecimal getBuyCost() {
        return buyCost;
    }

    public void setBuyCost(BigDecimal buyCost) {
        this.buyCost = buyCost;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getPrevClosePrice() {
        return prevClosePrice;
    }

    public void setPrevClosePrice(BigDecimal prevClosePrice) {
        this.prevClosePrice = prevClosePrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    public BigDecimal getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(BigDecimal currentCost) {
        this.currentCost = currentCost;
    }

    public BigDecimal getEnableAmount() {
        return enableAmount;
    }

    public void setEnableAmount(BigDecimal enableAmount) {
        this.enableAmount = enableAmount;
    }

    public BigDecimal getBuyIncomeBalance() {
        return buyIncomeBalance;
    }

    public void setBuyIncomeBalance(BigDecimal buyIncomeBalance) {
        this.buyIncomeBalance = buyIncomeBalance;
    }

    public BigDecimal getSaleIncomeBalance() {
        return saleIncomeBalance;
    }

    public void setSaleIncomeBalance(BigDecimal saleIncomeBalance) {
        this.saleIncomeBalance = saleIncomeBalance;
    }

    public BigDecimal getHoldIncomeBalance() {
        return holdIncomeBalance;
    }

    public void setHoldIncomeBalance(BigDecimal holdIncomeBalance) {
        this.holdIncomeBalance = holdIncomeBalance;
    }

    public BigDecimal getBonusBalance() {
        return bonusBalance;
    }

    public void setBonusBalance(BigDecimal bonusBalance) {
        this.bonusBalance = bonusBalance;
    }

    public BigDecimal getIncomeBalance() {
        return incomeBalance;
    }

    public void setIncomeBalance(BigDecimal incomeBalance) {
        this.incomeBalance = incomeBalance;
    }

    public BigDecimal getRemoveAsset() {
        return removeAsset;
    }

    public void setRemoveAsset(BigDecimal removeAsset) {
        this.removeAsset = removeAsset;
    }

    public BigDecimal getFeeAll() {
        return feeAll;
    }

    public void setFeeAll(BigDecimal feeAll) {
        this.feeAll = feeAll;
    }

    public String getSecurityIncomeTag() {
        return securityIncomeTag;
    }

    public void setSecurityIncomeTag(String securityIncomeTag) {
        this.securityIncomeTag = securityIncomeTag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isFromFundSerial() {
        return fromFundSerial;
    }

    public void setFromFundSerial(boolean fromFundSerial) {
        this.fromFundSerial = fromFundSerial;
    }

    @Override
    public String toString() {
        return "IncomeElemBase{" +
            "initDate=" + initDate +
            ", userId='" + userId + '\'' +
            ", stockCode='" + stockCode + '\'' +
            ", stockName='" + stockName + '\'' +
            ", market='" + market + '\'' +
            ", currentAmount=" + currentAmount +
            ", buyCost=" + buyCost +
            ", costPrice=" + costPrice +
            ", prevClosePrice=" + prevClosePrice +
            ", marketPrice=" + marketPrice +
            ", marketValue=" + marketValue +
            ", currentCost=" + currentCost +
            ", enableAmount=" + enableAmount +
            ", buyIncomeBalance=" + buyIncomeBalance +
            ", saleIncomeBalance=" + saleIncomeBalance +
            ", holdIncomeBalance=" + holdIncomeBalance +
            ", bonusBalance=" + bonusBalance +
            ", incomeBalance=" + incomeBalance +
            ", removeAsset=" + removeAsset +
            ", feeAll=" + feeAll +
            ", securityIncomeTag='" + securityIncomeTag + '\'' +
            ", remark='" + remark + '\'' +
            ", currency='" + currency + '\'' +
            ", fromFundSerial=" + fromFundSerial +
            '}';
    }
}
