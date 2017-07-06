package com.stock.calculate.service;

import com.stock.calculate.enums.BankTransType;
import com.stock.calculate.enums.IncomeStatus;
import com.stock.calculate.enums.SecurityIncomeTagType;
import com.stock.calculate.enums.SecurityType;
import com.stock.calculate.exception.NoFundIncomeCalcException;
import com.stock.calculate.formula.FormulaBase;
import com.stock.calculate.model.BankTransElem;
import com.stock.calculate.model.BizParam;
import com.stock.calculate.model.BondIncomeElem;
import com.stock.calculate.model.FundElem;
import com.stock.calculate.model.FundIncomeElem;
import com.stock.calculate.model.FundSerialAssetElem;
import com.stock.calculate.model.IncomeCalcBase;
import com.stock.calculate.model.IncomeCalcResult;
import com.stock.calculate.model.IncomeElemBase;
import com.stock.calculate.model.IncomeElement;
import com.stock.calculate.model.RepoIncomeElem;
import com.stock.calculate.model.StockIncomeElem;
import com.stock.calculate.utils.DataUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 收益计算基类.
 * 支持资产类型订制化, 从而计算日收益
 *
 * @param <T> 收益计算引擎核心类
 * @author bowen.yan   QQ:191752971
 * @since 2017-01-05
 */
public abstract class IncomeCalcServiceBase<T extends IncomeCalcBase> {
    protected static final Logger log = LoggerFactory.getLogger(IncomeCalcServiceBase.class);
    protected BizParam param;
    protected FundElem fund = new FundElem();
    protected List<BankTransElem> bankTrans = new ArrayList<>();
    protected List<FundSerialAssetElem> fundSerialAssets = new ArrayList<>();
    protected List<StockIncomeElem> stockIncomes = new ArrayList<>();
    protected List<BondIncomeElem> bondIncomes = new ArrayList<>();
    protected List<FundIncomeElem> fundIncomes = new ArrayList<>();
    protected List<RepoIncomeElem> repoIncomes = new ArrayList<>();
    private boolean isFirstDayOfWeek = false;
    private boolean isFirstDayOfMonth = false;
    private boolean keepPrevIncome = false; //特殊情况时保存收益率状态，不计算当天收益.

