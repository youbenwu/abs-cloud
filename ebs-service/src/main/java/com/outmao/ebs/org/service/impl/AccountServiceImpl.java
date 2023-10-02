package com.outmao.ebs.org.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.org.domain.AccountDomain;
import com.outmao.ebs.org.dto.AccountDTO;
import com.outmao.ebs.org.dto.AccountRoleDTO;
import com.outmao.ebs.org.dto.GetAccountListDTO;
import com.outmao.ebs.org.dto.SetAccountRoleDTO;
import com.outmao.ebs.org.entity.Account;
import com.outmao.ebs.org.entity.AccountRole;
import com.outmao.ebs.org.service.AccountService;
import com.outmao.ebs.org.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl extends BaseService implements AccountService {

    @Autowired
    private AccountDomain accountDomain;

    @Override
    public Account saveAccount(AccountDTO request) {
        return accountDomain.saveAccount(request);
    }

    @Override
    public void deleteAccountById(Long id) {
        accountDomain.deleteAccountById(id);
    }

    @Override
    public AccountVO getAccountVOById(Long id) {
        return accountDomain.getAccountVOById(id);
    }

    @Override
    public Page<AccountVO> getAccountVOPage(GetAccountListDTO request, Pageable pageable) {
        return accountDomain.getAccountVOPage(request,pageable);
    }

    @Override
    public AccountRole saveAccountRole(AccountRoleDTO request) {
        return accountDomain.saveAccountRole(request);
    }

    @Override
    public List<AccountRole> setAccountRole(SetAccountRoleDTO request) {
        return accountDomain.setAccountRole(request);
    }

    @Override
    public void deleteAccountRoleById(Long id) {
        accountDomain.deleteAccountRoleById(id);
    }
}
