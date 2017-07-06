package com.stock.calculate.enums;

/**
 * 证券收益类型.（用来标记证券的特殊收益：如新股上市第一天44.4%涨停板，在A股比赛中可能不能计算收益，因此打上标记以便滤掉该收益）.
 *
 * @author bowen.yan
 * @since 2017-05-13
 */
public enum SecurityIncomeTagType {
    NEW_STOCK_ONLINE("1", "新股上市"),
    UNKNOWN("-1", "未知类型");

    private final String value;
    private final String name;

    private SecurityIncomeTagType(String value, String name) {
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