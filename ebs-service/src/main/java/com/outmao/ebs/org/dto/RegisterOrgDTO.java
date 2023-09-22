package com.outmao.ebs.org.dto;


import lombok.Data;


@Data
public class RegisterOrgDTO extends OrgDTO {

    /**
     *
     * 组织类型对应ID
     * 租户ID
     * 商家ID
     * 门店ID
     */
    private Long targetId;

    /**
     *
     * 父ID
     *
     */
    private Long parentId;

    /**
     *
     * 组织类型
     *
     */
    private int type;



}
