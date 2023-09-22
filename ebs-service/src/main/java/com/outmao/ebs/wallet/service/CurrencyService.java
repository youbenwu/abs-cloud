package com.outmao.ebs.wallet.service;

import com.outmao.ebs.wallet.dto.CurrencyDTO;
import com.outmao.ebs.wallet.entity.Currency;

import java.util.List;

public interface CurrencyService {


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
