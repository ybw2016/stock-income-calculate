package com.stock.calculate;

import com.stock.calculate.service.AccountAnalysisCalcService;
import com.stock.calculate.service.RealMatchCalcService;
import org.junit.Test;

/**
 * 收益计算引擎测试类.
 * Created by Administrator on 2017/7/5.
 */
public class StockIncomeCalcServiceTest {
    @Test
    public void calcRealMatchIncome() {
        RealMatchCalcService realMatchCalcService = new RealMatchCalcService();
        realMatchCalcService.bindParameters(new DataFetcherOfRealMatch());
        realMatchCalcService.calcIncome();
    }

    @Test
    public void calcAccountAnalysisIncome() {
        AccountAnalysisCalcService accountAnalysisCalcService = new AccountAnalysisCalcService();
        accountAnalysisCalcService.bindParameters(new DataFetcherOfAcccountAnalysis());
        accountAnalysisCalcService.calcIncome();
    }
}
