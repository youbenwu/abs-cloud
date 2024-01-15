package com.outmao.ebs.org.domain;


import com.outmao.ebs.org.dto.*;
import com.outmao.ebs.org.entity.Department;
import com.outmao.ebs.org.entity.DepartmentMember;
import com.outmao.ebs.org.vo.DepartmentMemberVO;
import com.outmao.ebs.org.vo.DepartmentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface DepartmentDomain {

    public Department saveDepartment(DepartmentDTO request);

    public void deleteDepartment(DeleteDepartmentDTO request);

    public List<DepartmentVO> getDepartmentVOList(GetDepartmentListDTO request);

    public DepartmentMember saveDepartmentMember(DepartmentMemberDTO request);

    public List<DepartmentMember> saveDepartmentMemberList(DepartmentMemberListDTO request);

    public void deleteDepartmentMember(DeleteDepartmentMemberDTO request);

    public void deleteDepartmentMemberByMemberId(Long memberId);

    public Page<DepartmentMemberVO> getDepartmentMemberVOPage(GetDepartmentMemberListDTO request, Pageable pageable);


}
