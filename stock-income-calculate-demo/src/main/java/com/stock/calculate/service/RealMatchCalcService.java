package com.stock.calculate.service;

import com.stock.calculate.entity.UserAsset;
import com.stock.calculate.entity.UserBankTrans;
import com.stock.calculate.entity.UserFundSerialAsset;
import com.stock.calculate.entity.UserStockIncome;
import com.stock.calculate.enums.SecurityIncomeTagType;
import com.stock.calculate.enums.SecurityType;
import com.stock.calculate.formula.FormulaBase;
import com.stock.calculate.formula.IncomeFormula;
import com.stock.calculate.model.BizParam;
import com.stock.calculate.model.IncomeElement;
import com.stock.calculate.model.MatchParam;
import com.stock.calculate.model.RealMatchIncomeCalc;
import com.stock.calculate.utils.DataConverter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 实盘赛收益计算（只计算A股的收益）.
 *
 * @author bowen.yan
 * @since 2017-07-05
 */
public class RealMatchCalcService extends IncomeCalcServiceBase<RealMatchIncomeCalc> {
    private static final BigDecimal ASSET_1W = new BigDecimal(10000);
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
        int initDate = param.getInitDate();
        String userId = param.getUserId();

        // Read from db by mybatis-mapper
        //UserAsset userAsset = fundMapper.selectByUserId(initDate, userId);
        //List<UserBankTrans> rawBankTrans = bankTransMapper.selectByUserId(initDate, userId);
        //List<UserFundSerialAsset> rawFundSerialAssets = fundSerialAssetMapper.selectByUserId(initDate, userId);
        //final List<UserStockIncome> rawStockIncomes = stockIncomeMapper.selectByUserId(initDate, userId);

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
        // 使用汇总今日盈亏金额来计算日收益率
        return new IncomeFormula();
    }

    @Override
    protected List<SecurityType> getCalcIncomeSecurityTypes() {
        return Arrays.asList(
            SecurityType.STOCK
        );
    }

    @Override
    protected List<SecurityIncomeTagType> getExcludeIncomeTagTypes() {
        return Arrays.asList(
            SecurityIncomeTagType.NEW_STOCK_ONLINE
        );
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
    protected boolean checkCanCalcIncome(RealMatchIncomeCalc analysisIncome, RealMatchIncomeCalc prevAnalysisIncome) {
        boolean calcIncome = true;
        String remark = StringUtils.EMPTY;
        if (prevAnalysisIncome == null) {
            remark = "参赛第一天不计算收益";
            calcIncome = false;
        } else if (!prevAnalysisIncome.isStartCalcIncome() && isAssetLessThan1W(fund.getFundBalance())) {
            remark = "资产小于1W不计算收益";
            calcIncome = false;
        }
        analysisIncome.appendSameSecurityTypeRemark(remark);
        return calcIncome && super.checkCanCalcIncome(analysisIncome, prevAnalysisIncome);
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

    /**
     * 判断资产是否小于1W.
     *
     * @param asset 总资产
     */
    public static boolean isAssetLessThan1W(BigDecimal asset) {
        return ASSET_1W.compareTo(asset) > 0;
    }
}
