package com.outmao.ebs.wallet.domain.impl;

import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.common.util.StringUtil;
import com.outmao.ebs.user.dao.UserDao;
import com.outmao.ebs.wallet.dao.BankAccountDao;
import com.outmao.ebs.wallet.domain.BankAccountDomain;
import com.outmao.ebs.wallet.dto.BankAccountDTO;
import com.outmao.ebs.wallet.dto.GetBankAccountListDTO;
import com.outmao.ebs.wallet.entity.BankAccount;
import com.outmao.ebs.wallet.entity.QBankAccount;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
public class BankAccountDomainImpl extends BaseDomain implements BankAccountDomain {

    @Autowired
    private BankAccountDao bankAccountDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public BankAccount saveBankAccount(BankAccountDTO request) {
        BankAccount a=request.getId()==null?null:bankAccountDao.getOne(request.getId());

        if(a==null){
            a=new BankAccount();
            a.setUser(userDao.getOne(request.getUserId()));
            a.setCreateTime(new Date());
        }

        a.setUpdateTime(new Date());

        BeanUtils.copyProperties(request,a);

        bankAccountDao.save(a);

        return a;
    }

    @Transactional
    @Override
    public void deleteBankAccountById(Long id) {
        bankAccountDao.deleteById(id);
    }

    @Override
    public BankAccount getBankAccountById(Long id) {
        return bankAccountDao.findById(id).orElse(null);
    }

    @Override
    public List<BankAccount> getBankAccountListByUserId(Long userId) {
        return bankAccountDao.findAllByUserId(userId);
    }

    @Override
    public Page<BankAccount> getBankAccountPage(GetBankAccountListDTO request, Pageable pageable) {

        QBankAccount e=QBankAccount.bankAccount;
        Predicate p=null;
        if(request.getUserId()!=null){
            p=e.user.id.eq(request.getUserId());
        }
        if(StringUtil.isNotEmpty(request.getKeyword())){
            p=e.accountName.like("%"+request.getKeyword()+"%").and(p);
        }

        return bankAccountDao.findAll(p,pageable);
    }


}
