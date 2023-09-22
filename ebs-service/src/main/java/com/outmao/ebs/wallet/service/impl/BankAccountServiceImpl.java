package com.outmao.ebs.wallet.service.impl;

import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.wallet.domain.BankAccountDomain;
import com.outmao.ebs.wallet.dto.BankAccountDTO;
import com.outmao.ebs.wallet.dto.GetBankAccountListDTO;
import com.outmao.ebs.wallet.entity.BankAccount;
import com.outmao.ebs.wallet.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl extends BaseService implements BankAccountService {


    @Autowired
    private BankAccountDomain bankAccountDomain;


    @Override
    public BankAccount saveBankAccount(BankAccountDTO request) {
        return bankAccountDomain.saveBankAccount(request);
    }

    @Override
    public void deleteBankAccountById(Long id) {
        bankAccountDomain.deleteBankAccountById(id);
    }

    @Override
    public BankAccount getBankAccountById(Long id) {
        return bankAccountDomain.getBankAccountById(id);
    }

    @Override
    public List<BankAccount> getBankAccountListByUserId(Long userId) {
        return bankAccountDomain.getBankAccountListByUserId(userId);
    }

    @Override
    public Page<BankAccount> getBankAccountPage(GetBankAccountListDTO request, Pageable pageable) {
        return bankAccountDomain.getBankAccountPage(request,pageable);
    }
}
