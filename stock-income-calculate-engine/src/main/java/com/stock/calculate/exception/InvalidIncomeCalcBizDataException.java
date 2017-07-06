package com.stock.calculate.exception;

/**
 * 业务数据异常，需要停止计算任务时抛出异常.
 *
 * @author bowen.yan
 * @since 2017-02-09
 */
public abstract class InvalidIncomeCalcBizDataException extends RuntimeException {
    public InvalidIncomeCalcBizDataException(String message) {
        super(message);
    }
}
