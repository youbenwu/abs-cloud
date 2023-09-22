package com.outmao.ebs.org.service.impl;


import com.outmao.ebs.common.base.BaseService;
import com.outmao.ebs.org.domain.DepartmentDomain;
import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Department;
import com.outmao.ebs.org.entity.DepartmentMember;
import com.outmao.ebs.org.service.DepartmentService;
import com.outmao.ebs.org.vo.DepartmentMemberVO;
import com.outmao.ebs.org.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DepartmentServiceImpl extends BaseService implements DepartmentService {

    @Autowired
    private DepartmentDomain departmentDomain;

    @Override
    public Department saveDepartment(DepartmentDTO request) {
        return departmentDomain.saveDepartment(request);
    }

    @Override
    public void deleteDepartment(DeleteDepartmentDTO request) {
        departmentDomain.deleteDepartment(request);
    }

    @Override
    public List<DepartmentVO> getDepartmentVOList(GetDepartmentListDTO request) {
        return departmentDomain.getDepartmentVOList(request);
    }


    @Override
    public DepartmentMember saveDepartmentMember(DepartmentMemberDTO request) {
        return departmentDomain.saveDepartmentMember(request);
    }

    @Override
    public List<DepartmentMember> saveDepartmentMemberList(DepartmentMemberListDTO request) {
        return departmentDomain.saveDepartmentMemberList(request);
    }

    @Override
    public void deleteDepartmentMember(DeleteDepartmentMemberDTO request) {
        departmentDomain.deleteDepartmentMember(request);
    }

    @Override
    public Page<DepartmentMemberVO> getDepartmentMemberVOPage(GetDepartmentMemberListDTO request, Pageable pageable) {
        return departmentDomain.getDepartmentMemberVOPage(request,pageable);
    }



}
