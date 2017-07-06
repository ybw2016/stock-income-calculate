package com.stock.calculate.enums;

/**
 * @author bowen.yan
 * @since 2016-10-11
 */
public enum IncomeTagType {
    NORMAL("1", "正常"),
    LACK_OF_PREV_ASSET("2", "无前日总资产无法计算收益"),
    NEW_STOCK_ONLINE_PREV_SH("3", "上交所新股上市前一天修复市值"),
    NEW_STOCK_ONLINE("4", "新股上市"),
    NEW_STOCK_ADD_BACK_COST_AND_MKTPRC_AT_APPLY_DAY("5", "新股申购日修复成本市值"),
    SH_NEW_STOCK_ADD_BACK_MKTPRC_FROM_STK_LIST_PLAN("6", "上交所新股从上市信息中修复市价"),
    SZ_NEW_STOCK_ADD_BACK_COST_AND_MKTPRC_FROM_STK_LIST_PLAN("7", "深交所新股从上市信息中修复成本市值"),
    SZ_NEW_STOCK_ADD_BACK_COST_AND_MKTPRC("8", "深交所新股每日修复成本市值"),
    SZ_NEW_STOCK_ADD_BACK_COST("9", "深交所新股每日修复成本"),
    STOCK_PLEDGE_END_CALC_INCOME_BALANCE("10", "股票质押回购计算持仓盈亏"),
    DIVID_T1_CALC_AT_REG_DAY("11", "T+1红股上市计算登记日"),
    DIVID_T1_CALC_AT_ONL_DAY("12", "T+1红股上市计算上市日"),
    DIVID_T2_CALC_AT_REG_DAY("13", "T+2红股上市计算登记日"),
    DIVID_T2_CALC_AT_DIV_DAY("14", "T+2红股上市计算除权日"),
    DIVID_T2_CALC_AT_ONL_DAY("15", "T+2红股上市计算上市日"),
    DIVID_BONUS("16", "除权除息"),
    BONUS("17", "除息"),

    LESS_THAN_1W("30", "资产小于1W"),
    FUND_APPLY_ON_ROAD("31", "基金申购资金在途"),
    FUND_REDEEM_ON_ROAD("32", "基金赎回资金在途"),
    FUND_REMOVE_INCOME("33", "基金刨掉市值"),
    REPO("34", "回购"),
    UNKNOWN("-1", "未知类型");

    private final String value;
    private final String name;

    private IncomeTagType(String value, String name) {
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