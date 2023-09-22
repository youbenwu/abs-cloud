package com.outmao.ebs.wallet.domain;

import com.outmao.ebs.wallet.dto.CurrencyDTO;
import com.outmao.ebs.wallet.entity.Currency;

import java.util.List;

public interface CurrencyDomain {


    /*
     *
     * 保存货币信息
     *
     */
    public Currency saveCurrency(CurrencyDTO params);

    /*
     *
     * 获取货币信息
     *
     */
    public Currency getCurrencyById(String id);

    /*
     *
     * 获取货币信息
     *
     */
    public List<Currency> getCurrencyList();


}