    /**
     * 计算账户分析收益.
     */
    public void calcIncome() {
        log.info("income calc engine starting... -> {}", param.getAccountKey());
        initBizData();
        checkValid();

        T analysisIncome = createIncome();
        analysisIncome.setInitDate(fund.getInitDate());
        analysisIncome.setUserId(fund.getUserId());
        // 保存总资产
        analysisIncome.setAsset(fund.getFundBalance());
        // 绑定其他值
        saveInAndOutAssets(analysisIncome, fundSerialAssets);
        // 保存银证转账
        saveBankTrans(analysisIncome, bankTrans);
        // 汇总收益、并计算应该刨掉的总资产、保存备注
        calcIncomeBalance(analysisIncome);

        // 计算从对账单找回的总资产, 该处找回的资产用于收益计算
        analysisIncome.setAddBackAsset(getAddBackAssets(fundSerialAssets));
        // 计算今日真正总资产
        analysisIncome.setRealAsset(getTodayAsset(analysisIncome));
        // 修复后的总资产统一在 IncomeElement中计算
        analysisIncome.setNetValue(getNetValue(analysisIncome, fundSerialAssets));

        IncomeElement incomeElement = new IncomeElement();
        incomeElement.setFirstDayOfWeek(isFirstDayOfWeek);
        incomeElement.setFirstDayOfMonth(isFirstDayOfMonth);
        T prevAnalysisIncome = getPrevIncome();
        if (!checkCanCalcIncome(analysisIncome, prevAnalysisIncome) || isKeepPrevIncome()) {
            if (isKeepPrevIncome()) {
                analysisIncome.setDayIncome(prevAnalysisIncome.getDayIncome());
                analysisIncome.setWeekIncome(prevAnalysisIncome.getWeekIncome());
                analysisIncome.setMonthIncome(prevAnalysisIncome.getMonthIncome());
                analysisIncome.setTotalIncome(prevAnalysisIncome.getTotalIncome());
                analysisIncome.setPrevAsset(prevAnalysisIncome.getRealAsset());
                analysisIncome.setStatus(IncomeStatus.STARTED.getValue());
                analysisIncome.appendSameSecurityTypeRemark("保存用户前一日收益");
            } else {
                analysisIncome.setStatus(IncomeStatus.NOT_STARTED.getValue());
            }
            analysisIncome.setAllBankTrans(getAllBankTransValue(
                BigDecimal.ZERO, analysisIncome.getBankTransIn(), analysisIncome.getBankTransOut()));
        } else {
            incomeElement.setAsset(analysisIncome.getRealAsset());
            incomeElement.setIncomeBalance(analysisIncome.getIncomeBalance());
            incomeElement.setRemoveIncomeBalance(analysisIncome.getRemoveIncomeBalance());
            incomeElement.setExcludeSecurityIncomeBalance(getExcludeSecurityIncomes());
            incomeElement.setBankTransIn(getAllBankTransIn(analysisIncome));
            incomeElement.setBankTransOut(getAllBankTransOut(analysisIncome));
            incomeElement.setPrevAsset(prevAnalysisIncome.getRealAsset());
            incomeElement.setPrevWeekIncome(prevAnalysisIncome.getWeekIncome());
            incomeElement.setPrevMonthIncome(prevAnalysisIncome.getMonthIncome());
            incomeElement.setPrevTotalIncome(prevAnalysisIncome.getTotalIncome());
            // 收益计算
            IncomeCalcResult incomeCalcResult = getFormula().calculate(incomeElement);

            // 映射收益计算结果(日、周、月、总、今日盈亏金额等)
            // 绑定计算后的日、周、月、总收益
            analysisIncome.setPrevAsset(incomeElement.getPrevAsset());
            analysisIncome.setDayIncome(incomeCalcResult.getDayIncome());
            analysisIncome.setWeekIncome(incomeCalcResult.getWeekIncome());
            analysisIncome.setMonthIncome(incomeCalcResult.getMonthIncome());
            analysisIncome.setTotalIncome(incomeCalcResult.getTotalIncome());
            // 保存公式子类计算出来的真正盈亏金额
            analysisIncome.setIncomeBalance(incomeCalcResult.getIncomeBalance());
            analysisIncome.setStatus(IncomeStatus.STARTED.getValue());
            analysisIncome.setAllBankTrans(getAllBankTransValue(prevAnalysisIncome.getAllBankTrans(),
                analysisIncome.getBankTransIn(), analysisIncome.getBankTransOut()));
        }
        // 入库前调用端加入一些自定义信息
        handlePreSaveIncome(analysisIncome, prevAnalysisIncome, incomeElement);
        // 入库
        saveIncome(analysisIncome);
        log.info("income calc engine completed! -> {}", analysisIncome);
    }

    private void checkValid() {
        if (fund == null || StringUtils.isBlank(fund.getUserId())) {
            String errorMsg = String.format("无总资产无法计算收益! -> %s, %s", param.getAccountKey(), fund);
            log.warn(errorMsg);
            throw new NoFundIncomeCalcException(errorMsg);
        }
    }

    public BizParam getParam() {
        return param;
    }

    public void setParam(BizParam param) {
        this.param = param;
    }

    public boolean isFirstDayOfWeek() {
        return isFirstDayOfWeek;
    }

    public void setFirstDayOfWeek(boolean firstDayOfWeek) {
        isFirstDayOfWeek = firstDayOfWeek;
    }

    public boolean isFirstDayOfMonth() {
        return isFirstDayOfMonth;
    }

    public void setFirstDayOfMonth(boolean firstDayOfMonth) {
        isFirstDayOfMonth = firstDayOfMonth;
    }

    public boolean isKeepPrevIncome() {
        return keepPrevIncome;
    }

    public void setKeepPrevIncome(boolean keepPrevIncome) {
        this.keepPrevIncome = keepPrevIncome;
    }

    /**
     * 调用方初始化数据.
     */
    protected abstract void initBizData();

    private BigDecimal getAllBankTransValue(BigDecimal existTotal, BigDecimal bankTransIn, BigDecimal bankTransOut) {
        return existTotal.add(bankTransIn).subtract(bankTransOut);
    }

    private void saveBankTrans(T analysisIncome, List<BankTransElem> bankTrans) {
        // 入金
        BigDecimal bankTransIn = getBankTransByTransType(bankTrans, BankTransType.TRANS_IN);
        // 出金
        BigDecimal bankTransOut = getBankTransByTransType(bankTrans, BankTransType.TRANS_OUT);
        analysisIncome.setBankTransIn(bankTransIn);
        analysisIncome.setBankTransOut(bankTransOut);
    }

