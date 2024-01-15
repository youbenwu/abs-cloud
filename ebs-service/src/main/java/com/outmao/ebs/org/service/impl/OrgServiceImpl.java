package com.outmao.ebs.org.service.impl;



import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.common.vo.Contact;
import com.outmao.ebs.org.domain.AccountDomain;
import com.outmao.ebs.org.domain.OrgDomain;
import com.outmao.ebs.org.domain.OrgTypeDomain;
import com.outmao.ebs.org.domain.RoleDomain;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.dto.AccountDTO;
import com.outmao.ebs.org.entity.*;
import com.outmao.ebs.org.service.OrgService;
import com.outmao.ebs.org.vo.CacheOrgVO;
import com.outmao.ebs.org.vo.OrgTypeVO;
import com.outmao.ebs.org.vo.OrgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Order(2)
@Service
public class OrgServiceImpl extends BaseService implements OrgService, CommandLineRunner {


    @Autowired
    private OrgDomain orgDomain;

    @Autowired
    private OrgTypeDomain orgTypeDomain;

    @Autowired
    private AccountDomain accountDomain;

    @Autowired
    private RoleDomain roleDomain;


    @Override
    public void run(String... args) throws Exception {
       //创建根组织
       if(orgDomain.getCacheOrgVO()==null){
           RegisterOrgDTO request=new RegisterOrgDTO();
           request.setName("系统组织");
           request.setType(OrgType.TYPE_SYSTEM);
           request.setContact(new Contact());
           request.getContact().setName("admin");
           registerOrg(request);
       }
    }


    @Transactional
    @Override
    public Org registerOrg(RegisterOrgDTO request) {
        Org org= orgDomain.registerOrg(request);

        //创建管理员帐号
        saveAdminAccount(org);

        if(org.getType()==OrgType.TYPE_SYSTEM){
            org.setStatus(Status.NORMAL.getStatus());
            org.setStatusRemark(Status.NORMAL.getStatusRemark());
        }

        return org;
    }


    private void saveAdminAccount(Org org){
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
        orgTypeDomain.deleteOrgTypeByOrgId(id);
        orgDomain.deleteOrgById(id);
    }

    @Override
    public OrgParent saveOrgParent(OrgParentDTO request) {
        return orgDomain.saveOrgParent(request);
    }

    @Override
    public Org getOrgById(Long id) {
        return orgDomain.getOrgById(id);
    }

    @Override
    public Org getOrgByTargetId(Long targetId) {

        Org org= orgDomain.getOrgByTargetId(targetId);

        if(org==null){
            OrgType type=orgTypeDomain.getOrgTypeByTargetId(targetId);
            if(type!=null){
                return getOrgById(type.getOrgId());
            }
        }

        return org;
    }

    @Override
    public OrgVO getOrgVOById(Long id)
    {

        OrgVO vo= orgDomain.getOrgVOById(id);

        if(vo!=null){
            setOrgType(Arrays.asList(vo));
        }

        return vo;
    }

    @Override
    public Page<OrgVO> getOrgVOPage(GetOrgListDTO request, Pageable pageable) {
        Page<OrgVO> page= orgDomain.getOrgVOPage(request,pageable);
        setOrgType(page.getContent());
        return page;
    }

    @Override
    public List<OrgVO> getOrgVOListByIdIn(Collection<Long> idIn) {
        List<OrgVO> list=orgDomain.getOrgVOListByIdIn(idIn);
        setOrgType(list);
        return list;
    }

    private void setOrgType(List<OrgVO> list){
        if(list.isEmpty())
            return;
        Map<Long,OrgVO> map=list.stream().collect(Collectors.toMap(t->t.getId(),t->t));

        List<OrgTypeVO> types=orgTypeDomain.getOrgTypeVOListByOrgIdIn(map.keySet());

        for (OrgTypeVO t:types){
            OrgVO vo=map.get(t.getOrgId());
            if(vo.getTypes()==null){
                vo.setTypes(new ArrayList<>());
            }
            vo.getTypes().add(t);
        }

    }

    @Override
    public CacheOrgVO getCacheOrgVOById(Long id) {
        return orgDomain.getCacheOrgVOById(id);
    }

    @Override
    public CacheOrgVO getCacheOrgVO() {
        return orgDomain.getCacheOrgVO();
    }


    @Override
    public OrgType saveOrgType(OrgTypeDTO request) {
        return orgTypeDomain.saveOrgType(request);
    }

    @Override
    public void deleteOrgTypeById(Long id) {
        orgTypeDomain.deleteOrgTypeById(id);
    }

    @Override
    public void deleteOrgTypeByOrgId(Long orgId) {
        orgTypeDomain.deleteOrgTypeByOrgId(orgId);
    }

    @Override
    public OrgType getOrgTypeByTargetId(Long targetId) {
        return orgTypeDomain.getOrgTypeByTargetId(targetId);
    }

    @Override
    public List<OrgTypeVO> getOrgTypeVOListByOrgIdIn(Collection<Long> orgIdIn) {
        return orgTypeDomain.getOrgTypeVOListByOrgIdIn(orgIdIn);
    }


}
