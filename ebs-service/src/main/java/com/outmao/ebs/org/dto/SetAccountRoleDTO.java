package com.outmao.ebs.org.dto;

import lombok.Data;

import java.util.List;

@Data
public class SetAccountRoleDTO {

    private Long accountId;

    private List<Long> roles;

}
