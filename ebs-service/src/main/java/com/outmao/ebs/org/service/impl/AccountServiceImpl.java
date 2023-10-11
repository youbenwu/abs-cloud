package com.outmao.ebs.org.service.impl;


import com.mysema.commons.lang.Assert;
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
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;


@Service
public class AccountServiceImpl extends BaseService implements AccountService {

    @Autowired
    private AccountDomain accountDomain;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public Account saveAccount(AccountDTO request) {
        if(request.getUserId()==null){
           findOrRegisterUser(request);
        }
        else if(!StringUtils.isEmpty(request.getPassword())){
            userService.modifyUserPassword(request.getUserId(),null,request.getPassword());
        }
        return accountDomain.saveAccount(request);
    }

    private void findOrRegisterUser(AccountDTO request){

        Assert.notNull(request.getPhone(),"手机不能为空");

        User user=userService.getUserByUsername(request.getPhone());

        if(user==null){
            RegisterDTO registerDTO=new RegisterDTO();
            registerDTO.setPrincipal(request.getPhone());
            registerDTO.setCredentials(request.getPassword());
            registerDTO.setOauth(Oauth.PHONE.getName());
            registerDTO.setArgs(new HashMap<>());
            registerDTO.getArgs().put("nickname",request.getName());

            user=userService.registerUser(registerDTO);
        }else if(!StringUtils.isEmpty(request.getPassword())){
            userService.modifyUserPassword(user.getId(),null,request.getPassword());
        }

        request.setUserId(user.getId());

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
