package com.stock.calculate.exception;

/**
 * 无资金数据的异常.
 *
 * @author bowen.yan
 * @since 2017-02-09
 */
public class NoFundIncomeCalcException extends InvalidIncomeCalcBizDataException {
    public NoFundIncomeCalcException(String message) {
        super(message);
    }
}
