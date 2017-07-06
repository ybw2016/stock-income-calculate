package com.stock.calculate.exception;

/**
 * 数据拷贝失败异常.
 *
 * @author bowen.yan
 * @since 2017-05-02
 */
public class DataCopyFailedException extends RuntimeException {
    public DataCopyFailedException(Throwable throwable) {
        super("数据拷贝异常!", throwable);
    }
}
