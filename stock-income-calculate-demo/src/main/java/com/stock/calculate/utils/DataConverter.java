package com.stock.calculate.utils;

import com.stock.calculate.entity.IncomeBase;
import com.stock.calculate.entity.UserAsset;
import com.stock.calculate.entity.UserBankTrans;
import com.stock.calculate.entity.UserFundIncome;
import com.stock.calculate.entity.UserFundSerialAsset;
import com.stock.calculate.entity.UserStockIncome;
import com.stock.calculate.enums.BankTransType;
import com.stock.calculate.enums.IncomeTagType;
import com.stock.calculate.enums.SecurityIncomeTagType;
import com.stock.calculate.exception.DataCopyFailedException;
import com.stock.calculate.model.BankTransElem;
import com.stock.calculate.model.FundElem;
import com.stock.calculate.model.FundIncomeElem;
import com.stock.calculate.model.FundSerialAssetElem;
import com.stock.calculate.model.IncomeElemBase;
import com.stock.calculate.model.StockIncomeElem;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据适配器.
 * 将用户业务数据适配成收益计算引擎识别的数据.
 *
 * @author bowen.yan
 * @since 2016-09-25
 */
public class DataConverter {
    private static Logger log = LoggerFactory.getLogger(DataConverter.class);
    private static final Map<String, String> INCOME_TAG_TYPE_MAP = new HashMap<>();

    static {
        INCOME_TAG_TYPE_MAP.put(IncomeTagType.NEW_STOCK_ONLINE.getValue(),
            SecurityIncomeTagType.NEW_STOCK_ONLINE.getValue());
    }

    /**
     * 将用户资金模型适配成收益计算的资金模型.
     *
     * @param userAsset 资金模型
     * @return 收益计算的资金模型
     */
    public static FundElem convertFund(UserAsset userAsset) {
        if (userAsset == null) {
            throw new RuntimeException("用户资金为空，无法计算收益！");
        }
        FundElem fundElem = new FundElem();
        copy(userAsset, fundElem);
        return fundElem;
    }

    /**
     * 将用户银证转账模型适配成收益计算的银证转账模型.
     *
     * @param userBankTrans 银证转账模型
     * @return 收益计算的银证转账模型
     */
    public static List<BankTransElem> convertBankTrans(List<UserBankTrans> userBankTrans) {
        List<BankTransElem> bankTransElems = new ArrayList<>();
        for (UserBankTrans userBankTran : userBankTrans) {
            BankTransElem bankTransElem = new BankTransElem();
            copy(userBankTran, bankTransElem);
            BankTransType bankTransType = userBankTran.isBankTransIn()
                ? BankTransType.TRANS_IN : BankTransType.TRANS_OUT;
            bankTransElem.setBankTransType(bankTransType);
            bankTransElems.add(bankTransElem);
        }
        return bankTransElems;
    }

    /**
     * 将用户对账单特殊业务模型适配成收益计算的对账单特殊业务模型.
     *
     * @param userFundSerialAssets 对账单特殊业务模型
     * @return 收益计算的对账单特殊业务模型
     */
    public static List<FundSerialAssetElem> convertFundSerials(List<UserFundSerialAsset> userFundSerialAssets) {
        List<FundSerialAssetElem> fundSerialAssetElems = new ArrayList<>();
        userFundSerialAssets.forEach(rawFundSerialAsset -> {
            FundSerialAssetElem fundSerialAssetElem = new FundSerialAssetElem();
            copy(rawFundSerialAsset, fundSerialAssetElem);
            fundSerialAssetElems.add(fundSerialAssetElem);
        });
        return fundSerialAssetElems;
    }

    /**
     * 将用户清先后的证券模型 - 股票适配成收益计算的股票模型.
     *
     * @param stockIncomes 清先后的证券模型 - 股票
     * @return 收益计算的股票模型
     */
    public static List<StockIncomeElem> convertStockIncomes(List<UserStockIncome> stockIncomes) {
        return convertIncomeElems(stockIncomes, StockIncomeElem.class);
    }

    /**
     * 将用户清先后的证券模型 - 基金适配成收益计算的基金模型.
     *
     * @param fundIncomes 清先后的证券模型 - 基金
     * @return 收益计算的基金模型
     */
    public static List<FundIncomeElem> convertFundIncomes(List<UserFundIncome> fundIncomes) {
        return convertIncomeElems(fundIncomes, FundIncomeElem.class);
    }

    private static <S extends IncomeBase, T extends IncomeElemBase> List<T> convertIncomeElems(
        List<S> srcIncomes, Class<T> destClazz) {
        List<T> destIncomeElems = new ArrayList<>();
        srcIncomes.forEach(srcIncome -> {
            try {
                T destIncomeElem = newInstance(destClazz);
                copy(srcIncome, destIncomeElem);
                destIncomeElem.setSecurityIncomeTag(INCOME_TAG_TYPE_MAP.get(srcIncome.getIncomeTag()));
                destIncomeElems.add(destIncomeElem);
            } catch (Exception ex) {
                log.error("convertIncomeElems failed!", ex);
            }
        });
        return destIncomeElems;
    }

    private static <T> T newInstance(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        T newInst = clazz.newInstance();
        return newInst;
    }

    /**
     * 数据字段类拷贝类.
     *
     * @param src  原类
     * @param dest 目标类
     */
    public static void copy(Object src, Object dest) {
        try {
            copySafety(src, dest);
        } catch (Exception ex) {
            log.error("copy data failed!", ex);
        }
    }

    /**
     * 数据字段类拷贝类.
     *
     * @param src  原类
     * @param dest 目标类
     */
    public static void copySafety(Object src, Object dest) throws DataCopyFailedException {
        try {
            if (src != null) {
                BeanUtils.copyProperties(dest, src);
            }
        } catch (Exception ex) {
            throw new DataCopyFailedException(ex);
        }
    }
}
