package com.outmao.ebs.org.vo;


import lombok.Data;

import java.util.Date;

@Data
public class MemberRoleVO {

    private Long id;
    private Long memberId;
    private Long roleId;
    private RoleVO role;
    private Date createTime;

}
