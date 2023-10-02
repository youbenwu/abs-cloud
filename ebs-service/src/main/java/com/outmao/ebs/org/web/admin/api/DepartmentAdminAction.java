package com.outmao.ebs.org.web.admin.api;


import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Department;
import com.outmao.ebs.org.service.DepartmentService;
import com.outmao.ebs.org.vo.DepartmentMemberVO;
import com.outmao.ebs.org.vo.DepartmentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Api(value = "account-org-department", tags = "后台-组织-部门")
@RestController
@RequestMapping("/api/admin/org/department")
public class DepartmentAdminAction {



    @Autowired
    private DepartmentService departmentService;



    @PreAuthorize("hasPermission(#request.orgId,'/org/department','save')")
    @ApiOperation(value = "保存部门", notes = "保存部门")
    @PostMapping("/save")
    public Department saveDepartment(DepartmentDTO request){
        return departmentService.saveDepartment(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/department','delete')")
    @ApiOperation(value = "删除部门", notes = "删除部门")
    @PostMapping("/delete")
    public void deleteDepartment(DeleteDepartmentDTO request){
        departmentService.deleteDepartment(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/department','read')")
    @ApiOperation(value = "获取部门列表", notes = "获取部门列表")
    @PostMapping("/list")
    public List<DepartmentVO> getDepartmentVOList(GetDepartmentListDTO request){
        return departmentService.getDepartmentVOList(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/department/member','save')")
    @ApiOperation(value = "保存部门成员", notes = "保存部门成员")
    @PostMapping("/member/save")
    public void saveDepartmentMember(DepartmentMemberDTO request){
        departmentService.saveDepartmentMember(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/department/member','save')")
    @ApiOperation(value = "保存部门成员", notes = "保存部门成员")
    @PostMapping("/member/saveList")
    public void saveDepartmentMemberList(DepartmentMemberListDTO request){
        departmentService.saveDepartmentMemberList(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/department/member','delete')")
    @ApiOperation(value = "删除部门成员", notes = "删除部门成员")
    @PostMapping("/member/delete")
    public void deleteDepartmentMember(DeleteDepartmentMemberDTO request){
        departmentService.deleteDepartmentMember(request);
    }

    @PreAuthorize("hasPermission(#request.orgId,'/org/department/member','read')")
    @ApiOperation(value = "获取部门成员", notes = "获取部门成员")
    @PostMapping("/member/page")
    public Page<DepartmentMemberVO> getDepartmentMemberVOPage(GetDepartmentMemberListDTO request, Pageable pageable){
        return departmentService.getDepartmentMemberVOPage(request,pageable);
    }





}
