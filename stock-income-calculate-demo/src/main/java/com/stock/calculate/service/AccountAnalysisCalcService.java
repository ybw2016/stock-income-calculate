package com.stock.calculate.service;

import com.stock.calculate.entity.UserAsset;
import com.stock.calculate.entity.UserBankTrans;
import com.stock.calculate.entity.UserFundSerialAsset;
import com.stock.calculate.entity.UserStockIncome;
import com.stock.calculate.formula.AssetFormula;
import com.stock.calculate.formula.FormulaBase;
import com.stock.calculate.model.BizParam;
import com.stock.calculate.model.IncomeElement;
import com.stock.calculate.model.MatchParam;
import com.stock.calculate.model.RealMatchIncomeCalc;
import com.stock.calculate.utils.DataConverter;

import java.util.List;

/**
 * 账户分析收益计算（使用总资产相减法，计算所有品种的收益）.
 * Created by Administrator on 2017/7/5.
 * @author bowen.yan
 * @since 2017-07-05
 */
public class AccountAnalysisCalcService extends IncomeCalcServiceBase<RealMatchIncomeCalc> {
    private MatchParam matchParam;
    private DataFetcherable dataFetcherable;

    /**
     * 绑定大赛收益计算的参数.
     */
    public void bindParameters(DataFetcherable dataFetcherable) {
        this.matchParam = dataFetcherable.getMatchParam();
        this.param = new BizParam(dataFetcherable.getMatchParam().getBizDate(),
            dataFetcherable.getMatchParam().getUserId());
        this.dataFetcherable = dataFetcherable;
    }

    @Override
    protected void initBizData() {
        UserAsset userAsset = dataFetcherable.getUserAsset();
        List<UserBankTrans> rawBankTrans = dataFetcherable.getUserBankTranses();
        List<UserFundSerialAsset> rawFundSerialAssets = dataFetcherable.getUserFundSerialAssets();
        final List<UserStockIncome> rawStockIncomes = dataFetcherable.getUserStockIncomes();

        // 数据适配，翻译成收益计算引擎识别的格式
        fund = DataConverter.convertFund(userAsset);
        bankTrans = DataConverter.convertBankTrans(rawBankTrans);
        fundSerialAssets = DataConverter.convertFundSerials(rawFundSerialAssets);
        stockIncomes = DataConverter.convertStockIncomes(rawStockIncomes);
    }

    @Override
    protected FormulaBase getFormula() {
        // 使用总资产相减法来计算日收益率
        return new AssetFormula();
    }

    @Override
    protected RealMatchIncomeCalc createIncome() {
        return new RealMatchIncomeCalc();
    }

    @Override
    protected RealMatchIncomeCalc getPrevIncome() {
        // prevIncome read from db by mybatis-mapper
        // RealMatchIncomeCalc prevIncome = matchIncomeMapper.selectByUser(prevInitDate, matchId, param.getUserId())
        RealMatchIncomeCalc prevIncome = dataFetcherable.getPrevRealMatchIncomeCalc();
        RealMatchIncomeCalc realMatchIncomeCalc = new RealMatchIncomeCalc();
        DataConverter.copy(prevIncome, realMatchIncomeCalc);
        return realMatchIncomeCalc;
    }

    @Override
    protected void handlePreSaveIncome(RealMatchIncomeCalc analysisIncome, RealMatchIncomeCalc prevAnalysisIncome,
                                       IncomeElement incomeElement) {
        // handle before insert db, such as attach additional info to entity
    }

    @Override
    protected void saveIncome(RealMatchIncomeCalc analysisIncome) {
        // 收益计算引擎计算后的结果，保存到数据库中
        // save the calculated income data to db
        //log.info("收益计算引擎计算的结果如下 -> {}", analysisIncome);
    }
}
