package com.outmao.ebs.org.service.impl;



import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.common.configuration.constant.Status;
import com.outmao.ebs.org.domain.AdminDomain;
import com.outmao.ebs.org.domain.OrgDomain;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Org;
import com.outmao.ebs.org.service.OrgService;
import com.outmao.ebs.org.vo.OrgVO;
import com.outmao.ebs.security.vo.SecurityOrg;
import com.outmao.ebs.user.domain.UserDomain;
import com.outmao.ebs.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Order(2)
@Service
public class OrgServiceImpl extends BaseService implements OrgService, CommandLineRunner {


    @Autowired
    private OrgDomain orgDomain;

    @Autowired
    private UserDomain userDomain;

    @Autowired
    private AdminDomain adminDomain;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

       //创建根组织
       Org org=orgDomain.getOrg();
       if(org==null){
           User admin=userDomain.getUserByUsername("admin");
           RegisterOrgDTO request=new RegisterOrgDTO();
           request.setName("系统组织");
           request.setUserId(admin.getId());
           request.setType(Org.TYPE_SYSTEM);
           org=orgDomain.registerOrg(request);
           org.setStatus(Status.NORMAL.getStatus());
           org.setStatusRemark(Status.NORMAL.getStatusRemark());
           adminDomain.saveAdmin(new AdminDTO(
                   null,
                   org.getId(),
                   org.getUser().getId(),
                   "admin",
                   null,
                   "admin"
           ));
       }

    }

    @Override
    public Org registerOrg(RegisterOrgDTO request) {
        Org org= orgDomain.registerOrg(request);
        return org;
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
    public List<SecurityOrg> getSecurityOrgList(Collection<Long> orgIdIn, Long sysId) {
        return orgDomain.getSecurityOrgList(orgIdIn,sysId);
    }

}
