package com.stock.calculate.service;

import com.stock.calculate.entity.UserAsset;
import com.stock.calculate.entity.UserBankTrans;
import com.stock.calculate.entity.UserFundSerialAsset;
import com.stock.calculate.entity.UserStockIncome;
import com.stock.calculate.model.MatchParam;
import com.stock.calculate.model.RealMatchIncomeCalc;

import java.util.List;

/**
 * 数据绑定接口.
 * @author bowen.yan
 * @since 2017-07-05
 */
public interface DataFetcherable {
    static final String DEFAULT_USER_ID = "110100198807192282";

    MatchParam getMatchParam();

    RealMatchIncomeCalc getPrevRealMatchIncomeCalc();

    UserAsset getUserAsset();

    List<UserBankTrans> getUserBankTranses();

    List<UserFundSerialAsset> getUserFundSerialAssets();

    List<UserStockIncome> getUserStockIncomes();
}
