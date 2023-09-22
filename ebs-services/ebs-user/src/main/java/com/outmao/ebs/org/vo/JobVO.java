package com.outmao.ebs.org.vo;


import lombok.Data;

import java.util.Date;


/**
 *
 * 岗位 和组织关联
 *
 */
@Data
public class JobVO{


    private Long id;

    /**
     *
     * 组织
     *
     */
    private Long orgId;

    /**
     *
     * 岗位名称
     *
     */
    private String name;

    /**
     *
     * 岗位描述
     *
     */
    private String description;

    /**
     *
     * 创建时间
     *
     */
    private Date createTime;

    /**
     *
     * 更新时间
     *
     */
    private Date updateTime;


}
