package com.stock.calculate.model;

import java.math.BigDecimal;

/**
 * 对账单资金(找回缺少资金).
 *
 * @author bowen.yan
 * @since 2016-12-07
 */
public class FundSerialAssetElem {
    private Integer initDate;
    private String userId;
    private String dealTime;
    private String orderId;
    private String stockCode;
    private String stockName;
    private BigDecimal occurBalance = BigDecimal.ZERO;
    private BigDecimal dealAmount = BigDecimal.ZERO;
    // 当入金处理（某种业务导致突然入一笔钱，导致总资产变大）
    @CalcIncomeRequired
    private BigDecimal assetAsIn = BigDecimal.ZERO;
    // 当入金处理（某种业务导致突然出一笔钱，导致总资产变小）
    @CalcIncomeRequired
    private BigDecimal assetAsOut = BigDecimal.ZERO;
    // 需要加回的总资产（股票质押后，总资产每天都会变小，需要加回，以便计算真正总净值）
    @CalcIncomeRequired
    private BigDecimal assetAddBack = BigDecimal.ZERO;
    private String repairType;
    private String remark;

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

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public BigDecimal getOccurBalance() {
        return occurBalance;
    }

    public void setOccurBalance(BigDecimal occurBalance) {
        this.occurBalance = occurBalance;
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
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

    public BigDecimal getAssetAddBack() {
        return assetAddBack;
    }

    public void setAssetAddBack(BigDecimal assetAddBack) {
        this.assetAddBack = assetAddBack;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FundSerialAssetElem{" +
            "initDate=" + initDate +
            ", userId='" + userId + '\'' +
            ", dealTime='" + dealTime + '\'' +
            ", orderId='" + orderId + '\'' +
            ", stockCode='" + stockCode + '\'' +
            ", stockName='" + stockName + '\'' +
            ", occurBalance=" + occurBalance +
            ", dealAmount=" + dealAmount +
            ", assetAsIn=" + assetAsIn +
            ", assetAsOut=" + assetAsOut +
            ", assetAddBack=" + assetAddBack +
            ", repairType='" + repairType + '\'' +
            ", remark='" + remark + '\'' +
            '}';
    }
}