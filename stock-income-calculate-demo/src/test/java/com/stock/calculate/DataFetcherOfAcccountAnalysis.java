package com.stock.calculate;

import com.stock.calculate.entity.UserAsset;
import com.stock.calculate.entity.UserBankTrans;
import com.stock.calculate.entity.UserFundSerialAsset;
import com.stock.calculate.entity.UserStockIncome;
import com.stock.calculate.model.MatchParam;
import com.stock.calculate.model.RealMatchIncomeCalc;
import com.stock.calculate.service.DataFetcherable;
import com.stock.calculate.utils.DateUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 加载收益计算所需要的数据（账户分析）.
 * Created by Administrator on 2017/7/5.
 */
public class DataFetcherOfAcccountAnalysis implements DataFetcherable {
    public MatchParam getMatchParam() {
        MatchParam matchParam = new MatchParam();
        matchParam.setMatchId(1);
        matchParam.setBizDate(DateUtil.getDefaultDate());
        matchParam.setUserId(DEFAULT_USER_ID);
        return matchParam;
    }

    public RealMatchIncomeCalc getPrevRealMatchIncomeCalc() {
        RealMatchIncomeCalc prevMatchIncome = new RealMatchIncomeCalc();
        prevMatchIncome.setRealAsset(new BigDecimal("10000"));
        return prevMatchIncome;
    }

    public UserAsset getUserAsset() {
        UserAsset userAsset = new UserAsset();
        userAsset.setUserId(DEFAULT_USER_ID);
        userAsset.setFundBalance(new BigDecimal("12000"));
        return userAsset;
    }

    public List<UserBankTrans> getUserBankTranses() {
        List<UserBankTrans> userBankTrans = new ArrayList<>();
        UserBankTrans userBankTran = new UserBankTrans();
        userBankTran.setInitDate(DateUtil.getIntDateFrom(DateUtil.getDefaultDate()));
        userBankTran.setUserId(DEFAULT_USER_ID);
        userBankTran.setBankTransDirection("0");
        userBankTran.setOccurBalance(new BigDecimal("500"));
        userBankTrans.add(userBankTran);
        return userBankTrans;
    }

    public List<UserFundSerialAsset> getUserFundSerialAssets() {
        List<UserFundSerialAsset> fundSerialAssets = new ArrayList<>();
        UserFundSerialAsset userFundSerialAsset = new UserFundSerialAsset();
        userFundSerialAsset.setInitDate(DateUtil.getIntDateFrom(DateUtil.getDefaultDate()));
        userFundSerialAsset.setUserId(DEFAULT_USER_ID);
        userFundSerialAsset.setBizCode("PN1121");
        userFundSerialAsset.setBizName("OTC资金转入");
        userFundSerialAsset.setAssetAsIn(new BigDecimal("500"));
        fundSerialAssets.add(userFundSerialAsset);
        return fundSerialAssets;
    }

    public List<UserStockIncome> getUserStockIncomes() {
        List<UserStockIncome> userStockIncomes = new ArrayList<>();
        UserStockIncome userStockIncome = new UserStockIncome();
        userStockIncome.setInitDate(DateUtil.getIntDateFrom(DateUtil.getOffsetDate(DateUtil.getDefaultDate(), -1)));
        userStockIncome.setStockCode("601633");
        userStockIncome.setStockName("长城汽车");
        userStockIncome.setCurrentAmount(new BigDecimal("1000"));
        userStockIncome.setMarketPrice(new BigDecimal("12.20"));
        userStockIncome.setPrevClosePrice(new BigDecimal("11.20"));

        userStockIncome.setBuyIncomeBalance(new BigDecimal("0"));
        userStockIncome.setSaleIncomeBalance(new BigDecimal("0"));
        userStockIncome.setHoldIncomeBalance(new BigDecimal("1000"));
        userStockIncome.setBonusBalance(new BigDecimal("0"));
        userStockIncome.setIncomeBalance(new BigDecimal("1000"));

        userStockIncomes.add(userStockIncome);
        return userStockIncomes;
    }
}
