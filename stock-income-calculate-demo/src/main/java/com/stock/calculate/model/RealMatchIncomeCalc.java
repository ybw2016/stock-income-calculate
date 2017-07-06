package com.stock.calculate.model;

import com.stock.calculate.model.IncomeCalcBase;

/**
 * @author bowen.yan
 * @since 2017-01-05
 */
public class RealMatchIncomeCalc extends IncomeCalcBase {
    public boolean isStartCalcIncome() {
        return IncomeStatus.STARTED.getValue().equals(getStatus());
    }
}
