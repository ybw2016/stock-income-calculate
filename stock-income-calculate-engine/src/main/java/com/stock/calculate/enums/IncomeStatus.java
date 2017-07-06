package com.stock.calculate.enums;

/**
 * @author bowen.yan
 * @since 2016-10-11
 */
public enum IncomeStatus {
    NOT_STARTED("0", "未计算收益"),
    STARTED("1", "已计算收益");

    private final String value;
    private final String name;

    private IncomeStatus(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}