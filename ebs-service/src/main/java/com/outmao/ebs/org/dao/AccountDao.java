package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountDao extends JpaRepository<Account,Long> {

    public Account findByOrgIdAndUserId(Long orgId, Long userId);

    public List<Account> findAllByUserId(Long userId);


}
