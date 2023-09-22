package com.outmao.ebs.wallet.dao;


import com.outmao.ebs.wallet.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyDao  extends JpaRepository<Currency,String> {

}
