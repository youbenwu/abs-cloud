package com.outmao.ebs.org.dto;


import lombok.Data;


/**
 *
 * 组织机构类型
 *
 */
@Data
public class OrgTypeDTO {

    /**
     *
     * 组织ID
     *
     */
    private Long orgId;

    /**
     *
     * 组织类型对应ID
     *
     */
    private Long targetId;

    /**
     *
     * 组织类型
     *
     */
    private int type;

    /**
     *
     * 类型名称
     *
     */
    private String name;



}
