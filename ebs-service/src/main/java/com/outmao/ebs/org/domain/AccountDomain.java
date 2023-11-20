package com.outmao.ebs.org.domain;


import com.outmao.ebs.org.dto.AccountDTO;
import com.outmao.ebs.org.dto.AccountRoleDTO;
import com.outmao.ebs.org.dto.GetAccountListDTO;
import com.outmao.ebs.org.dto.SetAccountRoleDTO;
import com.outmao.ebs.org.entity.Account;
import com.outmao.ebs.org.entity.AccountRole;
import com.outmao.ebs.org.vo.AccountVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface AccountDomain {


    public Account saveAccount(AccountDTO request);
    public void deleteAccountById(Long id);
    public List<Account> getAccountListByUserId(Long userId);
    public AccountVO getAccountVOById(Long id);
    public Page<AccountVO> getAccountVOPage(GetAccountListDTO request, Pageable pageable);


    public AccountRole saveAccountRole(AccountRoleDTO request);
    public List<AccountRole> setAccountRole(SetAccountRoleDTO request);
    public void deleteAccountRoleById(Long id);


}
