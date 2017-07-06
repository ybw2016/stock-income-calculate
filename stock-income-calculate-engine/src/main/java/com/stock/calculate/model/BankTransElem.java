package com.stock.calculate.model;

import com.stock.calculate.enums.BankTransType;

import java.math.BigDecimal;

/**
 * 银证转账.
 *
 * @author bowen.yan
 */
public class BankTransElem {
    private int initDate;
    private String userId;
    @CalcIncomeRequired
    private String currency; //币种
    @CalcIncomeRequired
    private BankTransType bankTransType;
    @CalcIncomeRequired
    private BigDecimal occurBalance; //转账资金 (注意：无论转入转出都为正数)
    private String remark; //备注

    public int getInitDate() {
        return initDate;
    }

    public void setInitDate(int initDate) {
        this.initDate = initDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BankTransType getBankTransType() {
        return bankTransType;
    }

    public void setBankTransType(BankTransType bankTransType) {
        this.bankTransType = bankTransType;
    }

    public BigDecimal getOccurBalance() {
        return occurBalance;
    }

    public void setOccurBalance(BigDecimal occurBalance) {
        this.occurBalance = occurBalance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "BankTransElem{" +
            "initDate=" + initDate +
            ", userId='" + userId + '\'' +
            ", currency='" + currency + '\'' +
            ", bankTransType=" + bankTransType +
            ", occurBalance=" + occurBalance +
            ", remark='" + remark + '\'' +
            '}';
    }
}