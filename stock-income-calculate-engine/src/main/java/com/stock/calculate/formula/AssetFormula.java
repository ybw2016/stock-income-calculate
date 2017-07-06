package com.stock.calculate.formula;

import com.stock.calculate.model.IncomeElement;

import java.math.BigDecimal;

/**
 * 总资产相减法.
 * 根据总资产变化来计算日收益率
 * 今日收益率 = (今日总资产 - 昨日总资产 - 入金 + 出金 ) / (昨日总资产 + 入金 - 出金)
 *
 * @author bowen.yan
 * @since 2016-12-12
 */
public class AssetFormula extends FormulaBase {
    @Override
    protected BigDecimal getAllIncomeBalance(IncomeElement incomeElement) {
        return incomeElement.getAsset()
            .add(incomeElement.getBankTransOut())
            .subtract(incomeElement.getBankTransIn())
            .subtract(incomeElement.getPrevAsset())
            .subtract(incomeElement.getExcludeSecurityIncomeBalance());
    }
}
