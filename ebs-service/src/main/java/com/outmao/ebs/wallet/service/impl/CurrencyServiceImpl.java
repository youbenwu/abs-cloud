package com.outmao.ebs.wallet.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.wallet.domain.CurrencyDomain;
import com.outmao.ebs.wallet.dto.CurrencyDTO;
import com.outmao.ebs.wallet.entity.Currency;
import com.outmao.ebs.wallet.entity.Fee;
import com.outmao.ebs.wallet.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Order(0)
@Service
public class CurrencyServiceImpl extends BaseService implements CurrencyService, CommandLineRunner {



    @Autowired
    private CurrencyDomain currencyDomain;

    @Override
    public void run(String... args) throws Exception {
        List<Currency> currencies=currencyDomain.getCurrencyList();
        if(currencies.isEmpty()){
            currencyDomain.saveCurrency(new CurrencyDTO("RMB", "人民币", "元", 100, 100, true, new Fee(1,100,0)));
            currencyDomain.saveCurrency(new CurrencyDTO("COIN", "金币", "个", 1, 1, false, null));
        }
    }

    @Override
    public Currency saveCurrency(CurrencyDTO params) {
        return currencyDomain.saveCurrency(params);
    }

    @Override
    public Currency getCurrencyById(String id) {
        return currencyDomain.getCurrencyById(id);
    }

    @Override
    public List<Currency> getCurrencyList() {
        return currencyDomain.getCurrencyList();
    }


}