    /**
     * 从对账单修复表中取出基础值.
     *
     * @param securityIncome   收益处理类
     * @param fundSerialAssets 对账单修复表
     */
    private void saveInAndOutAssets(T securityIncome, List<FundSerialAssetElem> fundSerialAssets) {
        StringBuilder remarks = new StringBuilder();
        for (FundSerialAssetElem fundSerialAsset : fundSerialAssets) {
            securityIncome.setAssetAsIn(securityIncome.getAssetAsIn().add(fundSerialAsset.getAssetAsIn()));
            securityIncome.setAssetAsOut(securityIncome.getAssetAsOut().add(fundSerialAsset.getAssetAsOut()));
            remarks.append(fundSerialAsset.getRemark() + "#");
        }
        securityIncome.setRemark(StringUtils.removeEnd(remarks.toString(), "#"));
    }

    /**
     * 计算银证转账汇总.
     *
     * @param bankTrans     {@link BankTransElem}
     * @param bankTransType {@link BankTransType}
     * @return 转账发生的金额
     */
    private BigDecimal getBankTransByTransType(List<BankTransElem> bankTrans, BankTransType bankTransType) {
        if (CollectionUtils.isNotEmpty(bankTrans)) {
            return bankTrans.stream()
                .filter(bankTran -> bankTransType.equals(bankTran.getBankTransType()))
                .map(bankTran -> bankTran.getOccurBalance())
                .reduce(BigDecimal.ZERO, (current, next) -> current.add(next));
        }
        return BigDecimal.ZERO;
    }

    protected final <R extends IncomeElemBase> void setIncomeBalance(List<R> incomes, T securityIncome) {
        BigDecimal rmvAsset = incomes.stream()
            .map(income -> income.getRemoveAsset())
            .reduce(BigDecimal.ZERO, (current, next) -> current.add(next));
        securityIncome.setRemoveAsset(securityIncome.getRemoveAsset().add(rmvAsset));

        BigDecimal incomeBalance = BigDecimal.ZERO;
        BigDecimal rmvIncomeBalance = BigDecimal.ZERO;
        final StringBuilder mainRemarks = new StringBuilder();

        for (IncomeElemBase income : incomes) {
            // 所有的原始盈亏金额, 依然保存到总盈亏中。
            incomeBalance = incomeBalance.add(income.getIncomeBalance());
            // 具体要刨掉哪些盈亏金额, 由调用方决定
            if (getExcludeIncomeTagTypes().stream().anyMatch(t -> t.getValue().equals(income.getSecurityIncomeTag()))) {
                rmvIncomeBalance = rmvIncomeBalance.add(income.getIncomeBalance());
            }
        }
        securityIncome.setIncomeBalance(securityIncome.getIncomeBalance().add(incomeBalance));
        securityIncome.setRemoveIncomeBalance(securityIncome.getRemoveIncomeBalance().add(rmvIncomeBalance));
        // 汇总备注
        String remark = StringUtils.removeEnd(mainRemarks.toString(), "#");
        securityIncome.appendDiffSecurityTypeRemark(remark);
    }

    /**
     * 计算收益的核心算法钩子.
     * 客户端通过调用方法：setIncomeBalance()来定制需要计算收益的品种，如股票、基金、回购、债券、存款。。。
     *
     * @param securityIncome 收益分析结果类
     */
    private void calcIncomeBalance(T securityIncome) {
        for (SecurityType securityType : getCalcIncomeSecurityTypes()) {
            if (SecurityType.STOCK.equals(securityType)) {
                setIncomeBalance(stockIncomes, securityIncome);
            } else if (SecurityType.BOND.equals(securityType)) {
                setIncomeBalance(bondIncomes, securityIncome);
            } else if (SecurityType.FUND.equals(securityType)) {
                setIncomeBalance(fundIncomes, securityIncome);
            } else if (SecurityType.REPO.equals(securityType)) {
                setIncomeBalance(repoIncomes, securityIncome);
            }
            // TODO other security-types
            else {
                throw new NotImplementedException("该证券类型收益计算引擎暂未支持！");
            }
        }
    }

    /**
     * 今日真实总资产( 净值 ).
     * 今天真实总资产 = 总资产(资金) - 刨掉总资产 + 找回的总资产
     *
     * @return data
     */
    private BigDecimal getNetValue(T analysisIncome, List<FundSerialAssetElem> fundSerialAssets) {
        return analysisIncome.getAsset()
            .subtract(analysisIncome.getRemoveAsset())
            .add(getAddBackAssets(fundSerialAssets));
    }

