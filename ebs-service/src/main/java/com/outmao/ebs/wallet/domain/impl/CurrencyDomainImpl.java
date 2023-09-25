package com.outmao.ebs.wallet.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.wallet.dao.CurrencyDao;
import com.outmao.ebs.wallet.domain.CurrencyDomain;
import com.outmao.ebs.wallet.dto.CurrencyDTO;
import com.outmao.ebs.wallet.entity.Currency;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component
public class CurrencyDomainImpl extends BaseDomain implements CurrencyDomain {


    @Autowired
    private CurrencyDao currencyDao;

    @CacheEvict(value = "cache_currency", allEntries = true)
    @Transactional
    @Override
    public Currency saveCurrency(CurrencyDTO params) {
        Currency c= currencyDao.findById(params.getId()).orElse(null);
        if(c==null) {
            c=new Currency();
            c.setCreateTime(new Date());
        }
        BeanUtils.copyProperties(params,c);
        c.setUpdateTime(new Date());
        currencyDao.save(c);
        return c;
    }

    @Cacheable(value = "cache_currency", key = "#id")
    @Override
    public Currency getCurrencyById(String id) {
        return currencyDao.findById(id).orElse(null);
    }

    @Cacheable(value = "cache_currency", key = "method.name")
    @Override
    public List<Currency> getCurrencyList() {
        return currencyDao.findAll();
    }




}
