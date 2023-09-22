package com.outmao.ebs.org.dto;


import lombok.Data;

import java.util.List;


@Data
public class SetMemberRoleDTO {

    private Long memberId;

    private List<Long> roles;

}
