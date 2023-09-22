package com.outmao.ebs.org.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.org.domain.AdminDomain;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Admin;
import com.outmao.ebs.org.entity.AdminRole;
import com.outmao.ebs.org.service.AdminService;
import com.outmao.ebs.org.vo.AdminVO;
import com.outmao.ebs.security.vo.SecurityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AdminServiceImpl extends BaseService implements AdminService {

    @Autowired
    private AdminDomain adminDomain;

    @Override
    public Admin saveAdmin(AdminDTO request) {
        return adminDomain.saveAdmin(request);
    }

    @Override
    public void deleteAdmin(DeleteAdminDTO request) {
        adminDomain.deleteAdmin(request);
    }

    @Override
    public AdminVO getAdminVOById(Long id) {
        return adminDomain.getAdminVOById(id);
    }

    @Override
    public Page<AdminVO> getAdminVOPage(GetAdminListDTO request, Pageable pageable) {
        return adminDomain.getAdminVOPage(request,pageable);
    }

    @Override
    public AdminRole saveAdminRole(AdminRoleDTO request) {
        return adminDomain.saveAdminRole(request);
    }

    @Override
    public List<AdminRole> setAdminRole(SetAdminRoleDTO request) {
        return adminDomain.setAdminRole(request);
    }

    @Override
    public void deleteAdminRole(DeleteAdminRoleDTO request) {
        adminDomain.deleteAdminRole(request);
    }


    @Override
    public List<SecurityMember> getSecurityMemberListByUserId(Long userId) {
        return adminDomain.getSecurityMemberListByUserId(userId);
    }
}
