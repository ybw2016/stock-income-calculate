package com.stock.calculate.formula;

import com.stock.calculate.model.IncomeElement;

import java.math.BigDecimal;

/**
 * 盈亏相加法.
 * 根据金额盈亏金额明细来计算日收益率
 * 今日盈亏金额  = 持仓盈亏 + 买入盈亏 + 卖出盈亏 + 派息金额
 * 持仓盈亏 = 可用数量 * （当前价格 - 昨收价格）
 * 买入盈亏 = 买入数量 * （ 当前价格 - 买入价格 ）
 * 卖出盈亏 = 卖出数量 * （ 卖出价格 - 昨收价格 ）
 * 派息金额 = 成交流水中的派息金额
 * 当有除权除息时, 昨收价格应该进行除权处理, 计算公式如下:
 * 除权价格 = （昨日收盘价 - 每股派息金额）/ （ 1 + 送股率 ）
 *
 * @author bowen.yan
 * @since 2016-12-12
 */
public class IncomeFormula extends FormulaBase {
    @Override
    protected BigDecimal getAllIncomeBalance(IncomeElement incomeElement) {
        // 盈亏金额计算方式计算时, 盈亏金额不包含刨掉的盈亏金额( removeIncomeBalance )
        return incomeElement.getIncomeBalance();
    }
}
