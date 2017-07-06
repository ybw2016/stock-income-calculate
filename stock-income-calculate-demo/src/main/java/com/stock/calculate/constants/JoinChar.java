package com.stock.calculate.constants;

/**
 * @author bowen.yan
 * @since 2017-05-02
 */

/**
 * 连接字符串分隔符.
 *
 * @author bowen.yan
 * @since 2017-01-12
 */
public class JoinChar {
    // SQL in语句等使用
    public static final String COMMA_CHAR = ",";
    // Redis key等，如： userCode_fundAccount_InitDate
    public static final String KEY_JOIN_CHAR = "_";
    // 如：stockCode|StockName|CostPrice|MarketPrice -> 600856|中天能源|16.82|1682
    public static final String MSG_SPLIT_CHAR = "|";
    // 连接同一个集合List<T>时使用, 如elem1#elem2#elem3...
    public static final String INNER_LIST_JOIN_CHAR = "#";
    // 连接多个集合List<T>时使用，如list1@list2@list3...
    public static final String CROSS_LIST_JOIN_CHAR = "@";
}
