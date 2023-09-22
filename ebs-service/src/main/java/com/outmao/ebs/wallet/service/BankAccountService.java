package com.outmao.ebs.wallet.service;

import com.outmao.ebs.wallet.dto.BankAccountDTO;
import com.outmao.ebs.wallet.dto.GetBankAccountListDTO;
import com.outmao.ebs.wallet.entity.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankAccountService {

    public BankAccount saveBankAccount(BankAccountDTO request);

    public void deleteBankAccountById(Long id);

    public BankAccount getBankAccountById(Long id);

    public List<BankAccount> getBankAccountListByUserId(Long userId);

    public Page<BankAccount> getBankAccountPage(GetBankAccountListDTO request, Pageable pageable);


}
