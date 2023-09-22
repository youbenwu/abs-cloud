package com.outmao.ebs.security.vo;


import lombok.Data;


@Data
public class SecurityOrg {

    private Long orgId;

    private int orgType;

    private String orgName;

    private Long sysId;

    private String sysName;

}
