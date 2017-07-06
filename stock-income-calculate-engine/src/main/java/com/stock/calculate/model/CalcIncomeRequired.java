package com.stock.calculate.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记收益计算引擎用到的字段.
 * 类中没有标注的字段都是参考字段，非必需的字段
 *
 * @author bowen.yan
 * @since 2017-05-01
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CalcIncomeRequired {
    boolean value() default true;
}
