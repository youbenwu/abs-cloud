package com.outmao.ebs.org.service.impl;



import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.vo.Contact;
import com.outmao.ebs.common.vo.Item;
import com.outmao.ebs.org.common.data.BindingOrg;
import com.outmao.ebs.org.domain.AccountDomain;
import com.outmao.ebs.org.domain.OrgDomain;
import com.outmao.ebs.org.domain.RoleDomain;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.dto.AccountDTO;
import com.outmao.ebs.org.entity.Account;
import com.outmao.ebs.org.entity.Org;
import com.outmao.ebs.org.entity.Role;
import com.outmao.ebs.org.service.OrgService;
import com.outmao.ebs.org.vo.CacheOrgVO;
import com.outmao.ebs.org.vo.OrgVO;
import com.outmao.ebs.user.common.constant.Oauth;
import com.outmao.ebs.user.dto.RegisterDTO;
import com.outmao.ebs.user.entity.User;
import com.outmao.ebs.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Order(1)
@Service
public class OrgServiceImpl extends BaseService implements OrgService, CommandLineRunner {


    @Autowired
    private OrgDomain orgDomain;

    @Autowired
    private AccountDomain accountDomain;

    @Autowired
    private RoleDomain roleDomain;

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
       //创建根组织
       Org org=orgDomain.getOrg();
       if(org==null){
           RegisterOrgDTO request=new RegisterOrgDTO();
           request.setName("系统组织");
           request.setType(Org.TYPE_SYSTEM);
           request.setPassword("123456");
           request.setContact(new Contact());
           request.getContact().setName("admin");
           registerOrg(request);
       }
    }


    @Transactional
    @Override
    public Org registerOrg(RegisterOrgDTO request) {
        if(request.getUserId()==null){
            findOrRegisterUser(request);
        }
        Org org= orgDomain.registerOrg(request);
        createAdminAccount(org);
        if(org.getType()==Org.TYPE_SYSTEM){
            org.setStatus(Status.NORMAL.getStatus());
            org.setStatusRemark(Status.NORMAL.getStatusRemark());
        }
        return org;
    }

    @Override
    public Org registerOrg(BindingOrg bindingOrg) {
        Item item=bindingOrg.toItem();
        RegisterOrgDTO orgDTO=new RegisterOrgDTO();
        if(item.getType().equals("Merchant")){
            orgDTO.setType(Org.TYPE_MERCHANT);
        }else if(item.getType().equals("Store")){
            orgDTO.setType(Org.TYPE_STORE);
        }else if(item.getType().equals("Shop")){
            orgDTO.setType(Org.TYPE_SHOP);
        }else if(item.getType().equals("Hotel")){
            orgDTO.setType(Org.TYPE_HOTEL);
        }
        orgDTO.setParentId(bindingOrg.getParentOrgId());
        orgDTO.setTargetId(item.getId());
        orgDTO.setUserId(bindingOrg.getUserId());
        orgDTO.setName(item.getTitle());
        orgDTO.setContact(bindingOrg.getContact());
        Org org=registerOrg(orgDTO);
        bindingOrg.setOrgId(org.getId());
        return org;
    }

    private void createAdminAccount(Org org){
        AccountDTO accountDTO=new AccountDTO();
        accountDTO.setOrgId(org.getId());
        accountDTO.setUserId(org.getUser().getId());
        accountDTO.setName(org.getContact().getName());
        accountDTO.setPhone(org.getContact().getPhone());
        Account account=accountDomain.saveAccount(accountDTO);

        RoleDTO roleDTO=new RoleDTO();
        roleDTO.setTitle("超级管理员");
        roleDTO.setName("admin");
        roleDTO.setOrgId(org.getId());

        Role role=roleDomain.saveRole(roleDTO);

        accountDomain.saveAccountRole(new AccountRoleDTO(account.getId(),role.getId()));

    }

    private void findOrRegisterUser(RegisterOrgDTO request){

        Assert.notNull(request.getContact(),"联系人不能为空");

        String name=request.getContact().getName();
        String phone=request.getContact().getPhone();

        User user=userService.getUserByUsername(StringUtils.isEmpty(phone)?name:phone);

        if(user==null){
            RegisterDTO registerDTO=new RegisterDTO();
            registerDTO.setPrincipal(StringUtils.isEmpty(phone)?name:phone);
            registerDTO.setCredentials(request.getPassword());
            registerDTO.setOauth(StringUtils.isEmpty(phone)?Oauth.USERNAME.getName():Oauth.PHONE.getName());
            registerDTO.setArgs(new HashMap<>());
            registerDTO.getArgs().put("nickname",name);

            user=userService.registerUser(registerDTO);
        }

        request.setUserId(user.getId());

    }

    @Override
    public Org saveOrg(OrgDTO request) {
        return orgDomain.saveOrg(request);
    }

    @Override
    public Org setOrgStatus(SetOrgStatusDTO request) {
        return orgDomain.setOrgStatus(request);
    }

    @Override
    public void deleteOrgById(Long id) {
        orgDomain.deleteOrgById(id);
    }

    @Override
    public Org addOrgParent(Long id, Long parentId) {
        return orgDomain.addOrgParent(id,parentId);
    }

    @Override
    public Org getOrgById(Long id) {
        return orgDomain.getOrgById(id);
    }

    @Override
    public Org getOrgByTargetId(Long targetId) {
        return orgDomain.getOrgByTargetId(targetId);
    }

    @Override
    public Org getOrg() {
        return orgDomain.getOrg();
    }

    @Override
    public List<Org> getOrgListByIdIn(Collection<Long> idIn) {
        return orgDomain.getOrgListByIdIn(idIn);
    }

    @Override
    public Long getOrgIdByTargetId(Long targetId) {
        return orgDomain.getOrgIdByTargetId(targetId);
    }

    @Override
    public OrgVO getOrgVOById(Long id) {
        return orgDomain.getOrgVOById(id);
    }

    @Override
    public List<OrgVO> getOrgVOListByIdIn(Collection<Long> idIn) {
        return orgDomain.getOrgVOListByIdIn(idIn);
    }

    @Override
    public Page<OrgVO> getOrgVOPage(GetOrgListDTO request, Pageable pageable) {
        return orgDomain.getOrgVOPage(request,pageable);
    }

    @Override
    public CacheOrgVO getCacheOrgVOById(Long id) {
        return orgDomain.getCacheOrgVOById(id);
    }

    @Override
    public CacheOrgVO getCacheOrgVO() {
        return orgDomain.getCacheOrgVO();
    }

}
