package com.outmao.ebs.org.dao;

import com.outmao.ebs.org.entity.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface AccountRoleDao extends JpaRepository<AccountRole,Long> {

    @Modifying
    public void deleteAllByAccountId(Long accountId);

    @Modifying
    public void deleteAllByRoleId(Long roleId);

    @Modifying
    public void deleteAllByAccountIdAndRoleIdNotIn(Long accountId,List<Long> roleIdNotIn);

    public AccountRole findByAccountIdAndRoleId(Long accountId, Long roleId);

    public List<AccountRole> findAllByAccountId(Long accountId);

}
