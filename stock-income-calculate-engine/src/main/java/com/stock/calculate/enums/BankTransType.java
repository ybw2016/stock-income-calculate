package com.stock.calculate.enums;

/**
 * @author bowen.yan
 * @since 2017-04-30
 */
public enum BankTransType {
    TRANS_IN("1", "转入"),
    TRANS_OUT("2", "转出");
    private final String value;
    private final String name;

    private BankTransType(String value, String name) {
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
