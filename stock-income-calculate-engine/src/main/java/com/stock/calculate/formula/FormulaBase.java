package com.stock.calculate.formula;

import com.stock.calculate.model.IncomeCalcResult;
import com.stock.calculate.model.IncomeElement;
import com.stock.calculate.utils.DataUtil;

import java.math.BigDecimal;

/**
 * 收益计算公式基类.
 *
 * @author bowen.yan
 * @since 2016-12-12
 */
public abstract class FormulaBase {
    /**
     * 收益计算.
     * 周收益率、月收益率、总收益率都采用加权平均来计算
     */
    public IncomeCalcResult calculate(IncomeElement incomeElement) {
        BigDecimal calcIncomeBalance = getAllIncomeBalance(incomeElement);
        BigDecimal incomeBalance = calcIncomeBalance.subtract(incomeElement.getRemoveIncomeBalance());
        // 日收益率
        BigDecimal dayIncome = DataUtil.divideToRate(incomeBalance, incomeElement.getDividend());
        // 周收益计算
        BigDecimal prevWeekIncome = incomeElement.isFirstDayOfWeek()
            ? BigDecimal.ZERO : incomeElement.getPrevWeekIncome();
        BigDecimal weekRate = DataUtil.calcWeightingRate(prevWeekIncome, dayIncome);
        // 月收益计算
        BigDecimal prevMonthIncome = incomeElement.isFirstDayOfMonth()
            ? BigDecimal.ZERO : incomeElement.getPrevMonthIncome();
        BigDecimal monthRate = DataUtil.calcWeightingRate(prevMonthIncome, dayIncome);
        // 总收益计算
        BigDecimal totalRate = DataUtil.calcWeightingRate(incomeElement.getPrevTotalIncome(), dayIncome);

        // 保存计算结果
        IncomeCalcResult incomeCalcResult = new IncomeCalcResult();
        incomeCalcResult.setIncomeBalance(calcIncomeBalance);
        incomeCalcResult.setDayIncome(dayIncome);
        incomeCalcResult.setWeekIncome(weekRate);
        incomeCalcResult.setMonthIncome(monthRate);
        incomeCalcResult.setTotalIncome(totalRate);
        return incomeCalcResult;
    }

    protected abstract BigDecimal getAllIncomeBalance(IncomeElement incomeElement);
}
