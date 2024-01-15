package com.outmao.ebs.org.vo;


import lombok.Data;
import java.util.Date;

/**
 *
 * 组织机构类型
 *
 */
@Data
public class OrgTypeVO {

    private Long id;

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

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;




}
