package com.outmao.ebs.wallet.dao;

import com.outmao.ebs.wallet.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import java.util.List;

public interface BankAccountDao extends JpaRepository<BankAccount,Long> , QuerydslPredicateExecutor<BankAccount> {


    public List<BankAccount> findAllByUserId(Long userId);

}
