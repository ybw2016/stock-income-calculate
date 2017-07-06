package com.stock.calculate.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * 数据转换处理类.
 *
 * @author bowen.yan
 * @since 2016-09-23
 */
public class DataUtil {
    private static final Logger log = LoggerFactory.getLogger(DataUtil.class);

    public static final int RATE_SCALE = 4;
    public static final int BALANCE_SCALE = 3;
    public static final int PERCENT_SCALE = 2;
    public static final int PRICE_SCALE = 3;

    /**
     * 生成收益率百分比.
     * -0.1252 -> "-12.52%"
     * 0.2816  -> "+28.16%"
     *
     * @param rate           收益率
     * @param prefixWithSign 百分比
     */
    public static String rateToPercentString(BigDecimal rate, boolean prefixWithSign) {
        if (rate == null || rate.compareTo(BigDecimal.ZERO) == 0) {
            return "0.00%";
        }
        String sign = rate.compareTo(BigDecimal.ZERO) > 0 ? "+" : "";
        String percent = rate.multiply(new BigDecimal(100)).setScale(PERCENT_SCALE, RoundingMode.HALF_UP) + "%";
        if (prefixWithSign) {
            return sign + percent;
        }
        return percent;
    }

    /**
     * 调仓比如果大于100%(1),则取100%(1).
     *
     * @param rate 调仓比
     */
    public static BigDecimal checkRateOverFlow1(BigDecimal rate) {
        if (BigDecimal.ONE.compareTo(rate) < 0) {
            log.debug("纠正调仓比: rawRate:{} -> newRate:{}", rate, BigDecimal.ONE);
            rate = BigDecimal.ONE;
        }
        return rate;
    }

    /**
     * 返回带2位小数的收益率( 收益率 * 100 ).
     *
     * @param dividend 分子
     * @param divisor  分母
     * @return 返回小数点后2位的值
     */
    public static BigDecimal divideToPercent(BigDecimal dividend, BigDecimal divisor) {
        return divideWithScale(dividend, divisor, RATE_SCALE).multiply(new BigDecimal("100"));
    }

    /**
     * 返回带4位小数的收益率.
     *
     * @param dividend 分子
     * @param divisor  分母
     * @return 返回小数点后4位的值
     */
    public static BigDecimal divideToRate(BigDecimal dividend, BigDecimal divisor) {
        return divideWithScale(dividend, divisor, RATE_SCALE);
    }

    /**
     * 返回带3位小数的金额.
     *
     * @param dividend 分子
     * @param divisor  分母
     * @return 返回小数点后3位的值
     */
    public static BigDecimal divideToBalance(BigDecimal dividend, BigDecimal divisor) {
        return divideWithScale(dividend, divisor, BALANCE_SCALE);
    }

    /**
     * 返回带3位小数的收益率.
     *
     * @param dividend 分子
     * @param divisor  分母
     * @return 返回小数点后3位
     */
    public static BigDecimal divideToPrice(BigDecimal dividend, BigDecimal divisor) {
        return divideWithScale(dividend, divisor, PRICE_SCALE);
    }

    /**
     * 返回带N位小数的收益率（小数点处理模式: 四舍五入）.
     *
     * @param dividend 分子（被除数）
     * @param divisor  分母（除数）
     * @param scale    精度
     * @return 返回小数点后N位
     */
    public static BigDecimal divideWithScale(BigDecimal dividend, BigDecimal divisor, int scale) {
        return divideWithScaleAndMode(dividend, divisor, scale, RoundingMode.HALF_UP);
    }

    /**
     * 返回带N位小数的收益率.
     *
     * @param dividend     分子（被除数）
     * @param divisor      分母（除数）
     * @param scale        精度
     * @param roundingMode 小数点处理模式（如：四舍五入）
     * @return 返回小数点后N位
     */
    public static BigDecimal divideWithScaleAndMode(BigDecimal dividend, BigDecimal divisor, int scale,
                                                    RoundingMode roundingMode) {
        if (BigDecimal.ZERO.compareTo(divisor) == 0) {
            return BigDecimal.ZERO;
        }
        return dividend.divide(divisor, scale, roundingMode);
    }

