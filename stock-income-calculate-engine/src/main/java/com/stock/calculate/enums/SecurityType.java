package com.stock.calculate.enums;

/**
 * 标准证券类型.
 *
 * @author bowen.yan
 * @since 2016-09-25
 */
public enum SecurityType {
    STOCK("1", "股票"),
    BOND("2", "债券"),
    FUND("3", "基金"),
    OPTION("4", "权证"),
    INDEX("5", "指数"),
    FINANCE("6", "理财产品"),
    REPO("7", "回购"),
    FUTURE("9", "期货"),
    UNKNOWN("-1", "未知");

    private final String value;
    private final String name;

    private SecurityType(String value, String name) {
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