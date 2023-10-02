package com.outmao.ebs.org.domain.impl;


import com.outmao.ebs.common.base.BaseDomain;
import com.outmao.ebs.org.dao.AccountDao;
import com.outmao.ebs.org.dao.AccountRoleDao;
import com.outmao.ebs.org.dao.OrgDao;
import com.outmao.ebs.org.dao.RoleDao;
import com.outmao.ebs.org.domain.AccountDomain;
import com.outmao.ebs.org.domain.conver.*;
import com.outmao.ebs.org.dto.AccountDTO;
import com.outmao.ebs.org.dto.AccountRoleDTO;
import com.outmao.ebs.org.dto.GetAccountListDTO;
import com.outmao.ebs.org.dto.SetAccountRoleDTO;
import com.outmao.ebs.org.entity.*;
import com.outmao.ebs.org.vo.AccountRoleVO;
import com.outmao.ebs.org.vo.AccountVO;
import com.outmao.ebs.org.vo.RolePermissionVO;
import com.outmao.ebs.org.vo.RoleVO;
import com.outmao.ebs.user.dao.UserDao;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class AccountDomainImpl extends BaseDomain implements AccountDomain {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountRoleDao accountRoleDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private UserDao userDao;


    private AccountVOConver accountVOConver=new AccountVOConver();

    private AccountRoleVOConver accountRoleVOConver=new AccountRoleVOConver();

    private RolePermissionVOConver rolePermissionVOConver=new RolePermissionVOConver();



    @Transactional
    @Override
    public Account saveAccount(AccountDTO request) {
        Account account =request.getId()==null?
                accountDao.findByOrgIdAndUserId(request.getOrgId(),request.getUserId())
                : accountDao.getOne(request.getId());
        if(account ==null){
            account =new Account();
            account.setOrg(orgDao.getOne(request.getOrgId()));
            account.setUser(userDao.getOne(request.getUserId()));
            account.setCreateTime(new Date());
        }

        security.hasPermission(account.getOrg().getId(),null);

        BeanUtils.copyProperties(request, account,"id");

        account.setUpdateTime(new Date());

        account.setKeyword(getKeyword(account));

        accountDao.save(account);

        return account;
    }


    private String getKeyword(Account data){
        StringBuffer s=new StringBuffer();
        if(!StringUtils.isEmpty(data.getName())){
            s.append(" "+data.getName());
        }
        if(!StringUtils.isEmpty(data.getPhone())){
            s.append(" "+data.getPhone());
        }
        return s.toString().trim();
    }

    @Transactional
    @Override
    public void deleteAccountById(Long id) {

        Account account = accountDao.getOne(id);

        security.hasPermission(account.getOrg().getId(),null);

        accountRoleDao.deleteAllByAccountId(account.getId());

        accountDao.delete(account);

    }


    @Override
    public AccountVO getAccountVOById(Long id) {
        QAccount e=QAccount.account;
        AccountVO vo=queryOne(e,e.id.eq(id),accountVOConver);

        if(vo!=null) {
            loadRoles(vo);
        }

        return vo;

    }


    private void loadRoles(AccountVO vo){

        QAccountRole e=QAccountRole.accountRole;
        List<AccountRoleVO> list=queryList(e,e.account.id.eq(vo.getId()),accountRoleVOConver);

        List<RoleVO> roles=list.stream().map(t->t.getRole()).collect(Collectors.toList());

        loadPermissions(roles);

        vo.setRoles(list);

    }



    private void loadPermissions(List<RoleVO> roles){
        QRolePermission e=QRolePermission.rolePermission;
        List<RolePermissionVO> rolePermissions=queryList(e,e.role.id.in(roles.stream().map(t->t.getId()).collect(Collectors.toList())),rolePermissionVOConver);

        roles.forEach(t->{
            t.setPermissions(rolePermissions.stream().filter(rp->rp.getRoleId().equals(t.getId())).collect(Collectors.toList()));
        });

    }

    @Override
    public Page<AccountVO> getAccountVOPage(GetAccountListDTO request, Pageable pageable) {
        QAccount e=QAccount.account;

        Predicate p=null;

        if(request.getKeyword()!=null){
            p=e.keyword.like("%"+request.getKeyword()+"%");
        }

        p=e.org.id.eq(request.getOrgId()).and(p);

        Page<AccountVO> page=queryPage(e,p,accountVOConver,pageable);

        loadRoles(page.getContent());

        return page;
    }

    private void loadRoles(List<AccountVO> accounts){

        if(accounts==null||accounts.isEmpty())
            return;

        List<Long> adminIdIn=accounts.stream().map(t->t.getId()).collect(Collectors.toList());

        QAccountRole e=QAccountRole.accountRole;

        List<AccountRoleVO> list=queryList(e,e.account.id.in(adminIdIn),accountRoleVOConver);

        accounts.forEach(t->{
            t.setRoles(list.stream().filter(r->r.getAccountId().equals(t.getId())).collect(Collectors.toList()));
        });

    }


    @Transactional
    @Override
    public AccountRole saveAccountRole(AccountRoleDTO request) {

        AccountRole accountRole = accountRoleDao.findByAccountIdAndRoleId(request.getAccountId(),request.getRoleId());

        if(accountRole ==null){
            accountRole =new AccountRole();
            accountRole.setCreateTime(new Date());
            accountRole.setAccount(accountDao.getOne(request.getAccountId()));
            accountRole.setRole(roleDao.getOne(request.getRoleId()));
            accountRoleDao.save(accountRole);
        }

        return accountRole;
    }


    @Transactional
    @Override
    public List<AccountRole> setAccountRole(SetAccountRoleDTO request) {

        Account account = accountDao.getOne(request.getAccountId());

        List<AccountRole> roles= accountRoleDao.findAllByAccountId(account.getId());

        Map<Long, AccountRole> rolesMap=roles.stream().collect(Collectors.toMap(t->t.getRole().getId(), t->t));

        List<AccountRole> list=new ArrayList<>();

        request.getRoles().forEach(roleId->{
            AccountRole role=rolesMap.get(roleId);
            if(role==null){
                role =new AccountRole();
                role.setCreateTime(new Date());
                role.setAccount(account);
                role.setRole(roleDao.getOne(roleId));
            }
            list.add(role);
        });

        accountRoleDao.saveAll(list);
        accountRoleDao.deleteAllByAccountIdAndRoleIdNotIn(account.getId(),request.getRoles());

        return list;
    }


    @Transactional
    @Override
    public void deleteAccountRoleById(Long id) {

        AccountRole accountRole = accountRoleDao.getOne(id);

        accountRoleDao.delete(accountRole);

    }




}
