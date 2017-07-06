package com.stock.calculate.entity;

import java.math.BigDecimal;

/**
 * 对账单在途表(找回缺少资金，或者多余的资金).
 *
 * @author bowen.yan
 * @since 2016-12-07
 */
public class UserFundSerialAsset {
    private Integer id;
    private Integer initDate;
    private String userId;
    private String bizCode;
    private String bizName;
    private BigDecimal occurBalance = BigDecimal.ZERO;
    private BigDecimal dealAmount = BigDecimal.ZERO;
    private BigDecimal assetAsIn = BigDecimal.ZERO;
    private BigDecimal assetAsOut = BigDecimal.ZERO;
    private BigDecimal assetAddBack = BigDecimal.ZERO;

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

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
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

    @Override
    public String toString() {
        return "UserFundSerialAsset{" +
            "id=" + id +
            ", initDate=" + initDate +
            ", userId='" + userId + '\'' +
            ", bizCode='" + bizCode + '\'' +
            ", bizName='" + bizName + '\'' +
            ", occurBalance=" + occurBalance +
            ", dealAmount=" + dealAmount +
            ", assetAsIn=" + assetAsIn +
            ", assetAsOut=" + assetAsOut +
            ", assetAddBack=" + assetAddBack +
            '}';
    }
}