package com.outmao.ebs.org.service;

import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Admin;
import com.outmao.ebs.org.entity.AdminRole;
import com.outmao.ebs.org.vo.AdminVO;
import com.outmao.ebs.security.vo.SecurityMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {

    public Admin saveAdmin(AdminDTO request);

    public void deleteAdmin(DeleteAdminDTO request);

    public AdminVO getAdminVOById(Long id);

    public Page<AdminVO> getAdminVOPage(GetAdminListDTO request, Pageable pageable);


    public AdminRole saveAdminRole(AdminRoleDTO request);

    public List<AdminRole> setAdminRole(SetAdminRoleDTO request);

    public void deleteAdminRole(DeleteAdminRoleDTO request);

    public List<SecurityMember> getSecurityMemberListByUserId(Long userId);

}