    /**
     * 获取需要修复的总资产（计算用）.
     *
     * @param fundSerialAssets 对账单修复表
     * @return data
     */
    private BigDecimal getAddBackAssets(List<FundSerialAssetElem> fundSerialAssets) {
        return fundSerialAssets.stream()
            .map(f -> f.getAssetAddBack())
            .reduce(BigDecimal.ZERO, (current, next) -> current.add(next));
    }

    /**
     * 获取今日总资产（ 计算日收益率时需要使用 ）.
     * 计算日收益率时，今日总资产有两种计算方式：
     * 1. 当前总资产法;
     * 2. 总净值法;
     *
     * @param analysisIncome 收益分析结果类
     * @return data
     */
    protected BigDecimal getTodayAsset(T analysisIncome) {
        return analysisIncome.getAsset()
            .subtract(analysisIncome.getRemoveAsset())
            .add(analysisIncome.getAddBackAsset());
    }

    /**
     * 计算总入金.
     * 一些导致资产减少或增多的特殊业务的资金，直接当成出金、入金来处理
     *
     * @param analysisIncome 收益计算结果类
     * @return data
     */
    protected BigDecimal getAllBankTransIn(T analysisIncome) {
        return analysisIncome.getBankTransIn()
            .add(analysisIncome.getAssetAsIn());
    }

    /**
     * 计算总出金.
     * 一些导致资产减少或增多的特殊业务的资金，直接当成出金、入金来处理
     *
     * @param analysisIncome 收益计算结果类
     * @return data
     */
    protected BigDecimal getAllBankTransOut(T analysisIncome) {
        return analysisIncome.getBankTransOut()
            .add(analysisIncome.getAssetAsOut());
    }

    /**
     * 收益计算方式.
     * 1. 总资产相减法;
     * 2. 日收益相加法;
     *
     * @return data
     */
    protected abstract FormulaBase getFormula();

    /**
     * 需要计算的证券品种.
     * 默认计算所有爱投顾APP支持的品种（股票、基金、债券、逆回购...）
     *
     * @return securityTypes
     */
    protected List<SecurityType> getCalcIncomeSecurityTypes() {
        return Arrays.asList(
            SecurityType.STOCK,
            SecurityType.BOND,
            SecurityType.FUND,
            SecurityType.REPO
        );
    }

    /**
     * 获取不用计算盈亏金额的收益计算类型.
     * 计算：removeIncomeBalance 时使用，目前在实盘赛中，只有一种情况：[新股上市第一天]
     *
     * @return 收益计算类型
     */
    /**
     * 获取不用计算盈亏金额的收益计算类型.
     * 计算：removeIncomeBalance 时使用，目前在实盘赛中，只有一种情况：[新股上市第一天]
     *
     * @return 收益计算类型
     */
    protected List<SecurityIncomeTagType> getExcludeIncomeTagTypes() {
        return new ArrayList<>();
    }

    /**
     * 将其他品种的日收益排除掉（如基金，实盘大赛时需要计算这个）.
     * 注意：
     * 1. 在使用总资产相减时，才订制此属性;
     * 2. 此值是针对大层面（证券分类）排除不需要计算的类型;（如实盘赛刨掉：基金、债券、回购，需要将这几类日收益汇总）
     *
     * @return income
     */
    protected BigDecimal getExcludeSecurityIncomes() {
        return BigDecimal.ZERO;
    }

    /**
     * 是否开始计算日收益的前提条件.
     * 调用方可以订制一些特殊条件，从而不进行收益计算
     *
     * @param analysisIncome     账户收益模型
     * @param prevAnalysisIncome 前日账户收益模型
     * @return boolean
     */
    protected boolean checkCanCalcIncome(T analysisIncome, T prevAnalysisIncome) {
        if (prevAnalysisIncome == null || DataUtil.isEmpty(prevAnalysisIncome.getRealAsset())) {
            return false;
        }
        return true;
    }

    /**
     * 保存入库前可执行的操作.
     *
     * @param analysisIncome     收益计算
     * @param prevAnalysisIncome 前日收益计算
     * @param incomeElement      收益计算公式元素
     */
    protected void handlePreSaveIncome(T analysisIncome, T prevAnalysisIncome, IncomeElement incomeElement) {
    }

    protected abstract T createIncome();

    protected abstract T getPrevIncome();

    protected abstract void saveIncome(T analysisIncome);
}
