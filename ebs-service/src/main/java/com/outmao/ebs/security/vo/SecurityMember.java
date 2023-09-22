package com.outmao.ebs.security.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class SecurityMember {

    private Long id;

    private Long userId;

    private Long orgId;

    @JsonIgnore
    private List<SecurityRole> roles;

}
