package com.stock.calculate.entity;

import com.stock.calculate.constants.JoinChar;
import com.stock.calculate.utils.DataUtil;

import java.math.BigDecimal;

/**
 * @author bowen.yan
 * @since 2016-12-11
 */
public class IncomeBase {
    private Integer id;
    private Integer initDate;
    private String userId;
    private String userCode;
    private String fundAccount;
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
    private BigDecimal incomeBalance = BigDecimal.ZERO;
    private BigDecimal removeAsset = BigDecimal.ZERO;
    private BigDecimal feeAll = BigDecimal.ZERO;
    private String incomeTag = "";
    private String remark = "";
    private String currency = "";

    public void appendSameSecurityTypeRemark(String newRemark) {
        appendRemark(newRemark, JoinChar.INNER_LIST_JOIN_CHAR);
    }

    public void appendDiffSecurityTypeRemark(String newRemark) {
        appendRemark(newRemark, JoinChar.CROSS_LIST_JOIN_CHAR);
    }

    public void appendRemark(String newRemark, String joinChar) {
        this.remark = DataUtil.concatString(remark, newRemark, joinChar);
    }

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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getFundAccount() {
        return fundAccount;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
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

    public String getIncomeTag() {
        return incomeTag;
    }

    public void setIncomeTag(String incomeTag) {
        this.incomeTag = incomeTag;
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

    @Override
    public String toString() {
        return "IncomeBase{" +
            "id=" + id +
            ", initDate=" + initDate +
            ", userId='" + userId + '\'' +
            ", userCode='" + userCode + '\'' +
            ", fundAccount='" + fundAccount + '\'' +
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
            ", incomeTag='" + incomeTag + '\'' +
            ", remark='" + remark + '\'' +
            ", currency='" + currency + '\'' +
            '}';
    }
}
