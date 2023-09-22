package com.outmao.ebs.org.vo;

import lombok.Data;

@Data
public class DepartmentMemberVO  {

    private Long id;
    private Long departmentId;
    private Long memberId;
    private MemberVO member;

}
