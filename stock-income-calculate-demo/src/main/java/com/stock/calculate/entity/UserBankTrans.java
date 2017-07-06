package com.stock.calculate.entity;

import java.math.BigDecimal;

/**
 * 银证转账.
 *
 * @author bowen.yan
 */
public class UserBankTrans {
    private static final String BANK_TRANS_IN_TAG = "0";
    private int id;
    private int initDate;
    private String userId;
    private String bankTransDirection; //资金流向
    private BigDecimal occurBalance; //转账资金
    private String dealTime; //成交时间

    public boolean isBankTransIn() {
        return BANK_TRANS_IN_TAG.equals(bankTransDirection);
    }

    public boolean isBankTransOut() {
        return !isBankTransIn();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getBankTransDirection() {
        return bankTransDirection;
    }

    public void setBankTransDirection(String bankTransDirection) {
        this.bankTransDirection = bankTransDirection;
    }

    public BigDecimal getOccurBalance() {
        return occurBalance;
    }


    public void setOccurBalance(BigDecimal occurBalance) {
        this.occurBalance = occurBalance;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    @Override
    public String toString() {
        return "UserBankTrans{" +
            "id=" + id +
            ", initDate=" + initDate +
            ", userId='" + userId + '\'' +
            ", bankTransDirection='" + bankTransDirection + '\'' +
            ", occurBalance=" + occurBalance +
            ", dealTime='" + dealTime + '\'' +
            '}';
    }
}