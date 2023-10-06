package com.outmao.ebs.org.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SetAccountRoleDTO {

    private Long accountId;

    private List<Long> roles;

}