    /**
     * 计算加权平均收益率.
     *
     * @param prevIncomeRate  前日收益率
     * @param todayIncomeRate 今日收益率
     * @return rate
     */
    public static BigDecimal calcWeightingRate(BigDecimal prevIncomeRate, BigDecimal todayIncomeRate) {
        BigDecimal prevWeight = BigDecimal.ONE.add(prevIncomeRate);
        BigDecimal todayWeight = BigDecimal.ONE.add(todayIncomeRate);
        return prevWeight.multiply(todayWeight).subtract(BigDecimal.ONE)
            .setScale(RATE_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 收益率小数位数统一化.
     *
     * @param value 金额
     * @return 返回小数点后4位的值
     */
    public static BigDecimal setRateScale(BigDecimal value) {
        if (isEmpty(value)) return BigDecimal.ZERO;
        return value.setScale(RATE_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 金额小数位数统一化.
     *
     * @param value 金额
     * @return 返回小数点后3位的值
     */
    public static BigDecimal setBalanceScale(BigDecimal value) {
        if (isEmpty(value)) return BigDecimal.ZERO;
        return value.setScale(BALANCE_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 价格小数位数统一化.
     *
     * @param value 金额
     * @return 返回小数点后3位的值
     */
    public static BigDecimal setPriceScale(BigDecimal value) {
        if (isEmpty(value)) return BigDecimal.ZERO;
        return value.setScale(PRICE_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 判断数据是否为空.
     *
     * @param data 输入数据
     */
    public static boolean isEmpty(BigDecimal data) {
        return data == null || BigDecimal.ZERO.compareTo(data) == 0;
    }

    /**
     * 比较两个数字是否相等.
     *
     * @param data1 数据1
     * @param data2 数据2
     * @return boolean
     */
    public static boolean isEqual(BigDecimal data1, BigDecimal data2) {
        return emptyToZero(data1).compareTo(emptyToZero(data2)) == 0;
    }

    /**
     * 比较data1是否大于data2.
     *
     * @param data1 数据1
     * @param data2 数据2
     * @return boolean
     */
    public static boolean isGreaterThan(BigDecimal data1, BigDecimal data2) {
        return emptyToZero(data1).compareTo(emptyToZero(data2)) > 0;
    }

    /**
     * 比较data1是否大于等于data2.
     *
     * @param data1 数据1
     * @param data2 数据2
     * @return boolean
     */
    public static boolean isGreaterThanOrEqual(BigDecimal data1, BigDecimal data2) {
        return emptyToZero(data1).compareTo(emptyToZero(data2)) >= 0;
    }

    /**
     * 比较data1是否小于data2.
     *
     * @param data1 数据1
     * @param data2 数据2
     * @return boolean
     */
    public static boolean isLessThan(BigDecimal data1, BigDecimal data2) {
        return emptyToZero(data1).compareTo(emptyToZero(data2)) < 0;
    }

    /**
     * 比较data1是否小于等于data2.
     *
     * @param data1 数据1
     * @param data2 数据2
     * @return boolean
     */
    public static boolean isLessThanOrEqual(BigDecimal data1, BigDecimal data2) {
        return emptyToZero(data1).compareTo(emptyToZero(data2)) <= 0;
    }

    /**
     * 数据为空时返回0否则返回原数据.
     *
     * @param data 输入数据
     * @return 输出结果
     */
    public static BigDecimal emptyToZero(BigDecimal data) {
        return data == null ? BigDecimal.ZERO : data;
    }

    /**
     * 将枚举的value值转化成枚举对象.
     *
     * @param value value值
     * @param type  枚举类型对象
     * @param <T>   枚举类型
     * @return 枚举对象
     */
    public static <T extends Enum> T convert(String value, Class<T> type) {
        if (value == null) {
            return null;
        }
        T defaultVal = null;
        for (T enumValue : type.getEnumConstants()) {
            try {
                Method method = enumValue.getClass().getDeclaredMethod("getValue");
                Object findVal = method.invoke(enumValue);
                if (value.equals(findVal.toString())) {
                    return enumValue;
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ex) {
                log.debug("该枚举未实现getValue()方法或者无该枚举值!", ex);
            }
            if (defaultVal == null && "UNKNOWN".equals(enumValue.toString())) {
                defaultVal = enumValue;
            }
        }
        return defaultVal;
    }

    /**
     * 将两个字符串中间用分隔符连起来.
     *
     * @param current  当前字符串
     * @param next     待连接的字符串
     * @param joinChar 分隔符
     * @return 连接后的结果
     */
    public static String concatString(String current, String next, String joinChar) {
        if (StringUtils.isBlank(current)) {
            return next;
        }
        if (StringUtils.isBlank(next)) {
            return current;
        }
        return String.format("%s%s%s", current, joinChar, next);
    }

    /**
     * 生成一定长度的随机字符串.
     *
     * @param length 长度
     * @return result
     */
    public static String getRandomString(int length) {
        String[] chars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(36);
            builder.append(chars[index]);
        }
        return builder.toString();
    }
}
